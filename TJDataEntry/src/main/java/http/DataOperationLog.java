package http;

import database.AdvertisementService;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.ext.web.RoutingContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DataOperationLog {
    Logger logger = LoggerFactory.getLogger(DataOperationLog.class);

    /**
     * @param context
     * @param requestPath
     * @param requestMethod
     * @param name
     * @param time
     * @param ip
     */
    public void operationLog(String uploadfilename,
                             String sql,
                             AdvertisementService advertisementService,
                             JDBCClient jdbcClient,
                             RoutingContext context,
                             String requestPath,
                             String requestMethod,
                             String name,
                             String time,
                             String ip,
                             Long date) {
        switch (requestMethod) {
            case "GET":
                if (requestPath.contains("/getapplist")) {
                    logger.info("账号：" + name);
                    logger.info("时间：" + time);
                    logger.info("ip：" + ip);
                    logger.info("请求路径：" + requestPath);  ///user/info
                    logger.info("请求方式：" + requestMethod);  ///GET
                    logger.info("****************");
                    advertisementService.update(jdbcClient, sql, new JsonArray().add(name).add(time).add(ip).add("应用管理")
                            .add(requestPath).add(requestMethod).add("根据资源分配获取应用").add("无").add(date).add(context.request().getHeader("UserAgent"))).setHandler(rs -> {
                        if (rs.failed()) {
                            logger.error("插入日志失败---》", rs.cause());
                        }
                    });
                    break;
                }
                if (requestPath.contains("/repeat/get")) {
                    logger.info("账号：" + name);
                    logger.info("时间：" + time);
                    logger.info("ip：" + ip);
                    logger.info("请求路径：" + requestPath);  ///user/info
                    logger.info("请求方式：" + requestMethod);  ///GET
                    logger.info("****************");
                    advertisementService.update(jdbcClient, sql, new JsonArray().add(name).add(time).add(ip).add("去重模块")
                            .add(requestPath).add(requestMethod).add("重复数据获取（手动查重）").add("无").add(date).add(context.request().getHeader("UserAgent"))).setHandler(rs -> {
                        if (rs.failed()) {
                            logger.error("插入日志失败---》", rs.cause());
                        }
                    });
                    break;
                }
                if (requestPath.contains("/delRepeatDefault")) {
                    logger.info("账号：" + name);
                    logger.info("时间：" + time);
                    logger.info("ip：" + ip);
                    logger.info("请求路径：" + requestPath);  ///user/info
                    logger.info("请求方式：" + requestMethod);  ///GET
                    logger.info("****************");
                    advertisementService.update(jdbcClient, sql, new JsonArray().add(name).add(time).add(ip).add("去重模块")
                            .add(requestPath).add(requestMethod).add("重复数据去除（自动查重）").add("无").add(date).add(context.request().getHeader("UserAgent"))).setHandler(rs -> {
                        if (rs.failed()) {
                            logger.error("插入日志失败---》", rs.cause());
                        }
                    });
                    break;
                }
                if (requestPath.contains("/umengapp")) {
                    logger.info("账号：" + name);
                    logger.info("时间：" + time);
                    logger.info("ip：" + ip);
                    logger.info("请求路径：" + requestPath);  ///user/info
                    logger.info("请求方式：" + requestMethod);  ///GET
                    logger.info("****************");
                    advertisementService.update(jdbcClient, sql, new JsonArray().add(name).add(time).add(ip).add("数据分析")
                            .add(requestPath).add(requestMethod).add("获取所有友盟app").add("无").add(date).add(context.request().getHeader("UserAgent"))).setHandler(rs -> {
                        if (rs.failed()) {
                            logger.error("插入日志失败---》", rs.cause());
                        }
                    });
                    break;
                }
                if (requestPath.contains("/umengchannels")) {
                    logger.info("账号：" + name);
                    logger.info("时间：" + time);
                    logger.info("ip：" + ip);
                    logger.info("请求路径：" + requestPath);  ///user/info
                    logger.info("请求方式：" + requestMethod);  ///GET
                    logger.info("****************");
                    advertisementService.update(jdbcClient, sql, new JsonArray().add(name).add(time).add(ip).add("数据分析")
                            .add(requestPath).add(requestMethod).add("获取所有友盟app").add("无").add(date).add(context.request().getHeader("UserAgent"))).setHandler(rs -> {
                        if (rs.failed()) {
                            logger.error("插入日志失败---》", rs.cause());
                        }
                    });
                    break;
                }
                if (requestPath.contains("/data")) {
                    logger.info("账号：" + name);
                    logger.info("时间：" + time);
                    logger.info("ip：" + ip);
                    logger.info("请求路径：" + requestPath);  ///user/info
                    logger.info("请求方式：" + requestMethod);  ///GET
                    logger.info("****************");
                    advertisementService.update(jdbcClient, sql, new JsonArray().add(name).add(time).add(ip).add("Debug")
                            .add(requestPath).add(requestMethod).add("获取广告数据").add("无").add(date).add(context.request().getHeader("UserAgent"))).setHandler(rs -> {
                        if (rs.failed()) {
                            logger.error("插入日志失败---》", rs.cause());
                        }
                    });
                    break;
                }
                if (requestPath.contains("/names")) {
                    logger.info("账号：" + name);
                    logger.info("时间：" + time);
                    logger.info("ip：" + ip);
                    logger.info("请求路径：" + requestPath);  ///user/info
                    logger.info("请求方式：" + requestMethod);  ///GET
                    logger.info("****************");
                    advertisementService.update(jdbcClient, sql, new JsonArray().add(name).add(time).add(ip).add("数据获取")
                            .add(requestPath).add(requestMethod).add("获取app名字（原格式）").add("无").add(date).add(context.request().getHeader("UserAgent"))).setHandler(rs -> {
                        if (rs.failed()) {
                            logger.error("插入日志失败---》", rs.cause());
                        }
                    });
                    break;
                }
                if (requestPath.contains("/names")) {
                    logger.info("账号：" + name);
                    logger.info("时间：" + time);
                    logger.info("ip：" + ip);
                    logger.info("请求路径：" + requestPath);  ///user/info
                    logger.info("请求方式：" + requestMethod);  ///GET
                    logger.info("****************");
                    advertisementService.update(jdbcClient, sql, new JsonArray().add(name).add(time).add(ip).add("数据获取")
                            .add(requestPath).add(requestMethod).add("获取app名字（数组）").add("无").add(date).add(context.request().getHeader("UserAgent"))).setHandler(rs -> {
                        if (rs.failed()) {
                            logger.error("插入日志失败---》", rs.cause());
                        }
                    });
                    break;
                }
                if (requestPath.contains("/yixin")) {
                    logger.info("账号：" + name);
                    logger.info("时间：" + time);
                    logger.info("ip：" + ip);
                    logger.info("请求路径：" + requestPath);  ///user/info
                    logger.info("请求方式：" + requestMethod);  ///GET
                    logger.info("****************");
                    advertisementService.update(jdbcClient, sql, new JsonArray().add(name).add(time).add(ip).add("数据获取")
                            .add(requestPath).add(requestMethod).add("获取移信数据").add("无").add(date).add(context.request().getHeader("UserAgent"))).setHandler(rs -> {
                        if (rs.failed()) {
                            logger.error("插入日志失败---》", rs.cause());
                        }
                    });
                    break;
                }
                if (requestPath.contains("/userdata")) {
                    logger.info("账号：" + name);
                    logger.info("时间：" + time);
                    logger.info("ip：" + ip);
                    logger.info("请求路径：" + requestPath);  ///user/info
                    logger.info("请求方式：" + requestMethod);  ///GET
                    logger.info("****************");
                    advertisementService.update(jdbcClient, sql, new JsonArray().add(name).add(time).add(ip).add("数据获取")
                            .add(requestPath).add(requestMethod).add("获取用户数据").add("无").add(date).add(context.request().getHeader("UserAgent"))).setHandler(rs -> {
                        if (rs.failed()) {
                            logger.error("插入日志失败---》", rs.cause());
                        }
                    });
                    break;
                }
                if (requestPath.contains("/appdata")) {
                    logger.info("账号：" + name);
                    logger.info("时间：" + time);
                    logger.info("ip：" + ip);
                    logger.info("请求路径：" + requestPath);  ///user/info
                    logger.info("请求方式：" + requestMethod);  ///GET
                    logger.info("****************");
                    advertisementService.update(jdbcClient, sql, new JsonArray().add(name).add(time).add(ip).add("数据获取")
                            .add(requestPath).add(requestMethod).add("获取广告数据").add("无").add(date).add(context.request().getHeader("UserAgent"))).setHandler(rs -> {
                        if (rs.failed()) {
                            logger.error("插入日志失败---》", rs.cause());
                        }
                    });
                    break;
                }
                if (requestPath.contains("/channel")) {
                    logger.info("账号：" + name);
                    logger.info("时间：" + time);
                    logger.info("ip：" + ip);
                    logger.info("请求路径：" + requestPath);  ///user/info
                    logger.info("请求方式：" + requestMethod);  ///GET
                    logger.info("****************");
                    advertisementService.update(jdbcClient, sql, new JsonArray().add(name).add(time).add(ip).add("渠道管理")
                            .add(requestPath).add(requestMethod).add("获取所有渠道数据").add("无").add(date).add(context.request().getHeader("UserAgent"))).setHandler(rs -> {
                        if (rs.failed()) {
                            logger.error("插入日志失败---》", rs.cause());
                        }
                    });
                    break;
                }
                if (requestPath.contains("/app")) {
                    logger.info("账号：" + name);
                    logger.info("时间：" + time);
                    logger.info("ip：" + ip);
                    logger.info("请求路径：" + requestPath);  ///user/info
                    logger.info("请求方式：" + requestMethod);  ///GET
                    logger.info("****************");
                    advertisementService.update(jdbcClient, sql, new JsonArray().add(name).add(time).add(ip).add("应用管理")
                            .add(requestPath).add(requestMethod).add("获取所有应用").add("无").add(date).add(context.request().getHeader("UserAgent"))).setHandler(rs -> {
                        if (rs.failed()) {
                            logger.error("插入日志失败---》", rs.cause());
                        }
                    });
                    break;
                }
                if (requestPath.contains("/projectList")) {
                    logger.info("账号：" + name);
                    logger.info("时间：" + time);
                    logger.info("ip：" + ip);
                    logger.info("请求路径：" + requestPath);  ///user/info
                    logger.info("请求方式：" + requestMethod);  ///GET
                    logger.info("****************");
                    advertisementService.update(jdbcClient, sql, new JsonArray().add(name).add(time).add(ip).add("应用管理")
                            .add(requestPath).add(requestMethod).add("获取项目列表").add("无").add(date).add(context.request().getHeader("UserAgent"))).setHandler(rs -> {
                        if (rs.failed()) {
                            logger.error("插入日志失败---》", rs.cause());
                        }
                    });
                    break;
                }
                if (requestPath.contains("/project")) {
                    logger.info("账号：" + name);
                    logger.info("时间：" + time);
                    logger.info("ip：" + ip);
                    logger.info("请求路径：" + requestPath);  ///user/info
                    logger.info("请求方式：" + requestMethod);  ///GET
                    logger.info("****************");
                    advertisementService.update(jdbcClient, sql, new JsonArray().add(name).add(time).add(ip).add("项目管理")
                            .add(requestPath).add(requestMethod).add("获取所有项目").add("无").add(date).add(context.request().getHeader("UserAgent"))).setHandler(rs -> {
                        if (rs.failed()) {
                            logger.error("插入日志失败---》", rs.cause());
                        }
                    });
                    break;
                }
                if (requestPath.contains("/adtype")) {
                    logger.info("账号：" + name);
                    logger.info("时间：" + time);
                    logger.info("ip：" + ip);
                    logger.info("请求路径：" + requestPath);  ///user/info
                    logger.info("请求方式：" + requestMethod);  ///GET
                    logger.info("****************");
                    advertisementService.update(jdbcClient, sql, new JsonArray().add(name).add(time).add(ip).add("广告类型管理")
                            .add(requestPath).add(requestMethod).add("获取所有广告类型").add("无").add(date).add(context.request().getHeader("UserAgent"))).setHandler(rs -> {
                        if (rs.failed()) {
                            logger.error("插入日志失败---》", rs.cause());
                        }
                    });
                    break;
                }
            case "POST":
                if (requestPath.contains("/adtype")) {
                    logger.info("账号：" + name);
                    logger.info("时间：" + time);
                    logger.info("ip：" + ip);
                    logger.info("请求路径：" + requestPath);  ///user/info
                    logger.info("请求方式：" + requestMethod);  ///GET
                    logger.info("****************");
                    advertisementService.update(jdbcClient, sql, new JsonArray().add(name).add(time).add(ip).add("广告类型管理")
                            .add(requestPath).add(requestMethod).add("添加广告类型").add(context.request().getParam("name")+context.request().getParam("program_mark")+context.request().getParam("note")+context.request().getParam("introduce")).add(date).add(context.request().getHeader("UserAgent"))).setHandler(rs -> {
                        if (rs.failed()) {
                            logger.error("插入日志失败---》", rs.cause());
                        }
                    });
                    break;
                }
                if (requestPath.contains("/app")) {
                    logger.info("账号：" + name);
                    logger.info("时间：" + time);
                    logger.info("ip：" + ip);
                    logger.info("请求路径：" + requestPath);  ///user/info
                    logger.info("请求方式：" + requestMethod);  ///GET
                    logger.info("****************");
                    advertisementService.update(jdbcClient, sql, new JsonArray().add(name).add(time).add(ip).add("应用管理")
                            .add(requestPath).add(requestMethod).add("添加应用").add(context.request().getParam("name") + context.request().getParam("project")).add(date).add(context.request().getHeader("UserAgent"))).setHandler(rs -> {
                        if (rs.failed()) {
                            logger.error("插入日志失败---》", rs.cause());
                        }
                    });
                    break;
                }
                if (requestPath.contains("/channel")) {
                    logger.info("账号：" + name);
                    logger.info("时间：" + time);
                    logger.info("ip：" + ip);
                    logger.info("请求路径：" + requestPath);  ///user/info
                    logger.info("请求方式：" + requestMethod);  ///GET
                    logger.info("****************");
                    advertisementService.update(jdbcClient, sql, new JsonArray().add(name).add(time).add(ip).add("渠道管理")
                            .add(requestPath).add(requestMethod).add("添加渠道").add(context.request().getParam("name") + context.request().getParam("program_mark") + context.request().getParam("note")).add(date).add(context.request().getHeader("UserAgent"))).setHandler(rs -> {
                        if (rs.failed()) {
                            logger.error("插入日志失败---》", rs.cause());
                        }
                    });
                    break;
                }
                if (requestPath.contains("/fileupload")) {
                    logger.info("账号：" + name);
                    logger.info("时间：" + time);
                    logger.info("ip：" + ip);
                    logger.info("请求路径：" + requestPath);  ///user/info
                    logger.info("请求方式：" + requestMethod);  ///GET
                    logger.info("****************");
                    advertisementService.update(jdbcClient, sql, new JsonArray().add(name).add(time).add(ip).add("广告数据管理")
                            .add(requestPath).add(requestMethod).add("广告数据上传").add(uploadfilename).add(date).add(context.request().getHeader("User-Agent"))).setHandler(rs -> {
                        if (rs.failed()) {
                            logger.error("插入日志失败---》", rs.cause());
                        }
                    });
                    break;
                }
                if (requestPath.contains("/delRepeat")) {
                    logger.info("账号：" + name);
                    logger.info("时间：" + time);
                    logger.info("ip：" + ip);
                    logger.info("请求路径：" + requestPath);  ///user/info
                    logger.info("请求方式：" + requestMethod);  ///GET
                    logger.info("****************");
                    advertisementService.update(jdbcClient, sql, new JsonArray().add(name).add(time).add(ip).add("去重模块")
                            .add(requestPath).add(requestMethod).add("删除重复数据").add("无").add(date).add(context.request().getHeader("UserAgent"))).setHandler(rs -> {
                        if (rs.failed()) {
                            logger.error("插入日志失败---》", rs.cause());
                        }
                    });
                    break;
                }
                if (requestPath.contains("/umeng")) {
                    logger.info("账号：" + name);
                    logger.info("时间：" + time);
                    logger.info("ip：" + ip);
                    logger.info("请求路径：" + requestPath);  ///user/info
                    logger.info("请求方式：" + requestMethod);  ///GET
                    logger.info("****************");
                    advertisementService.update(jdbcClient, sql, new JsonArray().add(name).add(time).add(ip).add("数据获取")
                            .add(requestPath).add(requestMethod).add("获取umeng数据").add(context.getBodyAsJson().getString("start") + context.getBodyAsJson().getString("end")).add(date).add(context.request().getHeader("UserAgent"))).setHandler(rs -> {
                        if (rs.failed()) {
                            logger.error("插入日志失败---》", rs.cause());
                        }
                    });
                    break;
                }
                if (requestPath.contains("/umengretention")) {
                    logger.info("账号：" + name);
                    logger.info("时间：" + time);
                    logger.info("ip：" + ip);
                    logger.info("请求路径：" + requestPath);  ///user/info
                    logger.info("请求方式：" + requestMethod);  ///GET
                    logger.info("****************");
                    advertisementService.update(jdbcClient, sql, new JsonArray().add(name).add(time).add(ip).add("数据分析")
                            .add(requestPath).add(requestMethod).add("友盟留存分析").add(context.getBodyAsJson().getString("start") + context.getBodyAsJson().getString("end") + context.getBodyAsJson().getString("appkey")).add(date).add(context.request().getHeader("UserAgent"))).setHandler(rs -> {
                        if (rs.failed()) {
                            logger.error("插入日志失败---》", rs.cause());
                        }
                    });
                    break;
                }
                if (requestPath.contains("/daily")) {
                    logger.info("账号：" + name);
                    logger.info("时间：" + time);
                    logger.info("ip：" + ip);
                    logger.info("请求路径：" + requestPath);  ///user/info
                    logger.info("请求方式：" + requestMethod);  ///GET
                    logger.info("****************");
                    advertisementService.update(jdbcClient, sql, new JsonArray().add(name).add(time).add(ip).add("数据分析")
                            .add(requestPath).add(requestMethod).add("获取产品日常收益表").add(context.getBodyAsJson().getString("start") + context.getBodyAsJson().getString("end")).add(date).add(context.request().getHeader("UserAgent"))).setHandler(rs -> {
                        if (rs.failed()) {
                            logger.error("插入日志失败---》", rs.cause());
                        }
                    });
                    break;
                }
                if (requestPath.contains("/dailyadtype")) {
                    logger.info("账号：" + name);
                    logger.info("时间：" + time);
                    logger.info("ip：" + ip);
                    logger.info("请求路径：" + requestPath);  ///user/info
                    logger.info("请求方式：" + requestMethod);  ///GET
                    logger.info("****************");
                    advertisementService.update(jdbcClient, sql, new JsonArray().add(name).add(time).add(ip).add("数据分析")
                            .add(requestPath).add(requestMethod).add("获取产品展次表（新）").add(context.getBodyAsJson().getString("start") + context.getBodyAsJson().getString("end")).add(date).add(context.request().getHeader("UserAgent"))).setHandler(rs -> {
                        if (rs.failed()) {
                            logger.error("插入日志失败---》", rs.cause());
                        }
                    });
                    break;
                }
                if (requestPath.contains("/file")) {
                    logger.info("账号：" + name);
                    logger.info("时间：" + time);
                    logger.info("ip：" + ip);
                    logger.info("请求路径：" + requestPath);  ///user/info
                    logger.info("请求方式：" + requestMethod);  ///GET
                    logger.info("****************");
                    advertisementService.update(jdbcClient, sql, new JsonArray().add(name).add(time).add(ip).add("数据分析")
                            .add(requestPath).add(requestMethod).add("获取产品展次表（旧）").add(context.getBodyAsJson().getString("start") + context.getBodyAsJson().getString("end") + context.getBodyAsJson().getJsonObject("name").toString()).add(date).add(context.request().getHeader("UserAgent"))).setHandler(rs -> {
                        if (rs.failed()) {
                            logger.error("插入日志失败---》", rs.cause());
                        }
                    });
                    break;
                }
                if (requestPath.contains("/arpufile")) {
                    logger.info("账号：" + name);
                    logger.info("时间：" + time);
                    logger.info("ip：" + ip);
                    logger.info("请求路径：" + requestPath);  ///user/info
                    logger.info("请求方式：" + requestMethod);  ///GET
                    logger.info("****************");
                    advertisementService.update(jdbcClient, sql, new JsonArray().add(name).add(time).add(ip).add("数据分析")
                            .add(requestPath).add(requestMethod).add("获取产品arpu表").add("无").add(date).add(context.request().getHeader("UserAgent"))).setHandler(rs -> {
                        if (rs.failed()) {
                            logger.error("插入日志失败---》", rs.cause());
                        }
                    });
                    break;
                }
                if (requestPath.contains("/project")) {
                    logger.info("账号：" + name);
                    logger.info("时间：" + time);
                    logger.info("ip：" + ip);
                    logger.info("请求路径：" + requestPath);  ///user/info
                    logger.info("请求方式：" + requestMethod);  ///GET
                    logger.info("****************");
                    advertisementService.update(jdbcClient, sql, new JsonArray().add(name).add(time).add(ip).add("项目管理")
                            .add(requestPath).add(requestMethod).add("添加项目").add(context.getBodyAsJson().getString("project_name")).add(date).add(context.request().getHeader("UserAgent"))).setHandler(rs -> {
                        if (rs.failed()) {
                            logger.error("插入日志失败---》", rs.cause());
                        }
                    });
                    break;
                }

            case "PATCH":
                if (requestPath.contains("/adtype")) {
                    logger.info("账号：" + name);
                    logger.info("时间：" + time);
                    logger.info("ip：" + ip);
                    logger.info("请求路径：" + requestPath);  ///user/info
                    logger.info("请求方式：" + requestMethod);  ///GET
                    logger.info("****************");
                    String adtypename = "";
                    String program_mark = "";
                    String note = "";
                    String introduce = "";
                    Integer id = -1;
                    try {
                        adtypename = context.getBodyAsJson().getString("name");
                        program_mark = context.getBodyAsJson().getString("program_mark");
                        note = context.getBodyAsJson().getString("note");
                        id = Integer.valueOf(context.request().getParam("id"));
                        introduce = context.getBodyAsJson().getString("introduce");
                    } catch (Exception e) {
                        adtypename = context.request().getParam("name");
                        program_mark = context.request().getParam("program_mark");
                        note = context.request().getParam("note");
                        id = Integer.valueOf(context.request().getParam("id"));
                        introduce = context.request().getParam("introduce");
                    }
                    advertisementService.update(jdbcClient, sql, new JsonArray().add(name).add(time).add(ip).add("广告类型管理")
                            .add(requestPath).add(requestMethod).add("更新广告类型").add(adtypename+program_mark+note+id+introduce).add(date).add(context.request().getHeader("UserAgent"))).setHandler(rs -> {
                        if (rs.failed()) {
                            logger.error("插入日志失败---》", rs.cause());
                        }
                    });
                    break;
                }
                if (requestPath.contains("/channel")) {
                    logger.info("账号：" + name);
                    logger.info("时间：" + time);
                    logger.info("ip：" + ip);
                    logger.info("请求路径：" + requestPath);  ///user/info
                    logger.info("请求方式：" + requestMethod);  ///GET
                    logger.info("****************");
                    advertisementService.update(jdbcClient, sql, new JsonArray().add(name).add(time).add(ip).add("渠道管理")
                            .add(requestPath).add(requestMethod).add("更新渠道").add(context.getBodyAsJson().getString("name") + context.getBodyAsJson().getString("program_mark") + context.getBodyAsJson().getString("note")).add(date).add(context.request().getHeader("UserAgent"))).setHandler(rs -> {
                        if (rs.failed()) {
                            logger.error("插入日志失败---》", rs.cause());
                        }
                    });
                    break;
                }
                if (requestPath.contains("/app")) {
                    logger.info("账号：" + name);
                    logger.info("时间：" + time);
                    logger.info("ip：" + ip);
                    logger.info("请求路径：" + requestPath);  ///user/info
                    logger.info("请求方式：" + requestMethod);  ///GET
                    logger.info("****************");
                    advertisementService.update(jdbcClient, sql, new JsonArray().add(name).add(time).add(ip).add("应用管理")
                            .add(requestPath).add(requestMethod).add("更新应用").add(context.getBodyAsJson().getString("name") + context.getBodyAsJson().getString("project") + context.getBodyAsJson().getString("system")).add(date).add(context.request().getHeader("UserAgent"))).setHandler(rs -> {
                        if (rs.failed()) {
                            logger.error("插入日志失败---》", rs.cause());
                        }
                    });
                    break;
                }
                if (requestPath.contains("/project")) {
                    logger.info("账号：" + name);
                    logger.info("时间：" + time);
                    logger.info("ip：" + ip);
                    logger.info("请求路径：" + requestPath);  ///user/info
                    logger.info("请求方式：" + requestMethod);  ///GET
                    logger.info("****************");
                    advertisementService.update(jdbcClient, sql, new JsonArray().add(name).add(time).add(ip).add("项目管理")
                            .add(requestPath).add(requestMethod).add("更新项目").add(context.getBodyAsJson().getString("project_name")).add(date).add(context.request().getHeader("UserAgent"))).setHandler(rs -> {
                        if (rs.failed()) {
                            logger.error("插入日志失败---》", rs.cause());
                        }
                    });
                    break;
                }
            case "DELETE":
                if (requestPath.contains("/adtype")) {
                    logger.info("账号：" + name);
                    logger.info("时间：" + time);
                    logger.info("ip：" + ip);
                    logger.info("请求路径：" + requestPath);  ///user/info
                    logger.info("请求方式：" + requestMethod);  ///GET
                    logger.info("****************");
                    advertisementService.update(jdbcClient, sql, new JsonArray().add(name).add(time).add(ip).add("广告类型管理")
                            .add(requestPath).add(requestMethod).add("删除广告类型").add("无").add(date).add(context.request().getHeader("UserAgent"))).setHandler(rs -> {
                        if (rs.failed()) {
                            logger.error("插入日志失败---》", rs.cause());
                        }
                    });
                    break;
                }
                if (requestPath.contains("/channel")) {
                    logger.info("账号：" + name);
                    logger.info("时间：" + time);
                    logger.info("ip：" + ip);
                    logger.info("请求路径：" + requestPath);  ///user/info
                    logger.info("请求方式：" + requestMethod);  ///GET
                    logger.info("****************");
                    advertisementService.update(jdbcClient, sql, new JsonArray().add(name).add(time).add(ip).add("渠道管理")
                            .add(requestPath).add(requestMethod).add("删除渠道").add("无").add(date).add(context.request().getHeader("UserAgent"))).setHandler(rs -> {
                        if (rs.failed()) {
                            logger.error("插入日志失败---》", rs.cause());
                        }
                    });
                    break;
                }
                if (requestPath.contains("/app")) {
                    logger.info("账号：" + name);
                    logger.info("时间：" + time);
                    logger.info("ip：" + ip);
                    logger.info("请求路径：" + requestPath);  ///user/info
                    logger.info("请求方式：" + requestMethod);  ///GET
                    logger.info("****************");
                    advertisementService.update(jdbcClient, sql, new JsonArray().add(name).add(time).add(ip).add("应用管理")
                            .add(requestPath).add(requestMethod).add("删除应用").add("无").add(date).add(context.request().getHeader("UserAgent"))).setHandler(rs -> {
                        if (rs.failed()) {
                            logger.error("插入日志失败---》", rs.cause());
                        }
                    });
                    break;
                }
                if (requestPath.contains("/project")) {
                    logger.info("账号：" + name);
                    logger.info("时间：" + time);
                    logger.info("ip：" + ip);
                    logger.info("请求路径：" + requestPath);  ///user/info
                    logger.info("请求方式：" + requestMethod);  ///GET
                    logger.info("****************");
                    advertisementService.update(jdbcClient, sql, new JsonArray().add(name).add(time).add(ip).add("项目管理")
                            .add(requestPath).add(requestMethod).add("删除项目").add("无").add(date).add(context.request().getHeader("UserAgent"))).setHandler(rs -> {
                        if (rs.failed()) {
                            logger.error("插入日志失败---》", rs.cause());
                        }
                    });
                    break;
                }
        }

    }
}
