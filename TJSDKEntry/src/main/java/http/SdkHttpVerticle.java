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
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.Method;
import java.text.SimpleDateFormat;
import java.util.*;

public class SdkHttpVerticle extends AbstractVerticle {
    private Logger logger = LoggerFactory.getLogger(SdkHttpVerticle.class.getName());
    public static final String CONFIG_ACCOUNTDB_QUEUE = "accountdb.queue";
    private SdkDatabaseService dbService;
    HashMap<SqlConstants, String> sql;
    private Method method = new Method();
    private String sdkDbQueue;

    @Override
    public void start(Future<Void> startFuture) throws Exception {
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

        //项目配置表
        router.get("/api/projectconfig/:starttime/:endtime").handler(this::projectconfigListHandler);
        router.post("/api/projectconfig").handler(this::projectconfigCreateHandler);
        router.patch("/api/projectconfig/:id").handler(this::projectconfigUpdateHandler);
        router.get("/api/listtemplate").handler(this::listtemplateHandler);
        router.get("/api/projectconfig_publish").handler(this::projectconfigListPublishHandler);
        //sdk
        router.get("/api/sdk").handler(this::sdkListHandler);
        router.post("/api/sdk").handler(this::sdkCreateHandler);
        router.patch("/api/sdk/:id").handler(this::sdkUpdateHandler);

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

    private void projectconfigListPublishHandler(RoutingContext context) {
        list(sql.get(SqlConstants.PROJECT_CONFIG_PUBLISH_LIST),sql.get(SqlConstants.PROJECT_CONFIG_PARAMTER_LIST),context);
    }

    /**
     * 获取所有有效的sdk模版
     *
     * @param context
     */
    private void listtemplateHandler(RoutingContext context) {
        dbService.listDatas(sql.get(SqlConstants.SDK_INFO_LIST),result -> {
            dbService.listDatas(sql.get(SqlConstants.SDK_LIST_LIST), result1 -> {
                if (result.succeeded()&&result1.succeeded()) {
                    List<JsonObject> sdk_info = result.result();
                    List<JsonObject> sdk_list = result1.result();
                    JsonArray sdk_Template_Name_List=new JsonArray();
                    JsonArray sdk_Template_List=new JsonArray();
                    for (int i=0;i<sdk_info.size();i++){
                        if (sdk_info.get(i).getString("sdk_status").equals("0")){
                            continue;
                        }
                        JsonObject template=new JsonObject();
                        String mark=sdk_info.get(i).getString("sdk_mark");
                        String name=sdk_info.get(i).getString("sdk_name");
                        template.put("keymark",mark);
                        sdk_Template_Name_List.add(mark);
                        JsonArray sdkform=new JsonArray();
                        Iterator<JsonObject> it = sdk_list.iterator();
                        while (it.hasNext()) {
                            JsonObject jsonObject=it.next();
                            JsonObject sdk_template=new JsonObject();
                            if (jsonObject.getString("sdk_mark").equals(mark)){
                                sdk_template.put("key",Long.toHexString(new Date().getTime()));
                                sdk_template.put("param_name",mark+"-"+jsonObject.getString("sdk_paramter_name"));
                                sdk_template.put("param","");
                                sdk_template.put("note",jsonObject.getString("sdk_note"));
                                sdkform.add(sdk_template);
                                it.remove();
                            }
                        }
                        template.put("keyform",sdkform);
                        sdk_Template_List.add(template);
                    }
                    context.response().setStatusCode(200).end(Json.encodePrettily(
                            new JsonObject().put("code", 20000)
                                    .put("name_list",sdk_Template_Name_List)
                                    .put("list",sdk_Template_List)
                    ));
                }else {
                    if (result.failed()){
                        logger.error("sdk_info 查询失败", result1.cause());
                    }
                    if (result1.failed()){
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
            String param_note = domains.getJsonObject(i).getString("sdk_note");
            JsonArray jsonArray = new JsonArray();
            jsonArray.add(mark).add(param_name).add(param_note);
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
        dbService.listDatas(sql.get(SqlConstants.SDK_INFO_LIST),result -> {
            dbService.listDatas(sql.get(SqlConstants.SDK_LIST_LIST),result1 -> {
                if (result.succeeded()&&result1.succeeded()){
                    List<JsonObject> sdk_info = result.result();
                    List<JsonObject> sdk_list = result1.result();
                    for (int i=0;i<sdk_info.size();i++){
                        String mark=sdk_info.get(i).getString("sdk_mark");
                        JsonArray jsonArray=new JsonArray();
                        Iterator<JsonObject> it = sdk_list.iterator();
                        while (it.hasNext()) {
                            JsonObject jsonObject=it.next();
                            if (jsonObject.getString("sdk_mark").equals(mark)){
                                jsonObject.put("key", Long.toHexString(new Date().getTime()));
                                jsonArray.add(jsonObject);
                                it.remove();
                            }
                        }
                        sdk_info.get(i).put("paramter",jsonArray);
                    }
                    Collections.reverse(sdk_info);
                    context.response().setStatusCode(200).end(Json.encodePrettily(new JsonObject().put("code", 20000).put("data",sdk_info)));
                }else {
                    if (result.failed()){
                        logger.error("sdk_info 查询失败", result1.cause());
                    }
                    if (result1.failed()){
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
            String param_note = domains.getJsonObject(i).getString("sdk_note");
            JsonArray jsonArray = new JsonArray();
            jsonArray.add(mark).add(param_name).add(param_note);
            list.add(jsonArray);
        }
        dbService.query(sql.get(SqlConstants.SDK_INFO_UPDATE),new JsonArray().add(name).add(version).add(mark).add(sdkstatus).add(id),result -> {
            dbService.query(sql.get(SqlConstants.SDK_LIST_DELETE),new JsonArray().add(mark),result1 -> {
                dbService.batch(sql.get(SqlConstants.SDK_LIST_INSERT), list, result2 -> {
                    if (result.succeeded()&&result1.succeeded()&&result2.succeeded()){
                        context.response().setStatusCode(200).end(Json.encodePrettily(new JsonObject().put("code", 20000)));
                    }else {
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
        String sdk_config = context.getBodyAsJson().getString("sdk_config");
        String sdkstatus = context.getBodyAsJson().getString("sdkstatus");
        String sdk_require = context.getBodyAsJson().getString("sdk_require");
        String note = context.getBodyAsJson().getString("note");
        JsonObject jsonObject = context.getBodyAsJson().getJsonObject("form");
        JsonArray jsonArray = jsonObject.getJsonArray("domains");
        for (int i = 0; i < jsonArray.size(); i++) {
            String param_name = jsonArray.getJsonObject(i).getString("param_name");
            String param_value = jsonArray.getJsonObject(i).getString("param");
            String param_note = jsonArray.getJsonObject(i).getString("note");
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
        try {
            sdk_information.add(timevalue).add(app_name).add(package_name).add(version_online_version).add(version_update_version).add(versioncode_online_version).add(versioncode_update_version)
                    .add(channel_mark).add(sdk_config).add(sdk_require).add(note).add(sdkstatus).add(id);
            sdk_paramter_delete.add(timevalue).add(app_name).add(channel_mark);
        } catch (Exception e) {
            logger.error(e.toString());
            badRequest(context);
        }
        dbService.query(sql.get(SqlConstants.PROJECT_CONFIG_INFORMATION_UPDATE), sdk_information, result -> {
            dbService.query(sql.get(SqlConstants.PROJECT_CONFIG_PARAMTER_DELETE), sdk_paramter_delete, result1 -> {
                    if (result1.succeeded()){
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
                    }else {
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
    list(sql.get(SqlConstants.PROJECT_CONFIG_INFORMATION_LIST),sql.get(SqlConstants.PROJECT_CONFIG_PARAMTER_LIST),context);
    }

    /**
     * 创建项目配置
     *
     * @param context
     */
    private void projectconfigCreateHandler(RoutingContext context) {
        JsonArray sdk_information = new JsonArray();
        List<JsonArray> paramters = new ArrayList<>();
        Long timevalue = context.getBodyAsJson().getLong("timevalue");
        String app_name = context.getBodyAsJson().getString("app_name");
        String package_name = context.getBodyAsJson().getString("package_name");
        String channel_mark = context.getBodyAsJson().getString("channel_mark");
        String version_online_version = context.getBodyAsJson().getString("version_online_version");
        String version_update_version = context.getBodyAsJson().getString("version_update_version");
        String versioncode_online_version = context.getBodyAsJson().getString("versioncode_online_version");
        String versioncode_update_version = context.getBodyAsJson().getString("versioncode_update_version");
        String sdk_config = context.getBodyAsJson().getString("sdk_config");
        String sdkstatus = context.getBodyAsJson().getString("sdkstatus");
        String sdk_require = context.getBodyAsJson().getString("sdk_require");
        String note = context.getBodyAsJson().getString("note");
        String publish = context.getBodyAsJson().getString("publish");
        JsonObject jsonObject = context.getBodyAsJson().getJsonObject("form");
        JsonArray jsonArray = jsonObject.getJsonArray("domains");
//        System.out.println(timevalue);
//        System.out.println(app_name);
//        System.out.println(package_name);
//        System.out.println(channel_mark);
//        System.out.println(version_online_version);
//        System.out.println(version_update_version);
//        System.out.println(versioncode_online_version);
//        System.out.println(versioncode_update_version);
//        System.out.println(sdk_config);
//        System.out.println(sdkstatus);
//        System.out.println(sdk_require);
//        System.out.println(note);
//        System.out.println(publish);
//        System.out.println(jsonObject);

        for (int i = 0; i < jsonArray.size(); i++) {
            String param_name = jsonArray.getJsonObject(i).getString("param_name");
            String param_value = jsonArray.getJsonObject(i).getString("param");
            String param_note = jsonArray.getJsonObject(i).getString("note");
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
        try {
            sdk_information.add(timevalue).add(app_name).add(package_name).add(version_online_version).add(version_update_version)
                    .add(versioncode_online_version).add(versioncode_update_version).add(channel_mark).add(sdk_config)
                    .add(sdk_require).add(note).add(sdkstatus).add(publish);
        } catch (Exception e) {
            logger.error(e.toString());
            badRequest(context);
        }
        dbService.query(sql.get(SqlConstants.PROJECT_CONFIG_INFORMATION_INSERT), sdk_information, result -> {
            dbService.batch(sql.get(SqlConstants.PROJECT_CONFIG_PARAMTER_INSERT), paramters, result1 -> {
                if (result.succeeded() && result1.succeeded()) {
                    context.response().setStatusCode(200).end(Json.encodePrettily(new JsonObject().put("code", 20000)));
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

    private void list(String sql,String sql2,RoutingContext context){
        dbService.listDatas(sql, result -> {
            dbService.listDatas(sql2, result1 -> {
                if (result.succeeded() && result1.succeeded()) {
                    List<JsonObject> sdk_information = result.result();
                    List<JsonObject> paramter = result1.result();
                    for (int i = 0; i < sdk_information.size(); i++) {
                        Long longdate = sdk_information.get(i).getLong("date");
                        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS").format(new Date(longdate));
                        sdk_information.get(i).put("date", date);
                        JsonArray jsonArray = new JsonArray();
                        Iterator<JsonObject> it = paramter.iterator();
                        while (it.hasNext()) {
                            JsonObject jsonObject = it.next();
                            if (jsonObject.getLong("date").equals(longdate)) {
                                jsonObject.put("key", Long.toHexString(new Date().getTime()));
                                jsonObject.put("mark",jsonObject.getString("param_name").split("-")[0]);
                                jsonArray.add(jsonObject);
                                it.remove();
                            }
                        }
                        sdk_information.get(i).put("paramter", jsonArray);
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

}
