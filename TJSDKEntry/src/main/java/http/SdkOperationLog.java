package http;

import database.SdkDatabaseService;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SdkOperationLog {
    Logger logger= LoggerFactory.getLogger(SdkOperationLog.class);
    /**
     *
     * @param databaseService
     * @param context
     * @param requestPath
     * @param requestMethod
     * @param name
     * @param time
     * @param ip
     */
    public void operationLog (String sql,
                              SdkDatabaseService databaseService,
                              RoutingContext context,
                              String requestPath,
                              String requestMethod,
                              String name,
                              String time,
                              String ip,
                              Long date
            ){
        switch (requestMethod){
            case "GET":
                if (requestPath.contains("/api/projectconfig_publish")){
                    logger.info("-----------------");
                    logger.info("账号："+name);
                    logger.info("时间："+time);
                    logger.info("ip："+ip);
                    logger.info("请求路径："+requestPath);  ///user/info
                    logger.info("请求方式："+requestMethod);  ///GET
                    logger.info("模块：项目配置表管理");
                    logger.info("操作说明：获取项目配置表发布记录");
                    logger.info("参数：无");
                    logger.info("****************");
                    databaseService.query(sql,new JsonArray().add(name).add(time).add(ip).add("项目配置表管理")
                            .add(requestPath).add(requestMethod).add("获取项目配置表发布记录").add("无").add(date).add(context.request().getHeader("UserAgent")),rs->{
                        if (rs.failed()) {
                            logger.error("插入日志失败---》"+rs.cause());
                        }
                    });
                    break;
                }
                if (requestPath.contains("/api/projectconfig")){
                    logger.info("-----------------");
                    logger.info("账号："+name);
                    logger.info("时间："+time);
                    logger.info("ip："+ip);
                    logger.info("请求路径："+requestPath);  ///user/info
                    logger.info("请求方式："+requestMethod);  ///GET
                    logger.info("模块：项目配置表管理");
                    logger.info("操作说明：获取所有项目配置表");
                    logger.info("参数：无");
                    logger.info("****************");
                    databaseService.query(sql,new JsonArray().add(name).add(time).add(ip).add("项目配置表管理")
                                    .add(requestPath).add(requestMethod).add("获取所有项目配置表").add("无").add(date).add(context.request().getHeader("UserAgent")),rs->{
                                if (rs.failed()) {
                                    logger.error("插入日志失败---》"+rs.cause());
                                }
                            });
                    break;
                }
                if (requestPath.contains("/api/listtemplate")){
                    logger.info("-----------------");
                    logger.info("账号："+name);
                    logger.info("时间："+time);
                    logger.info("ip："+ip);
                    logger.info("请求路径："+requestPath);  ///user/info
                    logger.info("请求方式："+requestMethod);  ///GET
                    logger.info("模块：项目配置表管理");
                    logger.info("操作说明：获取有效的sdk模板");
                    logger.info("参数：无");
                    logger.info("****************");
                    databaseService.query(sql,new JsonArray().add(name).add(time).add(ip).add("项目配置表管理")
                            .add(requestPath).add(requestMethod).add("获取有效的sdk模板").add("无").add(date).add(context.request().getHeader("UserAgent")),rs->{
                        if (rs.failed()) {
                            logger.error("插入日志失败---》"+rs.cause());
                        }
                    });
                    break;
                }
                if (requestPath.contains("/api/sdk")){
                    logger.info("-----------------");
                    logger.info("账号："+name);
                    logger.info("时间："+time);
                    logger.info("ip："+ip);
                    logger.info("请求路径："+requestPath);  ///user/info
                    logger.info("请求方式："+requestMethod);  ///GET
                    logger.info("模块：SDK模板管理");
                    logger.info("操作说明：获取SDK模板列表");
                    logger.info("参数：无");
                    logger.info("****************");
                    databaseService.query(sql,new JsonArray().add(name).add(time).add(ip).add("SDK模板管理")
                            .add(requestPath).add(requestMethod).add("获取SDK模板列表").add("无").add(date).add(context.request().getHeader("UserAgent")),rs->{
                        if (rs.failed()) {
                            logger.error("插入日志失败---》"+rs.cause());
                        }
                    });
                    break;
                }

            case "POST":
                if (requestPath.contains("/api/projectconfig")){
                    logger.info("-----------------");
                    logger.info("账号："+name);
                    logger.info("时间："+time);
                    logger.info("ip："+ip);
                    logger.info("请求路径："+requestPath);  ///user/info
                    logger.info("请求方式："+requestMethod);  ///GET
                    logger.info("模块：项目配置表管理");
                    logger.info("操作说明：添加项目配置表");
                    logger.info("参数：无");
                    logger.info("****************");
                    databaseService.query(sql,new JsonArray().add(name).add(time).add(ip).add("项目配置表管理")
                            .add(requestPath).add(requestMethod).add("添加项目配置表").add("无").add(date).add(context.request().getHeader("UserAgent")),rs->{
                        if (rs.failed()) {
                            logger.error("插入日志失败---》"+rs.cause());
                        }
                    });
                    break;
                }
                if (requestPath.contains("/api/sdk")){
                    logger.info("-----------------");
                    logger.info("账号："+name);
                    logger.info("时间："+time);
                    logger.info("ip："+ip);
                    logger.info("请求路径："+requestPath);  ///user/info
                    logger.info("请求方式："+requestMethod);  ///GET
                    logger.info("模块：SDK模板管理");
                    logger.info("操作说明：添加sdk模板");
                    logger.info("参数：无");
                    logger.info("****************");
                    databaseService.query(sql,new JsonArray().add(name).add(time).add(ip).add("SDK模板管理")
                            .add(requestPath).add(requestMethod).add("添加项目配置表").add("无").add(date).add(context.request().getHeader("UserAgent")),rs->{
                        if (rs.failed()) {
                            logger.error("插入日志失败---》"+rs.cause());
                        }
                    });
                    break;
                }
                if (requestPath.contains("/api/publish_delete")){
                    logger.info("-----------------");
                    logger.info("账号："+name);
                    logger.info("时间："+time);
                    logger.info("ip："+ip);
                    logger.info("请求路径："+requestPath);  ///user/info
                    logger.info("请求方式："+requestMethod);  ///GET
                    logger.info("模块：项目配置表管理");
                    logger.info("操作说明：删除项目配置表发布记录");
                    logger.info("参数：无");
                    logger.info("****************");
                    databaseService.query(sql,new JsonArray().add(name).add(time).add(ip).add("项目配置表管理")
                            .add(requestPath).add(requestMethod).add("删除项目配置表发布记录").add("无").add(date).add(context.request().getHeader("UserAgent")),rs->{
                        if (rs.failed()) {
                            logger.error("插入日志失败---》"+rs.cause());
                        }
                    });
                    break;
                }

            case "PATCH":
                if (requestPath.contains("/api/projectconfig")){
                    logger.info("-----------------");
                    logger.info("账号："+name);
                    logger.info("时间："+time);
                    logger.info("ip："+ip);
                    logger.info("请求路径："+requestPath);  ///user/info
                    logger.info("请求方式："+requestMethod);  ///GET
                    logger.info("模块：项目配置表管理");
                    logger.info("操作说明：删除项目配置表发布记录");
                    logger.info("参数：无");
                    logger.info("****************");
                    databaseService.query(sql,new JsonArray().add(name).add(time).add(ip).add("项目配置表管理")
                            .add(requestPath).add(requestMethod).add("删除项目配置表发布记录").add("无").add(date).add(context.request().getHeader("UserAgent")),rs->{
                        if (rs.failed()) {
                            logger.error("插入日志失败---》"+rs.cause());
                        }
                    });
                    break;
                }
                if (requestPath.contains("/api/sdk")){
                    logger.info("-----------------");
                    logger.info("账号："+name);
                    logger.info("时间："+time);
                    logger.info("ip："+ip);
                    logger.info("请求路径："+requestPath);  ///user/info
                    logger.info("请求方式："+requestMethod);  ///GET
                    logger.info("模块：SDK模板管理");
                    logger.info("操作说明：更新sdk模板");
                    logger.info("参数：无");
                    logger.info("****************");
                    databaseService.query(sql,new JsonArray().add(name).add(time).add(ip).add("SDK模板管理")
                            .add(requestPath).add(requestMethod).add("更新sdk模板").add("无").add(date).add(context.request().getHeader("UserAgent")),rs->{
                        if (rs.failed()) {
                            logger.error("插入日志失败---》"+rs.cause());
                        }
                    });
                    break;
                }
                break;
        }
    }
}
