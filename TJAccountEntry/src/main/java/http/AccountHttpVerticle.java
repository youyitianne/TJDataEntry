package http;

import database.AccountDatabaseService;
import database.ConfigConstants;
import database.SqlQuery;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.KeyStoreOptions;
import io.vertx.ext.auth.PubSecKeyOptions;
import io.vertx.ext.auth.User;
import io.vertx.ext.auth.jdbc.JDBCAuth;
import io.vertx.ext.auth.jwt.JWTAuth;
import io.vertx.ext.auth.jwt.JWTAuthOptions;
import io.vertx.ext.auth.jwt.JWTOptions;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.*;
import io.vertx.ext.web.sstore.LocalSessionStore;
import io.vertx.ext.web.sstore.SessionStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class AccountHttpVerticle extends AbstractVerticle {
    public static final String CONFIG_HTTP_SERVER_PORT = "http.server.port";
    public static final String CONFIG_ACCOUNTDB_QUEUE = "accountdb.queue";
    private Logger logger = LoggerFactory.getLogger(AccountHttpVerticle.class.getName());
    private AccountDatabaseService dbService;
    private String accountDbQueue;
    private JWTAuth jwtAuth = null;
    private JDBCClient jdbcClient = null;
    private SessionStore store = null;
    private JDBCAuth authProvider = null;
    private JWTAuthHandler jwtAuthHandler;
    private AccountOperationLog operationLog=new AccountOperationLog();

    @Override
    public void start(Future<Void> startFuture) throws Exception {
        accountDbQueue = config().getString(CONFIG_ACCOUNTDB_QUEUE, "accountdb.queue");
        dbService = AccountDatabaseService.createProxy(vertx, CONFIG_ACCOUNTDB_QUEUE);
        AccountHttpService method = new AccountHttpService();
        HashMap<ConfigConstants, String> conf = method.loadSqlQueries();
        Router router = Router.router(vertx);

        JsonObject config = new JsonObject()
                .put("url", conf.get(ConfigConstants.DATABASE_URL))
                .put("driver_class", conf.get(ConfigConstants.DATABASE_DRIVER_CLASS))
                .put("max_pool_size", Integer.valueOf(conf.get(ConfigConstants.DATABASE_MAX_POOL_SIZE)))
                .put("user", conf.get(ConfigConstants.DATABASE_USER))
                .put("password", conf.get(ConfigConstants.DATABASE_PASSWORD));
        jdbcClient = JDBCClient.createShared(vertx, config);

        authProvider = JDBCAuth.create(vertx, jdbcClient);

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
        //跨域 end

        //sessionHandler start
//        store = LocalSessionStore.create(vertx, "myapp3.sessionmap");
//        SessionHandler sessionHandler = SessionHandler.create(store);
//        router.route().handler(sessionHandler);
        //sessionHandler end

        //token start

        //router.route("/user/*").handler(JWTAuthHandler.create(jwtAuth));     //token验证,生成user
        //router.route("/api/*").handler(JWTAuthHandler.create(jwtAuth));
        //token end


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
        router.route().handler(UserSessionHandler.create(authProvider));
        router.route().handler(this::operateHandler);
        //获取日志
        router.post("/operationlog").handler(this::operationLogHandler);
        //修改个人密码
        router.post("/updatePassword").handler(this::updatePasswordHandler);
        //路由    start
        router.post("/login/login").handler(this::loginHandler);
        router.get("/user/info").handler(this::getInfoHandler);
        router.post("/login/logout").handler(this::logoutHandler);
        //account相关
        router.get("/api/account").handler(this::findAccount);
        router.patch("/api/account/:id").handler(this::updateUser);
        router.post("/api/account").handler(this::addAccount);
        router.delete("/api/account/:id").handler(this::delAccount);
        //role相关
        router.get("/api/role").handler(this::findRole);
        router.post("/api/role").handler(this::addRole);
        router.patch("/api/role/:id").handler(this::updateRole);
        router.delete("/api/role/:id").handler(this::delRole);
//        //perm相关
//        router.get("/api/perm").handler(this::findPerm);
//        router.post("/api/perm").handler(this::addPerm);
//        router.patch("/api/perm/:id").handler(this::updatePerm);
//        router.delete("/api/perm/:id").handler(this::delPerm);
        //权限相关
        router.get("/api/perms/:id").handler(this::findPerms);
        router.post("/api/perms").handler(this::addPerms);
        //资源相关
        router.get("/api/resource/:id").handler(this::findResource);
        router.post("/api/resource").handler(this::addResource);
        //获取资源应用名
        router.post("/api/getresourcelist").handler(this::findApp_resource);
        //测试
        router.get("/api/getchannel").handler(this::testgetchannel);
        //路由    end
        Router apirouter = Router.router(vertx);
        router.mountSubRouter("/api", apirouter);
        //跨域  start

        apirouter.route().handler(CorsHandler.create("*")
                .allowedMethods(allowMethods)
                .allowedHeaders(allowHeaders));
        //跨域 end
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
     * 修改个人密码
     * @param context
     */
    private void updatePasswordHandler(RoutingContext context) {
        String account=context.getBodyAsJson().getString("account");
        String password=context.getBodyAsJson().getString("password");
        String salt = authProvider.generateSalt();
        String hash = authProvider.computeHash(password, salt);
        dbService.query(SqlQuery.UPDATE_PASSWORD, new JsonArray().add(salt).add(hash).add(password).add(account), res -> {
            if (res.succeeded()) {
                context.response().setStatusCode(200).end(Json.encodePrettily(new JsonObject().put("code", 20000)));
            } else {
                logger.error("发生错误------->" + res.cause());
                context.response().setStatusCode(200).end(Json.encodePrettily(new JsonObject().put("data", "获取数据失败请联系管理员")));
            }
        });
    }


    /**
     * 操作日志记录
     * @param context
     */
    private void operateHandler(RoutingContext context) {
        JWTAuthHandler jwtAuthHandler=JWTAuthHandler.create(jwtAuth);
        jwtAuthHandler.parseCredentials(context,rs->{
            if (rs.succeeded()){
                JsonObject jsonObject=rs.result();
                jwtAuth.authenticate(new JsonObject()
                        .put("jwt", jsonObject.getString("jwt"))
                        .put("options", new JsonObject()
                                .put("ignoreExpiration", true)),user->{
                    if (user.succeeded()){
                        User user1=user.result();
                        JsonObject userJson=user1.principal();
                        String time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                        Long date=new Date().getTime()/1000;
                        String username=userJson.getString("username");
                        String path=context.request().uri();
                        if (path.contains("?")){
                            path=path.substring(0,path.indexOf("?"));
                        }
                        operationLog.operationLog(dbService,context,path,context.request().method().toString(),username,time,context.request().getHeader("X-FORWARD-FOR"),date);
                    }else {
                        //身份验证
                        logger.error("身份验证",user.cause());
                    }
                });
            }else {
                //解析凭证失败
                logger.error("解析凭证失败",rs.cause());
            }
        });
        context.next();
    }

    /**
     * 获取操作日志
     * @param context
     */
    private void operationLogHandler(RoutingContext context) {
        String start=context.getBodyAsJson().getString("start");
        String end=context.getBodyAsJson().getString("end");
        Long startdate=0L;
        Long enddate=0L;
        try {
            startdate=new SimpleDateFormat("yyyy-MM-dd").parse(start).getTime()/1000;
            enddate=new SimpleDateFormat("yyyy-MM-dd").parse(end).getTime()/1000L+24*60*60-1;
            logger.info(enddate.toString());
        }catch (ParseException e){
            logger.error("日期转换失败-----》",e);
            context.response().setStatusCode(500).end();
        }
        dbService.fetchDatas(SqlQuery.GET_LOG,new JsonArray().add(startdate).add(enddate), res -> {
            if (res.succeeded()) {
                JsonObject names=new JsonObject();
                names.put("code", 20000);
                names.put("data",res.result());
                context.response().setStatusCode(200).end(Json.encode(names));
            } else {
               logger.error("查询日志失败-----》",res.cause());
                context.response().setStatusCode(500).end();
            }
        });
    }

    /**
     * 获取资源应用名
     * @param context
     */
    private void findApp_resource(RoutingContext context) {
        String username = context.request().getParam("username");
        System.out.println("username:"+username);
        if (username==null||username==""){
            badRequest(context);
            return;
        }
        dbService.fetchDatas(SqlQuery.SELECT_RESOURCE_NAME, new JsonArray().add(username), res -> {
            if (res.succeeded()) {
                List<JsonObject> list=res.result();
                List list1=new ArrayList();
                for (int i=0;i<list.size();i++){
                    list1.add(list.get(i).getString("resource_name"));
                }
                JsonObject names = new JsonObject();
                names.put("code", 20000);
                names.put("data",list1);
                context.response().setStatusCode(200).end(Json.encode(names));
            } else {
                System.out.println(res.cause());
                context.response().setStatusCode(500).end();
            }
        });
    }

    /**
     * 测试获取渠道
     *
     * @param context
     */
    private void testgetchannel(RoutingContext context) {
        List<JsonObject> jsonObjects = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.put("id", i).put("label", "资源" + i);
            jsonObjects.add(jsonObject);
        }
        JsonObject json = new JsonObject().put("code", 20000).put("data", jsonObjects);
        logger.info("ok");
        context.response().setStatusCode(200).end(Json.encodePrettily(json));
    }

    /**
     * 登录验证
     *
     * @param context
     */
    private void loginHandler(RoutingContext context) {
        String username = context.getBodyAsJson().getString("username").trim();
        String password = context.getBodyAsJson().getString("password").trim();
        JsonObject authInfo = new JsonObject().put("username", username).put("password", password);
        authProvider.authenticate(authInfo, res -> {
            if (res.succeeded()) {
                dbService.fetchDatas(SqlQuery.SELECT_USER_ROLE, new JsonArray().add(username), roleResult -> {
                    dbService.fetchDatas(SqlQuery.SELECT_USER_PERM, new JsonArray().add(username), permResult -> {
                        dbService.fetchDatas(SqlQuery.SELECT_USER_RESOURCE, new JsonArray().add(username), resourceResult -> {
                            if (resourceResult.succeeded() && roleResult.succeeded() && permResult.succeeded()) {
                                List<JsonObject> rolelist = roleResult.result();
                                List<JsonObject> permlist = permResult.result();
                                List<JsonObject> resourcelist = resourceResult.result();
                                //.put("resources", toJsonArray(resourcelist, "resource"))
                                String token = jwtAuth.generateToken(
                                        new JsonObject()
                                                .put("username", username)
                                                .put("roles", toJsonArray(rolelist, "role"))
                                                .put("perms", toJsonArray(permlist, "permission"))
                                                .put("resources", new JsonArray().add("你好")),
                                        new JWTOptions().setExpiresInMinutes(999L));
                                //token生成
                                JsonObject jsonObject = new JsonObject();
                                jsonObject.put("code", 20000)
                                        .put("data", new JsonObject().put("token", token));
                                logger.info(username + " 登录," + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "-------------------------");
                                context.response().setStatusCode(200).end(Json.encodePrettily(jsonObject));
                            } else {
                                logger.error("登录失败:" + res.cause());
                                context.response().setStatusCode(400).end(Json.encodePrettily(new JsonObject().put("code", 50020)));
                            }
                        });
                    });
                });
            } else {
                logger.error("登录失败:" + res.cause());
                context.response().setStatusCode(400).end(Json.encodePrettily(new JsonObject().put("code", 50020)));
            }
        });
    }

    /**
     * 获取角色信息
     * 名字 权限
     *
     * @param context
     */
    private void getInfoHandler(RoutingContext context) {
        jwtAuthHandler.parseCredentials(context, rs -> {
            if (rs.failed()) {
                logger.error("生成User 失败 -》" + rs.cause().toString());
                context.response().setStatusCode(200).end(Json.encodePrettily(new JsonObject().put("code", 50020)));
                return;
            } else {
                JsonObject users = rs.result();
                jwtAuth.authenticate(users, user -> {
                    if (user.failed()) {
                        user.cause().printStackTrace();
                        logger.error("生成User 失败 -》" + user.cause().toString());
                        context.response().setStatusCode(200).end(Json.encodePrettily(new JsonObject().put("code", 50020)));
                        return;
                    }
                    if (JWTBlackListAuth(context)) return;
                    String username = user.result().principal().getString("username", null);
                    JsonArray roles = user.result().principal().getJsonArray("roles", null);
                    vertx.executeBlocking(event -> {
                        JsonObject data = new JsonObject().put("roles", AccountHttpService.toList(roles)).put("name", username);
                        event.complete(data);
                    }, asyncResult -> {
                        if (asyncResult.succeeded()) {
                            JsonObject data = (JsonObject) asyncResult.result();
                            logger.info(username + "  get info success" + roles + "-------------------------------");
                            context.response().setStatusCode(200).end(Json.encodePrettily(new JsonObject().put("code", 20000).put("data", data)));
                        } else {
                            logger.info(username + "  get info failed" + roles + "-------------------------------" + asyncResult.cause());
                            context.response().setStatusCode(200).end(Json.encodePrettily(new JsonObject().put("code", 50020)));
                        }
                    });
                });
            }
        });
    }

    /**
     * 登出
     *
     * @param context
     */
    private void logoutHandler(RoutingContext context) {
        String token = context.request().getHeader("Authorization");
        CacheList.TOKEN_CACHE_BLACKLIST.add(token);
        context.response().setStatusCode(200).end(Json.encodePrettily(new JsonObject().put("code", 20000)));
    }

    /**
     * 获得权限
     *
     * @param context
     */
    private void findPerms(RoutingContext context) {
        Integer id = -1;
        try {
            id = Integer.valueOf(context.request().getParam("id"));
        } catch (Exception e) {
            badRequest(context);
            return;
        }
        dbService.fetchDatas(SqlQuery.SELECT_PERMS, new JsonArray().add(id), res -> {
            if (res.succeeded()) {
                List<JsonObject> jsonObjects = res.result();
                List<String> list = new ArrayList<>();
                for (int i = 0; i < jsonObjects.size(); i++) {
                    list.add(i, jsonObjects.get(i).getString("permission"));
                }
                JsonObject json = new JsonObject().put("code", 20000).put("data", list);
                logger.info("ok");
                context.response().setStatusCode(200).end(Json.encodePrettily(json));
            } else {
                logger.error("发生错误------->" + res.cause());
                context.response().setStatusCode(200).end(Json.encodePrettily(new JsonObject().put("data", "获取数据失败请联系管理员")));
            }
        });
    }

    /**
     * 更新权限
     *
     * @param context
     */
    private void addPerms(RoutingContext context) {
        JsonObject json = new JsonObject().put("code", 20000);
        String permissions = context.request().getParam("permissions");
        String username = context.request().getParam("username");
        String id = context.request().getParam("id");
        String username_mark = context.request().getParam("username_mark");
        List<JsonArray> jsonArrays = new ArrayList<>();
        if (permissions!=null){
            String[] list = permissions.split(",");
            for (int i = 0; i < list.length; i++) {
                if (list[i] == null || list[i].length() == 0) continue;
                JsonArray jsonArray = new JsonArray();
                jsonArray.add(username);
                jsonArray.add(username_mark);
                jsonArray.add(list[i]);
                jsonArrays.add(jsonArray);
            }
        }
        dbService.query(SqlQuery.DELETE_PERMS, new JsonArray().add(username_mark), res -> {
            if (res.succeeded()) {
                if (permissions==null) {
                    context.response().setStatusCode(200).end(Json.encodePrettily(json));
                    return;
                }
                dbService.batch(SqlQuery.INSERT_PERMS, jsonArrays, result -> {
                    if (result.succeeded()) {
                        context.response().setStatusCode(200).end(Json.encodePrettily(json));
                    } else {
                        //-------
                        logger.error(result.cause().toString());
                        context.response().setStatusCode(400);
                        result.cause().printStackTrace();
                    }
                });
            } else {
                //-------
                logger.error(res.cause().toString());
                context.response().setStatusCode(400);
                res.cause().printStackTrace();
            }
        });
    }

    /**
     * 获取资源
     * @param context
     */
    private void findResource(RoutingContext context) {
        Integer id = -1;
        try {
            id = Integer.valueOf(context.request().getParam("id"));
        } catch (Exception e) {
            badRequest(context);
            return;
        }
        System.out.println(id);
        dbService.fetchDatas(SqlQuery.SELECT_RESOURCE, new JsonArray().add(id), res -> {
            if (res.succeeded()) {
                List<JsonObject> jsonObjects = res.result();
                List<String> list = new ArrayList<>();
                for (int i = 0; i < jsonObjects.size(); i++) {
                    list.add(i, jsonObjects.get(i).getInteger("resource_mark").toString());
                }
                JsonObject json = new JsonObject().put("code", 20000).put("data", list);
                logger.info("get resourcelist success --"+list.toString());
                context.response().setStatusCode(200).end(Json.encodePrettily(json));
            } else {
                logger.error("发生错误------->" + res.cause());
                context.response().setStatusCode(200).end(Json.encodePrettily(new JsonObject().put("data", "获取数据失败请联系管理员")));
            }
        });
    }

    /**
     * 编辑资源
     * @param context
     */
    private void addResource(RoutingContext context) {
        JsonObject json = new JsonObject().put("code", 20000);
        String resources = context.request().getParam("resources");
        String username = context.request().getParam("username");
        String username_mark = context.request().getParam("username_mark");
        String resources_name=context.request().getParam("resources_name");
        List<JsonArray> jsonArrays = new ArrayList<>();
        if (resources!=null){
            String[] list2=resources_name.split(",");
            String[] list = resources.split(",");
            for (int i = 0; i < list.length; i++) {
                if (list[i] == null || list[i].length() == 0) continue;
                JsonArray jsonArray = new JsonArray();
                jsonArray.add(username);
                jsonArray.add(username_mark);
                jsonArray.add(list[i]);
                jsonArray.add(list2[i]);
                jsonArrays.add(jsonArray);
            }
        }
        dbService.query(SqlQuery.DELETE_RESOURCE, new JsonArray().add(username_mark), res -> {
            if (res.succeeded()) {
                if (resources==null) {
                    context.response().setStatusCode(200).end(Json.encodePrettily(json));
                    return;
                }
                dbService.batch(SqlQuery.INSERT_RESOURCE, jsonArrays, result -> {
                    if (result.succeeded()) {
                        context.response().setStatusCode(200).end(Json.encodePrettily(json));
                    } else {
                        //-------
                        logger.error(result.cause().toString());
                        context.response().setStatusCode(400);
                        result.cause().printStackTrace();
                    }
                });
            } else {
                //-------
                logger.error(res.cause().toString());
                context.response().setStatusCode(400);
                res.cause().printStackTrace();
            }
        });
    }

    /**
     * api获取帐号列表
     */
    private void findAccount(RoutingContext context) {
        dbService.fetchAllData(SqlQuery.SELECT_USER, rs -> {
            if (rs.succeeded()) {
                List<JsonObject> jsonObjects = rs.result();
                JsonObject json = new JsonObject().put("code", 20000).put("data", jsonObjects);
                context.response().setStatusCode(200).end(Json.encodePrettily(json));
            } else {
                logger.error("发生错误------->" + rs.cause());
                context.response().setStatusCode(200).end(Json.encodePrettily(new JsonObject().put("data", "获取数据失败请联系管理员")));
            }
        });
    }

    /**
     * api添加帐号
     */
    private void addAccount(RoutingContext context) {
        String name = context.request().getParam("username");
        String password = context.request().getParam("psd");
        String note = context.request().getParam("note");
        String position = context.request().getParam("position");
        String department = context.request().getParam("department");
        if (name == null || password == null || note == null || position == null || department == null || name.length() == 0 || password.length() == 0 || note.length() == 0 || position.length() == 0 || department.length() == 0) {
            badRequest(context);
            return;
        }
        String salt = authProvider.generateSalt();
        String hash = authProvider.computeHash(password, salt);
        dbService.query(SqlQuery.INSERT_USER, new JsonArray().add(name).add(hash).add(salt).add(note).add(password).add(position).add(department), res -> {
            if (res.succeeded()) {
                context.response().setStatusCode(200).end(Json.encodePrettily(new JsonObject().put("code", 20000)));
            } else {
                logger.error("发生错误------->" + res.cause());
                context.response().setStatusCode(200).end(Json.encodePrettily(new JsonObject().put("data", "创建失败,请检查帐号名是否重复")));
            }
        });
    }


    /**
     * api删除帐号
     */
    private void delAccount(RoutingContext context) {
        Integer id = -1;
        try {
            id = Integer.valueOf(context.request().getParam("id"));
        } catch (Exception e) {
            badRequest(context);
            return;
        }
        dbService.query(SqlQuery.DELETE_USER, new JsonArray().add(id), res -> {
            if (res.succeeded()) {
                context.response().setStatusCode(200).end(Json.encodePrettily(new JsonObject().put("code", 20000)));
            } else {
                logger.error("发生错误------->" + res.cause());
                context.response().setStatusCode(200).end(Json.encodePrettily(new JsonObject().put("data", "获取数据失败请联系管理员")));
            }
        });
    }

    /**
     * api 更新帐号
     */
    private void updateUser(RoutingContext context) {
        String name = "";
        String password = "";
        String note = "";
        String position = "";
        String department = "";
        Integer id = -1;
        try {
            name = context.getBodyAsJson().getString("username");
            password = context.getBodyAsJson().getString("password");
            note = context.getBodyAsJson().getString("note");
            position = context.getBodyAsJson().getString("position");
            department = context.getBodyAsJson().getString("department");
            id = Integer.valueOf(context.request().getParam("id"));
        } catch (Exception e) {
            name = context.request().getParam("username");
            password = context.request().getParam("password");
            note = context.request().getParam("note");
            position = context.request().getParam("position");
            department = context.request().getParam("department");
            id = Integer.valueOf(context.request().getParam("id"));
        }
        if (id == -1 || name == null || password == null || note == null || position == null || department == null || name.length() == 0 || password.length() == 0 || note.length() == 0 || position.length() == 0 || department.length() == 0) {
            badRequest(context);
            return;
        }
        String salt = authProvider.generateSalt();
        String hash = authProvider.computeHash(password, salt);
        dbService.query(SqlQuery.UPDATE_USER, new JsonArray().add(salt).add(name).add(hash).add(note).add(password).add(position).add(department).add(id), res -> {
            if (res.succeeded()) {
                context.response().setStatusCode(200).end(Json.encodePrettily(new JsonObject().put("code", 20000)));
            } else {
                logger.error("发生错误------->" + res.cause());
                context.response().setStatusCode(200).end(Json.encodePrettily(new JsonObject().put("data", "获取数据失败请联系管理员")));
            }
        });
    }

    /**
     * api获取角色表
     */
    private void findRole(RoutingContext context) {
        dbService.fetchAllData(SqlQuery.SELECT_ROLE, res -> {
            if (res.succeeded()) {
                List<JsonObject> jsonObjects = res.result();
                JsonObject json = new JsonObject().put("code", 20000).put("data", jsonObjects);
                context.response().setStatusCode(200).end(Json.encodePrettily(json));
            } else {
                logger.error("发生错误------->" + res.cause());
                context.response().setStatusCode(200).end(Json.encodePrettily(new JsonObject().put("data", "获取数据失败请联系管理员")));
            }
        });
    }

    /**
     * api添加角色
     */
    private void addRole(RoutingContext context) {
        Integer username_mark=-1;
        String username="";
        String role="";
        String note="";
        String role_name="";
        String role_describe="";
        try {
             username_mark = context.getBodyAsJson().getInteger("username_mark");
             username = context.getBodyAsJson().getString("username");
             role = context.getBodyAsJson().getString("role");
             note = context.getBodyAsJson().getString("note");
             role_name = context.getBodyAsJson().getString("role_name");
             role_describe = context.getBodyAsJson().getString("role_describe");
        }catch (Exception e){
            username_mark = Integer.valueOf(context.request().getParam("username_mark"));
            username = context.request().getParam("username");
            role = context.request().getParam("role");
            note = context.request().getParam("note");
            role_name = context.request().getParam("role_name");
            role_describe = context.request().getParam("role_describe");
        }
        if (username_mark==-1||username == null || username.length() == 0 || role_name == null || role_name.length() == 0 || role_describe == null || role_describe.length() == 0 || username_mark == null || role == null || role.length() == 0 || note == null || note.length() == 0) {
            badRequest(context);
            return;
        }
        dbService.query(SqlQuery.INSERT_ROLE, new JsonArray().add(username_mark).add(username).add(role).add(note).add(role_name).add(role_describe), res -> {
            if (res.succeeded()) {
                context.response().setStatusCode(200).end(Json.encodePrettily(new JsonObject().put("code", 20000)));
            } else {
                logger.error("发生错误------->" + res.cause());
                context.response().setStatusCode(200).end(Json.encodePrettily(new JsonObject().put("data", "获取数据失败请联系管理员")));
            }
        });
    }

    /**
     * api 更新角色
     */
    private void updateRole(RoutingContext context) {
        String role = "";
        String note = "";
        String role_name = "";
        String role_describe = "";
        Integer id = -1;
        try {
             role = context.getBodyAsJson().getString("role");
             note = context.getBodyAsJson().getString("note");
             role_name = context.getBodyAsJson().getString("role_name");
             role_describe = context.getBodyAsJson().getString("role_describe");
            id = Integer.valueOf(context.request().getParam("id"));
        } catch (Exception e) {
             role = context.request().getParam("role");
             note = context.request().getParam("note");
             role_name = context.request().getParam("role_name");
             role_describe = context.request().getParam("role_describe");
            id = Integer.valueOf(context.request().getParam("id"));
        }
        if (id==-1||role_name == null || role_name.length() == 0 || role_describe == null || role_describe.length() == 0 || role == null || role.length() == 0 || note == null || note.length() == 0) {
            badRequest(context);
            return;
        }
        dbService.query(SqlQuery.UPDATE_ROLE, new JsonArray().add(role).add(note).add(role_name).add(role_describe).add(id), res -> {
            if (res.succeeded()) {
                context.response().setStatusCode(200).end(Json.encodePrettily(new JsonObject().put("code", 20000)));
            } else {
                logger.error("发生错误------->" + res.cause());
                context.response().setStatusCode(200).end(Json.encodePrettily(new JsonObject().put("data", "获取数据失败请联系管理员")));
            }
        });
    }

    /**
     * api删除角色
     */
    private void delRole(RoutingContext context) {
        Integer id = -1;
        try {
            id = Integer.valueOf(context.request().getParam("id"));
        } catch (Exception e) {
            badRequest(context);
            return;
        }
        dbService.query(SqlQuery.DELETE_ROLE, new JsonArray().add(id), res -> {
            if (res.succeeded()) {
                context.response().setStatusCode(200).end(Json.encodePrettily(new JsonObject().put("code", 20000)));
            } else {
                logger.error("发生错误------->" + res.cause());
                context.response().setStatusCode(200).end(Json.encodePrettily(new JsonObject().put("data", "获取数据失败请联系管理员")));
            }
        });
    }

    /**
     * api获取权限  (旧)
     */
    private void findPerm(RoutingContext context) {
        dbService.fetchAllData(SqlQuery.SELECT_PERM, res -> {
            if (res.succeeded()) {
                List<JsonObject> jsonObjects = res.result();
                JsonObject json = new JsonObject().put("code", 20000).put("data", jsonObjects);
                context.response().setStatusCode(200).end(Json.encodePrettily(json));
            } else {
                logger.error("发生错误------->" + res.cause());
                serviceError(context, Json.encodePrettily(new JsonObject().put("data", "获取数据失败请联系管理员")));
            }
        });
    }

    /**
     * api添加权限  (旧)
     */
    private void addPerm(RoutingContext context) {
        String role = context.request().getParam("role");
        String perm = context.request().getParam("perm");
        String note = context.request().getParam("note");
        if (role == null || perm == null || role.length() == 0 || perm.length() == 0 || note == null || note.length() == 0) {
            badRequest(context);
            return;
        }
        dbService.query(SqlQuery.INSERT_PERM, new JsonArray().add(role).add(perm).add(note), res -> {
            if (res.succeeded()) {
                context.response().setStatusCode(200).end(Json.encodePrettily(new JsonObject().put("code", 20000)));
            } else {
                logger.error("发生错误------->" + res.cause());
                serviceError(context, Json.encodePrettily(new JsonObject().put("data", "获取数据失败请联系管理员")));
            }
        });
    }

    /**
     * api更新权限  (旧)
     */
    private void updatePerm(RoutingContext context) {
        String perm = context.getBodyAsJson().getString("perm");
        String role = context.getBodyAsJson().getString("role");
        String note = context.getBodyAsJson().getString("note");
        Integer id = -1;
        try {
            id = Integer.valueOf(context.request().getParam("id"));
        } catch (Exception e) {
            badRequest(context);
            return;
        }
        if (role == null || perm == null || role.length() == 0 || perm.length() == 0 || note == null || note.length() == 0) {
            badRequest(context);
            return;
        }
        dbService.query(SqlQuery.UPDATE_PERM, new JsonArray().add(role).add(perm).add(note).add(id), res -> {
            if (res.succeeded()) {
                context.response().setStatusCode(200).end(Json.encodePrettily(new JsonObject().put("code", 20000)));
            } else {
                logger.error("发生错误------->" + res.cause());
                serviceError(context, Json.encodePrettily(new JsonObject().put("data", "获取数据失败请联系管理员")));
            }
        });
    }

    /**
     * api删除权限 (旧)
     */
    private void delPerm(RoutingContext context) {
        Integer id = -1;
        try {
            id = Integer.valueOf(context.request().getParam("id"));
        } catch (Exception e) {
            badRequest(context);
            return;
        }
        dbService.query(SqlQuery.DELETE_PERM, new JsonArray().add(id), res -> {
            if (res.succeeded()) {
                context.response().setStatusCode(200).end(Json.encodePrettily(new JsonObject().put("code", 20000)));
            } else {
                logger.error("发生错误------->" + res.cause());
                serviceError(context, Json.encodePrettily(new JsonObject().put("data", "获取数据失败请联系管理员")));
            }
        });
    }

    /**
     * token黑名单验证
     */
    private Boolean JWTBlackListAuth(RoutingContext context) {
        String token = context.request().getHeader("Authorization");
        if (AccountHttpService.mach_TOKEN_BLACKLIST(token)) {
            String username = context.user().principal().getString("username", null);
            logger.error(username + " bad request " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "----------------------");
            context.response().setStatusCode(401).end();
            return true;
        }
        return false;
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
     * List<JsonObject> 转JsonArray
     *
     * @param jsonObjects
     * @param name
     * @return
     */
    private JsonArray toJsonArray(List<JsonObject> jsonObjects, String name) {
        JsonArray jsonArray = new JsonArray();
        for (int i = 0; i < jsonObjects.size(); i++) {
            jsonArray.add(jsonObjects.get(i).getString(name));
        }
        return jsonArray;
    }
}
