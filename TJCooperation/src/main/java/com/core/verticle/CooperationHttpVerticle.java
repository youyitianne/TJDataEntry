package com.core.verticle;

import com.core.enums.ConfigConstants;
import com.core.service.CooperationDatabaseService;
import com.core.util.ConfUtil;
import com.core.util.ExcelRead;
import com.core.util.Judgement;
import com.core.util.Transform;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.FileUpload;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CookieHandler;
import io.vertx.ext.web.handler.CorsHandler;
import io.vertx.ext.web.handler.SessionHandler;
import io.vertx.ext.web.sstore.LocalSessionStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CooperationHttpVerticle extends AbstractVerticle {
    public static final String CONFIG_COOPERATION_QUEUE = "cooperation.queue";
    Logger logger = LoggerFactory.getLogger(CooperationHttpVerticle.class.getName());
    ConfUtil initConf = new ConfUtil();
    private CooperationDatabaseService dbService;

    @Override
    public void start(Future<Void> startFuture) throws Exception {
        HashMap<ConfigConstants, String> conf = initConf.loadConfigQueries();
        dbService = CooperationDatabaseService.createProxy(vertx, CONFIG_COOPERATION_QUEUE);
        Router router = Router.router(vertx);
        router.route().handler(BodyHandler.create());
        //跨域  start
        Set<String> allowHeaders = new HashSet<>();
        allowHeaders.add("x-requested-with");
        allowHeaders.add("Access-Control-Allow-Origin");
        allowHeaders.add("origin");
        allowHeaders.add("Content-Type");
        allowHeaders.add("accept");
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
        router.route().handler(CookieHandler.create());
        router.route().handler(SessionHandler.create(LocalSessionStore.create(vertx)));

        router.route().handler(BodyHandler.create().setUploadsDirectory("Cooperation" + File.separator + "file-uploads"));

        //合作公司信息
        router.post("/cooperation/addCompany").handler(this::addCompanyHandler);
        router.get("/cooperation/listCompany").handler(this::listCompanyHandler);
        router.delete("/cooperation/delCompany").handler(this::delCompanyHandler);
        //合作游戏信息
        router.post("/cooperation/addApp").handler(this::addAppHandler);
        router.get("/cooperation/listApp").handler(this::listAppHandler);
        router.delete("/cooperation/delApp").handler(this::delAppHandler);
        //文件上传
        router.post("/fileupload").handler(this::uploadHandler);


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
     * 文件上传路由
     * 根据上传文件的文件名，将请求分发给不同的处理器
     */
    private void uploadHandler(RoutingContext context) {
        Set<FileUpload> fileUploads = context.fileUploads();
        String filename = "";
        for (FileUpload f : fileUploads) {
            filename = f.fileName();
        }
        String channel = "未知";
        if (filename.indexOf("_") > -1) {
            channel = filename.split("_")[0].trim();
        } else {
            if (filename.indexOf("(") > -1) {
                channel = filename.substring(0, filename.indexOf("(")).trim();    //比如移信的    移信(1).xlsx
            } else {
                channel = filename.substring(0, filename.indexOf(".")).trim();     //比如广点通
            }
        }
        switch (channel) {
            case "友盟3":
                this.uploadUmengHandler(context);
                break;
            case "oppo":
                this.uploadChannelAdDataHandler(context, 1);
                break;
            case "vivo":
                this.uploadChannelAdDataHandler(context, 3);
                break;
            default:
                logger.error("上传文件格式有问题---》" + filename);
                service3001Handler(context, filename + "     请检查上传文件的格式");
                break;
        }
    }

    /**
     * 渠道数据处理
     *
     * @param context
     */
    private void uploadChannelAdDataHandler(RoutingContext context, Integer type) {
        Set<FileUpload> fileUploads = context.fileUploads();
        String filename = "";
        String uploadfilename = "";
        for (FileUpload f : fileUploads) {
            filename = f.fileName();
            uploadfilename = f.uploadedFileName();
        }
        String finalname = filename;
        fileClassification(uploadfilename, filename).setHandler(rs -> {
            if (rs.succeeded()) {
                String newpath = rs.result();
                switch (type) {
                    case 1:   //oppo
                        if (finalname.split("_").length < 3) {
                            context.response().setStatusCode(503).end(Json.encodePrettily(new JsonObject().put("data", "请规范文件名")));
                            return;
                        }
                        String oppodate = finalname.split("_")[2];
                        try {
                            new SimpleDateFormat("yyyy-MM-dd").parse(oppodate);
                        } catch (Exception E) {
                            context.response().setStatusCode(503).end(Json.encodePrettily(new JsonObject().put("data", "请规范文件名")));
                            return;
                        }
                        getMatchingData().setHandler(matching -> {
                            if (matching.succeeded()) {
                                List<List<String>> lists = matching.result();
                                dataOperation.oppoOperation(newpath, oppodate, lists).setHandler(result -> {
                                    if (result.succeeded()) {
                                        List<AdData> adDataList = result.result();
                                        List<JsonArray> adlist = Transform.adDatatoJsonArrayList(adDataList);
                                        adResponse(context, adlist, finalname);
                                    } else {
                                        logger.error("oppo数据读取失败------->" + result.cause().toString());
                                        serviceError(context, Json.encodePrettily(new JsonObject().put("data", "oppo数据读取失败")));
                                    }
                                });
                            } else {
                                logger.error("匹配数据读取失败------->" + matching.cause().toString());
                                serviceError(context, Json.encodePrettily(new JsonObject().put("data", "匹配读取失败")));
                            }
                        });

                        break;
                    case 3:     //vivo
                        if (finalname.split("_").length < 3) {
                            context.response().setStatusCode(503).end(Json.encodePrettily(new JsonObject().put("data", "请规范文件名")));
                            return;
                        }
                        String vivodate = finalname.split("_")[2];
                        try {
                            new SimpleDateFormat("yyyy-MM-dd").parse(vivodate);
                        } catch (Exception E) {
                            context.response().setStatusCode(503).end(Json.encodePrettily(new JsonObject().put("data", "请规范文件名")));
                            return;
                        }
                        getMatchingData().setHandler(matching -> {
                            if (matching.succeeded()) {
                                List<List<String>> lists = matching.result();
                                dataOperation.vivoOperation(newpath, vivodate, lists).setHandler(result -> {
                                    if (result.succeeded()) {
                                        List<AdData> adDataList = result.result();
                                        List<JsonArray> adlist = Transform.adDatatoJsonArrayList(adDataList);
                                        adResponse(context, adlist, finalname);
                                    } else {
                                        logger.error("vivo数据读取失败------->" + result.cause().toString());
                                        serviceError(context, Json.encodePrettily(new JsonObject().put("data", "vivo数据读取失败")));
                                    }
                                });
                            } else {
                                logger.error("匹配数据读取失败------->" + matching.cause().toString());
                                serviceError(context, Json.encodePrettily(new JsonObject().put("data", "匹配读取失败")));
                            }
                        });
                        break;
                    default:
                        logger.error("上传无效类型------->" + newpath);
                        serviceError(context, Json.encodePrettily(new JsonObject().put("data", "上传无效类型")));
                        break;
                }
            } else {
                logger.error("文件路径出错------->" + rs.cause());
                serviceError(context, Json.encodePrettily(new JsonObject().put("data", "文件路径出错")));
            }
        });
    }


    /**
     * 友盟数据处理 友盟3
     *
     * @param context
     */
    private void uploadUmengHandler(RoutingContext context) {
        getMatchingData().setHandler(matching -> {
            if (matching.succeeded()) {
                List<List<String>> matchinglist = matching.result();
                ExcelRead excelRead = new ExcelRead();
                Set<FileUpload> fileUploads = context.fileUploads();
                String filename = "";
                String uploadfilename = "";
                for (FileUpload f : fileUploads) {
                    filename = f.fileName();
                    uploadfilename = f.uploadedFileName();
                }
                String finnalfilename = filename;
                //(date,app_name,channel,dnu,dau,startup_time,single_use_time,retention,version)
                fileClassification(uploadfilename, filename).setHandler(rs -> {
                    if (rs.succeeded()) {
                        String newpath = rs.result();
                        List<List> list = excelRead.readExcel(newpath);
                        List<JsonArray> jsonArrayList = new ArrayList<>();
                        for (int i = 0; i < list.size(); i++) {
                            List innerlist = list.get(i);
                            JsonArray jsonArray = new JsonArray();
                            for (int j = 0; j < innerlist.size(); j++) {
                                if (j == 0) {
                                    jsonArray.add(Transform.transForMilliSecondByTim(String.valueOf(innerlist.get(j)), "yyyy-MM-dd"));
                                } else if (j == 5) {
                                    if ((innerlist.get(j) + "").indexOf(".") != -1) {
                                        String launch = (innerlist.get(j) + "").substring(0, (innerlist.get(j) + "").indexOf("."));
                                        Pattern pattern = Pattern.compile("[0-9]*");
                                        Matcher isNum = pattern.matcher(launch);
                                        if (isNum.matches()) {
                                            jsonArray.add(launch);
                                        } else {
                                            jsonArray.add(0);
                                        }
                                    } else {
                                        jsonArray.add(0);
                                    }
                                } else if (j == 6) {
                                    jsonArray.add(" " + innerlist.get(j));  //mysql 5.7.23 存入00:00:00  数据时前两位值改变
                                } else if (j == 1) {
                                    if (Judgement.matchName(innerlist.get(1).toString(), matchinglist.get(0)).equals("未知")) {
                                        System.out.println("++++++++++++++");
                                        System.out.println( matchinglist.get(0));
                                        System.out.println("++++++++++++++");
                                        operationSuccess(context, new JsonObject().put("data", finnalfilename + "数据解析失败，(" + innerlist.get(1).toString() + ")发现异常命名~"));
                                        return;
                                    }
                                    jsonArray.add(Judgement.matchName(innerlist.get(1).toString(), matchinglist.get(0)));
                                } else {
                                    jsonArray.add(innerlist.get(j));
                                }
                            }
                            jsonArrayList.add(jsonArray);
                        }

                        StringBuilder builder = new StringBuilder();
                        if (jsonArrayList.size() < 35000) {
                            for (int i = 0; i < jsonArrayList.size(); i++) {
                                JsonArray jsonArray = jsonArrayList.get(i);
                                if (i == 0) {
                                    System.out.println(jsonArray);
                                }
                                Integer date = jsonArray.getInteger(0);
                                String app_name = jsonArray.getString(1);
                                String channel = jsonArray.getString(2);
                                String dnu = jsonArray.getString(3);
                                String dau = jsonArray.getString(4);
                                String startup_time = jsonArray.getString(5);
                                String single_use_time = jsonArray.getString(6);
                                String retention = jsonArray.getString(7);
                                String version = jsonArray.getString(8);
                                builder.append("(" + date + ","
                                        + "'" + app_name + "'" + ","
                                        + "'" + channel + "'" + ","
                                        + dnu + ","
                                        + dau + ","
                                        + startup_time + ","
                                        + "'" + single_use_time + "'" + ","
                                        + retention + ","
                                        + "'" + version + "'" + ")");
                                if (i != jsonArrayList.size() - 1) {
                                    builder.append(",");
                                }
                            }
                        } else {
                            serviceError(context, Json.encodePrettily(new JsonObject().put("data", "用户数据去重失败")));
                            return;
                        }
                        logger.info(" 友盟数据处理 友盟3---->" + SqlStatement.INSERT_USER + builder.toString());
                        builder.append(" ON DUPLICATE KEY UPDATE date=VALUES(date),app_name =VALUES(app_name),channel =VALUES(channel);");

//insert into advertisement.userdata (date,app_name,channel,dnu,dau,startup_time,single_use_time,retention,version) values
                        advertisementService.queryNoResult(jdbcClient, SqlStatement.INSERT_USER + builder).setHandler(result -> {
                            if (result.succeeded()) {
                                operationSuccess(context, new JsonObject().put("data", finnalfilename + "数据已成功上传，并未发现异常~"));
//                                advertisementService.removeRepeat(jdbcClient, SqlStatement.REPEAT_ID_USER, SqlStatement.DEL_ID_USER).setHandler(result1 -> {
//                                    System.out.println(4);
//
//                                    if (result1.succeeded()) {
//                                        operationSuccess(context, new JsonObject().put("data", finnalfilename + "数据已成功上传，并未发现异常~"));
//                                    } else {
//                                        logger.error("用户数据去重失败------->" + result1.cause());
//                                        serviceError(context, Json.encodePrettily(new JsonObject().put("data", "用户数据去重失败")));
//                                    }
//                                });
                            } else {
                                logger.error("用户数据插入失败------->" + result.cause());
                                serviceError(context, Json.encodePrettily(new JsonObject().put("data", "用户数据插入失败")));
                            }
                        });
                    } else {
                        logger.error("文件路径出错------->" + rs.cause().toString());
                        serviceError(context, Json.encodePrettily(new JsonObject().put("data", "文件路径出错")));
                    }
                });
            }
        });
    }



    /**
     * 添加公司
     *
     * @param context
     */
    private void addCompanyHandler(RoutingContext context) {
        String companyName = context.getBodyAsJson().getString("companyName");
        String creator = context.getBodyAsJson().getString("creator");
        dbService.query("INSERT INTO `cooperation`.`co_company` (`company_name`,`creator`) VALUES (?,?);", new JsonArray().add(companyName).add(creator), queryHandler -> {
            if (queryHandler.succeeded()) {
                logger.info("添加公司成功");
                service3000Handler(context, "添加成功");
            } else {
                logger.info("添加公司失败");
                service3001Handler(context, "添加公司失败");
            }
        });
    }

    /**
     * 删除公司
     *
     * @param context
     */
    private void delCompanyHandler(RoutingContext context) {
        Integer company_id = context.getBodyAsJson().getInteger("company_id");
        dbService.query("DELETE FROM `cooperation`.`co_company` WHERE company_id= ?;", new JsonArray().add(company_id), queryHandler -> {
            if (queryHandler.succeeded()) {
                logger.info("删除公司信息成功");
                service3000Handler(context, "删除公司信息成功");
            } else {
                logger.info("删除公司信息失败");
                service3001Handler(context, "删除公司信息失败");
            }
        });
    }

    /**
     * 获取公司列表
     *
     * @param context
     */
    private void listCompanyHandler(RoutingContext context) {
        Integer limit = 0;
        Integer page = 0;
        try {
            limit = Integer.valueOf(context.request().getParam("limit"));
            page = Integer.valueOf(context.request().getParam("page"));
        } catch (Exception e) {
            logger.error("分页参数错误");
            context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3001).put("data", "参数错误")));
            return;
        }
        StringBuilder limitsql = new StringBuilder();
        limitsql.append("  limit " + (page - 1) * limit + "," + limit + ";");
        dbService.listDatas("SELECT count(*) total FROM cooperation.co_company ", countResult -> {
            if (countResult.succeeded()) {
                Integer total = countResult.result().get(0).getInteger("total");
                dbService.listDatas("SELECT * FROM cooperation.co_company " + limitsql, queryHandler -> {
                    if (queryHandler.succeeded()) {
                        logger.info("获取公司信息列表成功");
                        context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3000).put("total", total).put("data", queryHandler.result())));
                    } else {
                        logger.info("获取公司信息列表失败");
                        service3001Handler(context, "获取公司信息列表失败");
                    }
                });
            } else {
                logger.info("获取公司信息列表失败");
                service3001Handler(context, "获取公司信息列表失败");
            }
        });
    }

    /**
     * 添加应用
     *
     * @param context
     */
    private void addAppHandler(RoutingContext context) {
        String app_name = context.getBodyAsJson().getString("appName");
        dbService.query("INSERT INTO `cooperation`.`co_app` (`app_name`) VALUES (?);", new JsonArray().add(app_name), queryHandler -> {
            if (queryHandler.succeeded()) {
                logger.info("添加应用成功");
                service3000Handler(context, "添加应用成功");
            } else {
                logger.info("添加应用失败");
                service3001Handler(context, "添加应用失败");
            }
        });
    }

    /**
     * 删除应用
     *
     * @param context
     */
    private void delAppHandler(RoutingContext context) {
        Integer app_id = context.getBodyAsJson().getInteger("app_id");
        dbService.query("DELETE FROM `cooperation`.`co_app` WHERE app_id = ?;", new JsonArray().add(app_id), queryHandler -> {
            if (queryHandler.succeeded()) {
                logger.info("删除应用信息成功");
                service3000Handler(context, "删除应用信息成功");
            } else {
                logger.info("删除应用信息失败");
                service3001Handler(context, "删除应用信息失败");
            }
        });
    }

    /**
     * 获取应用列表
     *
     * @param context
     */
    private void listAppHandler(RoutingContext context) {
        Integer limit = 0;
        Integer page = 0;
        try {
            limit = Integer.valueOf(context.request().getParam("limit"));
            page = Integer.valueOf(context.request().getParam("page"));
        } catch (Exception e) {
            logger.error("分页参数错误");
            context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3001).put("data", "参数错误")));
            return;
        }
        StringBuilder limitsql = new StringBuilder();
        limitsql.append("  limit " + (page - 1) * limit + "," + limit + ";");
        dbService.listDatas("SELECT count(*) total FROM cooperation.co_app ", countResult -> {
            if (countResult.succeeded()) {
                Integer total = countResult.result().get(0).getInteger("total");
                dbService.listDatas("SELECT * FROM cooperation.co_app " + limitsql, queryHandler -> {
                    if (queryHandler.succeeded()) {
                        logger.info("获取应用信息列表成功");
                        context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3000).put("total", total).put("data", queryHandler.result())));
                    } else {
                        logger.info("获取应用信息列表失败");
                        service3001Handler(context, "获取应用信息列表失败");
                    }
                });
            } else {
                logger.info("获取应用信息列表失败");
                service3001Handler(context, "获取应用信息列表失败");
            }
        });
    }









    /**
     * 将上传文件从文件缓存目录移动到固定目录
     *
     * @param path    文件缓存路径
     * @param newname 原文件名
     * @return String 返回文件移动后路径
     */
    private Future<String> fileClassification(String path, String newname) {
        Future<String> future = Future.future();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        File file = new File("TJDataEntry" + File.separator + "file-uploads" + File.separator + date);
        if (!file.exists()) {
            file.mkdir();
        }
        String newpath = file + File.separator + newname;
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
     * 请求成功返回
     *
     * @param context
     * @param result
     */
    private void service3000Handler(RoutingContext context, String result) {
        context.response()
                .putHeader("Content-type", "text/html;charset=utf-8")
                .end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3000).put("data", result)));
    }

    /**
     * 请求失败返回
     *
     * @param context
     * @param result
     */
    private void service3001Handler(RoutingContext context, String result) {
        context.response()
                .putHeader("Content-type", "text/html;charset=utf-8")
                .end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3001).put("data", result)));
    }

    /**
     * 503
     */
    private void serviceError(RoutingContext context, String string) {
        context.response().setStatusCode(503).end(string);
    }


    /**
     * 获取渠道名  游戏名  广告类型 项目名
     *
     * List.get(0)  应用名<br/>
     * List.get(1)  渠道<br>
     */
    private Future<List<List<String>>> getMatchingData() {
        Future<List<List<String>>> future = Future.future();
        List<String> gamelist = new ArrayList<>();
        List<String> channellist = new ArrayList<>();
        dbService.listDatas("",game -> {
            if (game.succeeded()) {
                List<JsonObject> jsonObjects = game.result();
                for (int i = 0; i < jsonObjects.size(); i++) {
                    String name = jsonObjects.get(i).getString("name");
                    gamelist.add(i, name);
                }
                dbService.listDatas("",channel -> {
                    if (channel.succeeded()) {
                        List<JsonObject> jsonObjects1 = channel.result();
                        for (int i = 0; i < jsonObjects1.size(); i++) {
                            String name = jsonObjects1.get(i).getString("name");
                            channellist.add(i, name);
                        }
                    } else {
                        logger.error("获取渠道信息失败" + channel.cause().toString());
                        future.failed();
                    }
                });
            } else {
                logger.error("获取游戏名失败" + game.cause().toString());
                future.failed();
            }
        });
        return future;
    }
}
