package database;

import http.PayHttpVerticle;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.serviceproxy.ServiceBinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.ConfigConstants;
import service.InitConf;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class PayDatabaseVerticle extends AbstractVerticle {
    Logger logger = LoggerFactory.getLogger(PayDatabaseVerticle.class.getName());
    InitConf initConf=new InitConf();

    @Override
    public void start(Future<Void> startFuture){
        HashMap<ConfigConstants, String> conf = initConf.loadConfigQueries();
        JsonObject jsonObject_conf = new JsonObject()
                .put("url", conf.get(ConfigConstants.DATABASE_URL))
                .put("driver_class", conf.get(ConfigConstants.DATABASE_DRIVER_CLASS))
                .put("max_pool_size", Integer.valueOf(conf.get(ConfigConstants.DATABASE_MAX_POOL_SIZE)))
                .put("user", conf.get(ConfigConstants.DATABASE_USER))
                .put("password", conf.get(ConfigConstants.DATABASE_PASSWORD));

        JDBCClient dbcClient = JDBCClient.createShared(vertx, jsonObject_conf);

        PayDatabaseService.create(lists, dbcClient, ready -> {
            if (ready.succeeded()) {
                ServiceBinder binder = new ServiceBinder(vertx);
                binder.setAddress(PayHttpVerticle.CONFIG_ACCOUNTDB_QUEUE)
                        .register(PayDatabaseService.class, ready.result());
                logger.info("database start success");
                startFuture.complete();
            } else {
                logger.error("database start failed ", ready.cause());
                startFuture.fail(ready.cause());
            }
        });
    }

    private List<String> lists = Arrays.asList(
            "CREATE TABLE if not exists `paydata` (\n" +
                    "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `order_time` bigint(20) DEFAULT NULL,\n" +
                    "  `payment_time` bigint(20) DEFAULT NULL,\n" +
                    "  `duration` int(11) DEFAULT NULL,\n" +
                    "  `app_name` varchar(45) DEFAULT NULL,\n" +
                    "  `product_name` varchar(45) DEFAULT NULL,\n" +
                    "  `payment` varchar(45) DEFAULT NULL,\n" +
                    "  `currency_type` varchar(45) DEFAULT NULL,\n" +
                    "  `pay_type` varchar(45) DEFAULT NULL,\n" +
                    "  `marketing_channel` varchar(45) DEFAULT NULL,\n" +
                    "  `order_status` varchar(45) DEFAULT NULL,\n" +
                    "  `order_result` varchar(45) DEFAULT NULL,\n" +
                    "  `bussiness_type` varchar(45) DEFAULT NULL,\n" +
                    "  `original_order` varchar(45) DEFAULT NULL,\n" +
                    "  `refund_time` bigint(20) DEFAULT NULL,\n" +
                    "  `refund_amount` int(11) DEFAULT NULL,\n" +
                    "  `country` varchar(45) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`id`)\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8;"
    );

}
