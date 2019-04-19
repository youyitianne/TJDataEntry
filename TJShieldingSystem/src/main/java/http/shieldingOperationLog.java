package http;

import database.RedDatabaseService;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class shieldingOperationLog {
    Logger logger = LoggerFactory.getLogger(shieldingOperationLog.class);

    /**
     * @param databaseService
     * @param context
     * @param requestPath
     * @param requestMethod
     * @param name
     * @param time
     * @param ip
     */
    public void operationLog(String sql,
                             RedDatabaseService databaseService,
                             RoutingContext context,
                             String requestPath,
                             String requestMethod,
                             String name,
                             String time,
                             String ip,
                             Long date
    ) {
        switch (requestMethod) {
            case "GET":
                break;
            case "POST":
                if (requestPath.contains("/api/system/getip")) {
                    logger.info("-----------------");
                    logger.info("账号：" + name);
                    logger.info("时间：" + time);
                    logger.info("ip：" + ip);
                    logger.info("请求路径：" + requestPath);  ///user/info
                    logger.info("请求方式：" + requestMethod);  ///GET
                    logger.info("模块：测试模式");
                    logger.info("操作说明：获取内网ip");
                    logger.info("参数：无");
                    logger.info("****************");
                    databaseService.query(sql, new JsonArray().add(name).add(time).add(ip).add("测试模式")
                            .add(requestPath).add(requestMethod).add("获取内网ip").add("无").add(date).add(context.request().getHeader("UserAgent")), rs -> {
                        if (rs.failed()) {
                            logger.error("插入日志失败---》" + rs.cause());
                        }
                    });
                    break;
                }
                if (requestPath.contains("/api/system/postip")) {
                    logger.info("-----------------");
                    logger.info("账号：" + name);
                    logger.info("时间：" + time);
                    logger.info("ip：" + ip);
                    logger.info("请求路径：" + requestPath);  ///user/info
                    logger.info("请求方式：" + requestMethod);  ///GET
                    logger.info("模块：测试模式");
                    logger.info("操作说明：修改内网ip");
                    logger.info("参数：无");
                    logger.info("****************");
                    databaseService.query(sql, new JsonArray().add(name).add(time).add(ip).add("测试模式")
                            .add(requestPath).add(requestMethod).add("修改内网ip").add("无").add(date).add(context.request().getHeader("UserAgent")), rs -> {
                        if (rs.failed()) {
                            logger.error("插入日志失败---》" + rs.cause());
                        }
                    });
                    break;
                }
                if (requestPath.contains("/api/system/getapplist")) {
                    logger.info("-----------------");
                    logger.info("账号：" + name);
                    logger.info("时间：" + time);
                    logger.info("ip：" + ip);
                    logger.info("请求路径：" + requestPath);  ///user/info
                    logger.info("请求方式：" + requestMethod);  ///GET
                    logger.info("模块：屏蔽系统");
                    logger.info("操作说明：获取应用列表");
                    logger.info("参数：无");
                    logger.info("****************");
                    databaseService.query(sql, new JsonArray().add(name).add(time).add(ip).add("屏蔽系统")
                            .add(requestPath).add(requestMethod).add("获取应用列表").add("无").add(date).add(context.request().getHeader("UserAgent")), rs -> {
                        if (rs.failed()) {
                            logger.error("插入日志失败---》" + rs.cause());
                        }
                    });
                    break;
                }
                if (requestPath.contains("/api/system/searchapp")) {
                    logger.info("-----------------");
                    logger.info("账号：" + name);
                    logger.info("时间：" + time);
                    logger.info("ip：" + ip);
                    logger.info("请求路径：" + requestPath);  ///user/info
                    logger.info("请求方式：" + requestMethod);  ///GET
                    logger.info("模块：屏蔽系统");
                    logger.info("操作说明：搜索应用");
                    logger.info("参数：无");
                    logger.info("****************");
                    databaseService.query(sql, new JsonArray().add(name).add(time).add(ip).add("屏蔽系统")
                            .add(requestPath).add(requestMethod).add("搜索应用").add("无").add(date).add(context.request().getHeader("UserAgent")), rs -> {
                        if (rs.failed()) {
                            logger.error("插入日志失败---》" + rs.cause());
                        }
                    });
                    break;
                }
                if (requestPath.contains("/api/system/getparams")) {
                    logger.info("-----------------");
                    logger.info("账号：" + name);
                    logger.info("时间：" + time);
                    logger.info("ip：" + ip);
                    logger.info("请求路径：" + requestPath);  ///user/info
                    logger.info("请求方式：" + requestMethod);  ///GET
                    logger.info("模块：屏蔽系统");
                    logger.info("操作说明：获取应用全部系统参数信息");
                    logger.info("参数：无");
                    logger.info("****************");
                    databaseService.query(sql, new JsonArray().add(name).add(time).add(ip).add("屏蔽系统")
                            .add(requestPath).add(requestMethod).add("获取应用全部系统参数信息").add("无").add(date).add(context.request().getHeader("UserAgent")), rs -> {
                        if (rs.failed()) {
                            logger.error("插入日志失败---》" + rs.cause());
                        }
                    });
                    break;
                }
                if (requestPath.contains("/api/system/addparams")) {
                    logger.info("-----------------");
                    logger.info("账号：" + name);
                    logger.info("时间：" + time);
                    logger.info("ip：" + ip);
                    logger.info("请求路径：" + requestPath);  ///user/info
                    logger.info("请求方式：" + requestMethod);  ///GET
                    logger.info("模块：屏蔽系统");
                    logger.info("操作说明：添加应用系统参数");
                    logger.info("参数：无");
                    logger.info("****************");
                    databaseService.query(sql, new JsonArray().add(name).add(time).add(ip).add("屏蔽系统")
                            .add(requestPath).add(requestMethod).add("添加应用系统参数").add(
                                    context.getBodyAsJson().getString("appId") +
                                            context.getBodyAsJson().getString("system") +
                                            context.getBodyAsJson().getJsonObject("status").toString()
                            ).add(date).add(context.request().getHeader("UserAgent")), rs -> {
                        if (rs.failed()) {
                            logger.error("插入日志失败---》" + rs.cause());
                        }
                    });
                    break;
                }
                if (requestPath.contains("/api/system/editparams")) {
                    logger.info("-----------------");
                    logger.info("账号：" + name);
                    logger.info("时间：" + time);
                    logger.info("ip：" + ip);
                    logger.info("请求路径：" + requestPath);  ///user/info
                    logger.info("请求方式：" + requestMethod);  ///GET
                    logger.info("模块：屏蔽系统");
                    logger.info("操作说明：编辑应用系统参数");
                    logger.info("参数：无");
                    logger.info("****************");
                    databaseService.query(sql, new JsonArray().add(name).add(time).add(ip).add("屏蔽系统")
                            .add(requestPath).add(requestMethod).add("编辑应用系统参数").add(
                                    context.getBodyAsJson().getInteger("appId").toString() +
                                            context.getBodyAsJson().getString("system") +
                                            context.getBodyAsJson().getJsonObject("status").toString()).add(date).add(context.request().getHeader("UserAgent")), rs -> {
                        if (rs.failed()) {
                            logger.error("插入日志失败---》" + rs.cause());
                        }
                    });
                    break;
                }
            case "PATCH":
                break;
        }
    }
}
