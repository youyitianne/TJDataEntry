package http;

import database.PayDatabaseService;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.web.FileUpload;
import io.vertx.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.DataOperation;

import java.util.Set;

public class PayOperationLog {
    Logger logger = LoggerFactory.getLogger(PayOperationLog.class);

    /**
     * @param databaseService
     * @param context
     * @param requestPath
     * @param requestMethod
     * @param name
     * @param time
     * @param ip
     */
    public void operationLog(String uploadfilename,
                             String sql,
                             PayDatabaseService databaseService,
                             RoutingContext context,
                             String requestPath,
                             String requestMethod,
                             String name,
                             String time,
                             String ip,
                             Long date) {
        switch (requestMethod) {
            case "GET":
                if (requestPath.contains("/paydata")) {
                    logger.info("-----------------");
                    logger.info("账号：" + name);
                    logger.info("时间：" + time);
                    logger.info("ip：" + ip);
                    logger.info("请求路径：" + requestPath);  ///user/info
                    logger.info("请求方式：" + requestMethod);  ///GET
                    logger.info("模块：支付管理");
                    logger.info("操作说明：支付管理数据获取");
                    logger.info("参数：无");
                    logger.info("****************");
                    databaseService.queryByParams(sql, new JsonArray().add(name).add(time).add(ip).add("支付管理")
                            .add(requestPath).add(requestMethod).add("支付管理数据获取").add("无").add(date).add(context.request().getHeader("UserAgent")), rs -> {
                        if (rs.failed()) {
                            logger.error("插入日志失败---》" + rs.cause());
                        }
                    });
                    break;
                }
            case "POST":
                if (requestPath.contains("/fileupload")) {
                    logger.info("-----------------");
                    logger.info("账号：" + name);
                    logger.info("时间：" + time);
                    logger.info("ip：" + ip);
                    logger.info("请求路径：" + requestPath);  ///user/info
                    logger.info("请求方式：" + requestMethod);  ///GET
                    logger.info("模块：支付管理");
                    logger.info("操作说明：支付管理数据上传");
                    logger.info("参数：无");
                    logger.info("****************");
                    if (databaseService != null) {
                        System.out.println(10002);
                    }
                    databaseService.queryByParams(sql, new JsonArray().add(name).add(time).add(ip).add("支付管理")
                            .add(requestPath).add(requestMethod).add("支付管理数据上传").add(uploadfilename).add(date).add(context.request().getHeader("User-Agent")), rs -> {
                        if (rs.failed()) {
                            logger.error("插入日志失败---》" + rs.cause());
                        }
                    });
                    break;
                }
        }
    }
}
