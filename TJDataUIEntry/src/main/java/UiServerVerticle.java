import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CorsHandler;
import io.vertx.ext.web.handler.StaticHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class UiServerVerticle extends AbstractVerticle {
    private Logger logger = null;

    @Override
    public void start(Future<Void> startFuture) {
        logger = LoggerFactory.getLogger(UiServerVerticle.class.getName());
        HashMap<ConfigConstants, String> conf = loadSqlQueries();


        Router router = Router.router(vertx);
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

        //静态资源处理
        router.route("/static/*").handler(StaticHandler.create());
        //静态资源处理
        router.get("/favicon.ico").handler(this::icoHandler);
        router.route("/*").handler(this::loginHandler);

        int portNumber = Integer.valueOf(conf.get(ConfigConstants.HTTP_PORT));
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

    private void icoHandler(RoutingContext context) {
        context.response().sendFile("webroot/favicon.ico");
    }

    private void loginHandler(RoutingContext context) {
        context.response().sendFile("webroot/index.html");
    }

    /**
     * 读取文件中sqlQueries
     * @return
     * @throws IOException
     */
    public HashMap<ConfigConstants, String> loadSqlQueries() {
        InputStream queriesInputStream=null;
        Properties queriesProps = new Properties();
        try {
            queriesInputStream=new FileInputStream("confTJDataUI/conf.properties");
            //queriesInputStream=this.getClass().getClassLoader().getResourceAsStream("conf.properties");
            queriesProps.load(queriesInputStream);
        }catch (IOException e){
            logger.error("read databasecon.properties failed "+e);
        }finally {
            try {
                queriesInputStream.close();
            }catch (IOException ioe){
                logger.error("close databasecon.properties failed "+ioe);
            }

        }
        HashMap<ConfigConstants, String> sqlQueries = new HashMap<>();
        sqlQueries.put(ConfigConstants.HTTP_PORT, queriesProps.getProperty("http_port"));
        return sqlQueries;
    }
}
