package http;

import database.AccountDatabaseService;
import database.SqlQuery;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccountOperationLog {
    Logger logger= LoggerFactory.getLogger(AccountOperationLog.class);
    /**
     *
     * @param requestPath 请求路径
     * @param requestMethod 请求方式
     */
    public void operationLog (AccountDatabaseService databaseService,
                              RoutingContext context,
                              String requestPath,
                              String requestMethod,
                              String name,
                              String time,
                              String ip,
                              Long date){
        switch (requestMethod){
            case "GET":
                if (requestPath.contains("/api/getchannel")){
                    logger.info("-----------------");
                    logger.info("账号："+name);
                    logger.info("时间："+time);
                    logger.info("ip："+ip);
                    logger.info("请求路径："+requestPath);  ///user/info
                    logger.info("请求方式："+requestMethod);  ///GET
                    logger.info("模块：测试模块");
                    logger.info("操作说明：测试");
                    logger.info("参数：无");
                    logger.info("****************");
                    databaseService.query(SqlQuery.INSERT_LOG,
                            new JsonArray().add(name).add(time).add(ip).add("测试模块")
                            .add(requestPath).add(requestMethod).add("测试").add("无").add(date).add(context.request().getHeader("UserAgent")),rs->{
                        if (rs.failed()) {
                            logger.error("插入日志失败---》"+rs.cause());
                        }
                    });
                    break;
                }
                if (requestPath.contains("/api/resource")){
                    logger.info("-----------------");
                    logger.info("账号："+name);
                    logger.info("时间："+time);
                    logger.info("ip："+ip);
                    logger.info("请求路径："+requestPath);  ///user/info
                    logger.info("请求方式："+requestMethod);  ///GET
                    logger.info("模块：权限管理");
                    logger.info("操作说明：获取角色权限列表");
                    logger.info("参数：无");
                    logger.info("****************");
                    databaseService.query(SqlQuery.INSERT_LOG,
                            new JsonArray().add(name).add(time).add(ip).add("权限管理")
                                    .add(requestPath).add(requestMethod).add("获取角色权限列表").add("无").add(date).add(context.request().getHeader("UserAgent")),rs->{
                                if (rs.failed()) {
                                    logger.error("插入日志失败---》"+rs.cause());
                                }
                            });
                    break;
                }
                if (requestPath.contains("/user/info")){
                    logger.info("-----------------");
                    logger.info("账号："+name);
                    logger.info("时间："+time);
                    logger.info("ip："+ip);
                    logger.info("请求路径："+requestPath);  ///user/info
                    logger.info("请求方式："+requestMethod);  ///GET
                    logger.info("模块：账号管理");
                    logger.info("操作说明：获取账号权限(登录)");
                    logger.info("参数：无");
                    logger.info("****************");
                    databaseService.query(SqlQuery.INSERT_LOG,
                            new JsonArray().add(name).add(time).add(ip).add("账号管理")
                                    .add(requestPath).add(requestMethod).add("获取账号权限(登录)").add("无").add(date).add(context.request().getHeader("UserAgent")),rs->{
                                if (rs.failed()) {
                                    logger.error("插入日志失败---》"+rs.cause());
                                }
                            });
                    break;
                }
                if (requestPath.contains("/api/account")){
                    logger.info("-----------------");
                    logger.info("账号："+name);
                    logger.info("时间："+time);
                    logger.info("ip："+ip);
                    logger.info("请求路径："+requestPath);  ///user/info
                    logger.info("请求方式："+requestMethod);  ///GET
                    logger.info("模块：账号管理");
                    logger.info("操作说明：获取账号列表");
                    logger.info("参数：无");
                    logger.info("****************");
                    databaseService.query(SqlQuery.INSERT_LOG,
                            new JsonArray().add(name).add(time).add(ip).add("账号管理")
                                    .add(requestPath).add(requestMethod).add("获取账号列表").add("无").add(date).add(context.request().getHeader("UserAgent")),rs->{
                                if (rs.failed()) {
                                    logger.error("插入日志失败---》"+rs.cause());
                                }
                            });
                    break;
                }
                if (requestPath.contains("/api/role")){
                    logger.info("-----------------");
                    logger.info("账号："+name);
                    logger.info("时间："+time);
                    logger.info("ip："+ip);
                    logger.info("请求路径："+requestPath);  ///user/info
                    logger.info("请求方式："+requestMethod);  ///GET
                    logger.info("模块：角色管理");
                    logger.info("操作说明：获取角色列表");
                    logger.info("参数：无");
                    logger.info("****************");
                    databaseService.query(SqlQuery.INSERT_LOG,
                            new JsonArray().add(name).add(time).add(ip).add("账号管理")
                                    .add(requestPath).add(requestMethod).add("获取角色列表").add("无").add(date).add(context.request().getHeader("UserAgent")),rs->{
                                if (rs.failed()) {
                                    logger.error("插入日志失败---》"+rs.cause());
                                }
                            });
                    break;
                }
                if (requestPath.contains("/api/perms")){
                    logger.info("-----------------");
                    logger.info("账号："+name);
                    logger.info("时间："+time);
                    logger.info("ip："+ip);
                    logger.info("请求路径："+requestPath);  ///user/info
                    logger.info("请求方式："+requestMethod);  ///GET
                    logger.info("模块：权限管理");
                    logger.info("操作说明：获取角色权限列表");
                    logger.info("参数：无");
                    logger.info("****************");
                    databaseService.query(SqlQuery.INSERT_LOG,
                            new JsonArray().add(name).add(time).add(ip).add("权限管理")
                                    .add(requestPath).add(requestMethod).add("获取角色权限列表").add("无").add(date).add(context.request().getHeader("UserAgent")),rs->{
                                if (rs.failed()) {
                                    logger.error("插入日志失败---》"+rs.cause());
                                }
                            });
                    break;
                }
            case "POST":
                if (requestPath.contains("/updatePassword")){
                    logger.info("-----------------");
                    logger.info("账号："+name);
                    logger.info("时间："+time);
                    logger.info("ip："+ip);
                    logger.info("请求路径："+requestPath);  ///user/info
                    logger.info("请求方式："+requestMethod);  ///GET
                    logger.info("模块：日志管理");
                    logger.info("操作说明：查看操作日志");
                    logger.info("参数：无");
                    logger.info(context.request().getHeader("UserAgent"));
                    logger.info("****************");
                    databaseService.query(SqlQuery.INSERT_LOG,
                            new JsonArray().add(name).add(time).add(ip).add("账号管理")
                                    .add(requestPath).add(requestMethod).add("修改个人密码").add(context.getBodyAsJson().getString("password")).add(date).add(context.request().getHeader("UserAgent")),rs->{
                                if (rs.failed()) {
                                    logger.error("插入日志失败---》"+rs.cause());
                                }
                            });
                    break;
                }
                if (requestPath.contains("/operationlog")){
                    logger.info("-----------------");
                    logger.info("账号："+name);
                    logger.info("时间："+time);
                    logger.info("ip："+ip);
                    logger.info("请求路径："+requestPath);  ///user/info
                    logger.info("请求方式："+requestMethod);  ///GET
                    logger.info("模块：日志管理");
                    logger.info("操作说明：查看操作日志");
                    logger.info("参数：无");
                    logger.info(context.request().getHeader("UserAgent"));
                    logger.info("****************");
                    databaseService.query(SqlQuery.INSERT_LOG,
                            new JsonArray().add(name).add(time).add(ip).add("日志管理")
                                    .add(requestPath).add(requestMethod).add("查看操作日志").add("无").add(date).add(context.request().getHeader("UserAgent")),rs->{
                                if (rs.failed()) {
                                    logger.error("插入日志失败---》"+rs.cause());
                                }
                            });
                    break;
                }
                if (requestPath.contains("/api/getresourcelist")){
                    logger.info("-----------------");
                    logger.info("账号："+name);
                    logger.info("时间："+time);
                    logger.info("ip："+ip);
                    logger.info("请求路径："+requestPath);  ///user/info
                    logger.info("请求方式："+requestMethod);  ///GET
                    logger.info("模块：资源管理");
                    logger.info("操作说明：根据资源分配获取项目列表");
                    logger.info("参数：无");
                    logger.info("****************");
                    databaseService.query(SqlQuery.INSERT_LOG,
                            new JsonArray().add(name).add(time).add(ip).add("资源管理")
                                    .add(requestPath).add(requestMethod).add("根据资源分配获取项目列表").add("无").add(date).add(context.request().getHeader("UserAgent")),rs->{
                                if (rs.failed()) {
                                    logger.error("插入日志失败---》"+rs.cause());
                                }
                            });
                    break;
                }
                if (requestPath.contains("/api/resource")){
                    logger.info("-----------------");
                    logger.info("账号："+name);
                    logger.info("时间："+time);
                    logger.info("ip："+ip);
                    logger.info("请求路径："+requestPath);  ///user/info
                    logger.info("请求方式："+requestMethod);  ///GET
                    logger.info("模块：资源管理");
                    logger.info("操作说明：账号资源添加");
                    logger.info("参数：");
                    String resources = context.request().getParam("resources");
                    String username = context.request().getParam("username");
                    String username_mark = context.request().getParam("username_mark");
                    String resources_name=context.request().getParam("resources_name");
                    logger.info("****************");
                    databaseService.query(SqlQuery.INSERT_LOG,
                            new JsonArray().add(name).add(time).add(ip).add("资源管理")
                                    .add(requestPath).add(requestMethod).add("账号资源添加").add(resources).add(date).add(context.request().getHeader("UserAgent")),rs->{
                                if (rs.failed()) {
                                    logger.error("插入日志失败---》"+rs.cause());
                                }
                            });
                    break;
                }
                if (requestPath.contains("/api/perms")){
                    logger.info("-----------------");
                    logger.info("账号："+name);
                    logger.info("时间："+time);
                    logger.info("ip："+ip);
                    logger.info("请求路径："+requestPath);  ///user/info
                    logger.info("请求方式："+requestMethod);  ///GET
                    logger.info("模块：权限管理");
                    logger.info("操作说明：账号角色权限添加");
                    logger.info("参数：");
                    String permissions = context.request().getParam("permissions");
                    String username = context.request().getParam("username");
                    String username_mark = context.request().getParam("username_mark");
                    logger.info("****************");
                    databaseService.query(SqlQuery.INSERT_LOG,
                            new JsonArray().add(name).add(time).add(ip).add("权限管理")
                                    .add(requestPath).add(requestMethod).add("账号角色权限添加").add(permissions).add(date).add(context.request().getHeader("UserAgent")),rs->{
                                if (rs.failed()) {
                                    logger.error("插入日志失败---》"+rs.cause());
                                }
                            });
                    break;
                }
                if (requestPath.contains("/login/login")){
                    logger.info("-----------------");
                    logger.info("账号："+name);
                    logger.info("时间："+time);
                    logger.info("ip："+ip);
                    logger.info("请求路径："+requestPath);  ///user/info
                    logger.info("请求方式："+requestMethod);  ///GET
                    logger.info("模块：账号管理");
                    logger.info("操作说明：登陆");
                    logger.info("参数：无");
                    logger.info("****************");
                    databaseService.query(SqlQuery.INSERT_LOG,
                            new JsonArray().add(name).add(time).add(ip).add("账号管理")
                                    .add(requestPath).add(requestMethod).add("登陆").add("无").add(date).add(context.request().getHeader("UserAgent")),rs->{
                                if (rs.failed()) {
                                    logger.error("插入日志失败---》"+rs.cause());
                                }
                            });
                    break;
                }
                if (requestPath.contains("/login/logout")){
                    logger.info("-----------------");
                    logger.info("账号："+name);
                    logger.info("时间："+time);
                    logger.info("ip："+ip);
                    logger.info("请求路径："+requestPath);  ///user/info
                    logger.info("请求方式："+requestMethod);  ///GET
                    logger.info("模块：账号管理");
                    logger.info("操作说明：登出");
                    logger.info("参数：无");
                    logger.info("****************");
                    databaseService.query(SqlQuery.INSERT_LOG,
                            new JsonArray().add(name).add(time).add(ip).add("账号管理")
                                    .add(requestPath).add(requestMethod).add("登出").add("无").add(date).add(context.request().getHeader("UserAgent")),rs->{
                                if (rs.failed()) {
                                    logger.error("插入日志失败---》"+rs.cause());
                                }
                            });
                    break;
                }
                if (requestPath.contains("/api/account")){
                    logger.info("-----------------");
                    logger.info("账号："+name);
                    logger.info("时间："+time);
                    logger.info("ip："+ip);
                    logger.info("请求路径："+requestPath);  ///user/info
                    logger.info("请求方式："+requestMethod);  ///GET
                    logger.info("模块：账号管理");
                    logger.info("操作说明：添加账号");
                    logger.info("参数：无");
                    String name1 = context.request().getParam("username");
                    String password = context.request().getParam("psd");
                    String note = context.request().getParam("note");
                    String position = context.request().getParam("position");
                    String department = context.request().getParam("department");
                    logger.info("****************");
                    databaseService.query(SqlQuery.INSERT_LOG,
                            new JsonArray().add(name).add(time).add(ip).add("账号管理")
                                    .add(requestPath).add(requestMethod).add("添加账号").add(name1+password+note+position+department).add(date).add(context.request().getHeader("UserAgent")),rs->{
                                if (rs.failed()) {
                                    logger.error("插入日志失败---》"+rs.cause());
                                }
                            });
                    break;
                }
                if (requestPath.contains("/api/role")){
                    logger.info("-----------------");
                    logger.info("账号："+name);
                    logger.info("时间："+time);
                    logger.info("ip："+ip);
                    logger.info("请求路径："+requestPath);  ///user/info
                    logger.info("请求方式："+requestMethod);  ///GET
                    logger.info("模块：角色管理");
                    logger.info("操作说明：添加角色");
                    logger.info("参数：无");
                    Integer username_mark = context.getBodyAsJson().getInteger("username_mark");
                    String username = context.getBodyAsJson().getString("username");
                    String role = context.getBodyAsJson().getString("role");
                    String note = context.getBodyAsJson().getString("note");
                    String role_name = context.getBodyAsJson().getString("role_name");
                    String role_describe = context.getBodyAsJson().getString("role_describe");
                    logger.info("****************");
                    databaseService.query(SqlQuery.INSERT_LOG,
                            new JsonArray().add(name).add(time).add(ip).add("账号管理")
                                    .add(requestPath).add(requestMethod).add("添加账号").add(username_mark+username+note+role+role_name+role_describe).add(date).add(context.request().getHeader("UserAgent")),rs->{
                                if (rs.failed()) {
                                    logger.error("插入日志失败---》"+rs.cause());
                                }
                            });
                    break;
                }
            case "PATCH":
                if (requestPath.contains("/api/account")){
                    logger.info("-----------------");
                    logger.info("账号："+name);
                    logger.info("时间："+time);
                    logger.info("ip："+ip);
                    logger.info("请求路径："+requestPath);  ///user/info
                    logger.info("请求方式："+requestMethod);  ///GET
                    logger.info("模块：账号管理");
                    logger.info("操作说明：更新账号信息");
                    logger.info("参数：");
                    logger.info(context.getBodyAsJson().getString("username"));
                    logger.info(context.getBodyAsJson().getString("password"));
                    logger.info(context.getBodyAsJson().getString("note"));
                    logger.info( context.getBodyAsJson().getString("position"));
                    logger.info(context.getBodyAsJson().getString("department"));
                    logger.info("****************");
                    databaseService.query(SqlQuery.INSERT_LOG,
                            new JsonArray().add(name).add(time).add(ip).add("账号管理")
                                    .add(requestPath).add(requestMethod).add("更新账号信息").add(context.getBodyAsJson().getString("username")
                                    +context.getBodyAsJson().getString("password")+context.getBodyAsJson().getString("note")
                                    + context.getBodyAsJson().getString("position")+context.getBodyAsJson().getString("department")).add(date).add(context.request().getHeader("UserAgent")),rs->{
                                if (rs.failed()) {
                                    logger.error("插入日志失败---》"+rs.cause());
                                }
                            });
                    break;
                }
                if (requestPath.contains("/api/role")){
                    logger.info("-----------------");
                    logger.info("账号："+name);
                    logger.info("时间："+time);
                    logger.info("ip："+ip);
                    logger.info("请求路径："+requestPath);  ///user/info
                    logger.info("请求方式："+requestMethod);  ///GET
                    logger.info("模块：角色管理");
                    logger.info("操作说明：更新账号角色信息");
                    logger.info("参数：");
                    String role = "";
                    String note = "";
                    String role_name = "";
                    String role_describe = "";
                    String id = "";
                    try {
                        role = context.getBodyAsJson().getString("role");
                        note = context.getBodyAsJson().getString("note");
                        role_name = context.getBodyAsJson().getString("role_name");
                        role_describe = context.getBodyAsJson().getString("role_describe");
                    } catch (Exception e) {
                        role = context.request().getParam("role");
                        note = context.request().getParam("note");
                        role_name = context.request().getParam("role_name");
                        role_describe = context.request().getParam("role_describe");
                    }
                    logger.info("****************");
                    databaseService.query(SqlQuery.INSERT_LOG,
                            new JsonArray().add(name).add(time).add(ip).add("账号管理")
                                    .add(requestPath).add(requestMethod).add("更新账号信息").add(role+note+role+role_name).add(date).add(context.request().getHeader("UserAgent")),rs->{
                                if (rs.failed()) {
                                    logger.error("插入日志失败---》"+rs.cause());
                                }
                            });
                    break;
                }
                break;
            case "DELETE":
                if (requestPath.contains("/api/account")){
                    logger.info("-----------------");
                    logger.info("账号："+name);
                    logger.info("时间："+time);
                    logger.info("ip："+ip);
                    logger.info("请求路径："+requestPath);  ///user/info
                    logger.info("请求方式："+requestMethod);  ///GET
                    logger.info("模块：账号管理");
                    logger.info("操作说明：删除账号");
                    logger.info("参数：无");
                    logger.info("****************");
                    databaseService.query(SqlQuery.INSERT_LOG,
                            new JsonArray().add(name).add(time).add(ip).add("账号管理")
                                    .add(requestPath).add(requestMethod).add("删除账号").add("无").add(date).add(context.request().getHeader("UserAgent")),rs->{
                                if (rs.failed()) {
                                    logger.error("插入日志失败---》"+rs.cause());
                                }
                            });
                    break;
                }
                if (requestPath.contains("/api/role")){
                    logger.info("-----------------");
                    logger.info("账号："+name);
                    logger.info("时间："+time);
                    logger.info("ip："+ip);
                    logger.info("请求路径："+requestPath);  ///user/info
                    logger.info("请求方式："+requestMethod);  ///GET
                    logger.info("模块：角色管理");
                    logger.info("操作说明：删除角色");
                    logger.info("参数：无");
                    logger.info("****************");
                    databaseService.query(SqlQuery.INSERT_LOG,
                            new JsonArray().add(name).add(time).add(ip).add("角色管理")
                                    .add(requestPath).add(requestMethod).add("删除角色").add("无").add(date).add(context.request().getHeader("UserAgent")),rs->{
                                if (rs.failed()) {
                                    logger.error("插入日志失败---》"+rs.cause());
                                }
                            });
                    break;
                }
        }
    }
}
