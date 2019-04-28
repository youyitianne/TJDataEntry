package http;

import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import database.RedDatabaseService;
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
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.*;

public class shieldingHttpVerticle extends AbstractVerticle {
    private Logger logger = LoggerFactory.getLogger(shieldingHttpVerticle.class.getName());
    private Method method = new Method();
    HttpMethodClient client = new HttpMethodClient();
    //String domainName = "192.168.1.101:8085";
    String domainName = "console.tomatojoy.cn";
    private JWTAuthHandler jwtAuthHandler;
    private JWTAuth jwtAuth = null;
    private RedDatabaseService dbService;
    public static final String CONFIG_REDSYSTEMDB_QUEUE = "redsystem.queue";
    HashMap<SqlConstants, String> sql = null;
    shieldingOperationLog operationLog = new shieldingOperationLog();
    private static String synchronize = "synchronize";

    @Override
    public void start(Future<Void> startFuture) throws Exception {
        dbService = RedDatabaseService.createProxy(vertx, CONFIG_REDSYSTEMDB_QUEUE);
        Router router = Router.router(vertx);
        router.route().handler(BodyHandler.create());
        HashMap<ConfigConstants, String> conf = method.loadConfigQueries();
        sql = method.loadSqlQueries();
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

        router.route().handler(this::operateHandler);
        //获取内网ip
        router.post("/api/system/getip").handler(this::getIPHandler);
        //修改内网ip
        router.post("/api/system/postip").handler(this::postIPHandler);
        //获取应用列表
        router.post("/api/system/getapplist").handler(this::getapplistHandler);
        //获取应用列表
        router.post("/api/system/applist").handler(this::applistHandler);
        //搜索应用
        router.post("/api/system/searchapp").handler(this::searchappHandler);
        //获取应用全部系统参数信息
        router.post("/api/system/getparams").handler(this::getparamsHandler);
        //添加应用系统参数
        router.post("/api/system/addparams").handler(this::addparamsHandler);
        //编辑应用系统参数
        router.post("/api/system/editparams").handler(this::editparamsHandler);
        /**
         * 弃用，该功能在TJResourceStoreEntry实现
         *  //获取素材库
         *  router.get("/api/system/listfodderinfo").handler(this::listFodderInfoHandler);
         *   //添加素材
         *   router.post("/api/system/addfodderinfo").handler(this::addFodderInfoHandler);
         */
        //获取推广配置
        router.get("/api/system/getChannelPromoEdit").handler(this::getChannelPromoEditHandler);
        //添加推广配置
        router.post("/api/system/addChannelPromoEdit").handler(this::addChannelPromoEditHandler);
        //编辑推广配置
        router.patch("/api/system/EditChannelPromoEdit").handler(this::EditChannelPromoEditHandler);
        //获取互推渠道
        router.get("/api/system/getPromoChannel").handler(this::getPromoChannelHandler);
        //添加互推渠道
        router.post("/api/system/addPromoChannel").handler(this::addPromoChannelHandler);
        //编辑互推渠道
        router.patch("/api/system/editPromoChannel").handler(this::editPromoChannelHandler);
        //获取互推列表
        router.get("/api/system/getPromoList").handler(this::getPromoListHandler);
        //添加互推列表
        router.post("/api/system/addPromoList").handler(this::addPromoListHandler);
        //编辑互推列表
        router.patch("/api/system/editPromoList").handler(this::editPromoListHandler);
        //删除互推列表
        router.delete("/api/system/delPromoList").handler(this::delPromoListHandler);
        //添加互推列表记录
        router.post("/api/system/addPromoListRecord").handler(this::addPromoListRecordHandler);
        //获取互推列表（本地）
        router.get("/api/system/getRowPromoList").handler(this::getRowPromoListHandler);
        //获取七牛令牌
        router.get("/api/system/getQiNiuToken").handler(this::getQiNiuTokenHandler);
        //同步渠道互推至本地
        router.post("/api/system/synchroPromoList").handler(this::synchroPromoListHandler);
        //删除七牛云本地上传记录
        router.delete("/api/system/delQiniuFileRecord").handler(this::delQiniuFileHandler);
        //获取渠道互推发布记录
        router.get("/api/system/listChannelPromoRecord").handler(this::listChannelPromoRecordHandler);
        //获取渠道互推发布记录(分页)
        router.get("/api/system/listChannelPromoRecord/limit").handler(this::listChannelPromoRecordLimitHandler);
        //获取七牛云上传记录
        router.get("/qiniufile").handler(this::getqiniufileHandler);
        //获取七牛云上传记录 分页
        router.get("/qiniufile/limit").handler(this::getqiniufileLimitHandler);
        //获取互推渠道(分页)
        router.get("/api/system/getPromoChannel/limit").handler(this::getPromoChannelLimitHandler);

        //添加默认被推规则
        //router.get("/test").handler(this::testHandler);

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
     * 获取本地七牛云上传记录
     * 分页
     *
     * @param context
     */
    private void getqiniufileLimitHandler(RoutingContext context) {
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
        dbService.listDatas("SELECT count(*) total FROM mission.qiniu_file_info ", countResult -> {
            if (countResult.succeeded()) {
                Integer total = countResult.result().get(0).getInteger("total");
                dbService.listDatas("SELECT * FROM mission.qiniu_file_info order by qiniu_file_date desc " + limitsql, rs -> {
                    if (rs.succeeded()) {
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        List<JsonObject> list = rs.result();
                        for (int i = 0; i < list.size(); i++) {
                            JsonObject jsonObject = list.get(i);
                            String date1 = jsonObject.getString("qiniu_file_date");
                            jsonObject.put("date", sdf.format(new Date(Long.valueOf(date1))));
                        }
                        logger.info("获取七牛云上传记录成功");
                        context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3000).put("total", total).put("data", rs.result())));
                    } else {
                        logger.error("获取七牛云上传记录失败", rs.cause());
                        context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3001).put("data", rs.cause())));
                    }
                });
            } else {
                logger.error("查询总数失败", countResult.cause());
                context.response().setStatusCode(200).end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3001)));
            }
        });
    }

    /**
     * 获取本地七牛云上传记录
     *
     * @param context
     */
    private void getqiniufileHandler(RoutingContext context) {
        dbService.listDatas("SELECT * FROM mission.qiniu_file_info order by qiniu_file_date desc;", rs -> {
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


    private void testHandler(RoutingContext context) {
        dbService.listDatas(sql.get(SqlConstants.GET_PROMO_CHANNEL), rs -> {
            if (rs.succeeded()) {
                List<JsonObject> list = rs.result();
                /**
                 * {"id":1,
                 * "promoChannelName":"OPPO软件商店",
                 * "promoChannelMark":"oppo",
                 * "promoChannelPackage":"com.oppo.market",
                 * "channelRule":"market://details?id={0}"}
                 */
                try {
                    String result = client.doGetClient("http://console.tomatojoy.cn/redsystem/api/applist?limit=999&page=1");
                    JSONObject jsonObject = JSONObject.fromObject(result);
                    /**
                     * {
                     * "id":1,
                     * "appName":"测试",
                     * "uid":"20170629120446209-8050",
                     * "channel":"test",
                     * "packageName":"com.tomaotjoy.test",
                     * "createAt":1498709086,
                     * "updateAt":null},
                     */
                    Object rows = jsonObject.get("rows");
                    JSONArray jsonArray = JSONArray.fromObject(rows);
                    StringBuilder sql = new StringBuilder();
                    for (int i = 0; i < jsonArray.size(); i++) {
                        JSONObject app = JSONObject.fromObject(jsonArray.get(i));
                        sql.append("('" + app.get("uid") + "',");
                        sql.append("'" + app.get("appName") + "',");
                        sql.append("'" + app.get("channel") + "',");
                        Boolean flag = true;
                        for (int j = 0; j < list.size(); j++) {
                            if (list.get(j).getString("promoChannelMark").equals(app.get("channel").toString())) {
                                flag = false;
                                sql.append("'" + list.get(j).getString("promoChannelPackage") + "',");
                                sql.append("'" + list.get(j).getString("channelRule") + "',");
                            }
                        }
                        if (flag) {
                            sql.append("'" + "暂无" + "',");
                            sql.append("'" + "market://details?id={0}" + "',");
                        }

                        sql.append("'" + app.get("packageName") + "')");
                        if (i != jsonArray.size() - 1) {
                            sql.append(",");
                        }
                    }
                    dbService.queryWithoutParam("INSERT INTO `mission`.`promoinfo`\n" +
                            "(`uid`,\n" +
                            "`promoName`,\n" +
                            "`channel`,\n" +
                            "`channelPackage`,\n" +
                            "`channelRule`,\n" +
                            "`promoPackageName`)\n" +
                            "VALUES " + sql, result1 -> {
                        if (result1.succeeded()) {
                            context.response().putHeader("Content-Type", "text/plain; charset=utf-8").end(sql.toString());
                        } else {
                            context.response().putHeader("Content-Type", "text/plain; charset=utf-8").end("fail");
                        }
                    });

                } catch (Exception e) {
                    /**
                     * uid
                     * promoName 开心玻璃杯
                     * channel
                     * channelPackage 渠道包名
                     * channelRule
                     * promoPackageName 包名
                     */
                    e.printStackTrace();
                }
            } else {
                logger.error("获取互推列表关联失败", rs.cause());
                context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3001).put("data", rs.cause())));
            }
        });


    }


    /**
     * 获取渠道互推发布记录
     *
     * @param context
     */
    private void listChannelPromoRecordHandler(RoutingContext context) {
        dbService.listDatas(sql.get(SqlConstants.LIST_CHANNELPROMO_LIST_RECORD), rs -> {
            if (rs.succeeded()) {
                logger.info("获取渠道互推发布记录成功");
                context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3000).put("data", rs.result())));
            } else {
                logger.error("获取渠道互推发布记录失败", rs.cause());
                context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3001)));
            }
        });
    }

    /**
     * 获取渠道互推发布记录
     * 分页
     *
     * @param context
     */
    private void listChannelPromoRecordLimitHandler(RoutingContext context) {
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
        dbService.listDatas("SELECT count(*) total FROM mission.promolistrecord ", countResult -> {
            if (countResult.succeeded()) {
                Integer total = countResult.result().get(0).getInteger("total");
                dbService.listDatas("SELECT * FROM mission.promolistrecord order by id desc" + limitsql, rs -> {
                    if (rs.succeeded()) {
                        logger.info("获取渠道互推发布记录成功");
                        context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3000).put("total", total).put("data", rs.result())));
                    } else {
                        logger.error("获取渠道互推发布记录失败", rs.cause());
                        context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3001)));
                    }
                });
            } else {
                logger.error("查询总数失败", countResult.cause());
                context.response().setStatusCode(200).end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3001)));
            }
        });
    }


    /**
     * 删除七牛云本地上传记录
     *
     * @param context
     */
    private void delQiniuFileHandler(RoutingContext context) {
        String id = context.request().getParam("id");
        logger.info("删除七牛云本地记录id+++++" + id.toString());
        dbService.query(sql.get(SqlConstants.DEL_QINIU_FILE_RECORD), new JsonArray()
                .add(id), rs -> {
            if (rs.succeeded()) {
                logger.info("删除七牛云本地记录成功");
                context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3000)));
            } else {
                logger.error("删除七牛云本地记录失败", rs.cause());
                context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3001)));
            }
        });
    }

    /**
     * 同步渠道互推至本地
     *
     * @param context [{
     *                "appid":"20170703160232147-9028",
     *                "promoName":"开心玻璃杯",
     *                "channelPackage":"com.bbk.appstore",
     *                "channelRule":"market://details?id={0}",
     *                "promoPackageName":"com.tomato.joy.ybh.vivo",
     *                "promoIconUrl":"https://image.tomatojoy.cn/blbcs5.png",
     *                "promoValue":"1"
     *                }]
     */
    private void synchroPromoListHandler(RoutingContext context) {
        JsonArray jsonArray = context.getBodyAsJson().getJsonArray("list");
        String appid = context.getBodyAsJson().getString("appid");
        StringBuilder promolistinfo = new StringBuilder();
        StringBuilder promoinfo = new StringBuilder();
        StringBuilder qiniu_file_info = new StringBuilder();
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject jsonObject = jsonArray.getJsonObject(i);
            String uid = UUID.randomUUID().toString();
            String puid = UUID.randomUUID().toString();
            String promoName = jsonObject.getString("promoName");
            String channelPackage = jsonObject.getString("channelPackage");
            String channelRule = jsonObject.getString("channelRule");
            String promoPackageName = jsonObject.getString("promoPackageName");
            String promoIconUrl = jsonObject.getString("promoIconUrl");
            String name = promoIconUrl;
            if (promoIconUrl.indexOf(".cn/") != -1) {
                name = promoIconUrl.substring(promoIconUrl.indexOf(".cn/") + 4);
            }
            String promoValue = jsonObject.getString("promoValue");
            promolistinfo.append(
                    "('" + appid
                            + "','" + uid
                            + "','" + puid
                            + "','" + promoValue
                            + "')"
            );
            promoinfo.append(
                    "('" + uid
                            + "','" + promoName
                            + "','" + "unknow"
                            + "','" + channelPackage
                            + "','" + channelRule
                            + "','" + promoPackageName
                            + "')"
            );
            qiniu_file_info.append(
                    "('" + puid
                            + "','" + "unknow"
                            + "','" + new Date().getTime()
                            + "','" + name
                            + "','" + promoIconUrl
                            + "')"
            );
            if (i != jsonArray.size() - 1) {
                promolistinfo.append(",");
                promoinfo.append(",");
                qiniu_file_info.append(",");
            }
        }
        promolistinfo.append(";");
        promoinfo.append(";");
        qiniu_file_info.append(";");
        logger.info(promolistinfo.toString());
        logger.info(promoinfo.toString());
        logger.info(qiniu_file_info.toString());
        List<String> list = new ArrayList<>();
        list.add("INSERT INTO `mission`.`promolistinfo`(`appid`,`uid`,`puid`,`promoValue`) VALUES " + promolistinfo);
        list.add("INSERT INTO `mission`.`promoinfo`(`uid`,`promoName`,`channel`,`channelPackage`,`channelRule`,`promoPackageName`)VALUES " + promoinfo);
        list.add("INSERT INTO `mission`.`qiniu_file_info`(`qiniu_file_guid`,`qiniu_file_md5`,`qiniu_file_date`,`qiniu_file_name`,`qiniu_file_path`) VALUES " + qiniu_file_info);
        dbService.batchWithoutParam(list, result -> {
            if (result.succeeded()) {
                logger.info("同步渠道互推成功");
                context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3000)));
            } else {
                logger.error("同步渠道互推失败", result.cause());
                context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3001)));
            }
        });
    }

    /**
     * 获取七牛令牌
     *
     * @param context
     */
    private void getQiNiuTokenHandler(RoutingContext context) {
        QiNiu qiNiu = getToken();
        context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3000).put("data",
                new JsonObject()
                        .put("token", qiNiu.getToken())
                        .put("key", qiNiu.getKey()))));
    }

    /**
     * 删除互推列表
     *
     * @param context
     */
    private void delPromoListHandler(RoutingContext context) {
        String id = context.request().getParam("id");
        logger.info("删除互推列表id+++++" + id.toString());
        dbService.query(sql.get(SqlConstants.DEL_PROMO_LIST), new JsonArray()
                .add(id), rs -> {
            if (rs.succeeded()) {
                logger.info("删除互推列表成功");
                context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3000)));
            } else {
                logger.error("删除互推列表失败", rs.cause());
                context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3001)));
            }
        });
    }

    /**
     * 获取一个应用的推广列表
     *
     * @param context
     */
    private void getRowPromoListHandler(RoutingContext context) {
        String appid = context.request().getParam("appId");
        logger.info(appid);
        dbService.fetchDatas(sql.get(SqlConstants.GET_ROW_PROMO_LIST), new JsonArray().add(appid), rs -> {
            if (rs.succeeded()) {
                logger.info("获取一个应用的推广列表成功", rs.result());
                context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3000).put("data", rs.result())));
            } else {
                logger.error("获取一个应用的推广列表失败", rs.cause());
                context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3001)));
            }
        });
    }

    /**
     * 添加互推列表记录
     *
     * @param context
     */
    private void addPromoListRecordHandler(RoutingContext context) {
        JsonArray table = context.getBodyAsJson().getJsonArray("table");
        StringBuilder insertSql = new StringBuilder();
        insertSql.append("INSERT INTO `mission`.`promolistrecord`(`promoName`,`channelPackage`,`channelRule`,`promoPackageName`,`promoIconUrl`,`promoValue`)VALUES ");
        for (int i = 0; i < table.size(); i++) {
            insertSql.append("('" +
                    table.getJsonObject(i).getString("promoName") + "','" +
                    table.getJsonObject(i).getString("channelPackage") + "','" +
                    table.getJsonObject(i).getString("channelRule") + "','" +
                    table.getJsonObject(i).getString("promoPackageName") + "','" +
                    table.getJsonObject(i).getString("promoIconUrl") + "','" +
                    table.getJsonObject(i).getString("promoValue") + "')"
            );
            if (i == table.size() - 1) {
                insertSql.append(";");
            } else {
                insertSql.append(",");
            }
        }
        if (table.size() > 0) {
            dbService.queryWithids(insertSql.toString(), sql.get(SqlConstants.GET_MAX_IDS), new JsonArray().add(table.size()), rs -> {
                if (rs.succeeded()) {
                    logger.info("添加互推列表记录成功", rs.result());
                    context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3000).put("data", rs.result())));
                } else {
                    logger.error("添加互推列表记录失败", rs.cause());
                    context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3001)));
                }
            });
        } else {
            logger.info("添加互推列表记录成功,表为空");
            context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3000)));
        }


    }

    /**
     * 获取互推列表推广
     *
     * @param context
     */
    private void getPromoListHandler(RoutingContext context) {
        dbService.listDatas(sql.get(SqlConstants.GET_PROMO_LIST), rs -> {
            if (rs.succeeded()) {
                logger.info("获取互推列表关联成功");
                context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3000).put("data", rs.result())));
            } else {
                logger.error("获取互推列表关联失败", rs.cause());
                context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3001).put("data", rs.cause())));
            }
        });
    }

    /**
     * 增加互推列表推广
     *
     * @param context
     */
    private void addPromoListHandler(RoutingContext context) {
        String uid = context.getBodyAsJson().getString("uid");
        String puid = context.getBodyAsJson().getString("puid");
        String promoValue = context.getBodyAsJson().getString("promoValue");
        String appid = context.getBodyAsJson().getString("appid");
        dbService.queryWithid(sql.get(SqlConstants.ADD_PROMO_LIST), sql.get(SqlConstants.GET_MAX_ID), new JsonArray()
                .add(uid)
                .add(puid)
                .add(promoValue)
                .add(appid), rs -> {
            if (rs.succeeded()) {
                logger.info("增加互推列表关联成功", rs.result());
                context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3000).put("data", rs.result())));
            } else {
                logger.error("增加互推列表关联失败", rs.cause());
                context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3001)));
            }
        });
    }

    /**
     * 编辑互推列表推广
     *
     * @param context
     */
    private void editPromoListHandler(RoutingContext context) {
        String uid = context.getBodyAsJson().getString("uid");
        String puid = context.getBodyAsJson().getString("puid");
        String promoValue = context.getBodyAsJson().getString("promoValue");
        String appid = context.getBodyAsJson().getString("appid");
        Integer id = context.getBodyAsJson().getInteger("id");
        dbService.query(sql.get(SqlConstants.EDIT_PROMO_LIST), new JsonArray()
                .add(uid)
                .add(puid)
                .add(promoValue)
                .add(appid)
                .add(id), rs -> {
            if (rs.succeeded()) {
                logger.info("编辑互推列表推广成功");
                context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3000)));
            } else {
                logger.error("编辑互推列表推广失败", rs.cause());
                context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3001)));
            }
        });
    }


    /**
     * 获取互推渠道
     * 分页
     *
     * @param context
     */
    private void getPromoChannelLimitHandler(RoutingContext context) {
        Integer limit = 0;
        Integer page = 0;
        String promoChannelName = context.request().getParam("promoChannelName");
        try {
            limit = Integer.valueOf(context.request().getParam("limit"));
            page = Integer.valueOf(context.request().getParam("page"));
        } catch (Exception e) {
            logger.error("参数错误");
            context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3001).put("data", "参数错误")));
        }
        StringBuilder condition = new StringBuilder();

        synchronized (synchronize) {
            Boolean where_flag = true;
            if (promoChannelName != null) {
                if (where_flag = true) {
                    condition.append(" where ");
                    where_flag = false;
                }
                condition.append(" promoChannelName like '%" + promoChannelName + "%' ");
            }
        }
        StringBuilder limitsql = new StringBuilder();
        limitsql.append("  limit " + (page - 1) * limit + "," + limit + ";");
        dbService.listDatas("SELECT count(*) total FROM mission.promochannelinfo " + condition, countResult -> {
            if (countResult.succeeded()) {
                Integer total = countResult.result().get(0).getInteger("total");
                dbService.listDatas("SELECT * FROM mission.promochannelinfo" + condition +"  order by id desc "+limitsql, rs -> {
                    if (rs.succeeded()) {
                        logger.info("获取互推渠道成功");
                        context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3000).put("total", total).put("data", rs.result())));
                    } else {
                        logger.error("获取互推渠道失败", rs.cause());
                        context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3001).put("data", rs.cause())));
                    }
                });
            } else {
                logger.error("查询总数失败", countResult.cause());
                context.response().setStatusCode(200).end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3001)));
            }
        });
    }

    /**
     * 获取互推渠道
     *
     * @param context
     */
    private void getPromoChannelHandler(RoutingContext context) {
        dbService.listDatas(sql.get(SqlConstants.GET_PROMO_CHANNEL), rs -> {
            if (rs.succeeded()) {
                logger.info("获取互推渠道成功");
                context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3000).put("data", rs.result())));
            } else {
                logger.error("获取互推渠道失败", rs.cause());
                context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3001).put("data", rs.cause())));
            }
        });
    }

    /**
     * 添加互推渠道
     *
     * @param context
     */
    private void addPromoChannelHandler(RoutingContext context) {
        String promoChannelName = context.getBodyAsJson().getString("promoChannelName");
        String promoChannelPackage = context.getBodyAsJson().getString("promoChannelPackage");
        String promoChannelMark = context.getBodyAsJson().getString("promoChannelMark");
        String channelRule = context.getBodyAsJson().getString("channelRule");
        dbService.query(sql.get(SqlConstants.ADD_PROMO_CHANNEL), new JsonArray()
                        .add(promoChannelName)
                        .add(promoChannelMark)
                        .add(promoChannelPackage)
                        .add(channelRule)
                , rs -> {
                    if (rs.succeeded()) {
                        logger.info("添加互推渠道成功");
                        context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3000)));
                    } else {
                        logger.error("添加互推渠道失败", rs.cause());
                        context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3001)));
                    }
                });
    }

    /**
     * 编辑互推渠道
     *
     * @param context
     */
    private void editPromoChannelHandler(RoutingContext context) {
        String promoChannelName = context.getBodyAsJson().getString("promoChannelName");
        String promoChannelPackage = context.getBodyAsJson().getString("promoChannelPackage");
        String promoChannelMark = context.getBodyAsJson().getString("promoChannelMark");
        String channelRule = context.getBodyAsJson().getString("channelRule");
        Integer id = context.getBodyAsJson().getInteger("id");
        dbService.query(sql.get(SqlConstants.EDIT_PROMO_CHANNEL), new JsonArray()
                .add(promoChannelName)
                .add(promoChannelMark)
                .add(promoChannelPackage)
                .add(channelRule)
                .add(id), rs -> {
            if (rs.succeeded()) {
                logger.info("编辑互推渠道成功");
                context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3000)));
            } else {
                logger.error("编辑互推渠道失败", rs.cause());
                context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3001)));
            }
        });
    }

    /**
     * 编辑推广配置
     *
     * @param context
     */
    private void EditChannelPromoEditHandler(RoutingContext context) {
        String channel = context.getBodyAsJson().getString("channel");
        String channelPackage = context.getBodyAsJson().getString("channelPackage");
        String channelRule = context.getBodyAsJson().getString("channelRule");
        String promoName = context.getBodyAsJson().getString("promoName");
        String promoPackageName = context.getBodyAsJson().getString("promoPackageName");
        String uid = context.getBodyAsJson().getString("uid");
        dbService.query(sql.get(SqlConstants.EDIT_CHANNEL_PROMO_EDIT), new JsonArray()
                .add(promoName)
                .add(channel)
                .add(channelPackage)
                .add(channelRule)
                .add(promoPackageName)
                .add(uid), rs -> {
            if (rs.succeeded()) {
                logger.info("编辑推广配置成功");
                context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3000)));
            } else {
                logger.error("编辑推广配置失败", rs.cause());
                context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3001)));
            }
        });
    }

    /**
     * 添加推广配置
     *
     * @param context
     */
    private void addChannelPromoEditHandler(RoutingContext context) {
        String channel = context.getBodyAsJson().getString("channel");
        String channelPackage = context.getBodyAsJson().getString("channelPackage");
        String channelRule = context.getBodyAsJson().getString("channelRule");
        String promoName = context.getBodyAsJson().getString("promoName");
        String promoPackageName = context.getBodyAsJson().getString("promoPackageName");
        String uid = context.getBodyAsJson().getString("uid");
        dbService.query(sql.get(SqlConstants.ADD_CHANNEL_PROMO_EDIT), new JsonArray()
                        .add(uid)
                        .add(promoName)
                        .add(channel)
                        .add(channelPackage)
                        .add(channelRule)
                        .add(promoPackageName)
                , rs -> {
                    if (rs.succeeded()) {
                        logger.info("添加推广配置成功");
                        context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3000)));
                    } else {
                        logger.error("添加推广配置失败", rs.cause());
                        context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3001)));
                    }
                });
    }

    /**
     * 获取推广配置
     *
     * @param context
     */
    private void getChannelPromoEditHandler(RoutingContext context) {
        dbService.listDatas(sql.get(SqlConstants.GET_CHANNEL_PROMO_EDIT), rs -> {
            if (rs.succeeded()) {
                logger.info("获取推广配置成功");
                context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3000).put("data", rs.result())));
            } else {
                logger.error("获取推广配置失败", rs.cause());
                context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3001).put("data", rs.cause())));
            }
        });
    }

    /**
     * 获取获取素材列表
     * get
     *
     * @param context
     */
    private void listFodderInfoHandler(RoutingContext context) {
        dbService.listDatas(sql.get(SqlConstants.FODDERINFO_LIST), rs -> {
            if (rs.succeeded()) {
                logger.info("获取素材列表成功");
                context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3000).put("data", rs.result())));
            } else {
                logger.error("获取素材列表失败", rs.cause());
                context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3001).put("data", rs.cause())));
            }
        });
    }

    /**
     * 添加获取素材列表
     * post
     *
     * @param context
     */
    private void addFodderInfoHandler(RoutingContext context) {
        String guid = context.getBodyAsJson().getString("guid");
        String fodderName = context.getBodyAsJson().getString("fodderName");
        String promoIconUrl = context.getBodyAsJson().getString("promoIconUrl");
        logger.info(guid);
        logger.info(fodderName);
        dbService.query(sql.get(SqlConstants.FODDERINFO_INSERT), new JsonArray().add(guid).add(fodderName), rs -> {
            if (rs.succeeded()) {
                logger.info("添加素材成功");
                context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3000)));
            } else {
                logger.error("添加素材失败", rs.cause());
                context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3001)));
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
            if (result == null) {
                context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3001).put("data", "修改失败:")));
            }
            context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3000).put("data", (result))));
        } catch (Exception e) {
            logger.error("获取外网存储内网ip失败" + e);
            context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3001).put("data", "获取失败:" + e)));
        }
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
            if (result == null) {
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
            if (result == null) {
                context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3001).put("data", "修改失败:")));
            }
            context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3000).put("data", result)));
        } catch (Exception e) {
            logger.error("获取应用列表失败" + e);
            context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3001).put("data", "获取失败:" + e)));
        }
    }

    /**
     * 获取应用列表
     * get
     * http://域名/redsystem/api/applist
     *
     * @param context
     */
    private void applistHandler(RoutingContext context) {
        try {
            String result = client.doGetClient("http://" + domainName + "/redsystem/api/applist");
            if (result == null) {
                context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3001).put("data", "获取失败:")));
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
            if (result == null) {
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
            if (result == null) {
                context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3001).put("data", "修改失败:")));
            }
            context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3000).put("data", (result))));
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
        String status = "";
        try {
            status = context.getBodyAsJson().getJsonObject("status").toString();
        } catch (Exception e) {
            status = context.getBodyAsJson().getJsonArray("status").toString();
        }
        logger.info(appId);
        logger.info(system);
        logger.info(status);
        List list = new ArrayList();
        JsonObject jsonObject = new JsonObject();
        jsonObject.put("key", "appId").put("value", appId);
        list.add(jsonObject);
        JsonObject jsonObject1 = new JsonObject();
        jsonObject1.put("key", "system").put("value", system);
        list.add(jsonObject1);
        JsonObject jsonObject2 = new JsonObject();
        jsonObject2.put("key", "status").put("value", status);
        list.add(jsonObject2);
        try {
            String result = client.doPostClient("http://" + domainName + "/redsystem/api/system", list);
            if (result == null) {
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
        String status = "";
        try {
            status = context.getBodyAsJson().getJsonObject("status").toString();
        } catch (Exception e) {
            status = context.getBodyAsJson().getJsonArray("status").toString();
        }
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
            if (result == null) {
                context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3001).put("data", "修改失败:")));
            }
            logger.info(result);
            context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3000).put("data", (result))));
        } catch (Exception e) {
            logger.error("修改存储ip失败" + e);
            context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("repcode", 3001).put("data", "修改失败:" + e)));
        }
    }

    /**
     * 获取七牛令牌
     *
     * @return
     */
    public QiNiu getToken() {
        QiNiu qiNiu = new QiNiu();
        String accessKey = "HrDIPPIoEngeu4kvvXLzL39g0g7v664B6pIAdILP";
        String secretKey = "7tJcPP-_hsoS6Kg4f3NBnJIy0Svk06YBEYcl1-3p";
        String bucket = "promo";
        long expireSeconds = 3600;   //过期时间
        StringMap putPolicy = new StringMap();
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket, null, expireSeconds, putPolicy);
        qiNiu.setKey(UUID.randomUUID().toString().replaceAll("\\-", ""));
        qiNiu.setToken(upToken);
        return qiNiu;
    }

    ;
}
