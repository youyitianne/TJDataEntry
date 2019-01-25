package http;

import database.PayDatabaseService;
import database.SqlConstants;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.ConfigConstants;
import service.DataOperation;
import service.InitConf;
import service.entity.PayObject;

import java.io.File;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

public class PayHttpVerticle extends AbstractVerticle {
    Logger logger = LoggerFactory.getLogger(PayHttpVerticle.class.getName());
    public static final String CONFIG_ACCOUNTDB_QUEUE = "paydb.queue";
    private PayDatabaseService dbService;
    private HashMap<SqlConstants, String> sql;
    InitConf initConf = new InitConf();
    private String payDbQueue;

    @Override
    public void start(Future<Void> startFuture) {
        //设置队列，创建代理
        payDbQueue = config().getString(CONFIG_ACCOUNTDB_QUEUE, "paydb.queue");
        dbService = PayDatabaseService.createProxy(vertx, CONFIG_ACCOUNTDB_QUEUE);
        Router router = Router.router(vertx);
        //设置上传文件缓存路径
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
        //文件上传
        router.post("/fileupload").handler(this::uploadHandler);
        //文件上传
        router.get("/paydata/:start/:end").handler(this::listPayDataHandler);

        //读取sql语句
        sql = initConf.loadSqlQueries();
        HashMap<ConfigConstants, String> conf = initConf.loadConfigQueries();
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

    private void listPayDataHandler(RoutingContext context) {
        String start = context.request().getParam("start");
        String end = context.request().getParam("end");
        Integer length = start.split("-").length;
        Long starttime = 0L;
        Long endtime = 0L;
        SimpleDateFormat sdf1  = new SimpleDateFormat("yyyy-MM-dd");
        if (length == 3) {
            try {
                starttime = sdf1.parse(start).getTime();
                endtime = sdf1.parse(end).getTime() + 1000L * 60 * 60 * 24 - 1;
            } catch (Exception e) {
                serviceError(context, "日期有错误");
                return;
            }
        } else if (length == 2) {
            try {
                starttime = sdf1.parse(start + "-01").getTime();
                endtime = sdf1.parse(getLastDay(end)).getTime() + 1000L * 60 * 60 * 24 - 1;
                System.out.println(start + "-01");
                System.out.println(getLastDay(end));
            } catch (Exception e) {
                e.printStackTrace();
                serviceError(context, "日期有错误");
                return;
            }
            sdf1 = new SimpleDateFormat("yyyy-MM");
        }
        SimpleDateFormat sdf = sdf1;
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(starttime).add(endtime);
        dbService.fetchDatas(sql.get(SqlConstants.PAY_LIST), jsonArray, rs -> {
            if (rs.succeeded()) {
                List<JsonObject> jsonObjects = rs.result();
                for (int i = 0; i < jsonObjects.size(); i++) {
                    JsonObject jsonObject = jsonObjects.get(i);
                    jsonObject.put("order_time1", sdf.format(new Date(jsonObject.getLong("order_time"))));
                    jsonObject.put("refund_time", jsonObject.getLong("refund_time") == 0 ? 0 : sdf.format(new Date(jsonObject.getLong("refund_time"))));
                    jsonObject.put("payment", Integer.parseInt(new BigDecimal(Double.valueOf(jsonObject.getString("payment"))).setScale(0, BigDecimal.ROUND_HALF_UP).toString()));
                    jsonObjects.set(i, jsonObject);
                }
                logger.info("查询成功");
                context.response().end(Json.encodePrettily(new JsonObject().put("code", 20000).put("data", jsonObjects)));
            } else {
                logger.error("支付信息查询失败-----》" + rs.cause().toString());
                serviceError(context, "查询失败---》" + rs.cause().toString());
            }
        });

    }

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
            case "华为":
                this.uploadHuaweiDataHandler(context, channel);
                break;
            default:
                logger.error("上传文件格式有问题---》" + filename);
                serviceError(context, filename + "     请检查上传文件的格式");
                break;
        }
    }

    private void uploadHuaweiDataHandler(RoutingContext context, String channel) {
        DataOperation dataOperation = new DataOperation(vertx);
        Set<FileUpload> fileUploads = context.fileUploads();
        String filename = "";
        String uploadfilename = "";
        for (FileUpload f : fileUploads) {
            filename = f.fileName();
            uploadfilename = f.uploadedFileName();
        }
        fileClassification(uploadfilename, filename).setHandler(rs -> {
            if (rs.succeeded()) {
                String newpath = rs.result();
                dataOperation.huaweiOperation(newpath, "huawei").setHandler(result -> {
                    if (result.succeeded()) {
                        List<PayObject> list = result.result();
                        List<JsonArray> jsonArrays = toJsonArrayList(list);
                        dbService.batch(sql.get(SqlConstants.PAY_INSERT), jsonArrays, result1 -> {
                            if (result1.succeeded()) {
                                removeRepeat(sql.get(SqlConstants.PAY_REPEAT_SELECT),sql.get(SqlConstants.PAY_REPEAT_DELETE)).setHandler(remove->{
                                   if (remove.succeeded()){
                                       context.response().setStatusCode(200).end(Json.encodePrettily(new JsonObject().put("data", "上传成功~")));
                                   }else {
                                       logger.error(remove.cause().toString());
                                       serviceError(context, Json.encodePrettily(new JsonObject().put("data", "上传失败----》" + remove.cause().toString() + "~")));
                                   }
                                });
                            } else {
                                logger.error(result1.cause().toString());
                                serviceError(context, Json.encodePrettily(new JsonObject().put("data", "上传失败----》" + result1.cause().toString() + "~")));
                            }
                        });
                    } else {
                        logger.error(result.cause().toString());
                        serviceError(context, Json.encodePrettily(new JsonObject().put("data", "上传失败----》" + result.cause().toString() + "~")));
                    }
                });
            } else {
                logger.error(rs.cause().toString());
                serviceError(context, Json.encodePrettily(new JsonObject().put("data", "上传失败----》" + rs.cause().toString() + "~")));
            }
        });
    }

    private List<JsonArray> toJsonArrayList(List<PayObject> payObjects) {
        List<JsonArray> jsonArrays = new ArrayList<>();
        for (int i = 0; i < payObjects.size(); i++) {
            PayObject payObject = payObjects.get(i);
            JsonArray jsonArray = new JsonArray();
            jsonArray.add(payObject.getOrder_number())
                    .add(payObject.getRequest_number())
                    .add(payObject.getOrder_time())
                    .add(payObject.getPayment_time())
                    .add(payObject.getDuration())
                    .add(payObject.getApp_name())
                    .add(payObject.getProduct_name())
                    .add(payObject.getPayment())
                    .add(payObject.getCurrency_type())
                    .add(payObject.getPay_type())
                    .add(payObject.getMarketint_channel())
                    .add(payObject.getOrder_status())
                    .add(payObject.getOrder_result())
                    .add(payObject.getBussiness_type())
                    .add(payObject.getOriginal_order())
                    .add(payObject.getRefund_time())
                    .add(payObject.getRefund_amount())
                    .add(payObject.getCountry())
                    .add(payObject.getChannel());
            jsonArrays.add(jsonArray);
        }
        System.out.println(jsonArrays);
        return jsonArrays;
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
        File file = new File("TJPayEntry" + File.separator + "file-uploads" + File.separator + date);
        if (!file.exists()) {
            file.mkdirs();
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
     * 获取一个月的最有一天
     * @param time
     * @return
     */
    public static String getLastDay(String time){
        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar=Calendar.getInstance();
        calendar.set(Calendar.YEAR, Integer.parseInt(time.split("-")[0]));
        calendar.set(Calendar.MONTH, Integer.parseInt(time.split("-")[1])-1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return sf.format(calendar.getTime());
    }

    /**
     * 查找表内重复数据
     * 只保留id最大的
     *
     * @param sel
     * @param del
     * @return
     */
    public Future<Boolean> removeRepeat(String sel, String del) {
        Future<Boolean> future = Future.future();
        dbService.listDatas(sel, rs -> {
            if (rs.succeeded()) {
                List<JsonObject> list = rs.result();
                if (list.size() == 0) {
                    future.complete(true);
                } else {
                    StringBuffer buffer = new StringBuffer();
                    for (int i = 0; i < list.size(); i++) {
                        if (i == 0) {
                            buffer.append("(");
                        }
                        buffer.append(list.get(i).getValue("id"));
                        if (i == (list.size() - 1)) {
                            buffer.append(");");
                        } else {
                            buffer.append(",");
                        }
                    }
                    String delete = del + buffer;
                    dbService.query(delete, result -> {
                        future.complete(true);
                    });
                }
            } else {
                future.complete(true);
            }
        });
        return future;
    }

    /**
     * 503
     */
    private void serviceError(RoutingContext context, String string) {
        context.response().setStatusCode(503).end(string);
    }
}
