package database;

import http.ConfigConstants;
import http.Method;
import http.shieldingHttpVerticle;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.serviceproxy.ServiceBinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class RedDatabaseVerticle extends AbstractVerticle {
    private static Logger logger = LoggerFactory.getLogger(RedDatabaseVerticle.class.getName());
    private Method method=new Method();
    @Override
    public void start(Future<Void> startFuture) {
        HashMap<ConfigConstants, String> conf = method.loadConfigQueries();
        JsonObject jsonObject_conf = new JsonObject()
                .put("url", conf.get(ConfigConstants.DATABASE_URL))
                .put("driver_class", conf.get(ConfigConstants.DATABASE_DRIVER_CLASS))
                .put("max_pool_size", Integer.valueOf(conf.get(ConfigConstants.DATABASE_MAX_POOL_SIZE)))
                .put("user", conf.get(ConfigConstants.DATABASE_USER))
                .put("password", conf.get(ConfigConstants.DATABASE_PASSWORD));

        JDBCClient dbcClient = JDBCClient.createShared(vertx, jsonObject_conf);

        RedDatabaseService.create(lists, dbcClient, ready -> {
            if (ready.succeeded()) {
                ServiceBinder binder = new ServiceBinder(vertx);
                binder.setAddress(shieldingHttpVerticle.CONFIG_REDSYSTEMDB_QUEUE)
                        .register(RedDatabaseService.class, ready.result());
                logger.info("database start success");
                startFuture.complete();
            } else {
                logger.error("database start failed ", ready.cause());
                startFuture.fail(ready.cause());
            }
        });
    }

    List<String> lists = Arrays.asList(
            "CREATE TABLE if not exists `fodderinfo` (\n" +
                    "  `fodderguid` varchar(255) NOT NULL,\n" +
                    "  `fodderName` varchar(255) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`fodderguid`)\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8;"
    );

}
