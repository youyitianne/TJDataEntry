package database;

import http.AccountHttpService;
import http.AccountHttpVerticle;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.jdbc.JDBCClient;
import io.vertx.serviceproxy.ServiceBinder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import io.vertx.ext.sql.SQLConnection;

public class AccountDatabaseVerticle extends AbstractVerticle {
    private static Logger logger = LoggerFactory.getLogger(AccountDatabaseVerticle.class.getName());

    @Override
    public void start(Future<Void> startFuture) {
        Load load=new Load();
        HashMap<ConfigConstants, String> conf = load.loadConfQueries();

        JsonObject jsonObject_conf = new JsonObject()
                .put("url", conf.get(ConfigConstants.DATABASE_URL))
                .put("driver_class", conf.get(ConfigConstants.DATABASE_DRIVER_CLASS))
                .put("max_pool_size", Integer.valueOf(conf.get(ConfigConstants.DATABASE_MAX_POOL_SIZE)))
                .put("user", conf.get(ConfigConstants.DATABASE_USER))
                .put("password", conf.get(ConfigConstants.DATABASE_PASSWORD));

        JDBCClient dbcClient = JDBCClient.createShared(vertx, jsonObject_conf);

        AccountDatabaseService.create(lists,dbcClient,ready->{
            if (ready.succeeded()){
                ServiceBinder binder=new ServiceBinder(vertx);
                binder.setAddress(AccountHttpVerticle.CONFIG_ACCOUNTDB_QUEUE)
                        .register(AccountDatabaseService.class,ready.result());
                logger.info("database start success");
                startFuture.complete();
            }else {
                logger.error("database start failed ",ready.cause());
                startFuture.fail(ready.cause());
            }
        }
        );
    }

    List<String> lists = Arrays.asList(
            "CREATE TABLE  if not exists  `operationlog` (\n" +
                    "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `accout` varchar(255) DEFAULT NULL,\n" +
                    "  `time` varchar(255) DEFAULT NULL,\n" +
                    "  `ip` varchar(255) DEFAULT NULL,\n" +
                    "  `module` varchar(255) DEFAULT NULL,\n" +
                    "  `path` varchar(255) DEFAULT NULL,\n" +
                    "  `method` varchar(255) DEFAULT NULL,\n" +
                    "  `instruction` varchar(255) DEFAULT NULL,\n" +
                    "  `parameter` varchar(255) DEFAULT NULL,\n" +
                    "  `useragent` varchar(3000) DEFAULT NULL,\n" +
                    "  `date` bigint(20) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`id`)\n" +
                    ") ENGINE=InnoDB AUTO_INCREMENT=27654 DEFAULT CHARSET=utf8;",
            "CREATE TABLE  if not exists  `roles_perms` (\n" +
                    "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `role` varchar(255) DEFAULT NULL,\n" +
                    "  `perm` varchar(255) DEFAULT NULL,\n" +
                    "  `note` varchar(45) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`id`)\n" +
                    ") ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;",
            "CREATE TABLE  if not exists  `USER` (\n" +
                    "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `username` varchar(255) DEFAULT NULL,\n" +
                    "  `password` varchar(255) DEFAULT NULL,\n" +
                    "  `password_salt` varchar(255) DEFAULT NULL,\n" +
                    "  `psd` varchar(45) DEFAULT NULL,\n" +
                    "  `note` varchar(45) DEFAULT NULL,\n" +
                    "  `position` varchar(45) DEFAULT NULL,\n" +
                    "  `department` varchar(45) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`id`),\n" +
                    "  UNIQUE KEY `username_UNIQUE` (`username`)\n" +
                    ") ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;",
            "CREATE TABLE  if not exists  `user_roles` (\n" +
                    "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `username` varchar(255) DEFAULT NULL,\n" +
                    "  `username_mark` varchar(45) DEFAULT NULL,\n" +
                    "  `role_name` varchar(45) DEFAULT NULL,\n" +
                    "  `role` varchar(255) DEFAULT NULL,\n" +
                    "  `role_describe` varchar(45) DEFAULT NULL,\n" +
                    "  `note` varchar(45) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`id`)\n" +
                    ") ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;",
            "CREATE TABLE  if not exists  `user_resource` (\n" +
                    "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `username` varchar(45) DEFAULT NULL,\n" +
                    "  `username_mark` int(11) DEFAULT NULL,\n" +
                    "  `resource_mark` int(11) DEFAULT NULL,\n" +
                    "  `resource_name` varchar(45) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`id`)\n" +
                    ") ENGINE=InnoDB AUTO_INCREMENT=776 DEFAULT CHARSET=utf8;",
            "CREATE TABLE  if not exists  `userperms` (\n" +
                    "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `username` varchar(45) DEFAULT NULL,\n" +
                    "  `username_mark` varchar(45) DEFAULT NULL,\n" +
                    "  `permission` varchar(45) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`id`)\n" +
                    ") ENGINE=InnoDB AUTO_INCREMENT=1666 DEFAULT CHARSET=utf8;\n"


    );




}
