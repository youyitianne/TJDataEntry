package http;

import database.Method;
import database.ResStoreDatabaseService;
import database.ResStoreDatabaseVerticle;
import database.SqlConstants;

import http.qiniu.QiNiuService;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.FileUpload;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CorsHandler;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class resStoreHttpVerticle extends AbstractVerticle {
    private Method method = new Method();
    public static final String CONFIG_RES_QUEUE = "resStore.queue";
    Logger logger = LoggerFactory.getLogger(resStoreHttpVerticle.class);
    private static final String cachePath = "fileCache";
    private static final String rootPath = "Resource";
    private static final String secondPath = "res";
    private ResStoreDatabaseService dbService;
    QiNiuService qiNiuService = null;
    HashMap<SqlConstants, String> sql = null;

    @Override
    public void start(Future<Void> startFuture) {
        dbService = ResStoreDatabaseService.createProxy(vertx, CONFIG_RES_QUEUE);
        sql = method.loadSqlQueries();
        Router router = Router.router(vertx);
        qiNiuService = new QiNiuService();
        //跨域  start
        Set<String> allowHeaders = new HashSet<>();
        allowHeaders.add("x-requested-with");
        allowHeaders.add("Access-Control-Allow-Origin");
        allowHeaders.add("origin");
        allowHeaders.add("Content-Type");
        allowHeaders.add("accept");
        allowHeaders.add("ip");
        allowHeaders.add("Access-Control-Allow-Headers");
        allowHeaders.add("Cache-Control");
        allowHeaders.add("Authorization");
        Set<HttpMethod> allowMethods = new HashSet<>();
        allowMethods.add(HttpMethod.GET);
        allowMethods.add(HttpMethod.POST);
        allowMethods.add(HttpMethod.DELETE);
        allowMethods.add(HttpMethod.PATCH);
        router.route().handler(CorsHandler.create("*")
                .allowedMethods(allowMethods)
                .allowedHeaders(allowHeaders));
        router.route().handler(BodyHandler.create().setUploadsDirectory(rootPath + File.separator + cachePath));
        //文件上传到七牛云
        router.post("/qiniufile").handler(this::qiniufileuploadHandler);
        router.get("/qiniufile").handler(this::getqiniufileHandler);

        //文件上传到本地
        router.post("/file").handler(this::uploadFileHandler);
        router.get("/file").handler(this::getFileHandler);
        router.delete("/file").handler(this::delFileHandler);

        HashMap<ConfigConstants, String> conf = loadConfigQueries();
        Integer portNumber = Integer.valueOf(conf.get(ConfigConstants.HTTP_PORT));
        HttpServer httpServer = vertx.createHttpServer();
        httpServer.requestHandler(router::accept).listen(portNumber, ar -> {
            if (ar.succeeded()) {
                logger.info("HTTP server running on port " + portNumber);
                startFuture.complete();
            } else {
                logger.error("Could not start a HTTP server", ar.cause());
                startFuture.fail(ar.cause());
            }
        });
    }

    /**
     * 删除上传文件
     *
     * @param context
     */
    private void delFileHandler(RoutingContext context) {
        String guid = context.request().getParam("path");//guid
        System.out.println(guid);
        dbService.listDatas("SELECT filemd5 FROM mission.fileinfo where fileguid = '" + guid + "';", findMD5Result -> {
            if (findMD5Result.succeeded()) {
                if (findMD5Result.result().size()==0){
                    context.response().end(Json.encodePrettily(new JsonObject().put("code",20000).put("repcode",3000)));
                    return;
                }
                String md5 = findMD5Result.result().get(0).getString("filemd5");
                dbService.listDatas("select count(*) count from  mission.fileinfo where filemd5='" + md5 + "';", countResult -> {
                    if (countResult.succeeded()) {
                        Integer countNumber = countResult.result().get(0).getInteger("count");
                        if (countNumber > 1) {
                            deleteFileInfoRecord(context,guid);
                        } else {
                            vertx.fileSystem().delete(rootPath + File.separator + secondPath + File.separator + md5, deleteResult -> {
                                if (deleteResult.succeeded()) {
                                    logger.info("删除资源成功");
                                    deleteFileInfoRecord(context,guid);
                                } else {
                                    logger.info("删除资源失败");
                                    context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3001)));
                                }
                            });
                        }
                    } else {
                        logger.error("countResult", countResult.cause());
                        context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3001)));
                    }
                });
            } else {
                logger.error("findMD5Result", findMD5Result.cause());
                context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3001)));
            }
        });
    }

    private void deleteFileInfoRecord(RoutingContext context,String guid){
        dbService.query("DELETE FROM `mission`.`fileinfo` WHERE fileguid = ?;",new JsonArray().add(guid),delResult->{
           if (delResult.succeeded()){
               context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3000)));
           }else {
               logger.error("delResult failed",delResult.cause());
               context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3001)));
           }
        });
    }


    /**
     * 获取本地七牛云上传记录
     *
     * @param context
     */
    private void getqiniufileHandler(RoutingContext context) {
        dbService.listDatas(sql.get(SqlConstants.QINIUFILE_LIST), rs -> {
            if (rs.succeeded()) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                List<JsonObject> list = rs.result();
                for (int i = 0; i < list.size(); i++) {
                    JsonObject jsonObject = list.get(i);
                    String date1 = jsonObject.getString("qiniu_file_date");
                    jsonObject.put("date", sdf.format(new Date(Long.valueOf(date1))));
                }
                logger.info("获取七牛云上传记录成功");
                context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3000).put("data", rs.result())));
            } else {
                logger.error("获取七牛云上传记录失败", rs.cause());
                context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3001).put("data", rs.cause())));
            }
        });
    }

    /**
     * 上传文件至七牛云
     *
     * @param context
     */
    private void qiniufileuploadHandler(RoutingContext context) {
        Set<FileUpload> fileUploads = context.fileUploads();
        String filename = "";
        String uploadfilename = "";
        for (FileUpload f : fileUploads) {
            filename = f.fileName();   //文件原名  友盟3.xlsx
            uploadfilename = f.uploadedFileName();      //文件缓存名    TJMission\file-uploads\711dcdb5-dec7-4ccb-b191-72696ea9a853
            logger.info(filename);
            logger.info(uploadfilename);
        }
        String finalUploadFilename = uploadfilename;
        String finalFilename = filename;
        String guid = UUID.randomUUID().toString();
        logger.info(guid);
        String md5 = "";
        try {
            md5 = DigestUtils.md5Hex(new FileInputStream(uploadfilename));
        } catch (Exception e) {
            logger.error("文件异常，计算md5失败", e);
        }
        String finalMD5 = md5;
        dbService.fetchDatas(sql.get(SqlConstants.QINIUFILE_COUNT), new JsonArray().add(md5), countResult -> {
            if (countResult.succeeded()) {
                List<JsonObject> list = countResult.result();
                if (list.get(0).getInteger("count") > 0) {
                    qiniuSaveRecord(guid, finalMD5, finalFilename, context);
                } else {
                    String response = qiNiuService.upload(finalUploadFilename, finalMD5);
                    if (response != null) {
                        qiniuSaveRecord(guid, finalMD5, finalFilename, context);
                    } else {
                        context.response().setStatusCode(500).end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3001).put("message", "上传至七牛云失败")));
                    }
                }
            } else {
                logger.error("查询本地记录失败", countResult.cause());
                context.response().setStatusCode(500).end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3001).put("message", "查询本地记录失败")));
            }
        });
    }

    /**
     * 保存七牛云上传记录到本地
     *
     * @param guid          文件guid
     * @param md5           文件md5
     * @param finalFilename 原文件名
     * @param context
     */
    public void qiniuSaveRecord(String guid, String md5, String finalFilename, RoutingContext context) {
        dbService.query(sql.get(SqlConstants.QINIUFILE_INSERT), new JsonArray().add(guid).add(md5).add(new Date().getTime()).add(finalFilename).add("http://image.tomatojoy.cn/" + md5), result -> {
            if (result.succeeded()) {
                context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3000).put("message", "上传至七牛云成功！")));
            } else {
                logger.error("保存七牛云上传记录到本地失败", result.cause());
                context.response().setStatusCode(500).end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3001).put("message", "上传至七牛云成功，本地记录保存失败！")));
            }
        });
    }


    /**
     * 获取文件
     *
     * @param context
     */
    private void getFileHandler(RoutingContext context) {
        String path = context.request().getParam("path");
        System.out.println(path);
        dbService.query("UPDATE `mission`.`fileinfo` SET `lastModifiedDate` = ? WHERE `fileguid` = ?;", new JsonArray().add(new Date().getTime()).add(path), updateResult -> {
            if (updateResult.succeeded()) {
                dbService.listDatas("SELECT filemd5 FROM mission.fileinfo where fileguid = '" + path + "';", findMD5Result -> {
                    if (findMD5Result.succeeded()) {
                        if (findMD5Result.result().size()==0){
                            logger.info("获取资源失败");
                            context.response().setStatusCode(500).end();
                            return;
                        }
                        String fileName = findMD5Result.result().get(0).getString("filemd5");
                        logger.info(fileName);
                        File file = new File(rootPath + File.separator + secondPath + File.separator + fileName);
                        if (file.exists()) {
                            logger.info("获取资源成功");
                            context.response().sendFile(rootPath + File.separator + secondPath + File.separator + fileName);
                        } else {
                            logger.info("获取资源失败");
                            context.response().setStatusCode(500).end();
                        }
                    } else {
                        logger.info("查找资源地址失败");
                        context.response().setStatusCode(500).end();
                    }
                });
            } else {
                logger.info("更新资源最后修改时间失败");
                context.response().setStatusCode(500).end();
            }
        });

    }


    /**
     * 上传文件处理
     */
    private void uploadFileHandler(RoutingContext context) {
        Set<FileUpload> fileUploads = context.fileUploads();
        String filename = "";
        String uploadfilename = "";
        for (FileUpload f : fileUploads) {
            filename = f.fileName();   //文件原名  友盟3.xlsx
            uploadfilename = f.uploadedFileName();      //文件缓存名    TJMission\file-uploads\711dcdb5-dec7-4ccb-b191-72696ea9a853
            logger.info(filename);
            logger.info(uploadfilename);
        }
        String finalFilename = filename;
        String finalUploadFileName = uploadfilename;
        Long date = new Date().getTime();
        String guid = UUID.randomUUID().toString();
        logger.info(guid);
        String md5 = "";
        try {
            md5 = DigestUtils.md5Hex(new FileInputStream(uploadfilename));
        } catch (Exception e) {
            logger.error("文件异常，计算md5失败", e);
        }
        String finalMD5 = md5;
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(guid).add(finalFilename).add(date).add(finalMD5).add(date);
        dbService.listDatas("SELECT count(fileguid) count FROM mission.fileinfo where filemd5 = '" + md5 + "';", checkResult -> {
            if (checkResult.succeeded()) {
                if (checkResult.result().get(0).getInteger("count") > 0) {
                    insertFileInfo(jsonArray, context);
                } else {
                    fileClassification(finalUploadFileName, finalMD5).setHandler(rs -> {
                        if (rs.succeeded()) {
                            //logger.info("文件上传成功");
                            //context.response().end(Json.encodePrettily(new JsonObject().put("repcode", "3000").put("data", new JsonObject().put("guid", guid).put("file", finalFilename))));
                            dbService.query("INSERT INTO `mission`.`fileinfo`(`fileguid`,`filename`,`date`,`filemd5`,`lastModifiedDate`)VALUES(?,?,?,?,?);", jsonArray, result -> {
                                if (result.succeeded()) {
                                    logger.info("上传成功");
                                    context.response().end(Json.encodePrettily(new JsonObject().put("repcode", "3000").put("data", new JsonObject().put("guid", guid)).put("file", finalFilename)));
                                } else {
                                    logger.error("保存失败,请重新上传", result.cause());
                                    context.response().end(Json.encodePrettily(new JsonObject().put("repcode", "3001").put("data", "保存失败,请重新上传")));
                                }
                            });
                        } else {
                            logger.error("文件操作失败", rs.cause());
                            context.response().end(Json.encodePrettily(new JsonObject().put("repcode", "3001").put("data", "保存失败,请重新上传")));
                        }
                    });
                }
            } else {
                Service3001(context, "查询文件是否存在失败");
            }
        });
    }


    /**
     * 请求失败返回
     *
     * @param context
     * @param error
     */
    private void Service3001(RoutingContext context, String error) {
        context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3001).put("data", error)));
    }


    /**
     * 插入上传文件信息
     *
     * @param jsonArray
     * @param context
     */
    private void insertFileInfo(JsonArray jsonArray, RoutingContext context) {
        dbService.query("INSERT INTO `mission`.`fileinfo`(`fileguid`,`filename`,`date`,`filemd5`,`lastModifiedDate`)VALUES(?,?,?,?,?);", jsonArray, result -> {
            if (result.succeeded()) {
                logger.info("上传成功");
                context.response().end(Json.encodePrettily(new JsonObject().put("repcode", "3000").put("data", new JsonObject().put("guid", jsonArray.getString(0))).put("file", jsonArray.getString(1))));
            } else {
                logger.error("保存失败,请重新上传", result.cause());
                context.response().end(Json.encodePrettily(new JsonObject().put("repcode", "3001").put("data", "保存失败,请重新上传")));
            }
        });
    }


    /**
     * 上传缓存文件分类
     *
     * @param path 文件上传路径
     * @return 文件分类路径
     */
    private Future<String> fileClassification(String path, String guid) {
        Future<String> future = Future.future();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        //多重路径在此添加
        File file = new File(rootPath + File.separator + secondPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        String newpath = file + File.separator + guid;
        if (new File(newpath).exists()) {
            vertx.fileSystem().deleteBlocking(newpath);
        }
        vertx.fileSystem().copy(path, newpath, rs -> {
            if (rs.succeeded()) {
                vertx.fileSystem().exists(path, result -> {
                    if (result.succeeded() && result.result()) {
                        vertx.fileSystem().delete(path, r -> {
                            future.complete(newpath);
                            logger.info(newpath + "   copy and delete success     ");
                        });
                    } else {
                        logger.error(result.cause().toString());
                        future.fail("delete failed" + result.cause());
                        System.err.println("Oh oh ... - cannot delete the file: " + result.cause());
                    }
                });
            } else {
                logger.error("copy failed" + rs.cause());
                future.fail(rs.cause());
            }
        });
        return future;
    }


    /**
     * 读取配置文件
     *
     * @return
     */
    public HashMap<ConfigConstants, String> loadConfigQueries() {
        InputStream queriesInputStream = null;
        Properties queriesProps = new Properties();
        try {
            queriesInputStream = new FileInputStream("confTJResourceStore/conf.properties");
            //queriesInputStream = this.getClass().getClassLoader().getResourceAsStream("conf.properties");
            queriesProps.load(queriesInputStream);
        } catch (IOException e) {
            logger.error("read databasecon.properties failed " + e);
        } finally {
            try {
                queriesInputStream.close();
            } catch (IOException e) {
                logger.error("read databasecon.properties failed " + e);
            }
        }
        HashMap<ConfigConstants, String> sqlQueries = new HashMap<>();
        sqlQueries.put(ConfigConstants.HTTP_PORT, queriesProps.getProperty("http_port"));
        return sqlQueries;
    }

}
