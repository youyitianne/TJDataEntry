package http;

import database.ConfigConstants;
import database.SdkDatabaseService;
import database.SqlConstants;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.KeyStoreOptions;
import io.vertx.ext.auth.PubSecKeyOptions;
import io.vertx.ext.auth.User;
import io.vertx.ext.auth.jwt.JWTAuth;
import io.vertx.ext.auth.jwt.JWTAuthOptions;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.*;
import io.vertx.ext.web.sstore.LocalSessionStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.Method;

import java.text.SimpleDateFormat;
import java.util.*;

public class SdkHttpVerticle extends AbstractVerticle {
    private Logger logger = LoggerFactory.getLogger(SdkHttpVerticle.class.getName());
    public static final String CONFIG_ACCOUNTDB_QUEUE = "accountdb.queue";
    private SdkDatabaseService dbService;
    private HashMap<SqlConstants, String> sql;
    private Method method = new Method();
    private String sdkDbQueue;
    private JWTAuthHandler jwtAuthHandler;
    private JWTAuth jwtAuth = null;
    private SdkOperationLog operationLog = new SdkOperationLog();
    private static final String domainName="http://192.168.1.144:8087";

    @Override
    public void start(Future<Void> startFuture) {
        sdkDbQueue = config().getString(CONFIG_ACCOUNTDB_QUEUE, "accountdb.queue");
        dbService = SdkDatabaseService.createProxy(vertx, CONFIG_ACCOUNTDB_QUEUE);
        Router router = Router.router(vertx);
        router.route().handler(BodyHandler.create());
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
        router.route().handler(BodyHandler.create());
        router.route().handler(CookieHandler.create());
        router.route().handler(SessionHandler.create(LocalSessionStore.create(vertx)));
        //router.route().handler(UserSessionHandler.create(authProvider));

        router.route().handler(this::operateHandler);

        router.get("/sdkapi").handler(this::getSDKHandler_new);
        router.get("/onesdkapi").handler(this::getSDKHandler);
        //项目配置表
        router.get("/api/projectconfig/:starttime/:endtime").handler(this::projectconfigListHandler);
        router.post("/api/projectconfig").handler(this::projectconfigCreateHandler);
        router.post("/api/projectconfig_pro").handler(this::projectconfigCreateHandler_pro);
        router.patch("/api/projectconfig/:id").handler(this::projectconfigUpdateHandler);
        router.get("/api/listtemplate").handler(this::listtemplateHandler);

        //sdk
        router.get("/api/sdk").handler(this::sdkListHandler);
        router.post("/api/sdk").handler(this::sdkCreateHandler);
        router.patch("/api/sdk/:id").handler(this::sdkUpdateHandler);
        //发布记录
        router.get("/api/projectconfig_publish").handler(this::projectconfigListPublishHandler);
        router.post("/api/publish_delete").handler(this::publishDeleteHandler);

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
     * 指定一张表
     * api获取SDK数据
     *
     * @param context
     */
    private void getSDKHandler(RoutingContext context) {
        String timestamp = context.request().getParam("timestamp");
        String app = context.request().getParam("app");
        String channel = context.request().getParam("channel");
        if (timestamp == null || app == null || channel == null) {
            JsonObject result = new JsonObject();
            result.put("message", "参数不完整");
            context.response().putHeader("Content-Type", "application/json; charset=utf-8").end(Json.encodePrettily(result));
            return;
        }
        apilist(sql.get(SqlConstants.API_PROJECT_LIST), sql.get(SqlConstants.API_SDK_LIST), context, new JsonArray().add(timestamp).add(app).add(channel));
    }

    /**
     * 最新的一张表
     * api获取SDK数据
     *
     * @param context
     */
    private void getSDKHandler_new(RoutingContext context) {
        String package_name = context.request().getParam("package_name").trim();
        String channel_mark = context.request().getParam("channel_mark").trim();
        if (package_name == null || channel_mark == null) {
            JsonObject result = new JsonObject();
            result.put("message", "参数不完整");
            context.response().putHeader("Content-Type", "text/plain; charset=utf-8").write(Json.encodePrettily(result)).end();
            //context.response().putHeader("Content-Type", "application/json; charset=utf-8").end(Json.encodePrettily(result));
            return;
        }
        apilist_new(sql.get(SqlConstants.API_PROJECT_LIST_NEW), sql.get(SqlConstants.API_SDK_LIST_NEW), context, new JsonArray().add(package_name).add(channel_mark), new JsonArray().add(channel_mark));

    }


    /**
     * 日志
     *
     * @param context
     */
    private void operateHandler(RoutingContext context) {
        JWTAuthHandler jwtAuthHandler = JWTAuthHandler.create(jwtAuth);
        jwtAuthHandler.parseCredentials(context, rs -> {
            if (rs.succeeded()) {
                JsonObject jsonObject = rs.result();
                jwtAuth.authenticate(new JsonObject()
                        .put("jwt", jsonObject.getString("jwt"))
                        .put("options", new JsonObject()
                                .put("ignoreExpiration", true)), user -> {
                    if (user.succeeded()) {
                        User user1 = user.result();
                        JsonObject userJson = user1.principal();
                        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                        Long date = new Date().getTime() / 1000;
                        String username = userJson.getString("username");
                        String path = context.request().uri();
                        if (path.contains("?")) {
                            path = path.substring(0, path.indexOf("?"));
                        }
                        operationLog.operationLog(sql.get(SqlConstants.OPERATION_LOG), dbService, context, path, context.request().method().toString(), username, time, context.request().getHeader("X-FORWARD-FOR"), date);
                    } else {
                        //身份验证
                        logger.error("身份验证", user.cause());
                    }
                });
            } else {
                //解析凭证失败
                logger.error("解析凭证失败", rs.cause());
            }
        });
        context.next();
    }

    /**
     * 删除已发布的配置表
     *
     * @param context
     */
    private void publishDeleteHandler(RoutingContext context) {
        Integer id = context.getBodyAsJson().getInteger("id");
        JsonArray form = context.getBodyAsJson().getJsonArray("paramter");
        List<JsonArray> list_delete = new ArrayList<>();
        for (int i = 0; i < form.size(); i++) {
            JsonArray jsonArray = new JsonArray();
            jsonArray.add(form.getJsonObject(i).getInteger("id"));
            list_delete.add(jsonArray);
        }
        dbService.query(sql.get(SqlConstants.PROJECT_CONFIG_INFORMATION_DELETE), new JsonArray().add(id), rs -> {
            if (rs.succeeded()) {
                dbService.batch(sql.get(SqlConstants.PROJECT_CONFIG_LIST_DELETE_BYID), list_delete, rs1 -> {
                    if (rs1.succeeded()) {
                        context.response().setStatusCode(200).end(Json.encodePrettily(new JsonObject().put("code", 20000)));
                    } else {
                        badRequest(context);
                    }
                });
            } else {
                badRequest(context);
            }
        });
    }

    /**
     * 获取已发布的配置表
     *
     * @param context
     */
    private void projectconfigListPublishHandler(RoutingContext context) {
        list(sql.get(SqlConstants.PROJECT_CONFIG_PUBLISH_LIST), sql.get(SqlConstants.PROJECT_CONFIG_PARAMTER_LIST), context);
    }

    /**
     * 获取所有有效的sdk模版
     *
     * @param context
     */
    private void listtemplateHandler(RoutingContext context) {
        dbService.listDatas(sql.get(SqlConstants.SDK_INFO_LIST), result -> {
            dbService.listDatas(sql.get(SqlConstants.SDK_LIST_LIST), result1 -> {
                if (result.succeeded() && result1.succeeded()) {
                    List<JsonObject> sdk_info = result.result();
                    List<JsonObject> sdk_list = result1.result();
                    JsonArray sdk_Template_Name_List = new JsonArray();
                    JsonArray sdk_Template_List = new JsonArray();
                    JsonArray select_List = new JsonArray();
                    for (int i = 0; i < sdk_info.size(); i++) {
                        if (sdk_info.get(i).getString("sdk_status").equals("0")) {
                            continue;
                        }
                        JsonObject template = new JsonObject();
                        String mark = sdk_info.get(i).getString("sdk_mark");
                        String name = sdk_info.get(i).getString("sdk_name");
                        template.put("keymark", mark);
//                        sdk_Template_Name_List.add(mark);
                        sdk_Template_Name_List.add(new JsonObject().put("mark", mark).put("name", name));
                        JsonArray sdkform = new JsonArray();
                        Iterator<JsonObject> it = sdk_list.iterator();
                        while (it.hasNext()) {
                            JsonObject jsonObject = it.next();
                            JsonObject sdk_template = new JsonObject();
                            String sdk_type = jsonObject.getString("sdk_type");
                            if (sdk_type.equals("1")) {   //多选框
                                JsonObject newjsonobject = new JsonObject();
                                newjsonobject.put("param_name", jsonObject.getString("sdk_paramter_name"));
                                newjsonobject.put("sdk_name", jsonObject.getString("sdk_mark"));
                                String[] list = jsonObject.getString("sdk_paramter").split(";");
                                JsonArray jsonArray = new JsonArray();
                                String default_value = "暂无";
                                for (int x = 0; x < list.length; x++) {
                                    if (x == 0) {
                                        default_value = list[x];
                                    }
                                    JsonObject jsonObject1 = new JsonObject().put("value", list[x]);
                                    jsonArray.add(jsonObject1);
                                }
                                JsonObject newjsonobject1 = new JsonObject();
                                newjsonobject.put("value", default_value);
                                newjsonobject.put("param", jsonArray);
                                select_List.add(newjsonobject);
                                it.remove();
                                continue;
                            }
                            if (jsonObject.getString("sdk_mark").equals(mark) && sdk_type.equals("0")) {
                                sdk_template.put("key", Long.toHexString(new Date().getTime()));
                                sdk_template.put("param_name", mark + "-" + jsonObject.getString("sdk_paramter_name"));
                                sdk_template.put("param_name1", jsonObject.getString("sdk_paramter_name"));
                                sdk_template.put("param", "");
                                sdk_template.put("note", jsonObject.getString("sdk_note"));
                                sdk_template.put("sdk_type", sdk_type);
                                sdkform.add(sdk_template);
                                it.remove();
                            }
                        }
                        template.put("keyform", sdkform);
                        sdk_Template_List.add(template);
                    }

                    context.response().setStatusCode(200).end(Json.encodePrettily(
                            new JsonObject().put("code", 20000)
                                    .put("name_list", sdk_Template_Name_List)
                                    .put("list", sdk_Template_List)
                                    .put("select_list", select_List)
                    ));
                } else {
                    if (result.failed()) {
                        logger.error("sdk_info 查询失败", result1.cause());
                    }
                    if (result1.failed()) {
                        logger.error("sdk_list 查询失败", result1.cause());
                    }
                    badRequest(context);
                }
            });
        });
    }


    /**
     * 创建sdk模版
     *
     * @param context
     */
    private void sdkCreateHandler(RoutingContext context) {
        String mark = context.getBodyAsJson().getString("mark");
        String name = context.getBodyAsJson().getString("name");
        String sdkstatus = context.getBodyAsJson().getString("sdkstatus");
        String version = context.getBodyAsJson().getString("version");
        JsonObject form = context.getBodyAsJson().getJsonObject("form");
        JsonArray domains = form.getJsonArray("domains");
        List<JsonArray> list = new ArrayList<>();
        for (int i = 0; i < domains.size(); i++) {
            String param_name = domains.getJsonObject(i).getString("sdk_paramter_name");
            String param = domains.getJsonObject(i).getString("sdk_paramter");
            String type = domains.getJsonObject(i).getString("sdk_type");
            JsonArray jsonArray = new JsonArray();
            jsonArray.add(mark).add(param_name).add(param).add(type);
            list.add(jsonArray);
        }
        dbService.query(sql.get(SqlConstants.SDK_INFO_INSERT), new JsonArray().add(name).add(version).add(mark).add(sdkstatus), result -> {
            dbService.batch(sql.get(SqlConstants.SDK_LIST_INSERT), list, result1 -> {
                if (result.succeeded() && result1.succeeded()) {
                    context.response().setStatusCode(200).end(Json.encodePrettily(new JsonObject().put("code", 20000)));
                } else {
                    if (result.failed()) {
                        logger.error("sdk_info 插入失败", result.cause());
                    }
                    if (result1.failed()) {
                        logger.error("sdk_list 插入失败", result1.cause());
                    }
                    badRequest(context);
                }
            });
        });
    }

    /**
     * 展示sdk模版
     *
     * @param context
     */
    private void sdkListHandler(RoutingContext context) {
        dbService.listDatas(sql.get(SqlConstants.SDK_INFO_LIST), result -> {
            dbService.listDatas(sql.get(SqlConstants.SDK_LIST_LIST), result1 -> {
                if (result.succeeded() && result1.succeeded()) {
                    List<JsonObject> sdk_info = result.result();
                    List<JsonObject> sdk_list = result1.result();
                    for (int i = 0; i < sdk_info.size(); i++) {
                        String mark = sdk_info.get(i).getString("sdk_mark");
                        JsonArray jsonArray = new JsonArray();
                        Iterator<JsonObject> it = sdk_list.iterator();
                        while (it.hasNext()) {
                            JsonObject jsonObject = it.next();
                            if (jsonObject.getString("sdk_mark").equals(mark)) {
                                jsonObject.put("key", Long.toHexString(new Date().getTime()));
                                jsonArray.add(jsonObject);
                                it.remove();
                            }
                        }
                        sdk_info.get(i).put("paramter", jsonArray);
                    }
                    Collections.reverse(sdk_info);
                    context.response().setStatusCode(200).end(Json.encodePrettily(new JsonObject().put("code", 20000).put("data", sdk_info)));
                } else {
                    if (result.failed()) {
                        logger.error("sdk_info 查询失败", result1.cause());
                    }
                    if (result1.failed()) {
                        logger.error("sdk_list 查询失败", result1.cause());
                    }
                    badRequest(context);
                }
            });
        });

    }

    /**
     * 更新sdk模版
     *
     * @param context
     */
    private void sdkUpdateHandler(RoutingContext context) {
        String id = context.request().getParam("id");
        String mark = context.getBodyAsJson().getString("mark");
        String name = context.getBodyAsJson().getString("name");
        String sdkstatus = context.getBodyAsJson().getString("sdkstatus");
        String version = context.getBodyAsJson().getString("version");
        JsonObject form = context.getBodyAsJson().getJsonObject("form");
        JsonArray domains = form.getJsonArray("domains");
        List<JsonArray> list = new ArrayList<>();
        for (int i = 0; i < domains.size(); i++) {
            String param_name = domains.getJsonObject(i).getString("sdk_paramter_name");
            String param = domains.getJsonObject(i).getString("sdk_paramter");
            String type = domains.getJsonObject(i).getString("sdk_type");
            JsonArray jsonArray = new JsonArray();
            jsonArray.add(mark).add(param_name).add(param).add(type);
            list.add(jsonArray);
        }


        dbService.query(sql.get(SqlConstants.SDK_INFO_UPDATE), new JsonArray().add(name).add(version).add(mark).add(sdkstatus).add(id), result -> {
            dbService.query(sql.get(SqlConstants.SDK_LIST_DELETE), new JsonArray().add(mark), result1 -> {
                dbService.batch(sql.get(SqlConstants.SDK_LIST_INSERT), list, result2 -> {
                    if (result.succeeded() && result1.succeeded() && result2.succeeded()) {
                        context.response().setStatusCode(200).end(Json.encodePrettily(new JsonObject().put("code", 20000)));
                    } else {
                        if (result.failed()) {
                            logger.error("sdk_info 更新失败", result.cause());
                        }
                        if (result1.failed()) {
                            logger.error("sdk_list 删除失败", result1.cause());
                        }
                        if (result2.failed()) {
                            logger.error("sdk_list 插入失败", result2.cause());
                        }
                        badRequest(context);
                    }
                });
            });
        });
    }

    /**
     * 更新项目配置
     *
     * @param context
     */
    private void projectconfigUpdateHandler(RoutingContext context) {
        JsonArray sdk_information = new JsonArray();
        JsonArray sdk_paramter_delete = new JsonArray();
        List<JsonArray> paramters = new ArrayList<>();
        String id = context.request().getParam("id");
        String time = context.getBodyAsJson().getString("timevalue");
        Long timevalue = 0L;
        try {
            timevalue = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").parse(time)).getTime();
        } catch (Exception e) {
            logger.error(e.toString());
            badRequest(context);
        }
        String app_name = context.getBodyAsJson().getString("app_name");
        String package_name = context.getBodyAsJson().getString("package_name");
        String channel_mark = context.getBodyAsJson().getString("channel_mark");
        String version_online_version = context.getBodyAsJson().getString("version_online_version");
        String version_update_version = context.getBodyAsJson().getString("version_update_version");
        String versioncode_online_version = context.getBodyAsJson().getString("versioncode_online_version");
        String versioncode_update_version = context.getBodyAsJson().getString("versioncode_update_version");
        String note = context.getBodyAsJson().getString("note");
        String sdkstatus = context.getBodyAsJson().getString("sdkstatus");
        String icon=context.getBodyAsJson().getString("icon");
        String splash=context.getBodyAsJson().getString("splash");
        JsonObject keystore=context.getBodyAsJson().getJsonObject("keystore");
        System.out.println(keystore);
        logger.info(icon);
        logger.info(splash);
        JsonObject jsonObject = context.getBodyAsJson().getJsonObject("form");
        JsonArray form = jsonObject.getJsonArray("domains");
        JsonArray select = jsonObject.getJsonArray("select");
        form = form.addAll(select);
        for (int i = 0; i < form.size(); i++) {
            String param_name = form.getJsonObject(i).getString("param_name");
            String param_value = form.getJsonObject(i).getString("param");
            String param_note = form.getJsonObject(i).getString("sdk_type");
            JsonArray array = new JsonArray();
            try {
                array.add(timevalue)
                        .add(app_name)
                        .add(channel_mark)
                        .add(param_name)
                        .add(param_value)
                        .add(param_note);
            } catch (Exception e) {
                logger.error(e.toString());
                badRequest(context);
            }
            paramters.add(i, array);
        }
        JsonArray checked = context.getBodyAsJson().getJsonArray("checked");
        StringBuilder checkbox = new StringBuilder();
        if (checked.size() > 0) {
            for (int i = 0; i < checked.size(); i++) {
                checkbox.append(checked.getString(i));
                if (i != checked.size() - 1) {
                    checkbox.append(",");
                }
            }
        } else {
            checkbox.append("暂无");
        }

        JsonArray second_checked = context.getBodyAsJson().getJsonArray("second_checked");
        StringBuilder second_checkbox = new StringBuilder();
        if (second_checked.size() > 0) {
            for (int i = 0; i < second_checked.size(); i++) {
                second_checkbox.append(second_checked.getString(i));
                if (i != second_checked.size() - 1) {
                    second_checkbox.append(",");
                }
            }
        } else {
            second_checkbox.append("暂无");
        }
        try {
            sdk_information.add(timevalue).add(app_name).add(package_name).add(version_online_version).add(version_update_version).add(versioncode_online_version).add(versioncode_update_version).add(note)
                    .add(channel_mark).add(sdkstatus).add(checkbox).add(second_checkbox).add(icon).add(splash)
                    .add(keystore.getString("filepath")).add(keystore.getString("keystorePass")).add(keystore.getString("keyaliasName")).add(keystore.getString("keyaliasPass")).add(id);
            sdk_paramter_delete.add(timevalue).add(app_name).add(channel_mark);
        } catch (Exception e) {
            logger.error(e.toString());
            badRequest(context);
            return;
        }
        dbService.query(sql.get(SqlConstants.PROJECT_CONFIG_INFORMATION_UPDATE), sdk_information, result -> {
            dbService.query(sql.get(SqlConstants.PROJECT_CONFIG_PARAMTER_DELETE), sdk_paramter_delete, result1 -> {
                if (result1.succeeded()) {
                    dbService.batch(sql.get(SqlConstants.PROJECT_CONFIG_PARAMTER_INSERT), paramters, result2 -> {
                        if (result.succeeded() && result2.succeeded()) {
                            context.response().setStatusCode(200).end(Json.encodePrettily(new JsonObject().put("code", 20000)));
                        } else {
                            if (result.failed()) {
                                logger.error("SDK_INFORMATION_UPDATE 更新失败-------》" + result.cause().toString());
                            }
                            if (result2.failed()) {
                                logger.error("SDK_PARAMTER_INSERT 插入失败-------》" + result2.cause().toString());
                            }
                            badRequest(context);
                        }
                    });
                } else {
                    if (result1.failed()) {
                        logger.error("SDK_PARAMTER_DELETE 删除失败-------》" + result1.cause().toString());
                    }
                    badRequest(context);
                }
            });
        });
    }

    /**
     * 展示项目配置
     *
     * @param context
     */
    private void projectconfigListHandler(RoutingContext context) {
        list(sql.get(SqlConstants.PROJECT_CONFIG_INFORMATION_LIST), sql.get(SqlConstants.PROJECT_CONFIG_PARAMTER_LIST), context);
    }

    /**
     * 发布项目配置
     *
     * @param context
     */
    private void projectconfigCreateHandler(RoutingContext context) {
        JsonArray sdk_information = new JsonArray();
        List<JsonArray> paramters = new ArrayList<>();
        Long timevalue = context.getBodyAsJson().getLong("timevalue");
        String app_name = context.getBodyAsJson().getString("app_name");
        String package_name = context.getBodyAsJson().getString("package_name").trim();
        String channel_mark = context.getBodyAsJson().getString("channel_mark").trim();
        String version_online_version = context.getBodyAsJson().getString("version_online_version");
        String version_update_version = context.getBodyAsJson().getString("version_update_version");
        String versioncode_online_version = context.getBodyAsJson().getString("versioncode_online_version");
        String versioncode_update_version = context.getBodyAsJson().getString("versioncode_update_version");
        String note = context.getBodyAsJson().getString("note");
//        String publisher = context.getBodyAsJson().getString("publisher");
        String sdkstatus = context.getBodyAsJson().getString("sdkstatus");
        String publish = context.getBodyAsJson().getString("publish");
        String icon=context.getBodyAsJson().getString("icon");
        String splash=context.getBodyAsJson().getString("splash");
        if (icon==null){
            icon=null;
        }
        if (splash==null){
            splash=null;
        }
        JsonObject keystore=context.getBodyAsJson().getJsonObject("keystore");
        System.out.println(keystore);
        logger.info(icon);
        logger.info(splash);

        JsonObject jsonObject = context.getBodyAsJson().getJsonObject("form");
        JsonArray select = jsonObject.getJsonArray("select");
        JsonArray form = jsonObject.getJsonArray("domains");
        if (select != null) {
            form = form.addAll(select);
        }
        for (int i = 0; i < form.size(); i++) {
            String param_name = form.getJsonObject(i).getString("param_name");
            String param_value = form.getJsonObject(i).getString("param");
            String param_note = form.getJsonObject(i).getString("sdk_type");
            JsonArray array = new JsonArray();
            try {
                array.add(timevalue)
                        .add(app_name)
                        .add(channel_mark)
                        .add(param_name)
                        .add(param_value)
                        .add(param_note);
            } catch (Exception e) {
                logger.error(e.toString());
                badRequest(context);
            }
            paramters.add(i, array);
        }
        JsonArray checked = context.getBodyAsJson().getJsonArray("checked");
        StringBuilder checkbox = new StringBuilder();
        if (checked.size() > 0) {
            for (int i = 0; i < checked.size(); i++) {
                checkbox.append(checked.getString(i));
                if (i != checked.size() - 1) {
                    checkbox.append(",");
                }
            }
        } else {
            checkbox.append("暂无");
        }
        JsonArray second_checked = context.getBodyAsJson().getJsonArray("second_checked");
        StringBuilder second_checkbox = new StringBuilder();
        if (second_checked.size() > 0) {
            for (int i = 0; i < second_checked.size(); i++) {
                second_checkbox.append(second_checked.getString(i));
                if (i != second_checked.size() - 1) {
                    second_checkbox.append(",");
                }
            }
        } else {
            second_checkbox.append("暂无");
        }
        try {
            sdk_information.add(timevalue).add(app_name).add(package_name).add(version_online_version).add(version_update_version)
                    .add(versioncode_online_version).add(versioncode_update_version).add(channel_mark).add(sdkstatus).add(publish).add(checkbox).add(second_checkbox).add(note)
                    .add(icon).add(splash)
                    .add(keystore.getString("filepath")).add(keystore.getString("keystorePass")).add(keystore.getString("keyaliasName")).add(keystore.getString("keyaliasPass"));
        } catch (Exception e) {
            logger.error(e.toString());
            badRequest(context);
        }
        logger.info("SDK 配置表发布 start---");

        if (publish.equals("1")) {
            dbService.query(sql.get(SqlConstants.PROJECT_CONFIG_INFORMATION_INSERT), sdk_information, result -> {
                dbService.batch(sql.get(SqlConstants.PROJECT_CONFIG_PARAMTER_INSERT), paramters, result1 -> {
                    if (result.succeeded() && result1.succeeded()) {
                        logger.info("SDK 配置表发布成功");
                        context.response().setStatusCode(200).end(Json.encodePrettily(new JsonObject().put("code", 20000).put("data", "成功")));
                    } else {
                        if (result.failed()) {
                            logger.error("sdk_information 插入失败", result1.cause());
                        }
                        if (result1.failed()) {
                            logger.error("paramters 插入失败", result.cause());
                        }
                        badRequest(context);
                    }
                });
            });
        } else {

            context.response().setStatusCode(200).end(Json.encodePrettily(new JsonObject().put("code", 20000).put("data", "失败")));
        }
    }

    /**
     * 创建项目配置
     *
     * @param context
     */
    private void projectconfigCreateHandler_pro(RoutingContext context) {
        String package_name = context.getBodyAsJson().getString("package_name").trim();
        String channel_mark = context.getBodyAsJson().getString("channel_mark").trim();
        Long date=context.getBodyAsJson().getLong("date");
        if (package_name==null||channel_mark==null||date==null){
            badRequest(context);
            return;
        }
        JsonArray sdk_information = new JsonArray()
                .add(date)
                .add("暂无").add(package_name).add("暂无").add("暂无").add("暂无") .add("暂无")
                .add(channel_mark).add(1).add(0).add("暂无").add("暂无").add("暂无").add("暂无").add("暂无").add("暂无").add("暂无").add("暂无").add("暂无");
        dbService.fetchDatas(sql.get(SqlConstants.PROJECT_CONFIG_INFORMATION_COUNT), new JsonArray().add(package_name).add(channel_mark), rs -> {
            if (rs.succeeded()) {
                List<JsonObject> jsonObjects = rs.result();
                if (jsonObjects.get(0).getInteger("count(*)").equals(0)) {
                    dbService.query(sql.get(SqlConstants.PROJECT_CONFIG_INFORMATION_INSERT), sdk_information, result -> {
                        if (result.succeeded() ) {
                            context.response().setStatusCode(200).end(Json.encodePrettily(new JsonObject().put("code", 20000).put("data", "成功")));
                        } else {
                            if (result.failed()) {
                                logger.error("sdk_information 插入失败", result.cause());
                            }
                            badRequest(context);
                        }
                    });
                } else {
                    context.response().setStatusCode(200).end(Json.encodePrettily(new JsonObject().put("code", 20000).put("data", "添加失败")));
                }
            }
        });


    }


    /**
     * 400
     *
     * @param context
     */
    private void badRequest(RoutingContext context) {
        context.response().setStatusCode(400).end();
        return;
    }

    /**
     * 从数据库获取项目配置表，拼接
     *
     * @param sql
     * @param sql2
     * @param context
     */
    private void list(String sql, String sql2, RoutingContext context) {
        dbService.listDatas(sql, result -> {
            dbService.listDatas(sql2, result1 -> {
                if (result.succeeded() && result1.succeeded()) {
                    List<JsonObject> sdk_information = result.result();
                    List<JsonObject> paramter = result1.result();
                    for (int i = 0; i < sdk_information.size(); i++) {
                        Long longdate = sdk_information.get(i).getLong("date");
                        String app_name = sdk_information.get(i).getString("app_name");
                        String channel_mark = sdk_information.get(i).getString("channel_mark");
                        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format(new Date(longdate));
                        String date1 = new SimpleDateFormat("yyyy年MM月dd HH:mm:ss").format(new Date(longdate));
                        sdk_information.get(i).put("date", date);
                        sdk_information.get(i).put("date1", date1);
                        sdk_information.get(i).put("timestamp", longdate);
                        JsonArray jsonArray = new JsonArray();
                        Iterator<JsonObject> it = paramter.iterator();
                        while (it.hasNext()) {
                            JsonObject jsonObject = it.next();
                            if (jsonObject.getLong("date").equals(longdate)
                                    && jsonObject.getString("app_name").equals(app_name)
                                    && jsonObject.getString("channel_mark").equals(channel_mark)) {
                                jsonObject.put("key", Long.toHexString(new Date().getTime()));
                                jsonObject.put("mark", jsonObject.getString("param_name").split("-")[0]);

                                String[] name = jsonObject.getString("param_name").split("-");
//                                jsonObject.put("param_name1", name[name.length - 1]);
                                String param_name1 = jsonObject.getString("param_name").substring(name[0].length() + 1).replace("-", "");
                                jsonObject.put("param_name1", param_name1);

                                jsonArray.add(jsonObject);
                                it.remove();
                            }
                        }
                        //JsonArray重新排序
                        JsonArray newjsonarray = new JsonArray();
                        JsonArray list1 = jsonArray;
                        for (int j = 0; j < list1.size(); j++) {
                            Boolean flag = true;
                            if (newjsonarray.size() == 0) {
                                for (int x = 0; x < newjsonarray.size(); x++) {
                                    if (newjsonarray.getJsonObject(x).getString("param_name").equals(list1.getJsonObject(j).getString("param_name"))) {
                                        flag = false;
                                    }
                                }
                            }
                            if (flag) {
                                JsonObject listjsonobject = list1.getJsonObject(j);
                                newjsonarray.add(listjsonobject);
                                String name = listjsonobject.getString("param_name").split("-")[0];
                                Iterator<Object> jsonarray = jsonArray.iterator();
                                while (jsonarray.hasNext()) {
                                    JsonObject jsonObject = JsonObject.mapFrom(jsonarray.next());
                                    String name1 = jsonObject.getString("param_name").split("-")[0];
                                    if (name.equals(name1) && !listjsonobject.getString("param_name").equals(jsonObject.getString("param_name"))) {
                                        newjsonarray.add(jsonObject);
                                        jsonarray.remove();
                                    }
                                }
                            }
                        }
                        sdk_information.get(i).put("paramter", newjsonarray);
                    }
                    Collections.reverse(sdk_information);
                    context.response().setStatusCode(200).end(Json.encodePrettily(new JsonObject().put("code", 20000).put("data", sdk_information)));
                } else {
                    if (result1.failed()) {
                        logger.error("SDK_PARAMTER_LIST 失败------》" + result1.cause().toString());
                    }
                    if (result.failed()) {
                        logger.error("SDK_INFORMATION_LIST 失败------》" + result.cause().toString());
                    }
                    badRequest(context);
                }
            });
        });
    }


    /**
     * SDK获取json格式key表（一张）
     * 从数据库获取项目配置表，拼接(api)
     *
     * @param sql
     * @param sql2
     * @param context
     */
    private void apilist(String sql, String sql2, RoutingContext context, JsonArray param) {
        dbService.fetchDatas(sql, param, result -> {
            dbService.fetchDatas(sql2, param, result1 -> {
                if (result.succeeded() && result1.succeeded()) {
                    List<JsonObject> sdk_information = result.result();
                    if (sdk_information.size() == 0) {
                        JsonObject failed = new JsonObject();
                        failed.put("message", "没找到这张表！");
                        context.response().putHeader("Content-Type", "application/javascript; charset=UTF-8").end(Json.encodePrettily(failed));
                        return;
                    }
                    List<JsonObject> paramter = result1.result();
                    JsonObject sdk_config = sdk_information.get(0);
                    logger.info(sdk_information.toString());
                    logger.info(paramter.toString());
                    JsonObject configtable = new JsonObject();
                    configtable.put("productName", sdk_config.getString("app_name").trim());
                    configtable.put("packageName", sdk_config.getString("package_name").trim());
                    configtable.put("channel", sdk_config.getString("channel_mark").trim());
                    configtable.put("versionName", sdk_config.getString("version_update").trim());
                    configtable.put("versionCode", Integer.valueOf(sdk_config.getString("versioncode_update_version").trim()));
                    JsonObject success = new JsonObject();
                    success.put("basic", configtable);
                    JsonArray jsonArray = new JsonArray();
                    Iterator<JsonObject> it = paramter.iterator();
                    while (it.hasNext()) {
                        JsonObject jsonObject = it.next();
                        JsonObject sdk = new JsonObject();
                        String[] name = jsonObject.getString("param_name").split("-");
//                                jsonObject.put("param_name1", name[name.length - 1]);
                        String module = jsonObject.getString("param_name").split("-")[0].trim();
                        String paramte_name = jsonObject.getString("param_name").substring(name[0].length() + 1).replace("-", "").trim();
                        String paramte = jsonObject.getString("param").trim();
                        System.out.println(paramte);
                        //将String转成布尔值
                        if (!paramte.equals("无")) {
                            if (success.getJsonObject(module) == null) {
                                if (paramte.equals("true")) {
                                    System.out.println(1);
                                    success.put(module, new JsonObject().put(paramte_name, true));
                                } else if (paramte.equals("false")) {
                                    System.out.println(2);
                                    success.put(module, new JsonObject().put(paramte_name, false));
                                } else {
                                    success.put(module, new JsonObject().put(paramte_name, paramte));
                                }
                            } else {
                                if (paramte.equals("true")) {
                                    System.out.println(3);
                                    success.getJsonObject(module).put(paramte_name, true);
                                } else if (paramte.equals("false")) {
                                    System.out.println(4);
                                    success.getJsonObject(module).put(paramte_name, false);
                                } else {
                                    success.getJsonObject(module).put(paramte_name, paramte);
                                }
                            }
                            it.remove();
                        }

                    }
                    context.response().putHeader("Content-Type", "application/javascript; charset=UTF-8").end(Json.encodePrettily(success));
                } else {
                    if (result1.failed()) {
                        logger.error("api获取sdk失败------》" + result1.cause().toString());
                    }
                    if (result.failed()) {
                        logger.error("api获取sdk失败------》" + result.cause().toString());
                    }
                    JsonObject failed = new JsonObject();
                    failed.put("message", "数据库错误，查找失败");
                    context.response().putHeader("Content-Type", "application/javascript; charset=UTF-8").end(Json.encodePrettily(failed));
                }
            });
        });
    }

    /**
     * SDK获取json格式key表（最新）
     * 从数据库获取项目配置表，拼接(api)
     *
     * @param sql
     * @param sql2
     * @param context
     */
    private void apilist_new(String sql, String sql2, RoutingContext context, JsonArray param, JsonArray param1) {
        dbService.fetchDatas(sql, param, result -> {
            dbService.fetchDatas(sql2, param1, result1 -> {
                if (result.succeeded() && result1.succeeded()) {
                    List<JsonObject> sdk_information = result.result();
                    if (sdk_information.size() == 0) {
                        JsonObject failed = new JsonObject();
                        failed.put("message", "没找到这张表！");
                        context.response().putHeader("Content-Type", "text/plain; charset=utf-8").end(Json.encodePrettily(failed));
                        return;
                    }
                    List<JsonObject> paramter = result1.result();
                    JsonObject sdk_config = sdk_information.get(0);
                    JsonObject configtable = new JsonObject();
                    Integer date = sdk_config.getInteger("date");
                    String channel = sdk_config.getString("channel_mark").trim();
                    configtable.put("productName", sdk_config.getString("app_name").trim());
                    configtable.put("packageName", sdk_config.getString("package_name").trim());
                    configtable.put("channel", sdk_config.getString("channel_mark").trim());
                    configtable.put("versionName", sdk_config.getString("version_update").trim());
                    configtable.put("versionCode", Integer.valueOf(sdk_config.getString("versioncode_update_version").trim()));

                    if (!"暂无".equals(sdk_config.getString("icon").trim())){
                        configtable.put("defaultIcon", domainName+"/file?path="+sdk_config.getString("icon").trim());
                    }
                    if (!"暂无".equals(sdk_config.getString("splash").trim())){
                        configtable.put("splashImage", domainName+"/file?path="+sdk_config.getString("splash").trim());
                    }
                    if (!"暂无".equals(sdk_config.getString("keystorePath").trim())){
                        configtable.put("keystorePath", domainName+"/file?path="+sdk_config.getString("keystorePath").trim());
                    }
                    if (!"暂无".equals(sdk_config.getString("keystorePass").trim())){
                        configtable.put("keystorePass", sdk_config.getString("keystorePass").trim());
                    }
                    if (!"暂无".equals(sdk_config.getString("keyaliasName").trim())){
                        configtable.put("keyaliasName", sdk_config.getString("keyaliasName").trim());
                    }
                    if (!"暂无".equals(sdk_config.getString("keyaliasPass").trim())){
                        configtable.put("keyaliasPass", sdk_config.getString("keyaliasPass").trim());
                    }



                    JsonObject success = new JsonObject();
                    success.put("basic", configtable);
                    Iterator<JsonObject> it = paramter.iterator();

                    while (it.hasNext()) {
                        JsonObject jsonObject = it.next();
                        if (date.equals(jsonObject.getInteger("date")) && channel.equals(jsonObject.getString("channel_mark"))) {
                            String[] name = jsonObject.getString("param_name").split("-");
//                                jsonObject.put("param_name1", name[name.length - 1]);
                            String module = jsonObject.getString("param_name").split("-")[0].trim();
                            String paramte_name = jsonObject.getString("param_name").substring(name[0].length() + 1).replace("-", "").trim();
                            String paramte = jsonObject.getString("param").trim();
                            if (!paramte.equals("无")) {
                                if (success.getJsonObject(module) == null) {
                                    if (paramte.equals("true")) {
                                        success.put(module, new JsonObject().put(paramte_name, true));
                                    } else if (paramte.equals("false")) {
                                        success.put(module, new JsonObject().put(paramte_name, false));
                                    } else {
                                        success.put(module, new JsonObject().put(paramte_name, paramte));
                                    }
                                } else {
                                    if (paramte.equals("true")) {
                                        success.getJsonObject(module).put(paramte_name, true);
                                    } else if (paramte.equals("false")) {
                                        success.getJsonObject(module).put(paramte_name, false);
                                    } else {
                                        success.getJsonObject(module).put(paramte_name, paramte);
                                    }
                                }
                                it.remove();
                            }
                        }
                    }
                    context.response().putHeader("Content-Type", "text/plain; charset=utf-8").end(Json.encodePrettily(success));
                } else {
                    if (result1.failed()) {
                        logger.error("api获取sdk失败------》" + result1.cause().toString());
                    }
                    if (result.failed()) {
                        logger.error("api获取sdk失败------》" + result.cause().toString());
                    }
                    JsonObject failed = new JsonObject();
                    failed.put("message", "数据库错误，查找失败");
                    context.response().putHeader("Content-Type", "text/plain; charset=utf-8").end(Json.encodePrettily(failed));
                }
            });
        });
    }

}
