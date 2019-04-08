package database;

import http.MissionHttpVerticle;
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

public class MissionDatabaseVerticle extends AbstractVerticle {
    private static Logger logger = LoggerFactory.getLogger(MissionDatabaseVerticle.class.getName());
    private Method method = new Method();

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

        MissionDatabaseService.create(lists, dbcClient, ready -> {
            if (ready.succeeded()) {
                ServiceBinder binder = new ServiceBinder(vertx);
                binder.setAddress(MissionHttpVerticle.CONFIG_MISSION_QUEUE)
                        .register(MissionDatabaseService.class, ready.result());
                logger.info("database start success");
                startFuture.complete();
            } else {
                logger.error("database start failed ", ready.cause());
                startFuture.fail(ready.cause());
            }
        });
    }


    List<String> lists = Arrays.asList(
            "CREATE TABLE if not exists  `fileinfo` (\n" +
                    "  `fileguid` varchar(255) NOT NULL,\n" +
                    "  `filename` varchar(255) DEFAULT NULL,\n" +
                    "  `date` mediumtext,\n" +
                    "  PRIMARY KEY (`fileguid`),\n" +
                    "  UNIQUE KEY `guid_UNIQUE` (`fileguid`)\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8;",
            "CREATE TABLE  if not exists `keystoreinfo` (\n" +
                    "  `keystoreguid` varchar(255) NOT NULL,\n" +
                    "  `date` mediumtext,\n" +
                    "  `keystoreName` varchar(255) DEFAULT NULL,\n" +
                    "  `keystorePass` varchar(255) DEFAULT '暂无',\n" +
                    "  `keyaliasName` varchar(255) DEFAULT '暂无',\n" +
                    "  `keyaliasPass` varchar(255) DEFAULT '暂无',\n" +
                    "  PRIMARY KEY (`keystoreguid`),\n" +
                    "  UNIQUE KEY `keystoreguid_UNIQUE` (`keystoreguid`)\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8;"
    );


}
