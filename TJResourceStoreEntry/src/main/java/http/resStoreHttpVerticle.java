package http;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.FileUpload;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CorsHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

public class resStoreHttpVerticle extends AbstractVerticle {
    Logger logger = LoggerFactory.getLogger(resStoreHttpVerticle.class);
    private static final String cachePath = "fileCache";
    private static final String rootPath = "Resource";
    private static final String secondPath = "res";


    @Override
    public void start(Future<Void> startFuture) {
        Router router = Router.router(vertx);

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

        router.route().handler(BodyHandler.create().setUploadsDirectory(rootPath + File.separator + cachePath));

        //文件上传
        router.post("/file").handler(this::uploadHandler);
        router.get("/file").handler(this::getHandler);
        HashMap<ConfigConstants, String> conf = loadConfigQueries();
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
     * 获取文件
     *
     * @param context
     */
    private void getHandler(RoutingContext context) {
        String path = context.request().getParam("path");
        File file = new File(rootPath + File.separator + secondPath + File.separator + path);
        if (file.exists()) {
            logger.info("获取资源成功");
            context.response().sendFile(rootPath + File.separator + secondPath + File.separator + path);
        } else {
            logger.info("获取资源失败");
            context.response().putHeader("Content-Type", "text/plain; charset=utf-8").end(Json.encodePrettily(new JsonObject().put("message", "路径错误")));
        }
    }


    /**
     * 上传文件处理
     */
    private void uploadHandler(RoutingContext context) {
        Set<FileUpload> fileUploads = context.fileUploads();
        String filename = "";
        String uploadfilename = "";
        for (FileUpload f : fileUploads) {
            filename = f.fileName();   //文件原名  友盟3.xlsx
            uploadfilename = f.uploadedFileName();      //文件缓存名    TJMission\file-uploads\711dcdb5-dec7-4ccb-b191-72696ea9a853
            logger.info(filename);
            logger.info(uploadfilename);
        }
        String finalFilename = filename;
        String guid = UUID.randomUUID().toString();
        logger.info(guid);
        fileClassification(uploadfilename, guid).setHandler(rs -> {
            if (rs.succeeded()) {
                logger.info("文件上传成功");
                context.response().end(Json.encodePrettily(new JsonObject().put("repcode", "3000").put("data", new JsonObject().put("guid", guid).put("file", finalFilename))));
            } else {
                logger.error("文件操作失败", rs.cause());
                context.response().end(Json.encodePrettily(new JsonObject().put("repcode", "3001").put("data", "保存失败,请重新上传")));
            }
        });

    }

    /**
     * 上传缓存文件分类
     *
     * @param path 文件上传路径
     * @return 文件分类路径
     */
    private Future<String> fileClassification(String path, String guid) {
        Future<String> future = Future.future();
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        //多重路径在此添加
        File file = new File(rootPath + File.separator + secondPath);
        if (!file.exists()) {
            file.mkdirs();
        }
        String newpath = file + File.separator + guid;
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
     * 读取配置文件
     *
     * @return
     */
    public HashMap<ConfigConstants, String> loadConfigQueries() {
        InputStream queriesInputStream = null;
        Properties queriesProps = new Properties();
        try {
            queriesInputStream = new FileInputStream("confTJResourceStore/conf.properties");
            //queriesInputStream = this.getClass().getClassLoader().getResourceAsStream("conf.properties");
            queriesProps.load(queriesInputStream);
        } catch (IOException e) {
            logger.error("read databasecon.properties failed " + e);
        } finally {
            try {
                queriesInputStream.close();
            } catch (IOException e) {
                logger.error("read databasecon.properties failed " + e);
            }
        }
        HashMap<ConfigConstants, String> sqlQueries = new HashMap<>();
        sqlQueries.put(ConfigConstants.HTTP_PORT, queriesProps.getProperty("http_port"));
        return sqlQueries;
    }

}
