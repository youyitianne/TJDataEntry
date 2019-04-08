package http;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import database.ConfigConstants;
import database.Method;
import database.MissionDatabaseService;
import database.SqlConstants;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.KeyStoreOptions;
import io.vertx.ext.auth.PubSecKeyOptions;
import io.vertx.ext.auth.jwt.JWTAuth;
import io.vertx.ext.auth.jwt.JWTAuthOptions;
import io.vertx.ext.web.FileUpload;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.*;
import io.vertx.ext.web.sstore.LocalSessionStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MissionHttpVerticle extends AbstractVerticle {
    private Logger logger = LoggerFactory.getLogger(MissionHttpVerticle.class);
    public static final String CONFIG_MISSION_QUEUE = "mission.queue";
    private static final String ROOTPATH = "res";
    private MissionDatabaseService dbService;
    private JWTAuth jwtAuth = null;
    private JWTAuthHandler jwtAuthHandler;
    private HashMap<SqlConstants, String> sql;
    private Method method = new Method();
    private static final String rootPath = "TJMission";
    private static final String domainName = "http://192.168.1.144:8087";


    @Override
    public void start(Future<Void> startFuture) throws Exception {
        dbService = MissionDatabaseService.createProxy(vertx, CONFIG_MISSION_QUEUE);
        Router router = Router.router(vertx);

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

        jwtAuth = JWTAuth.create(vertx, new JWTAuthOptions()
                .setKeyStore(
                        new KeyStoreOptions()
                                .setPath("keystore.jceks")
                                .setPassword("secret"))
                .addPubSecKey(new PubSecKeyOptions()
                        .setPublicKey("test")
                ));

        jwtAuthHandler = JWTAuthHandler.create(jwtAuth);

        router.route().handler(BodyHandler.create().setUploadsDirectory("TJMission" + File.separator + "file-uploads"));
        router.route().handler(CookieHandler.create());
        router.route().handler(SessionHandler.create(LocalSessionStore.create(vertx)));

        //文件上传
        router.get("/file").handler(this::getHandler);
        router.post("/file").handler(this::uploadHandler_file);
        router.get("/fileInfo").handler(this::listHandler);
        router.post("/keystore").handler(this::uploadHandler_keystore);
        router.get("/keystoreInfo").handler(this::listHandler_keystore);
        router.get("/getFile").handler(this::downLoadHandler1);
        router.delete("/delFile").handler(this::delHandler);
        router.patch("/updateKeystore").handler(this::updateKeystoreHandler);

        HashMap<ConfigConstants, String> conf = method.loadConfigQueries();
        sql = method.loadSqlQueries();

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
     * 获取文件
     *
     * @param context
     */
    private void getHandler(RoutingContext context) {
        String path = context.request().getParam("path");
        File file = new File(rootPath + File.separator + "res" + File.separator + path);
        if (file.exists()) {
            logger.info("获取资源成功");
            context.response().sendFile(rootPath + File.separator + "res" + File.separator + path);
        } else {
            logger.info("获取资源失败");
            context.response().putHeader("Content-Type", "text/plain; charset=utf-8").end(Json.encodePrettily(new JsonObject().put("message", "路径错误")));
        }
    }


//    /**
//     *  keystore上传处理
//     *
//     *  @param context
//     *
//     */
//
//    private void uploadHandler_keystore(RoutingContext context) {
//        String guid=context.getBodyAsJson().getString("guid");
//        String file=context.getBodyAsJson().getString("file");
//        Long date = new Date().getTime();
//        JsonArray jsonArray = new JsonArray();
//        jsonArray.add(guid).add(file).add(date);
//        dbService.query(sql.get(SqlConstants.FILEINFO_INSERT), jsonArray, result -> {
//            if (result.succeeded()) {
//                logger.info("上传成功");
//                dbService.query(sql.get(SqlConstants.KEYSTORE_INSERT), new JsonArray().add(date).add(guid).add(file), result1 -> {
//                    if (result1.succeeded()) {
//                        context.response().end(Json.encodePrettily(new JsonObject().put("repcode", 3000)));
//                    } else {
//                        logger.error("保存失败,请重新上传", result1.cause());
//                        context.response().end(Json.encodePrettily(new JsonObject().put("repcode", 3001).put("data", "保存失败,请重新上传")));
//                    }
//                });
//            } else {
//                logger.error("保存失败,请重新上传", result.cause());
//                context.response().end(Json.encodePrettily(new JsonObject().put("repcode", 3001).put("data", "保存失败,请重新上传")));
//            }
//        });
//    }


    /**
     * keystore上传处理
     *
     * @param context
     */
    private void uploadHandler_keystore(RoutingContext context) {
        String secondPath = ROOTPATH;
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
        Long date = new Date().getTime();
        String guid = UUID.randomUUID().toString();
        logger.info(guid);
        fileClassification(uploadfilename, guid, secondPath).setHandler(rs -> {
            if (rs.succeeded()) {
                JsonArray jsonArray = new JsonArray();
                jsonArray.add(guid).add(finalFilename).add(date);
                dbService.query(sql.get(SqlConstants.FILEINFO_INSERT), jsonArray, result -> {
                    if (result.succeeded()) {
                        logger.info("上传成功");
                        dbService.query(sql.get(SqlConstants.KEYSTORE_INSERT), new JsonArray().add(date).add(guid).add(finalFilename), result1 -> {
                            if (result1.succeeded()) {
                                context.response().end(Json.encodePrettily(new JsonObject().put("repcode", "3000").put("data", domainName + "/getFile?path=" + guid + "&name=" + finalFilename)));
                            } else {
                                logger.error("保存失败,请重新上传", result1.cause());
                                context.response().end(Json.encodePrettily(new JsonObject().put("repcode", "3001").put("data", "保存失败,请重新上传")));
                            }
                        });
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

    /**
     * 更新keystore配置
     *
     * @param context
     */
    private void updateKeystoreHandler(RoutingContext context) {
        String fileguid = context.getBodyAsJson().getString("fileguid");
        String keystorePass = context.getBodyAsJson().getString("keystorePass");
        String keyaliasName = context.getBodyAsJson().getString("keyaliasName");
        String keyaliasPass = context.getBodyAsJson().getString("keyaliasPass");
        logger.info(fileguid);
        logger.info(keystorePass);
        logger.info(keyaliasName);
        logger.info(keyaliasPass);
        if (fileguid == null || keystorePass == null || keyaliasName == null || keyaliasPass == null) {
            context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3001).put("data", "参数不完整！")));
            return;
        }
        dbService.query(sql.get(SqlConstants.KEYSTORE_UPDATE), new JsonArray().add(keystorePass).add(keyaliasName).add(keyaliasPass).add(fileguid), result -> {
            if (result.succeeded()) {
                logger.info("添加成功");
                context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3000).put("data", "添加成功")));
            } else {
                logger.error("添加失败", result.cause());
                context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3001).put("data", "参数不完整！")));
            }
        });

    }

    /**
     * 删除文件
     *
     * @param context
     */
    private void delHandler(RoutingContext context) {
        String path = context.getBodyAsJson().getString("filepath");
        String guid = context.getBodyAsJson().getString("fileguid");
        if (path == null || guid == null) {
            context.response().end(Json.encodePrettily(new JsonObject().put("code", "20000").put("data", "参数不完整！")));
            return;
        }
        logger.info(path);
        dbService.query(sql.get(SqlConstants.FILEINFO_DEL), new JsonArray().add(guid), result -> {
            if (result.succeeded()) {
                File file = new File(path);
                if (file.exists()) {
                    vertx.executeBlocking(handler1 -> {
                        file.delete();
                        handler1.complete();
                    }, handler2 -> {
                        if (handler2.succeeded()) {
                            context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3000).put("date", "success")));
                        } else {
                            logger.error("文件删除失败", handler2.cause());
                            context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3001).put("date", "文件删除失败")));
                        }
                    });
                }
            } else {
                logger.error("数据库删除文件记录失败", result.cause());
                context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3001).put("date", "文件删除失败")));
            }
        });
    }

    /**
     * 下载文件1
     * <p>
     * path 就是guid
     * /getFile?path=TJMission\res\51b45c16-0d4c-415d-a4c5-eee9930bac48
     *
     * @param context
     */
    private void downLoadHandler1(RoutingContext context) {
        String path = context.request().getParam("path");
        String fileName = context.request().getParam("name");
        if (path == null || path.length() == 0) {
            context.response().end("No resources found");
            return;
        }
        logger.info(path);
        File file = new File("TJMission" + File.separator + "res" + File.separator + path);
        if (file.exists()) {
            logger.info("下载成功");
            if (fileName != null) {
                context.response().sendFile("TJMission" + File.separator + "res" + File.separator + path);
            } else {
                context.response().putHeader("Content-Disposition", "attachment; filename=" + path).sendFile("TJMission" + File.separator + "res" + File.separator + path);
            }
        } else {
            logger.info("下载失败");
            context.response().putHeader("Content-Type", "text/plain; charset=utf-8").end(Json.encodePrettily(new JsonObject().put("message", "路径错误")));
        }
    }


    /**
     * 获取所有上传文件信息
     *
     * @param context
     */
    private void listHandler(RoutingContext context) {
        dbService.listDatas(sql.get(SqlConstants.FILEINFO_LIST), result -> {
            if (result.succeeded()) {
                List<JsonObject> list = result.result();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                System.out.println(list);
                for (int i = 0; i < list.size(); i++) {
                    String date = list.get(i).getString("date");
                    Date date1 = new Date();
                    date1.setTime(Long.valueOf(date));
                    list.get(i).put("date1", sdf.format(date1));
                    list.get(i).put("path", domainName + "/getFile?path=" + list.get(i).getString("fileguid"));
                }
                logger.info("查询成功");
                context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 0).put("data", list)));
            } else {
                logger.error("查询失败，刷新试试", result.cause());
                context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 1).put("data", "查询失败,刷新试试！")));
            }
        });
    }

    /**
     * 获取所有KEYSTORE信息
     *
     * @param context
     */
    private void listHandler_keystore(RoutingContext context) {
        dbService.listDatas(sql.get(SqlConstants.KEYSTORE_LIST), result -> {
            if (result.succeeded()) {
                List<JsonObject> list = result.result();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                System.out.println(list);
                for (int i = 0; i < list.size(); i++) {
                    String date = list.get(i).getString("date");
                    Date date1 = new Date();
                    date1.setTime(Long.valueOf(date));
                    list.get(i).put("date1", sdf.format(date1));
                    list.get(i).put("path", domainName + "/getFile?path=" + list.get(i).getString("keystoreguid"));
                }
                logger.info("查询成功");
                context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 0).put("data", list)));
            } else {
                logger.error("查询失败，刷新试试", result.cause());
                context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 1).put("data", "查询失败,刷新试试！")));
            }
        });
    }


//    /**
//     * 上传文件处理
//     */
//    private void uploadHandler_file(RoutingContext context) {
//        String guid = context.getBodyAsJson().getString("guid").trim();
//        String file = context.getBodyAsJson().getString("file").trim();
//        JsonArray jsonArray = new JsonArray();
//        Long date = new Date().getTime();
//        jsonArray.add(guid).add(file).add(date);
//        dbService.query(sql.get(SqlConstants.FILEINFO_INSERT), jsonArray, result -> {
//            if (result.succeeded()) {
//                logger.info("上传成功");
//                context.response().end(Json.encodePrettily(new JsonObject().put("repcode", 3000).put("data", guid)));
//            } else {
//                logger.error("保存失败,请重新上传", result.cause());
//                context.response().end(Json.encodePrettily(new JsonObject().put("repcode", 3001).put("data", "保存失败,请重新上传")));
//            }
//        });
//
//    }

    /**
     * 上传文件处理
     */
    private void uploadHandler_file(RoutingContext context) {
        String secondPath = ROOTPATH;
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
        Long date = new Date().getTime();
        String guid = UUID.randomUUID().toString();
        logger.info(guid);
        fileClassification(uploadfilename, guid, secondPath).setHandler(rs -> {
            if (rs.succeeded()) {
                JsonArray jsonArray = new JsonArray();
                jsonArray.add(guid).add(finalFilename).add(date);
                dbService.query(sql.get(SqlConstants.FILEINFO_INSERT), jsonArray, result -> {
                    if (result.succeeded()) {
                        logger.info("上传成功");
                        context.response().end(Json.encodePrettily(new JsonObject().put("repcode", "3000").put("data",new JsonObject().put("guid",guid)).put("path", domainName+"/getFile?path=" + guid + "&name=" + finalFilename)));
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


    /**
     * 上传缓存文件分类
     *
     * @param path 文件上传路径
     * @return 文件分类路径
     */
    private Future<String> fileClassification(String path, String guid, String secondPath) {
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


}
