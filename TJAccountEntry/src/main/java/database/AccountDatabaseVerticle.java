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
        HashMap<ConfigConstants, String> conf = loadSqlQueries();

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

//        jdbcClient.getConnection(conn -> {
//            if (conn.succeeded()) {
//                SQLConnection connection = conn.result();
//                connection.batch(user,rs->{
//                   if (rs.succeeded()){
//                       connection.close();
//                       logger.info("database start success");
//                       startFuture.complete();
//                   }else {
//                       logger.error("database start failed ",rs.cause());
//                       startFuture.fail(rs.cause());
//                   }
//                });
//            } else {
//            }
//        });
    }

    List<String> lists = Arrays.asList(
            "CREATE TABLE  if not exists `tjaccount`.`roles_perms` (\n" +
                    "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `role` varchar(255) DEFAULT NULL,\n" +
                    "  `perm` varchar(255) DEFAULT NULL,\n" +
                    "  `note` varchar(45) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`id`)\n" +
                    ") ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;",
            "CREATE TABLE  if not exists `tjaccount`.`user` (\n" +
                    "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `username` varchar(255) DEFAULT NULL,\n" +
                    "  `psd` varchar(45) DEFAULT NULL,\n" +
                    "  `department` varchar(45) DEFAULT NULL,\n" +
                    "  `position` varchar(45) DEFAULT NULL,\n" +
                    "  `note` varchar(45) DEFAULT NULL,\n" +
                    "  `password` varchar(255) DEFAULT NULL,\n" +
                    "  `password_salt` varchar(255) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`id`),\n" +
                    "  UNIQUE KEY `username_UNIQUE` (`username`)\n" +
                    ") ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;",
            "CREATE TABLE if not exists `tjaccount`.`user_resource` (\n" +
                    "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `username` varchar(45) DEFAULT NULL,\n" +
                    "  `username_mark` int(11) DEFAULT NULL,\n" +
                    "  `resource_mark` int(11) DEFAULT NULL,\n" +
                    "  `resource_name` varchar(45) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`id`)\n" +
                    ") ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;",
            "CREATE TABLE if not exists `tjaccount`.`user_roles` (\n" +
                    "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `username` varchar(255) DEFAULT NULL,\n" +
                    "  `username_mark` varchar(45) DEFAULT NULL,\n" +
                    "  `role_name` varchar(45) DEFAULT NULL,\n" +
                    "  `role` varchar(255) DEFAULT NULL,\n" +
                    "  `role_describe` varchar(45) DEFAULT NULL,\n" +
                    "  `note` varchar(45) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`id`)\n" +
                    ") ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;",
            "CREATE TABLE if not exists `tjaccount`.`userperms` (\n" +
                    "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `username` varchar(45) DEFAULT NULL,\n" +
                    "  `username_mark` varchar(45) DEFAULT NULL,\n" +
                    "  `permission` varchar(45) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`id`)\n" +
                    ") ENGINE=InnoDB AUTO_INCREMENT=341 DEFAULT CHARSET=utf8;"
    );

    private HashMap<ConfigConstants, String> loadSqlQueries() {
        InputStream queriesInputStream=null;
        Properties queriesProps = new Properties();
        try {
            queriesInputStream=this.getClass().getClassLoader().getResourceAsStream("conf.properties");
            queriesProps.load(queriesInputStream);
            queriesInputStream.close();
        }catch (IOException e){
            logger.error("read databasecon.properties failed "+e);
        }
        HashMap<ConfigConstants, String> sqlQueries = new HashMap<>();
        sqlQueries.put(ConfigConstants.DATABASE_URL, queriesProps.getProperty("url"));
        sqlQueries.put(ConfigConstants.DATABASE_DRIVER_CLASS, queriesProps.getProperty("driver_class"));
        sqlQueries.put(ConfigConstants.DATABASE_MAX_POOL_SIZE, queriesProps.getProperty("max_pool_size"));
        sqlQueries.put(ConfigConstants.DATABASE_USER, queriesProps.getProperty("user"));
        sqlQueries.put(ConfigConstants.DATABASE_PASSWORD, queriesProps.getProperty("password"));
        sqlQueries.put(ConfigConstants.HTTP_PORT, queriesProps.getProperty("http_port"));
        return sqlQueries;
    }


}
