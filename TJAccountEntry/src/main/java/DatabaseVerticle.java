import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.jdbc.JDBCClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import io.vertx.ext.sql.SQLConnection;

public class DatabaseVerticle extends AbstractVerticle {
    private static Logger logger = LoggerFactory.getLogger(DatabaseVerticle.class.getName());

    @Override
    public void start(Future<Void> startFuture) {
        Method method = new Method();
        HashMap<ConfigConstants, String> conf = method.loadSqlQueries();
        JsonObject config = new JsonObject()
                .put("url", conf.get(ConfigConstants.DATABASE_URL))
                .put("driver_class", conf.get(ConfigConstants.DATABASE_DRIVER_CLASS))
                .put("max_pool_size", Integer.valueOf(conf.get(ConfigConstants.DATABASE_MAX_POOL_SIZE)))
                .put("user", conf.get(ConfigConstants.DATABASE_USER))
                .put("password", conf.get(ConfigConstants.DATABASE_PASSWORD));

        JDBCClient jdbcClient = JDBCClient.createShared(vertx, config);


        jdbcClient.getConnection(conn -> {
            if (conn.succeeded()) {
                SQLConnection connection = conn.result();
                connection.batch(user,rs->{
                   if (rs.succeeded()){
                       connection.close();
                       logger.info("database start success");
                       startFuture.complete();
                   }else {
                       logger.error("database start failed ",rs.cause());
                       startFuture.fail(rs.cause());
                   }
                });
            } else {

            }

        });


    }

    List<String> user = Arrays.asList(
            "CREATE TABLE if not exists `tjaccount`.`user` (\n" +
                    "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `username` varchar(45) DEFAULT NULL,\n" +
                    "  `password` varchar(255) DEFAULT NULL,\n" +
                    "  `password_salt` varchar(255) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`id`)\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8;",
            "CREATE TABLE if not exists `tjaccount`.`user_roles` (\n" +
                    "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `username` varchar(45) DEFAULT NULL,\n" +
                    "  `role_varchar` varchar(45) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`id`)\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8;",
            "CREATE TABLE if not exists `tjaccount`.`roles_perms` (\n" +
                    "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `role` varchar(45) DEFAULT NULL,\n" +
                    "  `perm` varchar(45) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`id`)\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8;");


}
