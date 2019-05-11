package com.core.verticle;

import com.core.enums.ConfigConstants;
import com.core.service.CooperationDatabaseService;
import com.core.util.ConfUtil;
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

public class CooperationDatabaseVerticle extends AbstractVerticle {
    Logger logger = LoggerFactory.getLogger(CooperationDatabaseVerticle.class.getName());
    ConfUtil initConf = new ConfUtil();

    @Override
    public void start(Future<Void> startFuture) throws Exception {
        HashMap<ConfigConstants, String> conf = initConf.loadConfigQueries();
        JsonObject jsonObject_conf = new JsonObject()
                .put("url", conf.get(ConfigConstants.DATABASE_URL))
                .put("driver_class", conf.get(ConfigConstants.DATABASE_DRIVER_CLASS))
                .put("max_pool_size", Integer.valueOf(conf.get(ConfigConstants.DATABASE_MAX_POOL_SIZE)))
                .put("user", conf.get(ConfigConstants.DATABASE_USER))
                .put("password", conf.get(ConfigConstants.DATABASE_PASSWORD));

        JDBCClient dbcClient = JDBCClient.createShared(vertx, jsonObject_conf);

            CooperationDatabaseService.create(lists, dbcClient, ready -> {
                if (ready.succeeded()) {
                    ServiceBinder binder = new ServiceBinder(vertx);
                    binder.setAddress(CooperationHttpVerticle.CONFIG_COOPERATION_QUEUE)
                            .register(CooperationDatabaseService.class, ready.result());
                    logger.info("database start success");
                    startFuture.complete();
                } else {
                    logger.error("database start failed ", ready.cause());
                    startFuture.fail(ready.cause());
                }
            });

    }


    List<String> lists = Arrays.asList("use statistics;");

}
