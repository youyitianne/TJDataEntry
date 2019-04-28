package http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import database.ConfigConstants;
import database.Method;
import database.MissionDatabaseService;
import database.SqlConstants;
import http.Util.DeleteUtil;
import http.apk.ApkInfo;
import http.apk.ApkUtil;
import http.apk.IconUtil;
import http.filetransmit.FileUtil;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
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
    private static final String domainName = "http://192.168.1.144:8091";
    private static final String synchronize = "";//锁
    private static final String ResourcePath = "http://192.168.1.144:8091/file?path=";
    private String download_cache_path="";


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

        router.get("/fileInfo").handler(this::listHandler);//文件列表
        router.get("/fileInfo/limit").handler(this::listLimitHandler);//文件列表     分页
        router.post("/keystore").handler(this::uploadHandler_keystore);//上传keystore
        router.get("/keystoreInfo").handler(this::listHandler_keystore);//展示keystore信息
        router.patch("/updateKeystore").handler(this::updateKeystoreHandler);//更新keystore
        router.delete("/keystore").handler(this::deleteHandler_keystore);//删除上传keystore
        //apk包上传
        router.post("/apkFile").handler(this::apkFileHandler);//apk上传记录保存
        router.get("/apkFile").handler(this::ListapkFileHandler);//获取上传apk记录列表
        router.delete("/apkFile").handler(this::deleteapkFileHandler);//删除apk
        router.post("/apkInfo").handler(this::getApkInfoHandler);//解析apk
        router.post("/onlineAPK").handler(this::setOnlineAPKHandler);//设置上线包

        router.get("/addsdkguid").handler(this::addSDKGuidHandler);
        router.get("/addsdkguid1").handler(this::addSDKGuidHandler1);
        router.get("/addsdkguid2").handler(this::addSDKGuidHandler2);
        router.get("/syncFile").handler(this::syncFileHandler);

        HashMap<ConfigConstants, String> conf = method.loadConfigQueries();
        sql = method.loadSqlQueries();
        download_cache_path=conf.get(ConfigConstants.DOWNLOAD_CACHE_PATH);
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
     * 设置apk为上线包
     * @param context
     */
    private void setOnlineAPKHandler(RoutingContext context) {
        String apkguid=context.getBodyAsJson().getString("apkguid");
        String sdkguid=context.getBodyAsJson().getString("sdkguid");
        String online=context.getBodyAsJson().getString("online");
        dbService.queryWithoutParam("UPDATE `mission`.`apk_file_info` SET `online` = '0' WHERE `sdkguid` = '"+sdkguid+"';",resetResult->{
            if (resetResult.succeeded()){
                if ("0".equals(online)){
                    Service3000(context,"");
                    return;
                }
                dbService.queryWithoutParam("UPDATE `mission`.`apk_file_info` SET `online` = '1' WHERE `apkguid` = '"+apkguid+"';",setResult->{
                    if (setResult.succeeded()){
                        Service3000(context,"");
                    }else {
                        Service3001(context,setResult.cause().toString());
                    }
                });
            }else {
                Service3001(context,resetResult.cause().toString());
            }
        });
    }

    /**
     * 删除keystore
     *
     * @param context
     */
    private void deleteHandler_keystore(RoutingContext context) {
        String guid = context.request().getParam("apkguid");
        dbService.query("DELETE FROM `mission`.`keystoreinfo` WHERE keystoreguid = ?;", new JsonArray().add(guid), delResult -> {
            if (delResult.succeeded()) {
                logger.info("删除成功");
                context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3000)));
            } else {
                logger.error("删除错误", delResult.cause());
                Service3001(context, "删除错误");
            }
        });
    }

    private void syncFileHandler(RoutingContext context) {
        dbService.listDatas("SELECT * FROM mission.fileinfo;", listResult -> {
            if (listResult.succeeded()) {
                List<JsonObject> jsonObjectList = listResult.result();
                List<JsonArray> jsonArrayList = new ArrayList<>();
                for (int i = 0; i < jsonObjectList.size(); i++) {
                    if (jsonObjectList.get(i).getString("filemd5") == null) {
                        jsonArrayList.add(new JsonArray()
                                .add(jsonObjectList.get(i).getString("fileguid"))
                                .add(jsonObjectList.get(i).getString("date"))
                                .add(jsonObjectList.get(i).getString("fileguid")));
                    }
                }
                dbService.batch("UPDATE `mission`.`fileinfo` SET `filemd5` = ?,`lastModifiedDate` = ? WHERE `fileguid` = ?;", jsonArrayList, batchResult -> {
                    if (batchResult.succeeded()) {
                        context.response().end("123");
                    } else {
                        logger.error("batch错误", batchResult.cause());
                        context.response().end(batchResult.cause().toString());
                    }
                });
            } else {
                logger.error("list错误", listResult.cause());
                context.response().end(listResult.cause().toString());
            }
        });
    }

    private void addSDKGuidHandler2(RoutingContext context) {
        dbService.listDatas("SELECT a.apkguid,a.time,a.packageName,a.channelName,a.fileName,b.sdkguid FROM mission.apk_file_info a join " +
                        "advertisement.project_list b on a.packageName=b.package_name and a.channelName = b.channel;",
                listResult -> {
                    if (listResult.succeeded()) {
                        List<JsonObject> list = listResult.result();
                        List<JsonArray> jsonArrayList = new ArrayList<>();
                        for (int i = 0; i < list.size(); i++) {
                            jsonArrayList.add(new JsonArray().add(list.get(i).getString("sdkguid")).add(list.get(i).getString("apkguid")));
                        }
                        dbService.batch("UPDATE `mission`.`apk_file_info` SET `sdkguid` = ? WHERE `apkguid` = ?;", jsonArrayList, batchResult -> {
                            if (batchResult.succeeded()) {
                                context.response().end("12345");
                            } else {
                                logger.error("", batchResult.cause());
                            }
                        });
                    } else {
                        logger.error("", listResult.cause());
                    }
                });
    }

    private void addSDKGuidHandler1(RoutingContext context) {
        dbService.listDatas("SELECT a.`id`,a.`project_name`,a.`package_name`,a.`channel`,b.sdkguid " +
                        "FROM `advertisement`.`project_list` a join tjsdk.project_config_info b on a.package_name=b.package_name and a.channel = b.channel_mark and b.publish = 0;",
                listResult -> {
                    if (listResult.succeeded()) {
                        List<JsonObject> list = listResult.result();
                        List<JsonArray> jsonArrayList = new ArrayList<>();
                        for (int i = 0; i < list.size(); i++) {
                            jsonArrayList.add(new JsonArray().add(list.get(i).getString("sdkguid")).add(list.get(i).getInteger("id")));
                        }
                        dbService.batch("UPDATE `advertisement`.`project_list` SET `sdkguid` = ? WHERE `id` = ?;", jsonArrayList, batchResult -> {
                            if (batchResult.succeeded()) {
                                context.response().end("12345");
                            } else {
                                logger.error("", batchResult.cause());
                            }
                        });
                    } else {
                        logger.error("", listResult.cause());
                    }
                });
    }

    private void addSDKGuidHandler(RoutingContext context) {
        dbService.listDatas("select id,sdkguid FROM tjsdk.project_config_info;", listResult -> {
            if (listResult.succeeded()) {
                List<JsonObject> list = listResult.result();
                List<JsonArray> jsonArrayList = new ArrayList<>();
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getString("sdkguid") == null) {
                        jsonArrayList.add(new JsonArray().add(UUID.randomUUID().toString()).add(list.get(i).getInteger("id")));
                    }
                }
                dbService.batch("update tjsdk.project_config_info set sdkguid=? where id = ?;", jsonArrayList, batchResult -> {
                    if (batchResult.succeeded()) {
                        context.response().end("12345");
                    } else {
                        logger.error("", batchResult.cause());
                    }
                });
            } else {
                logger.error("", listResult.cause());
            }
        });
    }

    /**
     * 解析apk包
     *
     * @param context
     */
    private void getApkInfoHandler(RoutingContext context) {
        String apkguid = context.getBodyAsJson().getString("apkguid");
        try {
            String runtime = System.getProperty("user.dir");
            File apkfile = FileUtil.downloadFile(apkguid, download_cache_path+ File.separator);
            if (!apkfile.exists()) {
                //不存在
                //下载文件失败
                logger.info("下载文件失败");
                Service3001(context, "下载文件失败");
                return;
            }
            String demo = runtime + File.separator + "Resource" + File.separator + "res" + File.separator + apkguid;
            ApkInfo apkInfo = new ApkUtil().getApkInfo(apkfile.getPath());
            if (apkInfo == null) {
                Service3001(context, "非法apk");
                return;
            }
            //生成临时图片名称，防止重复
            String iconCacheGUID = UUID.randomUUID().toString();
            //icon路径
            String icon = apkInfo.getApplicationIcon();
            String saveRootDirectory = download_cache_path + File.separator;

            new IconUtil().zipFileRead(icon, apkfile.getPath(), saveRootDirectory, iconCacheGUID);
            //解析icon路径
            String iconpath = saveRootDirectory + iconCacheGUID;
            File file = new File(iconpath);
            Map<String, Object> param = new HashMap<String, Object>();
            String res = FileUtil.postFile("http://192.168.1.144:8091/file", param, file);
            System.out.println(res);
            JSONObject result = JSONObject.parseObject(res);
            if ("3000".equals(result.getString("repcode"))) {
                //file.delete();//删除本地文件
                String iconguid = result.getJSONObject("data").getString("guid");
                System.out.println(iconguid);
                logger.info("apk解析成功:\n" + apkInfo);
                context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3000).put("data", new JsonObject().put("icon", iconguid).put("basic", JsonObject.mapFrom(apkInfo)))));
            } else {
                Service3001(context, "保存icon失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Service3001(context, e.toString());
        }
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
     * 请求成功返回
     *
     * @param context
     * @param error
     */
    private void Service3000(RoutingContext context, String error) {
        context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3000).put("data", error)));
    }

    /**
     * 删除apk文件
     *
     * @param context
     */
    private void deleteapkFileHandler(RoutingContext context) {
        String apkguid = context.request().getParam("apkguid");
        dbService.queryWithoutParam("DELETE FROM `mission`.`apk_file_info` WHERE apkguid = '" + apkguid + "';", apkFileInfoResult -> {
            if (apkFileInfoResult.succeeded()) {
                dbService.queryWithoutParam("DELETE FROM `mission`.`apk_parsing_info` WHERE apkguid = '" + apkguid + "';", apkPasingInfoResult -> {
                    if (apkFileInfoResult.succeeded()) {
                        try {
                            String response = DeleteUtil.doGetClient("http://192.168.1.144:8091/file?path=" + apkguid);
                            JSONObject jsonObject = JSONObject.parseObject(response);
                            if (jsonObject.getInteger("repcode") == 3000) {
                                context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3000)));
                            } else {
                                Service3001(context, "删除apkinfo失败");
                            }
                        } catch (Exception e) {
                            logger.error("删除apkinfo失败", e);
                            Service3001(context, "删除apkinfo失败");
                        }
                    } else {
                        logger.error("删除apkpasing失败", apkFileInfoResult.cause());
                        Service3001(context, "删除apkpasing失败");
                    }
                });
            } else {
                logger.error("删除apkinfo失败", apkFileInfoResult.cause());
                Service3001(context, "删除apkinfo失败");
            }
        });
    }

    /**
     * 获取apk包记录
     *
     * @param context
     */
    private void ListapkFileHandler(RoutingContext context) {
        Integer limit = 0;
        Integer page = 0;
        String sdkguid = context.request().getParam("sdkguid");
        try {
            limit = Integer.valueOf(context.request().getParam("limit"));
            page = Integer.valueOf(context.request().getParam("page"));
        } catch (Exception e) {
            logger.error("参数错误", e);
            context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3001).put("data", "参数错误")));
            return;
        }
        StringBuilder condition = new StringBuilder();

        condition.append(" where a.sdkguid = '" + sdkguid + "' ");
        StringBuilder limitsql = new StringBuilder();
        limitsql.append("  limit " + (page - 1) * limit + "," + limit + ";");
        dbService.listDatas("SELECT count(*) total FROM mission.apk_file_info a " + condition, countResult -> {
            if (countResult.succeeded()) {
                Integer total = countResult.result().get(0).getInteger("total");
                logger.info("SELECT * FROM mission.apk_file_info " + condition + " order by time desc " + limitsql);
                dbService.listDatas("SELECT a.apkguid,a.sdkguid,a.online,b.filename,b.date time,\n" +
                        "c.applicationLable,c.features,c.impliedFeatures,c.launchableActivity,\n" +
                        "c.minSdkVersion,c.basic_packageName,c.sdkVersion,c.targetSdkVersion,\n" +
                        "c.usesPermissions,c.versionCode,c.versionName,c.localIconPath\n" +
                        "FROM mission.apk_file_info a left join mission.fileinfo b on a.apkguid=b.fileguid left join `mission`.`apk_parsing_info` c on a.apkguid = c.apkguid " + condition + " order by time desc " + limitsql, listResult -> {
                    if (listResult.succeeded()) {
                        Date date = new Date();
                        List<JsonObject> list = listResult.result();
                        for (int i = 0; i < list.size(); i++) {
                            Long time = 0L;
                            if (list.get(i).getString("time") != null) {
                                time = Long.valueOf(list.get(i).getString("time"));
                            }
                            String path = "";
                            if (list.get(i).getString("localIconPath") != null) {
                                path = ResourcePath + list.get(i).getString("localIconPath");
                            }
                            date.setTime(time);
                            list.get(i).put("date", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
                            list.get(i).put("iconPath", path);
                        }
                        context.response().setStatusCode(200).end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3000).put("total", total).put("data", list)));
                    } else {
                        logger.error("查询失败", listResult.cause());
                        context.response().setStatusCode(200).end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3001)));
                    }
                });
            } else {
                logger.error("查询总数失败", countResult.cause());
                logger.info("SELECT count(*) total FROM mission.apk_file_info " + condition);
                context.response().setStatusCode(200).end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3001)));
            }
        });
    }

    /**
     * apk记录保存
     *
     * @param context
     */
    private void apkFileHandler(RoutingContext context) {
        String apkguid = context.getBodyAsJson().getString("apkguid");
        String sdkguid = context.getBodyAsJson().getString("sdkguid");
        JsonObject basic = context.getBodyAsJson().getJsonObject("basic");
        String localIconPath = context.getBodyAsJson().getString("localIconPath");
        //应用程序标签
        String applicationLable = basic.getString("applicationLable");
        //特性
        String features = basic.getJsonArray("features").toString();
        //隐含的特征
        String impliedFeatures = basic.getJsonArray("impliedFeatures").toString();
        //可启动活动
        String launchableActivity = basic.getString("launchableActivity");
        //最小SDK版本
        String minSdkVersion = basic.getString("minSdkVersion");
        //包名
        String basic_packageName = basic.getString("packageName");
        //sdk版本
        String sdkVersion = basic.getString("sdkVersion");
        //目标版本
        String targetSdkVersion = basic.getString("targetSdkVersion");
        //用户权限
        String usesPermissions = basic.getJsonArray("usesPermissions").toString();
        //版本号
        String versionCode = basic.getString("versionCode");
        //班额本名
        String versionName = basic.getString("versionName");
        StringBuilder basic_sql = new StringBuilder();
        basic_sql.append("('" + apkguid + "','" + applicationLable + "','" + features + "','" + impliedFeatures + "','" + launchableActivity + "','" + minSdkVersion + "','" + basic_packageName + "','" + sdkVersion + "'," +
                "'" + targetSdkVersion + "','" + usesPermissions + "','" + versionCode + "','" + versionName + "','" + localIconPath + "')");

        dbService.queryWithoutParam("INSERT INTO `mission`.`apk_parsing_info`\n" +
                "(`apkguid`,`applicationLable`,`features`,`impliedFeatures`,`launchableActivity`,`minSdkVersion`,`basic_packageName`,`sdkVersion`,\n" +
                "`targetSdkVersion`,`usesPermissions`,`versionCode`,`versionName`,`localIconPath`)\n" +
                "VALUES " + basic_sql.toString(), queryResult -> {
            if (queryResult.succeeded()) {
                dbService.query("INSERT INTO `mission`.`apk_file_info`(`apkguid`,`sdkguid`)VALUES(?,?)", new JsonArray()
                        .add(apkguid)
                        .add(sdkguid), result -> {
                    if (result.succeeded()) {
                        logger.info("apk上传记录保存成功");
                        context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3000)));
                    } else {
                        logger.info("apk上传记录保存失败", result.cause());
                        context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3001)));
                    }
                });
            } else {
                logger.info("apk解析数据保存失败", queryResult.cause());
                context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3001)));
            }
        });
    }

    /**
     * keystore上传处理
     *
     * @param context
     */
    private void uploadHandler_keystore(RoutingContext context) {
        String guid = context.getBodyAsJson().getString("guid");
        String finalFilename = context.getBodyAsJson().getString("finalFilename");
        Long date = new Date().getTime();
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(guid).add(finalFilename).add(date);
        dbService.query(sql.get(SqlConstants.KEYSTORE_INSERT), new JsonArray().add(date).add(guid).add(finalFilename), result1 -> {
            if (result1.succeeded()) {
                logger.error("上传keystore成功");
                context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3000).put("data", finalFilename)));
            } else {
                logger.error("保存失败,请重新上传", result1.cause());
                context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3001).put("data", "保存失败,请重新上传")));
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
        String MD5 = context.getBodyAsJson().getString("MD5");
        String SHA1 = context.getBodyAsJson().getString("SHA1");
        String SHA256 = context.getBodyAsJson().getString("SHA256");
        logger.info(fileguid);
        logger.info(keystorePass);
        logger.info(keyaliasName);
        logger.info(keyaliasPass);
        logger.info(MD5);
        logger.info(SHA1);
        logger.info(SHA256);
        if (fileguid == null || keystorePass == null || keyaliasName == null || keyaliasPass == null || MD5 == null || SHA1 == null || SHA256 == null) {
            context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3001).put("data", "参数不完整！")));
            return;
        }
        dbService.query(sql.get(SqlConstants.KEYSTORE_UPDATE), new JsonArray()
                .add(keystorePass)
                .add(keyaliasName)
                .add(keyaliasPass)
                .add(MD5)
                .add(SHA1)
                .add(SHA256)
                .add(fileguid), result -> {
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
     * 获取所有上传文件信息
     *
     * @param context
     */
    private void listHandler(RoutingContext context) {
        dbService.listDatas(sql.get(SqlConstants.FILEINFO_LIST), result -> {
            if (result.succeeded()) {
                List<JsonObject> list = result.result();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                for (int i = 0; i < list.size(); i++) {
                    String date = list.get(i).getString("date");
                    Date date1 = new Date();
                    date1.setTime(Long.valueOf(date));
                    list.get(i).put("date1", sdf.format(date1));
                    list.get(i).put("path", domainName + "/file?path=" + list.get(i).getString("fileguid"));
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
     * 获取所有上传文件信息
     * 分页
     *
     * @param context
     */
    private void listLimitHandler(RoutingContext context) {
        Integer limit = 0;
        Integer page = 0;
        try {
            limit = Integer.valueOf(context.request().getParam("limit"));
            page = Integer.valueOf(context.request().getParam("page"));
        } catch (Exception e) {
            logger.error("参数错误");
            context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3001).put("data", "参数错误")));
            return;
        }
        StringBuilder limitsql = new StringBuilder();
        limitsql.append("  limit " + (page - 1) * limit + "," + limit + ";");
        dbService.listDatas("SELECT count(*) total FROM mission.fileinfo", countResult -> {
            if (countResult.succeeded()) {
                Integer total = countResult.result().get(0).getInteger("total");
                dbService.listDatas("SELECT * FROM mission.fileinfo order by date desc " + limitsql, result -> {
                    if (result.succeeded()) {
                        List<JsonObject> list = result.result();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        for (int i = 0; i < list.size(); i++) {
                            String date = list.get(i).getString("date");
                            Date date1 = new Date();
                            date1.setTime(Long.valueOf(date));
                            list.get(i).put("date1", sdf.format(date1));
                            list.get(i).put("path", domainName + "/file?path=" + list.get(i).getString("fileguid"));
                        }
                        logger.info("查询成功");
                        context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3000).put("total", total).put("data", list)));
                    } else {
                        logger.error("查询失败，刷新试试", result.cause());
                        context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3001).put("data", "查询失败,刷新试试！")));
                    }
                });
            } else {
                logger.error("查询总数失败", countResult.cause());
                context.response().setStatusCode(200).end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3001)));
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
                    list.get(i).put("path", domainName + "/file?path=" + list.get(i).getString("keystoreguid"));
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
