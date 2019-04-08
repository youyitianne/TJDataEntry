package http;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CorsHandler;
import net.sf.json.JSONObject;
import org.omg.CORBA.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

import static org.apache.logging.log4j.message.MapMessage.MapFormat.JSON;

public class shieldingHttpVerticle extends AbstractVerticle {
    private Logger logger = LoggerFactory.getLogger(shieldingHttpVerticle.class.getName());
    private Method method = new Method();
    HttpMethodClient client = new HttpMethodClient();
    //String domainName = "192.168.1.101:8085";
    String domainName = "console.tomatojoy.cn";

    @Override
    public void start(Future<Void> startFuture) throws Exception {
        Router router = Router.router(vertx);
        router.route().handler(BodyHandler.create());
        HashMap<ConfigConstants, String> conf = method.loadConfigQueries();
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

        //获取内网ip
        router.post("/api/system/getip").handler(this::getIPHandler);
        //修改内网ip
        router.post("/api/system/postip").handler(this::postIPHandler);
        //获取应用列表
        router.post("/api/system/getapplist").handler(this::getapplistHandler);
        //搜索应用
        router.post("/api/system/searchapp").handler(this::searchappHandler);
        //获取应用全部系统参数信息
        router.post("/api/system/getparams").handler(this::getparamsHandler);
        //添加应用系统参数
        router.post("/api/system/addparams").handler(this::addparamsHandler);
        //编辑应用系统参数
        router.post("/api/system/editparams").handler(this::editparamsHandler);

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
     * 获取外网存储ip
     * get
     * http://域名/redsystem/api/testip
     *
     * @param context
     */
    private void getIPHandler(RoutingContext context) {
        try {
            String result = client.doGetClient("http://" + domainName + "/redsystem/api/testip");
            if (result==null){
                context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3001).put("data", "修改失败:")));
            }
            context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3000).put("data", (result))));
        } catch (Exception e) {
            logger.error("获取外网存储内网ip失败" + e);
            context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3001).put("data", "获取失败:" + e)));
        }
    }

    /**
     * 修改外网存储ip
     * post
     * http://域名/redsystem/api/testip
     *
     * @param context
     */
    private void postIPHandler(RoutingContext context) {
        String ip = context.getBodyAsJson().getString("ip");
        logger.info(ip);
        List list = new ArrayList();
        JsonObject jsonObject = new JsonObject();
        jsonObject.put("key", "ip").put("value", ip);
        list.add(jsonObject);
        try {
            String result = client.doPostClient("http://" + domainName + "/redsystem/api/testip", list);
            if (result==null){
                context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3001).put("data", "修改失败:")));
            }
            context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3000).put("data", (result))));
        } catch (Exception e) {
            logger.error("修改存储ip失败" + e);
            context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3001).put("data", "修改失败:" + e)));
        }
    }

    /**
     * 获取应用列表
     * get
     * http://域名/redsystem/api/applist
     *
     * @param context
     */
    private void getapplistHandler(RoutingContext context) {
        Integer page = context.getBodyAsJson().getInteger("page");
        Integer limit = context.getBodyAsJson().getInteger("limit");
        logger.info(page.toString());
        logger.info(limit.toString());
        try {
            String result = client.doGetClient("http://" + domainName + "/redsystem/api/applist?page=" + page + "&limit=" + limit);
            if (result==null){
                context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3001).put("data", "修改失败:")));
            }
            context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3000).put("data", result)));
        } catch (Exception e) {
            logger.error("获取应用列表失败" + e);
            context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3001).put("data", "获取失败:" + e)));
        }
    }

    /**
     * 搜索应用
     * get
     * http://域名/redsystem/api/app
     *
     * @param context
     */
    private void searchappHandler(RoutingContext context) {
        String appName = context.getBodyAsJson().getString("appName").trim();
        String appId = context.getBodyAsJson().getString("appId").trim();
        String appPackageName = context.getBodyAsJson().getString("appPackageName").trim();
        Integer page = context.getBodyAsJson().getInteger("page");
        Integer limit = context.getBodyAsJson().getInteger("limit");

        logger.info(appName);
        logger.info(appId);
        logger.info(appPackageName);
        logger.info(page.toString());
        logger.info(limit.toString());
        try {
            String result = client.doGetClient("http://" + domainName + "/redsystem/api/app?page=" + page + "&limit=" + limit
                    + "&appName=" + appName
                    + "&appId=" + appId
                    + "&appPackageName=" + appPackageName);
            if (result==null){
                context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3001).put("data", "修改失败:")));
            }
            context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3000).put("data", (result))));
        } catch (Exception e) {
            logger.error("获取应用列表失败" + e);
            context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3001).put("data", "获取失败:" + e)));
        }
    }

    /**
     * 获取应用全部参数
     * GET
     * http://域名/redsystem/api/system
     *
     * @param context
     */
    private void getparamsHandler(RoutingContext context) {
        String appId = context.getBodyAsJson().getString("appId");
        logger.info(appId);
        try {
            String result = client.doGetClient("http://" + domainName + "/redsystem/api/system?appId=" + appId);
            if (result==null){
                context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3001).put("data", "修改失败:")));
            }
            context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3000).put("data",(result))));
        } catch (Exception e) {
            logger.error("获取应用全部系统参数失败" + e);
            context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3001).put("data", "获取失败:" + e)));
        }
    }

    /**
     * 添加应用参数
     * post
     * http://域名/redsystem/api/system
     *
     * @param context
     */
    private void addparamsHandler(RoutingContext context) {
        String appId = context.getBodyAsJson().getString("appId");
        String system = context.getBodyAsJson().getString("system");
        JsonObject status = context.getBodyAsJson().getJsonObject("status");
        logger.info(appId);
        logger.info(system);
        logger.info(status.toString());
        List list = new ArrayList();
        JsonObject jsonObject = new JsonObject();
        jsonObject.put("key", "appId").put("value", appId);
        list.add(jsonObject);
        JsonObject jsonObject1 = new JsonObject();
        jsonObject1.put("key", "system").put("value", system);
        list.add(jsonObject1);
        JsonObject jsonObject2 = new JsonObject();
        jsonObject2.put("key", "status").put("value", status.toString());
        list.add(jsonObject2);
        try {
            String result = client.doPostClient("http://" + domainName + "/redsystem/api/system", list);
            if (result==null){
                context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3001).put("data", "修改失败:")));
            }
            context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3000).put("data", (result))));
        } catch (Exception e) {
            logger.error("添加应用参数失败" + e);
            context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3001).put("data", "修改失败:" + e)));
        }
    }

    /**
     * 编辑应用参数
     * put
     * http://域名/redsystem/api/system
     *
     * @param context
     */
    private void editparamsHandler(RoutingContext context) {
        Integer appId = context.getBodyAsJson().getInteger("appId");
        String system = context.getBodyAsJson().getString("system");
        JsonObject status = context.getBodyAsJson().getJsonObject("status");
        logger.info(appId.toString());
        logger.info(system);
        logger.info(status.toString());
        List list = new ArrayList();
        JsonObject jsonObject = new JsonObject();
        jsonObject.put("key", "id").put("value", appId.toString());
        list.add(jsonObject);
        JsonObject jsonObject1 = new JsonObject();
        jsonObject1.put("key", "system").put("value", system);
        list.add(jsonObject1);
        JsonObject jsonObject2 = new JsonObject();
        jsonObject2.put("key", "status").put("value", status.toString());
        list.add(jsonObject2);
        try {
            String result = client.doPutClient("http://" + domainName + "/redsystem/api/system", list);
            if (result==null){
                context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3001).put("data", "修改失败:")));
            }
            logger.info(result);
            context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3000).put("data", (result))));
        } catch (Exception e) {
            logger.error("修改存储ip失败" + e);
            context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3001).put("data", "修改失败:" + e)));
        }
    }
}
