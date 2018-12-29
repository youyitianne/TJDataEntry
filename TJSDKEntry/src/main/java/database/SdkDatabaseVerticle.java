package database;

import http.SdkHttpVerticle;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.serviceproxy.ServiceBinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class SdkDatabaseVerticle extends AbstractVerticle {
    private static Logger logger = LoggerFactory.getLogger(SdkDatabaseVerticle.class.getName());
    private Method method=new Method();
    @Override
    public void start(Future<Void> startFuture) throws Exception {
        HashMap<ConfigConstants, String> conf = method.loadConfigQueries();

        JsonObject jsonObject_conf = new JsonObject()
                .put("url", conf.get(ConfigConstants.DATABASE_URL))
                .put("driver_class", conf.get(ConfigConstants.DATABASE_DRIVER_CLASS))
                .put("max_pool_size", Integer.valueOf(conf.get(ConfigConstants.DATABASE_MAX_POOL_SIZE)))
                .put("user", conf.get(ConfigConstants.DATABASE_USER))
                .put("password", conf.get(ConfigConstants.DATABASE_PASSWORD));

        JDBCClient dbcClient = JDBCClient.createShared(vertx, jsonObject_conf);

        SdkDatabaseService.create(lists, dbcClient, ready -> {
            if (ready.succeeded()) {
                ServiceBinder binder = new ServiceBinder(vertx);
                binder.setAddress(SdkHttpVerticle.CONFIG_ACCOUNTDB_QUEUE)
                        .register(SdkDatabaseService.class, ready.result());
                logger.info("database start success");
                startFuture.complete();
            } else {
                logger.error("database start failed ", ready.cause());
                startFuture.fail(ready.cause());
            }
        });
    }

    List<String> lists = Arrays.asList(
            "CREATE TABLE if not exists `project_config_info` (\n" +
                    "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `date` bigint(20) DEFAULT NULL,\n" +
                    "  `app_name` varchar(255) DEFAULT NULL,\n" +
                    "  `package_name` varchar(255) DEFAULT NULL,\n" +
                    "  `version_online` varchar(255) DEFAULT NULL,\n" +
                    "  `version_update` varchar(255) DEFAULT NULL,\n" +
                    "  `versioncode_online_version` varchar(255) DEFAULT NULL,\n" +
                    "  `versioncode_update_version` varchar(255) DEFAULT NULL,\n" +
                    "  `channel_mark` varchar(255) DEFAULT NULL,\n" +
                    "  `sdk_config` varchar(255) DEFAULT NULL,\n" +
                    "  `sdk_require` varchar(255) DEFAULT NULL,\n" +
                    "  `note` varchar(255) DEFAULT NULL,\n" +
                    "  `sdk_status` tinyint(4) DEFAULT NULL,\n" +
                    "  `publish` tinyint(4) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`id`)\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8;",
            "CREATE TABLE if not exists `project_config_list` (\n" +
                    "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `date` bigint(20) DEFAULT NULL,\n" +
                    "  `app_name` varchar(255) DEFAULT NULL,\n" +
                    "  `channel_mark` varchar(255) DEFAULT NULL,\n" +
                    "  `param_name` varchar(255) DEFAULT NULL,\n" +
                    "  `param` varchar(255) DEFAULT NULL,\n" +
                    "  `sdk_type` varchar(255) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`id`)\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8;"
    );

}
