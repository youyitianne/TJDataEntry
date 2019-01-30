package http;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import database.AdvertisementService;
import database.ConfigConstants;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.file.FileSystem;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.web.FileUpload;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CorsHandler;
import jdk.nashorn.internal.ir.LiteralNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.Constant.CacheList;
import service.Constant.SqlStatement;
import service.Constant.TemplateFile;
import service.dataprocessing.DataOperation;
import service.dataprocessing.ExcelRead;
import service.dataprocessing.ExcelWrite;
import service.entity.AdData;
import service.entity.ArpuData;
import service.entity.TotalVO;
import service.pubmethod.CacheOpertion;
import service.pubmethod.InitConf;
import service.pubmethod.Judgement;
import service.pubmethod.Transform;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

public class HttpServerVerticle extends AbstractVerticle {
    private Logger logger = null;
    private DataOperation dataOperation = null;
    private AdvertisementService advertisementService = new AdvertisementService();
    private ExcelWrite excelWrite = new ExcelWrite();
    private JDBCClient jdbcClient = null;

    @Override
    public void start(Future<Void> startFuture) {
        InitConf initConf = new InitConf();
        HashMap<ConfigConstants, String> conf = initConf.loadSqlQueries();
        Router router = Router.router(vertx);
        dataOperation = new DataOperation(vertx);
        JsonObject config = new JsonObject()
                .put("url", conf.get(ConfigConstants.DATABASE_URL))
                .put("driver_class", conf.get(ConfigConstants.DATABASE_DRIVER_CLASS))
                .put("max_pool_size", Integer.valueOf(conf.get(ConfigConstants.DATABASE_MAX_POOL_SIZE)))
                .put("user", conf.get(ConfigConstants.DATABASE_USER))
                .put("password", conf.get(ConfigConstants.DATABASE_PASSWORD));
        jdbcClient = JDBCClient.createShared(vertx, config);
        logger = LoggerFactory.getLogger(HttpServerVerticle.class.getName());
        router.route().handler(BodyHandler.create().setUploadsDirectory("TJDataEntry" + File.separator + "file-uploads"));
        //跨域请求
        Set<String> allowHeaders = new HashSet<>();
        allowHeaders.add("x-requested-with");
        allowHeaders.add("Access-Control-Allow-Origin");
        allowHeaders.add("origin");
        allowHeaders.add("Content-Type");
        allowHeaders.add("accept");
        allowHeaders.add("Access-Control-Allow-Headers");
        allowHeaders.add("Cache-Control");
        Set<HttpMethod> allowMethods = new HashSet<>();
        allowMethods.add(HttpMethod.GET);
        allowMethods.add(HttpMethod.POST);
        allowMethods.add(HttpMethod.DELETE);
        allowMethods.add(HttpMethod.PATCH);

        router.route().handler(BodyHandler.create());
        router.route().handler(CorsHandler.create("*")
                .allowedMethods(allowMethods)
                .allowedHeaders(allowHeaders));
//        //静态资源处理
//        router.route("/static/*").handler(StaticHandler.create());

        //文件上传
        router.post("/fileupload").handler(this::uploadHandler);
        //文件下载
        router.post("/file").handler(this::DownloadFileHandler);
        router.post("/arpufile/*").handler(this::DownloadARPUFileHandler);
        //api获取广告数据
        router.get("/data/*").handler(this::FindDatasHandler);
        //获取游戏名
        router.get("/name").handler(this::nameHandler);
        router.get("/names").handler(this::namesHandler);
        //api获取移信数据
        router.get("/yixin/:starttime/:endtime").handler(this::yixinHandler);
        //api获取用户数据
        router.get("/userdata/:starttime/:endtime").handler(this::userDataHandler);
        router.get("/appdata/:starttime/:endtime").handler(this::appDataHandler);
        //channel相关
        router.get("/channel").handler(this::findChannel);
        router.post("/channel").handler(this::addChannel);
        router.patch("/channel/:id").handler(this::updateChannel);
        router.delete("/channel/:id").handler(this::delChannel);
        //app相关
        router.get("/app").handler(this::findApp);
        router.get("/projectList").handler(this::findProjectListHandler);
        router.post("/app").handler(this::addApp);
        router.patch("/app/:id").handler(this::updateApp);
        router.delete("/app/:id").handler(this::delApp);
        //project相关
        router.get("/project").handler(this::findProject);
        router.post("/project").handler(this::addProject);
        router.patch("/project/:id").handler(this::updateProject);
        router.delete("/project/:id").handler(this::delProject);
        //adtype相关
        router.get("/adtype").handler(this::findAdType);
        router.post("/adtype").handler(this::addAdType);
        router.patch("/adtype/:id").handler(this::updateAdType);
        router.delete("/adtype/:id").handler(this::delAdType);
        //权限获取应用名
        router.get("/getapplist").handler(this::findApp_perms);

//      router.get("/test").handler(this::getMatchingData);

        int portNumber = Integer.valueOf(conf.get(ConfigConstants.HTTP_PORT));
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
     * 根据时间查询所有数据
     *
     * @param context
     */
    private void appDataHandler(RoutingContext context) {
        String starttime = context.request().getParam("starttime");
        String endtime = context.request().getParam("endtime");
        Integer num = starttime.split("-").length - 1;
        if (num == 2) {
            String statis_time = starttime.replace(starttime.split("-")[2], "01");
            Long start = 0L;
            Long end = 0L;
            Long statis = 0L;
            try {
                statis = new SimpleDateFormat("yyyy-MM-dd").parse(statis_time).getTime() / 1000;
                start = new SimpleDateFormat("yyyy-MM-dd").parse(starttime).getTime() / 1000;
                end = new SimpleDateFormat("yyyy-MM-dd").parse(endtime).getTime() / 1000;
            } catch (Exception e) {
                badRequest(context);
            }
            Long finalstart = start;
            Long finalend = end;
            Long startss = statis;
            advertisementService.findDatasWithParams(jdbcClient, SqlStatement.SELECT_USERDATA_BY_TIME, new JsonArray().add(start).add(end)).setHandler(result1 -> {
                advertisementService.findDatasWithParams(jdbcClient, SqlStatement.SELECT_APPDATA_BY_TIME, new JsonArray().add(finalstart).add(finalend)).setHandler(result -> {
                    advertisementService.findDatasWithParams(jdbcClient, SqlStatement.SELECT_MONDATA, new JsonArray().add(startss).add(finalstart)).setHandler(result2 -> {
                        if (result.succeeded() && result1.succeeded() && result2.succeeded()) {
                            List<JsonObject> userObjects = result1.result();
                            List<JsonObject> appObjects = result.result();
                            List<JsonObject> monObjects = result2.result();
                            JsonArray jsonArray = new JsonArray();
                            for (int i = 0; i < userObjects.size(); i++) {
                                Date date1 = new Date();
                                date1.setTime(1000L * userObjects.get(i).getLong("date"));
                                String date = new SimpleDateFormat("yyyy-MM-dd").format(date1);
                                userObjects.get(i).put("date", date);
                                jsonArray.add(userObjects.get(i));
                            }
                            JsonArray jsonArray1 = new JsonArray();
                            for (int i = 0; i < appObjects.size(); i++) {
                                Date date1 = new Date();
                                date1.setTime(1000L * appObjects.get(i).getLong("date"));
                                String date = new SimpleDateFormat("yyyy-MM-dd").format(date1);
                                appObjects.get(i).put("date", date);
                                jsonArray1.add(appObjects.get(i));
                            }
                            context.response().setStatusCode(200).end(Json.encodePrettily(new JsonObject().put("code", 20000).put("data", jsonArray).put("data1", jsonArray1).put("mon", monObjects)));
                        } else {
                            if (result.failed()) {
                                logger.error(result.cause().toString());
                            }
                            if (result1.failed()) {
                                logger.error(result1.cause().toString());
                            }
                            if (result2.failed()) {
                                logger.error(result2.cause().toString());
                            }
                            badRequest(context);
                        }
                    });
                });
            });
        } else if (num == 1) {
            starttime = starttime + "-01";
            endtime = Transform.getLastDay(endtime);
            Long start = 0L;
            Long end = 0L;
            try {
                start = new SimpleDateFormat("yyyy-MM-dd").parse(starttime).getTime() / 1000;
                end = new SimpleDateFormat("yyyy-MM-dd").parse(endtime).getTime() / 1000;
            } catch (Exception e) {
                badRequest(context);
            }
            Long finalstart = start;
            Long finalend = end;
            advertisementService.findDatasWithParams(jdbcClient, SqlStatement.SELECT_USERDATA_BY_TIME, new JsonArray().add(start).add(end)).setHandler(result1 -> {
                advertisementService.findDatasWithParams(jdbcClient, SqlStatement.SELECT_APPDATA_BY_TIME, new JsonArray().add(finalstart).add(finalend)).setHandler(result -> {
                    if (result.succeeded() && result1.succeeded()) {
                        List<JsonObject> userObjects = result1.result();
                        List<JsonObject> appObjects = result.result();
                        JsonArray jsonArray = new JsonArray();
                        for (int i = 0; i < userObjects.size(); i++) {
                            Date date1 = new Date();
                            date1.setTime(1000L * userObjects.get(i).getLong("date"));
                            String date = new SimpleDateFormat("yyyy-MM").format(date1);
                            userObjects.get(i).put("date", date);
                            jsonArray.add(userObjects.get(i));
                        }
                        JsonArray jsonArray1 = new JsonArray();
                        for (int i = 0; i < appObjects.size(); i++) {
                            Date date1 = new Date();
                            date1.setTime(1000L * appObjects.get(i).getLong("date"));
                            String date = new SimpleDateFormat("yyyy-MM").format(date1);
                            appObjects.get(i).put("date", date);
                            jsonArray1.add(appObjects.get(i));
                        }
                        context.response().setStatusCode(200).end(Json.encodePrettily(new JsonObject().put("code", 20000).put("data", jsonArray).put("data1", jsonArray1).put("mon", new JsonArray().add(0))));
                    } else {
                        if (result.failed()) {
                            logger.error(result.cause().toString());
                        }
                        if (result1.failed()) {
                            logger.error(result1.cause().toString());
                        }
                        badRequest(context);
                    }
                });
            });
        } else if (num == 0) {
            starttime = starttime + "-01-01";
            endtime = Transform.getLastDay(endtime + "-12");
            Long start = 0L;
            Long end = 0L;
            try {
                start = new SimpleDateFormat("yyyy-MM-dd").parse(starttime).getTime() / 1000;
                end = new SimpleDateFormat("yyyy-MM-dd").parse(endtime).getTime() / 1000;
            } catch (Exception e) {
                badRequest(context);
            }
            Long finalstart = start;
            Long finalend = end;
            advertisementService.findDatasWithParams(jdbcClient, SqlStatement.SELECT_USERDATA_BY_TIME, new JsonArray().add(start).add(end)).setHandler(result1 -> {
                advertisementService.findDatasWithParams(jdbcClient, SqlStatement.SELECT_APPDATA_BY_TIME, new JsonArray().add(finalstart).add(finalend)).setHandler(result -> {
                    if (result.succeeded() && result1.succeeded()) {
                        List<JsonObject> userObjects = result1.result();
                        List<JsonObject> appObjects = result.result();
                        JsonArray jsonArray = new JsonArray();
                        for (int i = 0; i < userObjects.size(); i++) {
                            Date date1 = new Date();
                            date1.setTime(1000L * userObjects.get(i).getLong("date"));
                            String date = new SimpleDateFormat("yyyy").format(date1);
                            userObjects.get(i).put("date", date);
                            jsonArray.add(userObjects.get(i));
                        }
                        JsonArray jsonArray1 = new JsonArray();
                        for (int i = 0; i < appObjects.size(); i++) {
                            Date date1 = new Date();
                            date1.setTime(1000L * appObjects.get(i).getLong("date"));
                            String date = new SimpleDateFormat("yyyy").format(date1);
                            appObjects.get(i).put("date", date);
                            jsonArray1.add(appObjects.get(i));
                        }
                        context.response().setStatusCode(200).end(Json.encodePrettily(new JsonObject().put("code", 20000).put("data", jsonArray).put("data1", jsonArray1).put("mon", new JsonArray().add(0))));
                    } else {
                        if (result.failed()) {
                            logger.error(result.cause().toString());
                        }
                        if (result1.failed()) {
                            logger.error(result1.cause().toString());
                        }
                        badRequest(context);
                    }
                });
            });
        } else {
            badRequest(context);
        }
    }

    /**
     * 文件上传路由
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
            case "友盟":
                this.uploadUserDataHandler(context);
                break;
            case "友盟1":
                this.uploadNewUserDataHandler(context);
                break;
            case "oppo":
                this.uploadChannelAdDataHandler(context, 1);
                break;
            case "小米":
                this.uploadChannelAdDataHandler(context, 2);
                break;
            case "vivo":
                this.uploadChannelAdDataHandler(context, 3);
                break;
            case "魅族":
                this.uploadChannelAdDataHandler(context, 4);
                break;
            case "360":
                this.uploadChannelAdDataHandler(context, 5);
                break;
            case "三星":
                this.uploadChannelAdDataHandler(context, 6);
                break;
            case "联想":
                this.uploadChannelAdDataHandler(context, 7);
                break;
            case "九游":
                this.uploadChannelAdDataHandler(context, 8);
                break;
            case "九游1":
                this.uploadChannelAdDataHandler(context, 9);
                break;
            case "广点通":
                this.uploadGuangdiantongHandler(context);
                break;
            case "移信":
                this.uploadYixinHandler(context);
                break;
            case "4399":
                this.uploadFourThreeHandler(context);
                break;
            case "头条":
                this.uploadToutiaoHandler(context);
                break;
            default:
                logger.error("上传文件格式有问题---》" + filename);
                serviceError(context, filename + "     请检查上传文件的格式");
                break;
        }
    }

    /**
     * 头条数据上传
     *
     * @param context
     */
    private void uploadToutiaoHandler(RoutingContext context) {
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
                getMatchingData().setHandler(matching -> {
                    if (matching.succeeded()) {
                        List<List<String>> matchings = matching.result();
                        dataOperation.toutiaoOperation(newpath, matchings).setHandler(result -> {
                            if (result.succeeded()) {
                                List<AdData> adDataList = result.result();
                                List<JsonArray> adlist = Transform.adDatatoJsonArrayList(adDataList);
                                adResponse(context, adlist, finalname);
                            } else {
                                logger.error("头条数据读取失败------->" + result.cause().toString());
                                serviceError(context, Json.encodePrettily(new JsonObject().put("data", "头条数据读取失败")));
                            }
                        });
                    } else {
                        logger.error("匹配数据读取失败------->" + matching.cause().toString());
                        serviceError(context, Json.encodePrettily(new JsonObject().put("data", "匹配读取失败")));
                    }
                });
            }
        });
    }

    /**
     * 用户数据处理
     *
     * @param context
     */
    private void uploadUserDataHandler(RoutingContext context) {
        getMatchingData().setHandler(matching -> {
            if (matching.succeeded()) {
                List<List<String>> matchinglist = matching.result();
                Set<FileUpload> fileUploads = context.fileUploads();
                String filename = "";
                String uploadfilename = "";
                for (FileUpload f : fileUploads) {
                    filename = f.fileName();
                    uploadfilename = f.uploadedFileName();
                }
                String finnalfilename = filename;
                String finalname = Judgement.matchName(finnalfilename, matchinglist.get(0));
                String finalchannel = Judgement.matchChannel(finnalfilename, matchinglist.get(3), matchinglist.get(3));
                if (finalname.equals("未知") || finalchannel.equals("未知")) {
                    context.response().setStatusCode(503).end(Json.encodePrettily(new JsonObject().put("data", "请规范文件名")));
                    return;
                }
                fileClassification(uploadfilename, filename).setHandler(rs -> {
                    if (rs.succeeded()) {
                        String newpath = rs.result();
                        dataOperation.userDataOpertion(newpath, finalname, finalchannel).setHandler(listAsyncResult -> {
                            if (listAsyncResult.succeeded()) {
                                List<JsonArray> value = Transform.userDatatoUserJsonArrayList(listAsyncResult.result());
                                advertisementService.batchWithParams(jdbcClient, SqlStatement.INSERT_USER, value).setHandler(result -> {
                                    if (result.succeeded()) {
                                        advertisementService.removeRepeat(jdbcClient, SqlStatement.REPEAT_ID_USER, SqlStatement.DEL_ID_USER).setHandler(result1 -> {
                                            if (result1.succeeded()) {
                                                operationSuccess(context, new JsonObject().put("data", finnalfilename + "     数据已成功上传，并未发现异常~"));
                                            } else {
                                                logger.error("用户数据去重失败------->" + result1.cause());
                                                serviceError(context, Json.encodePrettily(new JsonObject().put("data", "用户数据去重失败")));
                                            }
                                        });
                                    } else {
                                        logger.error("用户数据插入失败------->" + result.cause());
                                        serviceError(context, Json.encodePrettily(new JsonObject().put("data", "用户数据插入失败")));
                                    }
                                });
                            } else {
                                logger.error("用户数据读取失败------->" + listAsyncResult.cause());
                                serviceError(context, Json.encodePrettily(new JsonObject().put("data", "用户数据读取失败")));
                            }
                        });
                    } else {
                        logger.error("文件路径出错------->" + rs.cause());
                        serviceError(context, Json.encodePrettily(new JsonObject().put("data", "文件路径出错")));
                    }
                });
            } else {
                logger.error("文件路径出错------->" + matching.cause());
                serviceError(context, Json.encodePrettily(new JsonObject().put("data", "读取出错")));
            }
        });
    }

    /**
     * 用户数据处理
     *
     * @param context
     */
    private void uploadNewUserDataHandler(RoutingContext context) {
        getMatchingData().setHandler(matching -> {
            if (matching.succeeded()) {
                List<List<String>> matchinglist = matching.result();
                Set<FileUpload> fileUploads = context.fileUploads();
                String filename = "";
                String uploadfilename = "";
                for (FileUpload f : fileUploads) {
                    filename = f.fileName();
                    uploadfilename = f.uploadedFileName();
                }
                String finnalfilename = filename;
                String finalname = Judgement.matchName(finnalfilename, matchinglist.get(0));
                String finalchannel = Judgement.matchChannel(finnalfilename, matchinglist.get(3), matchinglist.get(3));
                if (finalname.equals("未知") || finalchannel.equals("未知")) {
                    context.response().setStatusCode(503).end(Json.encodePrettily(new JsonObject().put("data", "请规范文件名")));
                }
                fileClassification(uploadfilename, filename).setHandler(rs -> {
                    if (rs.succeeded()) {
                        String newpath = rs.result();
                        dataOperation.newUserDataOpertion(newpath, finalname, finalchannel).setHandler(listAsyncResult -> {
                            if (listAsyncResult.succeeded()) {
                                List<JsonArray> value = Transform.userDatatoUserJsonArrayList(listAsyncResult.result());
                                advertisementService.batchWithParams(jdbcClient, SqlStatement.INSERT_USER, value).setHandler(result -> {
                                    if (result.succeeded()) {
                                        advertisementService.removeRepeat(jdbcClient, SqlStatement.REPEAT_ID_USER, SqlStatement.DEL_ID_USER).setHandler(result1 -> {
                                            if (result1.succeeded()) {
                                                operationSuccess(context, new JsonObject().put("data", finnalfilename + "   数据已成功上传，并未发现异常~"));
                                            } else {
                                                logger.error("用户数据去重失败------->" + result1.cause());
                                                serviceError(context, Json.encodePrettily(new JsonObject().put("data", "用户数据去重失败")));
                                            }
                                        });
                                    } else {
                                        logger.error("用户数据插入失败------->" + result.cause());
                                        serviceError(context, Json.encodePrettily(new JsonObject().put("data", "用户数据插入失败")));
                                    }
                                });
                            } else {
                                logger.error("用户数据读取失败------->" + listAsyncResult.cause());
                                serviceError(context, Json.encodePrettily(new JsonObject().put("data", "用户数据读取失败")));
                            }
                        });
                    } else {
                        logger.error("文件路径出错------->" + rs.cause());
                        serviceError(context, Json.encodePrettily(new JsonObject().put("data", "文件路径出错")));
                    }
                });
            } else {
                logger.error("文件路径出错------->" + matching.cause());
                serviceError(context, Json.encodePrettily(new JsonObject().put("data", "读取出错")));
            }
        });
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
                        if (finalname.split("_").length < 2) {
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
                    case 2:     //小米
                        getMatchingData().setHandler(matching -> {
                            if (matching.succeeded()) {
                                List<List<String>> matchings = matching.result();
                                dataOperation.xiaomiOperation(newpath, matchings).setHandler(result -> {
                                    if (result.succeeded()) {
                                        List<AdData> adDataList = result.result();
                                        List<JsonArray> adlist = Transform.adDatatoJsonArrayList(adDataList);
                                        adResponse(context, adlist, finalname);
                                    } else {
                                        logger.error("小米数据读取失败------->" + result.cause().toString());
                                        serviceError(context, Json.encodePrettily(new JsonObject().put("data", "小米数据读取失败")));
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
                    case 4:     //魅族
                        getMatchingData().setHandler(matching -> {
                            if (matching.succeeded()) {
                                List<List<String>> lists = matching.result();
                                Integer years = 0;
                                try {
                                    String name = finalname.split("_")[1];
                                    years = Integer.valueOf(name);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    serviceError(context, Json.encodePrettily(new JsonObject().put("data", "魅族命名格式出错，示例：魅族_2019_xxxxxxxxxxxx")));
                                    return;
                                }
//                                if (finalname.split("_").length < 4) {
//                                    context.response().setStatusCode(503).end(Json.encodePrettily(new JsonObject().put("data", "请规范文件名")));
//                                    return;
//                                }
//                                String[] name = finalname.split("_");
//                                String adType = name[name.length - 2];
                                dataOperation.meizuOperation(newpath, lists, years).setHandler(result -> {
                                    if (result.succeeded()) {
                                        List<AdData> adDataList = result.result();
                                        List<JsonArray> adlist = Transform.adDatatoJsonArrayList(adDataList);
                                        adResponse(context, adlist, finalname);
                                    } else {
                                        logger.error("魅族数据读取失败------->" + result.cause().toString());
                                        serviceError(context, Json.encodePrettily(new JsonObject().put("data", "魅族数据读取失败")));
                                    }
                                });
                            } else {
                                logger.error("匹配数据读取失败------->" + matching.cause().toString());
                                serviceError(context, Json.encodePrettily(new JsonObject().put("data", "匹配读取失败")));
                            }
                        });
                        break;
                    case 5:  //360
                        getMatchingData().setHandler(matching -> {
                            if (matching.succeeded()) {
//                                if (finalname.split("_").length < 2) {
//                                    context.response().setStatusCode(503).end(Json.encodePrettily(new JsonObject().put("data", "请规范文件名")));
//                                    return;
//                                }
//                                String[] classifyData = finalname.split("_");
//                                String adtype = classifyData[1];
                                List<List<String>> lists = matching.result();
                                dataOperation.qihooOperation(newpath, lists).setHandler(result -> {
                                    if (result.succeeded()) {
                                        List<AdData> adDataList = result.result();
                                        List<JsonArray> adlist = Transform.adDatatoJsonArrayList(adDataList);
                                        adResponse(context, adlist, finalname);
                                    } else {
                                        logger.error("360数据读取失败------->" + result.cause().toString());
                                        serviceError(context, "360数据读取失败");
                                    }
                                });
                            } else {
                                logger.error("匹配数据读取失败------->" + matching.cause().toString());
                                serviceError(context, Json.encodePrettily(new JsonObject().put("data", "匹配读取失败")));
                            }
                        });
                        break;
                    case 6: //三星
                        String[] adtypeAndName = finalname.substring(0, finalname.indexOf(".")).split("_");
                        if (adtypeAndName.length < 3) {
                            context.response().setStatusCode(503).end(Json.encodePrettily(new JsonObject().put("data", "请规范文件名")));
                            return;
                        }
                        String appname = adtypeAndName[1];
                        String adtype = adtypeAndName[2];
                        dataOperation.samsungOperation(newpath, adtype, appname).setHandler(result -> {
                            if (result.succeeded()) {
                                List<AdData> adDataList = result.result();
                                List<JsonArray> adlist = Transform.adDatatoJsonArrayList(adDataList);
                                adResponse(context, adlist, finalname);
                            } else {
                                logger.error("三星数据读取失败------->" + result.cause().toString());
                                serviceError(context, "三星数据读取失败");
                            }
                        });
                        break;
                    case 7: //联想
                        String[] lenovoadtypeAndName = finalname.substring(0, finalname.indexOf(".")).split("_");
                        if (lenovoadtypeAndName.length < 3) {
                            context.response().setStatusCode(503).end(Json.encodePrettily(new JsonObject().put("data", "请规范文件名")));
                            return;
                        }
                        String lenovoappname = lenovoadtypeAndName[1];
                        String lonovoadtype = lenovoadtypeAndName[2];
                        dataOperation.lenovoOperation(newpath, lonovoadtype, lenovoappname).setHandler(result -> {
                            if (result.succeeded()) {
                                List<AdData> adDataList = result.result();
                                List<JsonArray> adlist = Transform.adDatatoJsonArrayList(adDataList);
                                List<Integer> list1 = Judgement.getMismatch(adlist);
                                adlist = Judgement.removeUseless(adlist);
                                String message;

                                if (list1.size() == 0) {
                                    message = finalname + "    数据已成功上传，并未发现异常~";
                                } else {
                                    for (int i = 0; i < list1.size(); i++) {
                                        list1.set(i, list1.get(i) + 1);
                                    }
                                    message = finalname + "    第" + list1 + "行数据发现异常，请检查上传文件~";
                                }
                                advertisementService.batchWithParams(jdbcClient, SqlStatement.INSERT_ADVERTISEMENT, adlist).setHandler(result1 -> {
                                    if (result1.succeeded()) {
                                        advertisementService.removeRepeat(jdbcClient, SqlStatement.REPEAT_ID_ADVERTISEMENT, SqlStatement.DEL_ID_ADVERTISEMENT).setHandler(result2 -> {
                                            if (result2.succeeded()) {
                                                context.response().setStatusCode(200).end(Json.encodePrettily(new JsonObject().put("data", message)));
                                            } else {
                                                logger.error("广告数据格式有错误,去重失败------->" + result2.cause().toString());
                                                serviceError(context, Json.encodePrettily(new JsonObject().put("data", "请联系管理员。")));
                                            }
                                        });
                                    } else {
                                        logger.error("广告数据格式有错误,插入失败------->" + result.cause().toString());
                                        serviceError(context, Json.encodePrettily(new JsonObject().put("data", "广告数据格式有错误")));
                                    }
                                });

                            } else {
                                logger.error("联想数据读取失败------->" + result.cause());
                                serviceError(context, "联想数据读取失败");
                            }
                        });
                        break;
                    case 8: //九游
                        getMatchingData().setHandler(matching -> {
                            if (matching.succeeded()) {
                                List<List<String>> matchings = matching.result();
                                dataOperation.jiuyouOperation(newpath, matchings).setHandler(result -> {
                                    if (result.succeeded()) {
                                        List<AdData> adDataList = result.result();
                                        List<JsonArray> adlist = Transform.adDatatoJsonArrayList(adDataList);
                                        adResponse(context, adlist, finalname);
                                    } else {
                                        logger.error("九游数据读取失败------->" + result.cause().toString());
                                        serviceError(context, Json.encodePrettily(new JsonObject().put("data", "九游数据读取失败")));
                                    }
                                });
                            } else {
                                logger.error("匹配数据读取失败------->" + matching.cause().toString());
                                serviceError(context, Json.encodePrettily(new JsonObject().put("data", "匹配读取失败")));
                            }
                        });
                        break;
                    case 9: //九游1
                        getMatchingData().setHandler(matching -> {
                            if (matching.succeeded()) {
                                List<List<String>> matchings = matching.result();
                                dataOperation.newjiuyouOperation(newpath, matchings).setHandler(result -> {
                                    if (result.succeeded()) {
                                        List<AdData> adDataList = result.result();
                                        List<JsonArray> adlist = Transform.adDatatoJsonArrayList(adDataList);
                                        adResponse(context, adlist, finalname);
                                    } else {
                                        logger.error("九游数据读取失败------->" + result.cause().toString());
                                        serviceError(context, Json.encodePrettily(new JsonObject().put("data", "九游数据读取失败")));
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
     * 广点通数据处理
     *
     * @param context
     */
    private void uploadGuangdiantongHandler(RoutingContext context) {
        Set<FileUpload> fileUploads = context.fileUploads();
        String filename = "";
        String uploadfilename = "";
        for (FileUpload f : fileUploads) {
            filename = f.fileName();
            uploadfilename = f.uploadedFileName();
        }
        String finnalfilename = filename;
        fileClassification(uploadfilename, filename).setHandler(rs -> {
            if (rs.succeeded()) {
                String newpath = rs.result();
                getMatchingData().setHandler(matching -> {
                    if (matching.succeeded()) {
                        List<List<String>> lists = matching.result();
                        dataOperation.guangdiantongOperation(newpath, lists).setHandler(result -> {
                            if (result.succeeded()) {
                                List<AdData> adDataList = result.result();
                                List<JsonArray> adlist = Transform.adDatatoJsonArrayList(adDataList);
                                adResponse(context, adlist, finnalfilename);
                            } else {
                                logger.error("广点通数据读取失败------->" + result.cause().toString());
                                serviceError(context, Json.encodePrettily(new JsonObject().put("data", "广点通数据读取失败")));
                            }
                        });
                    } else {
                        logger.error("匹配数据读取失败------->" + matching.cause().toString());
                        serviceError(context, Json.encodePrettily(new JsonObject().put("data", "匹配读取失败")));
                    }
                });
            } else {
                logger.error("文件错误------->" + rs.cause().toString());
                serviceError(context, Json.encodePrettily(new JsonObject().put("data", "文件错误")));
            }
        });
    }

    /**
     * 4399数据处理
     *
     * @param context
     */
    private void uploadFourThreeHandler(RoutingContext context) {
        Set<FileUpload> fileUploads = context.fileUploads();
        String filename = "";
        String uploadfilename = "";
        for (FileUpload f : fileUploads) {
            filename = f.fileName();
            uploadfilename = f.uploadedFileName();
        }
        String finnalfilename = filename;
        fileClassification(uploadfilename, filename).setHandler(rs -> {
            if (rs.succeeded()) {
                String newpath = rs.result();
                getMatchingData().setHandler(matching -> {
                    if (matching.succeeded()) {
                        List<List<String>> lists = matching.result();
                        dataOperation.fourthreeOperation(newpath, lists).setHandler(result -> {
                            if (result.succeeded()) {
                                List<AdData> adDataList = result.result();
                                List<JsonArray> adlist = Transform.adDatatoJsonArrayList(adDataList);
                                adResponse(context, adlist, finnalfilename);
                            } else {
                                logger.error("4399数据读取失败------->" + result.cause().toString());
                                serviceError(context, Json.encodePrettily(new JsonObject().put("data", "4399数据读取失败")));
                            }
                        });
                    } else {
                        logger.error("匹配数据读取失败------->" + matching.cause().toString());
                        serviceError(context, Json.encodePrettily(new JsonObject().put("data", "匹配读取失败")));
                    }
                });
            } else {
                logger.error("文件错误------->" + rs.cause().toString());
                serviceError(context, Json.encodePrettily(new JsonObject().put("data", "文件错误")));
            }
        });
    }


    /**
     * 移信数据处理
     *
     * @param context
     */
    private void uploadYixinHandler(RoutingContext context) {
        ExcelRead excelRead = new ExcelRead();
        Set<FileUpload> fileUploads = context.fileUploads();
        String filename = "";
        String uploadfilename = "";
        for (FileUpload f : fileUploads) {
            filename = f.fileName();
            uploadfilename = f.uploadedFileName();
        }
        String finnalfilename = filename;
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
                        } else {
                            jsonArray.add(innerlist.get(j));
                        }
                    }
                    jsonArrayList.add(jsonArray);
                }
                adResponse(context, jsonArrayList, finnalfilename);
            } else {
                logger.error("文件路径出错------->" + rs.cause().toString());
                serviceError(context, Json.encodePrettily(new JsonObject().put("data", "文件路径出错")));
            }
        });
    }

    /**
     * 在广告数据插入数据库前筛选处理
     *
     * @param context       RoutingContext
     * @param jsonArrayList List<JsonArray>
     */
    private void adResponse(RoutingContext context, List<JsonArray> jsonArrayList, String filename) {
        List list1 = Judgement.getMismatch(jsonArrayList);
        jsonArrayList = Judgement.removeUseless(jsonArrayList);
        String message;
        if (list1.size() == 0) {
            message = filename + "    数据已成功上传，并未发现异常~";
        } else {
            message = filename + "    第" + list1 + "行数据发现异常，请检查上传文件~";
        }

        advertisementService.batchWithParams(jdbcClient, SqlStatement.INSERT_ADVERTISEMENT, jsonArrayList).setHandler(result -> {
            if (result.succeeded()) {
                advertisementService.removeRepeat(jdbcClient, SqlStatement.REPEAT_ID_ADVERTISEMENT, SqlStatement.DEL_ID_ADVERTISEMENT).setHandler(result2 -> {
                    if (result2.succeeded()) {
                        context.response().setStatusCode(200).end(Json.encodePrettily(new JsonObject().put("data", message)));
                    } else {
                        logger.error("广告数据格式有错误,去重失败------->" + result2.cause().toString());
                        serviceError(context, Json.encodePrettily(new JsonObject().put("data", "请联系管理员。")));
                    }
                });


            } else {
                logger.error("广告数据格式有错误,插入失败------->" + result.cause().toString());
                serviceError(context, Json.encodePrettily(new JsonObject().put("data", "广告数据格式有错误")));
            }
        });
    }

    /**
     * 处理数据总表下载请求
     *
     * @param context
     */
    private void DownloadFileHandler(RoutingContext context) {
        FileSystem fileSystem = vertx.fileSystem();
        JsonObject name = context.getBodyAsJson().getJsonObject("name");
        String project_name=name.getString("project_name");
        JsonArray list=name.getJsonArray("applist");
        List applist=new ArrayList();
        for (int i=0;i<list.size();i++){
            applist.add(list.getJsonObject(i).getString("app_name"));
        }
        String endtime = context.getBodyAsJson().getString("end");
        String starttime = context.getBodyAsJson().getString("start");
        List arrayList = context.getBodyAsJson().getJsonArray("list").getList();
        List<String> datess;
        try {
            datess = Transform.getBetweenDate(starttime, endtime);
        } catch (Exception e) {
            serviceError(context, Json.encodePrettily(new JsonObject().put("data", "请选择正确的时间段")));
            return;
        }
        Integer longstarttime = Transform.transForMilliSecondByTim(starttime, "yyyy-MM-dd");
        Integer longendtime = Transform.transForMilliSecondByTim(endtime, "yyyy-MM-dd");
        List<String> dates = datess;
        String filename = starttime + "_" + endtime +"project_name" +"_.xls";
        if (new File(TemplateFile.DOWNLOAD_FILE_PATH + filename).exists()) {
            fileSystem.deleteBlocking(TemplateFile.DOWNLOAD_FILE_PATH + filename);
        }
        if (!new File(TemplateFile.DOWNLOAD_FILE_PATH).exists()) {
            new File(TemplateFile.DOWNLOAD_FILE_PATH).mkdir();
        }
        fileSystem.copy(TemplateFile.TEMPLATE_FILE_PATH, TemplateFile.DOWNLOAD_FILE_PATH + filename, rs -> {
            StringBuilder builder = new StringBuilder();
            List<Long> longdates = new ArrayList<>();
            for (int i = 0; i < dates.size(); i++) {
                Integer datetime = Transform.transForMilliSecondByTim(dates.get(i), "yyyy-MM-dd");
                builder.append(datetime);
                if (i != dates.size() - 1) {
                    builder.append(",");
                }
                longdates.add(i, (long) datetime);
            }
            advertisementService.findDatasWithParams(jdbcClient, SqlStatement.SELECT_USERDATA, new JsonArray().add(longstarttime).add(longendtime)).setHandler(listAsyncResult -> {
                if (listAsyncResult.succeeded()) {
                    List<JsonObject> userlist = listAsyncResult.result();
                    advertisementService.findDatasWithParams(jdbcClient, SqlStatement.SELECT_ADDATA, new JsonArray().add(longstarttime).add(longendtime)).setHandler(listAsyncResult1 -> {
                        if (listAsyncResult1.succeeded()) {
                            List<JsonObject> adlist = listAsyncResult1.result();
                            vertx.executeBlocking(handler1 -> {
                                //                            List<TotalVO> list1 = dataOperation.listToTotalVO(userlist, adlist, longdates, name);  //修改总表添加新渠道  DataOperation  859行
                                //                            List<List> listList = dataOperation.listToTotalVOToList(list1);
                                List<TotalVO> list1 = dataOperation.listToTotalVO2(userlist, adlist, longdates, project_name,applist);  //修改总表添加新渠道  DataOperation  859行
                                List<List> listList = dataOperation.listToTotalVOToList1(list1);
                                excelWrite.writeall2(TemplateFile.DOWNLOAD_FILE_PATH + filename, 0, 0, 3, listList, project_name);
                                try {
                                    Transform.removeRow(TemplateFile.DOWNLOAD_FILE_PATH + filename, arrayList);
                                    //Transform.deleteRow(TemplateFile.DOWNLOAD_FILE_PATH + filename);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                handler1.complete();
                            }, handler2 -> {
                                if (handler2.succeeded()) {
                                    logger.info(TemplateFile.DOWNLOAD_FILE_PATH + filename);
                                    context.response().putHeader("mark", "wenjian").
                                            putHeader("Content-Disposition", "filename=aaa.xls").
                                            putHeader("Content-Type", "application/octet-stream").
                                            putHeader("statusText", "wenjian").
                                            setStatusMessage("wenjian").
                                            sendFile(TemplateFile.DOWNLOAD_FILE_PATH + filename);
                                } else {
                                    logger.error("文件操作失败------->" + listAsyncResult.cause());
                                    serviceError(context, Json.encodePrettily(new JsonObject().put("data", "文件写入失败~")));
                                }
                            });
                        } else {
                            logger.error("在数据库中查找用户数据时发生错误------->" + listAsyncResult.cause());
                            serviceError(context, Json.encodePrettily(new JsonObject().put("data", "获取数据失败请联系管理员")));
                        }
                    });
                } else {
                    logger.error("在数据库中查找游戏数据时发生错误------->" + listAsyncResult.cause());
                    serviceError(context, Json.encodePrettily(new JsonObject().put("data", "获取数据失败请联系管理员")));
                }
            });
        });
    }

    /**
     * 处理下载收益表
     *
     * @param context
     */
    private void DownloadARPUFileHandler(RoutingContext context) {
        String starttime;
        String endtime;
        JsonObject applist;
        String name;//to do
        try {
            applist=context.getBodyAsJson().getJsonObject("name");
            starttime = context.request().getParam("start");
            endtime = context.request().getParam("end");
        } catch (Exception E) {
            context.response().setStatusCode(404).end();
            return;
        }
        List app_name=new ArrayList();
        name=applist.getString("project_name");
        JsonArray apps=applist.getJsonArray("applist");
        for (int i=0;i<apps.size();i++){
            app_name.add(apps.getJsonObject(i).getString("app_name"));
        }
        FileSystem fileSystem = vertx.fileSystem();
        List<String> datess = new ArrayList<>();
        try {
            datess = Transform.getBetweenDate(starttime, endtime);
        } catch (Exception e) {
            serviceError(context, Json.encodePrettily(new JsonObject().put("data", "请选择正确的时间段")));
            return;
        }
        List<String> datesss = datess;
        Integer longstarttime = Transform.transForMilliSecondByTim(starttime, "yyyy-MM-dd");
        Integer longendtime = Transform.transForMilliSecondByTim(endtime, "yyyy-MM-dd");

        List<String> dates = datess;
        String filename = starttime + "_" + endtime + ".xls";
        if (new File(TemplateFile.DOWNLOAD_FILE_PATH + filename).exists()) {
            fileSystem.deleteBlocking(TemplateFile.DOWNLOAD_FILE_PATH + filename);
        }
        if (!new File(TemplateFile.DOWNLOAD_FILE_PATH).exists()) {
            new File(TemplateFile.DOWNLOAD_FILE_PATH).mkdir();
        }
        fileSystem.copy(TemplateFile.TEMPLATE_ARPU_FILE_PATH, TemplateFile.DOWNLOAD_FILE_PATH + filename, rs -> {
            StringBuilder builder = new StringBuilder();
            List<Long> longdates = new ArrayList<>();
            for (int i = 0; i < dates.size(); i++) {
                Integer datetime = Transform.transForMilliSecondByTim(dates.get(i), "yyyy-MM-dd");
                builder.append(datetime);
                if (i != dates.size() - 1) {
                    builder.append(",");
                }
                longdates.add(i, (long) datetime);
            }
            advertisementService.findDatasWithParams(jdbcClient, SqlStatement.SELECT_USERDATA_arpu_withoutname, new JsonArray().add(longstarttime).add(longendtime)).setHandler(userListAsyncResult -> {
                if (userListAsyncResult.succeeded()) {
                    advertisementService.findDatasWithParams(jdbcClient, SqlStatement.SELECT_ADDATA_arpu_withoutname, new JsonArray().add(longstarttime).add(longendtime)).setHandler(adListAsyncResult -> {
                        if (adListAsyncResult.succeeded()) {
                            List<JsonObject> userlist = userListAsyncResult.result();
                            List<JsonObject> adlist = adListAsyncResult.result();
                            vertx.executeBlocking(handler1 -> {
                                    List<List<ArpuData>> arpuData_lists = InitConf.getAllArpuData1(userlist, adlist, longdates, app_name);
                                    List<List> outerlist = new ArrayList<>();
                                    for (int i = 0; i < arpuData_lists.size(); i++) {
                                        List<String> innerlist = new ArrayList<>();
                                        List<ArpuData> arpu_list = arpuData_lists.get(i);
                                        Double total_earned = 0.0;
                                        Double total_user = 0.0;
                                        Double total_arpu = 0.0;
                                        for (int j = 0; j < arpu_list.size(); j++) {
                                            ArpuData arpuData = arpu_list.get(j);
                                            if (j == 0) {
                                                innerlist.add(datesss.get(i) + "");
                                                innerlist.add(total_earned.toString());
                                                innerlist.add(total_user.toString());
                                                innerlist.add(total_arpu.toString());
                                            }
                                            innerlist.add(Judgement.formatDouble2(arpuData.getToutiao_earned()) + "");
                                            innerlist.add(Judgement.formatDouble2(arpuData.getChannel_earned()) + "");
                                            innerlist.add(Judgement.formatDouble2(arpuData.getGdt_earned()) + "");
                                            innerlist.add(Judgement.formatDouble2(arpuData.getAll_earned()) + "");
                                            innerlist.add(arpuData.getActive_user() + "");
                                            innerlist.add(Judgement.formatDouble3(arpuData.getToutiao_arpu()) + "");
                                            innerlist.add(Judgement.formatDouble3(arpuData.getChannel_arpu()) + "");
                                            innerlist.add(Judgement.formatDouble3(arpuData.getGdt_arpu()) + "");
                                            innerlist.add(Judgement.formatDouble3(arpuData.getAll_arpu()) + "");
                                            total_earned = total_earned + arpuData.getAll_earned();
                                            total_user = total_user + arpuData.getActive_user();
                                        }
                                        innerlist.set(1, Judgement.formatDouble2(total_earned) + "");
                                        innerlist.set(2, Judgement.formatDouble2(total_user) + "");
                                        innerlist.set(3, Judgement.NonScientificNotation(Judgement.formatDouble2(total_earned / total_user) + ""));
                                        outerlist.add(innerlist);
                                    }
                                    excelWrite.writeall(TemplateFile.DOWNLOAD_FILE_PATH + filename, 0, 0, 2, outerlist, name);
                                handler1.complete(Future.succeededFuture());
                            }, handler2 -> {
                                if (handler2.succeeded()) {
                                    logger.info(TemplateFile.DOWNLOAD_FILE_PATH + filename);
                                    context.response().
                                            putHeader("Content-Disposition", "filename=aaa.xls").
                                            putHeader("Content-Type", "application/octet-stream;charset=UTF-8").
                                            sendFile(TemplateFile.DOWNLOAD_FILE_PATH + filename);
                                } else {
                                    serviceError(context, handler2.cause().toString());
                                }
                            });
                        } else {
                            adListAsyncResult.cause();
                        }
                    });
                } else {
                    userListAsyncResult.cause();
                }
            });

        });

    }

    /**
     * 获取游戏名
     *
     * @param context
     */
    private void nameHandler(RoutingContext context) {
        advertisementService.findone(jdbcClient, SqlStatement.SELECT_GAME_NAME, "name").setHandler(rs -> {
            if (rs.succeeded()) {
                context.response().putHeader("content-type", "application/json").end(Json.encode(rs.result()));
            } else {
                context.response().setStatusCode(404).end();
            }
        });
    }

    /**
     * api广告数据获取请求
     *
     * @param context
     */
    private void FindDatasHandler(RoutingContext context) {
        FileSystem fileSystem = vertx.fileSystem();
        String url = context.request().absoluteURI();
        String ur = "";
        if (url.indexOf("=") != -1) {
            ur = url.substring(url.indexOf("=") + 1);
        } else {
            ur = url.substring(url.indexOf("data/") + 5);
        }
        if (ur.split("_").length < 2) {
            badRequest(context);
            return;
        }
        String starttime = ur.split("_")[0];
        String endtime = ur.split("_")[1];
        Integer longstarttime = Transform.transForMilliSecondByTim(starttime, "yyyy-MM-dd");
        Integer longendtime = Transform.transForMilliSecondByTim(endtime, "yyyy-MM-dd");
        String name = Transform.getURLDecoderString(ur.split("_")[2]);
        List<String> datess = new ArrayList<>();
        try {
            datess = Transform.getBetweenDate(starttime, endtime);
        } catch (Exception e) {
            badRequest(context);
            return;
        }
        List<String> dates = datess;
        if (dates.size() == 0) {
            badRequest(context);
            return;
        }
        String filename = starttime + "_" + endtime + "_" + name + ".xls";
        if (new File(TemplateFile.DOWNLOAD_FILE_PATH + filename).exists()) {
            fileSystem.deleteBlocking(TemplateFile.DOWNLOAD_FILE_PATH + filename);
        }
        fileSystem.copy(TemplateFile.TEMPLATE_FILE_PATH, TemplateFile.DOWNLOAD_FILE_PATH + filename, rs -> {
            advertisementService.findDatasWithParams(jdbcClient, SqlStatement.SELECT_ADDATA1, new JsonArray().add(longstarttime).add(longendtime).add(name)).setHandler(listAsyncResult -> {
                if (listAsyncResult.succeeded()) {
                    List<JsonObject> adlist = listAsyncResult.result();
                    advertisementService.findDatasWithParams(jdbcClient, SqlStatement.SELECT_USERDATA1, new JsonArray().add(longstarttime).add(longendtime).add(name)).setHandler(listAsyncResult1 -> {
                        if (listAsyncResult1.succeeded()) {
                            List<JsonObject> userlist = listAsyncResult1.result();
                            // 时间戳转时间
                            for (int e = 0; e < adlist.size(); e++) {
                                adlist.get(e).put("date", Transform.transForDate(adlist.get(e).getInteger("date")));
                            }
                            JsonObject jsonObject = new JsonObject().put("code", 20000).put("data", adlist);
                            context.response().setStatusCode(200).end(Json.encodePrettily(jsonObject));
                        } else {
                            logger.error("在数据库中查找用户数据时发生错误------->" + listAsyncResult.cause());
                            serviceError(context, Json.encodePrettily(new JsonObject().put("data", "获取数据失败请联系管理员")));
                        }
                    });
                } else {
                    logger.error("在数据库中查找游戏数据时发生错误------->" + listAsyncResult.cause());
                    serviceError(context, Json.encodePrettily(new JsonObject().put("data", "获取数据失败请联系管理员")));
                }
            });
        });
    }

    /**
     * api获取游戏名
     *
     * @param context
     */
    private void namesHandler(RoutingContext context) {
        advertisementService.findone(jdbcClient, SqlStatement.SELECT_GAME_NAME, "name").setHandler(rs -> {
            if (rs.succeeded()) {
                JsonObject names = new JsonObject();
                names.put("code", 20000);
                List namess = new ArrayList();
                String[] gamename = rs.result().getString("gamename").split(",");
                for (int i = 0; i < gamename.length; i++) {
                    namess.add(gamename[i]);
                }
                names.put("data", namess);
                context.response().setStatusCode(200).end(Json.encode(names));
            } else {
                logger.error("获取游戏名失败---------》", rs.cause());
                context.response().setStatusCode(404).end();
            }
        });
    }

    /**
     * 查找用户数据
     *
     * @param context
     */
    private void userDataHandler(RoutingContext context) {
        String starttime = context.request().getParam("starttime");
        String endtime = context.request().getParam("endtime");
        Long start = 0L;
        Long end = 0L;
        try {
            start = new SimpleDateFormat("yyyy-MM-dd").parse(starttime).getTime() / 1000;
            end = new SimpleDateFormat("yyyy-MM-dd").parse(endtime).getTime() / 1000;
        } catch (Exception e) {
            badRequest(context);
        }
        advertisementService.findDatasWithParams(jdbcClient, SqlStatement.SELECT_USERDATA_BY_TIME, new JsonArray().add(start).add(end)).setHandler(result -> {
            if (result.succeeded()) {
                List<JsonObject> jsonObjects = result.result();
                JsonArray jsonArray = new JsonArray();
                for (int i = 0; i < jsonObjects.size(); i++) {
                    Date date1 = new Date();
                    date1.setTime(1000L * jsonObjects.get(i).getLong("date"));
                    String date = new SimpleDateFormat("yyyy-MM-dd").format(date1);
                    jsonObjects.get(i).put("date", date);
                    jsonArray.add(jsonObjects.get(i));
                }
                context.response().setStatusCode(200).end(Json.encodePrettily(new JsonObject().put("code", 20000).put("data", jsonArray)));
            } else {
                badRequest(context);
            }
        });
    }

    /**
     * API移信数据获取
     *
     * @param context
     */
    private void yixinHandler(RoutingContext context) {
        String starttime = context.request().getParam("starttime");
        String endtime = context.request().getParam("endtime");
        dataOperation = new DataOperation(vertx);
        getMatchingData().setHandler(matching -> {
            if (matching.succeeded()) {
                List<List<String>> lists = matching.result();
                dataOperation.FindYixin(starttime, endtime, lists).setHandler(rs -> {
                    if (rs.succeeded()) {
                        List<AdData> adDataList = rs.result();
                        List<JsonArray> adlist = Transform.adDatatoJsonArrayList(adDataList);
                        List<JsonObject> jsonObjects = new ArrayList<>();
                        for (JsonArray jsonArray : adlist) {
                            JsonObject jsonObject = new JsonObject();
                            jsonObject.put("date", Transform.transForDate(jsonArray.getInteger(0)));
                            jsonObject.put("app_name", jsonArray.getString(1));
                            jsonObject.put("channel", jsonArray.getString(2));
                            jsonObject.put("advertising_type", jsonArray.getString(3));
                            jsonObject.put("earned", jsonArray.getDouble(4));
                            jsonObject.put("ecpm", jsonArray.getDouble(5));
                            jsonObject.put("click_rate", jsonArray.getDouble(6));
                            jsonObject.put("impression", jsonArray.getInteger(7));
                            jsonObject.put("click", jsonArray.getInteger(8));
                            jsonObject.put("fill_rate", jsonArray.getDouble(9));
                            jsonObject.put("platform", jsonArray.getString(10));
                            jsonObject.put("note", jsonArray.getString(11));
                            jsonObject.put("sdk_name", jsonArray.getString(12));
                            jsonObjects.add(jsonObject);
                        }
                        JsonObject jsonObject = new JsonObject().put("code", 20000).put("data", jsonObjects);
                        context.response().setStatusCode(200).end(Json.encodePrettily(jsonObject));
                    } else {
                        serviceError(context, Json.encodePrettily(new JsonObject().put("data", "移信数据读取失败")));
                    }
                });
            } else {
                logger.error("匹配数据读取失败------->" + matching.cause().toString());
                serviceError(context, Json.encodePrettily(new JsonObject().put("data", "匹配读取失败")));
            }
        });
    }

    /**
     * api广告数据获取请求
     *
     * @param context
     */
    private void TestHandler(RoutingContext context) {
        List date = new ArrayList();
        date.add("2018-11-1");
        date.add("2018-11-2");
        date.add("2018-11-3");
        List henfu = new ArrayList();
        henfu.add("300");
        henfu.add("600");
        henfu.add("800");
        List kaipin = new ArrayList();
        kaipin.add("200");
        kaipin.add("400");
        kaipin.add("800");
        List chapin = new ArrayList();
        chapin.add("100");
        chapin.add("400");
        chapin.add("300");
        List shipin = new ArrayList();
        shipin.add("900");
        shipin.add("200");
        shipin.add("700");
        List adlist = new ArrayList();
        adlist.add(date);
        adlist.add(henfu);
        adlist.add(kaipin);
        adlist.add(chapin);
        adlist.add(shipin);
        JsonObject jsonObject = new JsonObject().put("code", 20000).put("data", adlist);
        context.response().setStatusCode(200).end(Json.encodePrettily(jsonObject));
    }

    /**
     * 上传缓存文件分类
     *
     * @param path 文件上传路径
     * @return 文件分类路径
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
     * 获取渠道名  游戏名  广告类型
     *
     * @return
     */
    private Future<List<List<String>>> getMatchingData() {
        Future<List<List<String>>> future = Future.future();
        List<String> gamelist = new ArrayList<>();
        List<String> channellist = new ArrayList<>();
        List<String> adtypelist = new ArrayList<>();
        List<String> game_marklist = new ArrayList<>();
        Boolean sqllists = CacheList.AD_TYPE == null || CacheList.APP_NAME == null || CacheList.APP_MARK == null || CacheList.CHANNEL == null;
        if (!sqllists) {
            List<List<String>> constantlists = new ArrayList<>();
            constantlists.add(0, CacheList.APP_NAME);
            constantlists.add(1, CacheList.CHANNEL);
            constantlists.add(2, CacheList.AD_TYPE);
            constantlists.add(3, CacheList.APP_MARK);
            future.complete(constantlists);
            return future;
        }
        advertisementService.query(jdbcClient, SqlStatement.SELECT_GAME_NAME).setHandler(game -> {
            if (game.succeeded()) {
                List<JsonObject> jsonObjects = game.result();
                for (int i = 0; i < jsonObjects.size(); i++) {
                    String name = jsonObjects.get(i).getString("name");
                    gamelist.add(i, name);
                }
                advertisementService.query(jdbcClient, SqlStatement.SELECT_CHANNEL_NAME).setHandler(channel -> {
                    if (channel.succeeded()) {
                        List<JsonObject> jsonObjects1 = channel.result();
                        for (int i = 0; i < jsonObjects1.size(); i++) {
                            String name = jsonObjects1.get(i).getString("name");
                            String gamemark = jsonObjects1.get(i).getString("program_mark");
                            game_marklist.add(i, gamemark);
                            channellist.add(i, name);
                        }
                        advertisementService.query(jdbcClient, SqlStatement.SELECT_ADTYPE_NAME).setHandler(adtype -> {
                            if (adtype.succeeded()) {
                                List<JsonObject> jsonObjects11 = adtype.result();
                                for (int i = 0; i < jsonObjects11.size(); i++) {
                                    String name = jsonObjects11.get(i).getString("name");
                                    adtypelist.add(i, name);
                                }
                                List<List<String>> lists = new ArrayList<>();
                                lists.add(0, gamelist);
                                lists.add(1, channellist);
                                lists.add(2, adtypelist);
                                lists.add(3, game_marklist);
                                CacheList.APP_NAME = gamelist;
                                CacheList.CHANNEL = channellist;
                                CacheList.AD_TYPE = adtypelist;
                                CacheList.APP_MARK = game_marklist;
                                future.complete(lists);
                            } else {
                                logger.error("获取广告类型失败" + adtype.cause().toString());
                                future.failed();
                            }
                        });
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

    /**
     * 200
     */
    private void operationSuccess(RoutingContext context, JsonObject jsonObject) {
        context.response().setStatusCode(200).end(Json.encodePrettily(jsonObject));
    }

    /**
     * 503
     */
    private void serviceError(RoutingContext context, String string) {
        context.response().setStatusCode(503).end(string);
    }

    /**
     * 400
     *
     * @param context
     */
    private void badRequest(RoutingContext context) {
        context.response().setStatusCode(400).end();
    }

    /**
     * API 添加渠道
     *
     * @param context
     */
    private void addChannel(RoutingContext context) {
        String name = context.request().getParam("name");
        String program_mark = context.request().getParam("program_mark");
        String note = context.request().getParam("note");
        if (name == null || program_mark == null || note == null || name.length() == 0 || program_mark.length() == 0 || note.length() == 0) {
            badRequest(context);
            return;
        }
        advertisementService.update(jdbcClient, SqlStatement.INSERT_CHANNEL, new JsonArray().add(name).add(program_mark).add(note)).setHandler(rs -> {
            if (rs.succeeded()) {
                CacheOpertion.removeCacheLists();
                context.response().setStatusCode(200).end(Json.encodePrettily(new JsonObject().put("code", 20000)));
            } else {
                logger.error("在数据库中查找渠道数据时发生错误------->" + rs.cause());
                serviceError(context, Json.encodePrettily(new JsonObject().put("data", "获取数据失败请联系管理员")));
            }
        });
    }

    /**
     * API删除渠道
     *
     * @param context
     */
    private void delChannel(RoutingContext context) {
        Integer id = -1;
        try {
            id = Integer.valueOf(context.request().getParam("id"));
        } catch (Exception e) {
            badRequest(context);
            return;
        }
        advertisementService.update(jdbcClient, SqlStatement.DELETE_CHANNEL, new JsonArray().add(id)).setHandler(rs -> {
            if (rs.succeeded()) {
                CacheOpertion.removeCacheLists();
                context.response().setStatusCode(200).end(Json.encodePrettily(new JsonObject().put("code", 20000)));
            } else {
                logger.error("在数据库中查找渠道数据时发生错误------->" + rs.cause());
                serviceError(context, Json.encodePrettily(new JsonObject().put("code", 20000).put("data", "获取数据失败请联系管理员")));
            }
        });
    }

    /**
     * API更新渠道
     *
     * @param context
     */
    private void updateChannel(RoutingContext context) {
        String name = "";
        String program_mark = "";
        String note = "";
        Integer id = -1;
        try {
            name = context.getBodyAsJson().getString("name");
            program_mark = context.getBodyAsJson().getString("program_mark");
            note = context.getBodyAsJson().getString("note");
            id = Integer.valueOf(context.request().getParam("id"));
        } catch (Exception e) {
            name = context.request().getParam("name");
            program_mark = context.request().getParam("program_mark");
            note = context.request().getParam("note");
            id = Integer.valueOf(context.request().getParam("id"));
        }
        if (name == null || program_mark == null || note == null || name.length() == 0 || program_mark.length() == 0 || note.length() == 0 || id == -1) {
            badRequest(context);
            return;
        }
        advertisementService.update(jdbcClient, SqlStatement.UPDATE_CHANNEL, new JsonArray().add(name).add(program_mark).add(note).add(id)).setHandler(rs -> {
            if (rs.succeeded()) {
                CacheOpertion.removeCacheLists();
                context.response().setStatusCode(200).end(Json.encodePrettily(new JsonObject().put("code", 20000)));
            } else {
                logger.error("在数据库中查找渠道数据时发生错误------->" + rs.cause());
                serviceError(context, Json.encodePrettily(new JsonObject().put("code", 20000).put("data", "获取数据失败请联系管理员")));
            }
        });
    }

    /**
     * API获取渠道
     *
     * @param context
     */
    private void findChannel(RoutingContext context) {
        if (CacheList.CHANNEL_JSON != null) {
            List<JsonObject> jsonObjects = CacheList.CHANNEL_JSON;
            JsonObject json = new JsonObject().put("code", 20000).put("data", jsonObjects);
            context.response().setStatusCode(200).end(Json.encodePrettily(json));
            return;
        }
        advertisementService.query(jdbcClient, SqlStatement.SELECT_CHANNEL).setHandler(conn -> {
            if (conn.succeeded()) {
                List<JsonObject> jsonObjects = conn.result();
                JsonObject json = new JsonObject().put("code", 20000).put("data", jsonObjects);
                CacheList.CHANNEL_JSON = jsonObjects;
                context.response().setStatusCode(200).end(Json.encodePrettily(json));
            } else {
                logger.error("在数据库中查找渠道数据时发生错误------->" + conn.cause());
                serviceError(context, Json.encodePrettily(new JsonObject().put("data", "获取数据失败请联系管理员")));
            }
        });
    }

    /**
     * API添加游戏
     *
     * @param context
     */
    private void addApp(RoutingContext context) {
        String name = context.request().getParam("name");
        String system = context.request().getParam("system");
        String icon = context.request().getParam("icon");
        String project = context.request().getParam("project");
        if (name == null || system == null || icon == null || project == null || name.length() == 0 || system.length() == 0 || icon.length() == 0 || project.length() == 0) {
            badRequest(context);
            return;
        }
        advertisementService.update(jdbcClient, SqlStatement.INSERT_APP, new JsonArray().add(name).add(system).add(icon).add(project)).setHandler(rs -> {
            if (rs.succeeded()) {
                CacheOpertion.removeCacheLists();
                context.response().setStatusCode(201).end(Json.encodePrettily(new JsonObject().put("code", 20000)));
            } else {
                logger.error("在数据库中插入数据时发生错误------->" + rs.cause());
                serviceError(context, Json.encodePrettily(new JsonObject().put("data", "获取数据失败请联系管理员")));
            }
        });
    }

    /**
     * API删除游戏
     *
     * @param context
     */
    private void delApp(RoutingContext context) {
        Integer id = -1;
        try {
            id = Integer.valueOf(context.request().getParam("id"));
        } catch (Exception e) {
            badRequest(context);
            return;
        }
        advertisementService.update(jdbcClient, SqlStatement.DELETE_APP, new JsonArray().add(id)).setHandler(rs -> {
            if (rs.succeeded()) {
                CacheOpertion.removeCacheLists();
                context.response().setStatusCode(200).end(Json.encodePrettily(new JsonObject().put("code", 20000)));
            } else {
                logger.error("在数据库中查找渠道数据时发生错误------->" + rs.cause());
                serviceError(context, Json.encodePrettily(new JsonObject().put("data", "获取数据失败请联系管理员")));
            }
        });
    }

    /**
     * API更新游戏
     *
     * @param context
     */
    private void updateApp(RoutingContext context) {
        String name = "";
        String system = "";
        String icon = "";
        String project = "";
        Integer id = -1;
        try {
            id = Integer.valueOf(context.request().getParam("id"));
            name = context.getBodyAsJson().getString("name");
            system = context.getBodyAsJson().getString("system");
            icon = context.getBodyAsJson().getString("icon");
            project = context.getBodyAsJson().getString("project");
        } catch (Exception e) {

            id = Integer.valueOf(context.request().getParam("id"));
            name = context.request().getParam("name");
            system = context.request().getParam("system");
            icon = context.request().getParam("icon");
            project = context.request().getParam("project");
        }
        if (name == null || system == null || icon == null || project == null || name.length() == 0 || system.length() == 0 || icon.length() == 0 || project.length() == 0 || id == -1) {
            badRequest(context);
            return;
        }
        advertisementService.update(jdbcClient, SqlStatement.UPDATE_APP, new JsonArray().add(name).add(system).add(icon).add(project).add(id)).setHandler(rs -> {
            if (rs.succeeded()) {
                CacheOpertion.removeCacheLists();
                context.response().setStatusCode(200).end(Json.encodePrettily(new JsonObject().put("code", 20000)));
            } else {
                logger.error("在数据库中查找渠道数据时发生错误------->" + rs.cause());
                serviceError(context, Json.encodePrettily(new JsonObject().put("data", "获取数据失败请联系管理员")));
            }
        });
    }

    /**
     * API获取游戏
     *
     * @param context
     */
    private void findApp(RoutingContext context) {
        if (CacheList.APP_NAME_JSON != null) {
            List<JsonObject> jsonObjects = CacheList.APP_NAME_JSON;
            JsonObject json = new JsonObject().put("code", 20000).put("data", jsonObjects);
            context.response().setStatusCode(200).end(Json.encodePrettily(json));
            return;
        }
        advertisementService.query(jdbcClient, SqlStatement.SELECT_APP).setHandler(conn -> {
            if (conn.succeeded()) {
                List<JsonObject> jsonObjects = conn.result();
                JsonObject json = new JsonObject().put("code", 20000).put("data", jsonObjects);
                CacheList.APP_NAME_JSON = jsonObjects;
                context.response().setStatusCode(200).end(Json.encodePrettily(json));
            } else {
                logger.error("在数据库中查找数据时发生错误------->" + conn.cause());
                serviceError(context, Json.encodePrettily(new JsonObject().put("data", "获取数据失败请联系管理员")));
            }
        });
    }

    /**
     * API获取游戏
     *
     * @param context
     */
    private void findProjectListHandler(RoutingContext context) {
        advertisementService.query(jdbcClient, SqlStatement.SELECT_APP).setHandler(conn -> {
            if (conn.succeeded()) {
                List<JsonObject> jsonObjects = conn.result();
                List<JsonObject> list=new ArrayList<>();
                for (int i=0;i<jsonObjects.size();i++){
                    Boolean flag=true;
                    for (int j=0;j<list.size();j++){
                        if (list.get(j).getString("project_name").equals(jsonObjects.get(i).getString("project"))){
                            flag=false;
                            list.get(j).getJsonArray("applist").add(new JsonObject().put("app_name",jsonObjects.get(i).getString("name")));
                            break;
                        }
                    }
                    if (flag){
                        JsonObject jsonObject1=new JsonObject().put("project_name",jsonObjects.get(i).getString("project"));
                        JsonArray inner=new JsonArray();
                        JsonObject jsonObject2=new JsonObject().put("app_name",jsonObjects.get(i).getString("name"));
                        inner.add(jsonObject2);
                        jsonObject1.put("applist",inner);
                        list.add(jsonObject1);
                    }
                }
                JsonObject json = new JsonObject().put("code", 20000).put("data", list);
                context.response().putHeader("Content-Type","application/javascript; charset=UTF-8").setStatusCode(200).end(Json.encodePrettily(json));
            } else {
                logger.error("在数据库中查找数据时发生错误------->" + conn.cause());
                serviceError(context, Json.encodePrettily(new JsonObject().put("data", "获取数据失败请联系管理员")));
            }
        });
    }


    /**
     * 资源列表获取游戏
     *
     * @param context
     */
    private void findApp_perms(RoutingContext context) {
        if (CacheList.APP_NAME_JSON != null) {
            List<JsonObject> jsonObjects = CacheList.APP_NAME_JSON;
            List<JsonObject> permList = new ArrayList<>();
            for (int i = 0; i < jsonObjects.size(); i++) {
                JsonObject newJsonObject = new JsonObject();
                newJsonObject.put("id", jsonObjects.get(i).getInteger("id"));
                newJsonObject.put("label", jsonObjects.get(i).getString("name"));
                permList.add(newJsonObject);
            }
            JsonObject json = new JsonObject().put("code", 20000).put("data", permList);
            context.response().setStatusCode(200).end(Json.encodePrettily(json));
            return;
        }
        advertisementService.query(jdbcClient, SqlStatement.SELECT_APP).setHandler(conn -> {
            if (conn.succeeded()) {
                List<JsonObject> jsonObjects = conn.result();
                List<JsonObject> permList = new ArrayList<>();
                for (int i = 0; i < jsonObjects.size(); i++) {
                    JsonObject newJsonObject = new JsonObject();
                    newJsonObject.put("id", jsonObjects.get(i).getInteger("id"));
                    newJsonObject.put("label", jsonObjects.get(i).getString("name"));
                    permList.add(newJsonObject);
                }
                JsonObject json = new JsonObject().put("code", 20000).put("data", permList);
                CacheList.APP_NAME_JSON = jsonObjects;
                context.response().setStatusCode(200).end(Json.encodePrettily(json));
            } else {
                logger.error("在数据库中查找数据时发生错误------->" + conn.cause());
                serviceError(context, Json.encodePrettily(new JsonObject().put("data", "获取数据失败请联系管理员")));
            }
        });
    }

    /**
     * API添加广告类型
     *
     * @param context
     */
    private void addAdType(RoutingContext context) {
        String name = context.request().getParam("name");
        String program_mark = context.request().getParam("program_mark");
        String note = context.request().getParam("note");
        String introduce = context.request().getParam("introduce");
        if (name == null || program_mark == null || note == null || introduce == null || name.length() == 0 || program_mark.length() == 0 || note.length() == 0 || introduce.length() == 0) {
            badRequest(context);
            return;
        }
        advertisementService.update(jdbcClient, SqlStatement.INSERT_ADTYPE, new JsonArray().add(name).add(program_mark).add(note).add(introduce)).setHandler(rs -> {
            if (rs.succeeded()) {
                CacheOpertion.removeCacheLists();
                context.response().setStatusCode(201).end(Json.encodePrettily(new JsonObject().put("code", 20000)));
            } else {
                logger.error("在数据库中插入数据时发生错误------->" + rs.cause());
                serviceError(context, Json.encodePrettily(new JsonObject().put("data", "获取数据失败请联系管理员")));
            }
        });
    }

    /**
     * API删除广告类型
     *
     * @param context
     */
    private void delAdType(RoutingContext context) {
        Integer id = -1;
        try {
            id = Integer.valueOf(context.request().getParam("id"));
        } catch (Exception e) {
            badRequest(context);
            return;
        }
        advertisementService.update(jdbcClient, SqlStatement.DELETE_ADTYPE, new JsonArray().add(id)).setHandler(rs -> {
            if (rs.succeeded()) {
                CacheOpertion.removeCacheLists();
                context.response().setStatusCode(200).end(Json.encodePrettily(new JsonObject().put("code", 20000)));
            } else {
                logger.error("在数据库中查找渠道数据时发生错误------->" + rs.cause());
                serviceError(context, Json.encodePrettily(new JsonObject().put("data", "获取数据失败请联系管理员")));
            }
        });
    }

    /**
     * API更新广告类型
     *
     * @param context
     */
    private void updateAdType(RoutingContext context) {
        String name = "";
        String program_mark = "";
        String note = "";
        String introduce = "";
        Integer id = -1;
        try {
            name = context.getBodyAsJson().getString("name");
            program_mark = context.getBodyAsJson().getString("program_mark");
            note = context.getBodyAsJson().getString("note");
            id = Integer.valueOf(context.request().getParam("id"));
            introduce = context.getBodyAsJson().getString("introduce");
        } catch (Exception e) {
            name = context.request().getParam("name");
            program_mark = context.request().getParam("program_mark");
            note = context.request().getParam("note");
            id = Integer.valueOf(context.request().getParam("id"));
            introduce = context.request().getParam("introduce");
        }
        if (name == null || program_mark == null || note == null || introduce == null || name.length() == 0 || program_mark.length() == 0 || note.length() == 0 || introduce.length() == 0 || id == -1) {
            badRequest(context);
            return;
        }
        advertisementService.update(jdbcClient, SqlStatement.UPDATE_ADTYPE, new JsonArray().add(name).add(program_mark).add(note).add(introduce).add(id)).setHandler(rs -> {
            if (rs.succeeded()) {
                CacheOpertion.removeCacheLists();
                context.response().setStatusCode(200).end(Json.encodePrettily(new JsonObject().put("code", 20000)));
            } else {
                logger.error("在数据库中查找渠道数据时发生错误------->" + rs.cause());
                serviceError(context, Json.encodePrettily(new JsonObject().put("data", "获取数据失败请联系管理员")));
            }
        });
    }

    /**
     * API获取游戏广告类型
     *
     * @param context
     */
    private void findAdType(RoutingContext context) {
        if (CacheList.AD_TYPE_JSON != null) {
            List<JsonObject> jsonObjects = CacheList.AD_TYPE_JSON;
            JsonObject json = new JsonObject().put("code", 20000).put("data", jsonObjects);
            context.response().setStatusCode(200).end(Json.encodePrettily(json));
            return;
        }
        advertisementService.query(jdbcClient, SqlStatement.SELEECT_ADTYPE).setHandler(conn -> {
            if (conn.succeeded()) {
                List<JsonObject> jsonObjects = conn.result();
                JsonObject json = new JsonObject().put("code", 20000).put("data", jsonObjects);
                CacheList.AD_TYPE_JSON = jsonObjects;
                context.response().setStatusCode(200).end(Json.encodePrettily(json));
            } else {
                logger.error("在数据库中查找数据时发生错误------->" + conn.cause());
                serviceError(context, Json.encodePrettily(new JsonObject().put("data", "获取数据失败请联系管理员")));
            }
        });
    }

    /**
     * API添加游戏
     *
     * @param context
     */
    private void addProject(RoutingContext context) {
        String project_name = context.getBodyAsJson().getString("project_name");
        String preheat = context.getBodyAsJson().getString("preheat");
        String schedule = context.getBodyAsJson().getString("schedule");
        String compete_good = context.getBodyAsJson().getString("compete_good");
        String version_plan = context.getBodyAsJson().getString("version_plan");
        String note = context.getBodyAsJson().getString("note");
        JsonArray applist = context.getBodyAsJson().getJsonArray("applist");
        List<JsonArray> jsonArrays = new ArrayList<>();
        for (int i = 0; i < applist.size(); i++) {
            JsonArray jsonArray = new JsonArray();
            JsonObject jsonObject = applist.getJsonObject(i);
            jsonArray.add(project_name).add(jsonObject.getString("package_name")).add(jsonObject.getString("channel"));
            jsonArrays.add(jsonArray);
        }
//        System.out.println(project_name);
//        System.out.println(preheat);
//        System.out.println(schedule);
//        System.out.println(compete_good);
//        System.out.println(version_plan);
//        System.out.println(note);
//        System.out.println(applist);
        JsonArray insert = new JsonArray();
        insert.add(project_name).add(preheat).add(schedule).add(compete_good).add(version_plan).add(note);
        JsonArray repeat = new JsonArray();
        repeat.add(project_name);
        advertisementService.findDatasWithParams(jdbcClient, SqlStatement.REPEAT_PROJECT, repeat).setHandler(result -> {
            if (result.succeeded()) {
                List<JsonObject> list = result.result();
                Integer count = list.get(0).getInteger("count(*)");
                if (count == 0) {
                    advertisementService.update(jdbcClient, SqlStatement.INSERT_PROJECT, insert).setHandler(rs1 -> {
                        advertisementService.batchWithParams(jdbcClient, SqlStatement.INSERT_PROJECT_LIST, jsonArrays).setHandler(rs2 -> {
                            if (rs1.succeeded() && rs2.succeeded()) {
                                context.response().setStatusCode(201).end(Json.encodePrettily(new JsonObject().put("code", 20000).put("data", "ok")));
                            } else {
                                logger.error("在数据库中插入数据时发生错误------->" + rs1.cause());
                                serviceError(context, Json.encodePrettily(new JsonObject().put("data", "请重新添加")));
                            }
                        });
                    });
                } else {
                    logger.error("项目名重复");
                    context.response().setStatusCode(201).end(Json.encodePrettily(new JsonObject().put("code", 20000).put("data", "repeat")));
                }
            } else {
                logger.error("在数据库中插入数据时发生错误------->" + result.cause());
                serviceError(context, Json.encodePrettily(new JsonObject().put("data", "请重新添加")));
            }
        });
    }

    /**
     * API删除游戏
     *
     * @param context
     */
    private void delProject(RoutingContext context) {
        String id = context.request().getParam("id");
        String project_name = context.getBodyAsJson().getString("project_name");
        advertisementService.update(jdbcClient, SqlStatement.DELETE_PROJECT_LIST, new JsonArray().add(project_name)).setHandler(result1 -> {
            advertisementService.update(jdbcClient, SqlStatement.DELETE_PROJECT, new JsonArray().add(id)).setHandler(result2 -> {
                if (result1.succeeded()&&result2.succeeded()){
                    context.response().setStatusCode(200).end(Json.encodePrettily(new JsonObject().put("code", 20000)));
                }else {
                    if (result1.failed()) {
                        logger.error("数据库发生错误------->" + result1.cause());
                    } else if (result2.failed()) {
                        logger.error("数据库发生错误------->" + result2.cause());
                    }
                    serviceError(context, Json.encodePrettily(new JsonObject().put("data", "刷新试试")));
                }
            });
        });
    }

    /**
     * API更新游戏
     *
     * @param context
     */
    private void updateProject(RoutingContext context) {
        String id = context.request().getParam("id");
        String project_name = context.getBodyAsJson().getString("project_name");
        String preheat = context.getBodyAsJson().getString("preheat");
        String schedule = context.getBodyAsJson().getString("schedule");
        String compete_good = context.getBodyAsJson().getString("compete_good");
        String version_plan = context.getBodyAsJson().getString("version_plan");
        String note = context.getBodyAsJson().getString("note");
        JsonArray applist = context.getBodyAsJson().getJsonArray("applist");
        List<JsonArray> jsonArrays = new ArrayList<>();
        for (int i = 0; i < applist.size(); i++) {
            JsonArray jsonArray = new JsonArray();
            JsonObject jsonObject = applist.getJsonObject(i);
            jsonArray.add(project_name).add(jsonObject.getString("package_name")).add(jsonObject.getString("channel"));
            jsonArrays.add(jsonArray);
        }
//        System.out.println(id);
//        System.out.println(project_name);
//        System.out.println(preheat);
//        System.out.println(schedule);
//        System.out.println(compete_good);
//        System.out.println(version_plan);
//        System.out.println(note);
//        System.out.println(applist);
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(project_name).add(preheat).add(schedule).add(compete_good).add(version_plan).add(note).add(id);
        advertisementService.update(jdbcClient, SqlStatement.DELETE_PROJECT_LIST, new JsonArray().add(project_name)).setHandler(result1 -> {
            advertisementService.batchWithParams(jdbcClient, SqlStatement.INSERT_PROJECT_LIST, jsonArrays).setHandler(result2 -> {
                advertisementService.update(jdbcClient, SqlStatement.UPDATE_PROJECT, jsonArray).setHandler(result3 -> {
                    if (result1.succeeded() && result2.succeeded() && result3.succeeded()) {
                        context.response().setStatusCode(200).end(Json.encodePrettily(new JsonObject().put("code", 20000)));
                    } else {
                        if (result1.failed()) {
                            logger.error("数据库发生错误------->" + result1.cause());
                        } else if (result2.failed()) {
                            logger.error("数据库发生错误------->" + result2.cause());
                        } else if (result3.failed()) {
                            logger.error("数据库发生错误------->" + result3.cause());
                        }
                        serviceError(context, Json.encodePrettily(new JsonObject().put("data", "刷新试试")));
                    }
                });
            });
        });
    }

    /**
     * API获取游戏
     *
     * @param context
     */
    private void findProject(RoutingContext context) {
        advertisementService.query(jdbcClient, SqlStatement.SELECT_PROJECT).setHandler(result1 -> {
            advertisementService.query(jdbcClient, SqlStatement.SELECT_PROJECT_LIST).setHandler(result2 -> {
                if (result1.succeeded() && result2.succeeded()) {
                    List<JsonObject> project = result1.result();
                    List<JsonObject> projectlist = result2.result();
                    for (int i = 0; i < project.size(); i++) {
                        List<JsonObject> jsonObjects = new ArrayList<>();
                        String project_name = project.get(i).getString("project_name");
                        Iterator it = projectlist.iterator();
                        while (it.hasNext()) {
                            JsonObject jsonObject = JsonObject.mapFrom(it.next());
                            if (project_name.equals(jsonObject.getString("project_name"))) {
                                jsonObjects.add(jsonObject);
                                it.remove();
                            }
                        }
                        project.get(i).put("applist", jsonObjects);
                    }
                    context.response().putHeader("Content-Type","application/javascript; charset=UTF-8").setStatusCode(200).end(Json.encodePrettily(new JsonObject().put("code", 20000).put("data", project)));
                } else {
                    if (result1.failed()) {
                        logger.error("查询数据库时发生错误------->" + result1.cause());
                    }
                    if (result2.failed()) {
                        logger.error("查询数据库时发生错误------->" + result2.cause());
                    }
                    serviceError(context, Json.encodePrettily(new JsonObject().put("data", "刷新试试")));
                }
            });
        });
    }
}
