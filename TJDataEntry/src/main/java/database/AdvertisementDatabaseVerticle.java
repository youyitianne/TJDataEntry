package database;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.jdbc.JDBCClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.pubmethod.InitConf;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class AdvertisementDatabaseVerticle extends AbstractVerticle {
    private static Logger logger = LoggerFactory.getLogger(AdvertisementDatabaseVerticle.class.getName());
    private AdvertisementService advertisementService = new AdvertisementService();

    @Override
    public void start(Future<Void> startFuture) {

        InitConf initConf = new InitConf();
        HashMap<ConfigConstants, String> conf = initConf.loadSqlQueries();
        JsonObject config = new JsonObject()
                .put("url", conf.get(ConfigConstants.DATABASE_URL))
                .put("driver_class", conf.get(ConfigConstants.DATABASE_DRIVER_CLASS))
                .put("max_pool_size", Integer.valueOf(conf.get(ConfigConstants.DATABASE_MAX_POOL_SIZE)))
                .put("user", conf.get(ConfigConstants.DATABASE_USER))
                .put("password", conf.get(ConfigConstants.DATABASE_PASSWORD));

        logger.info("start connect to database");
        JDBCClient jdbcClient = JDBCClient.createShared(vertx, config);
        logger.info("jdbcClient started");
        advertisementService.queryNoResult(jdbcClient, intString()).setHandler(rs -> {
            if (rs.succeeded()) {
                logger.info("+++++++++++++++++++++++");
                logger.info("database is ready");
                startFuture.complete();
            } else {
                logger.error("Database initialization failed ", rs.cause());
                logger.info("+++++++++++++++++++++++");
                startFuture.fail(rs.cause());
            }
        });
    }

    private List<String> initlist(){
        List<String> databaseInitSqlQueries=new ArrayList<>();
//        for (int i=0;i<1500;i++){
//            databaseInitSqlQueries.add("INSERT INTO `advertisement`.`new_table`(`test`)VALUES('"+i+"');");
//        }

        databaseInitSqlQueries.add(
                "CREATE TABLE  if not exists  `advertisingdata` (\n" +
                "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                "  `date` int(11) DEFAULT NULL,\n" +
                "  `app_name` varchar(45) DEFAULT NULL,\n" +
                "  `channel` varchar(45) DEFAULT NULL,\n" +
                "  `advertising_type` varchar(45) DEFAULT NULL,\n" +
                "  `earned` double DEFAULT NULL,\n" +
                "  `click_rate` double DEFAULT NULL,\n" +
                "  `ecpm` double DEFAULT NULL,\n" +
                "  `impression` int(11) DEFAULT NULL,\n" +
                "  `click` int(11) DEFAULT NULL,\n" +
                "  `fill_rate` double DEFAULT NULL,\n" +
                "  `platform` varchar(45) DEFAULT NULL,\n" +
                "  `note` varchar(45) DEFAULT NULL,\n" +
                "  `sdk_name` varchar(45) DEFAULT NULL,\n" +
                "  PRIMARY KEY (`id`)\n" +
                ") ENGINE=InnoDB AUTO_INCREMENT=551183 DEFAULT CHARSET=utf8;");
        return databaseInitSqlQueries;

    }

    private String intString(){
        List<String> databaseInitSqlQueries=new ArrayList<>();
//        for (int i=0;i<1500;i++){
//            databaseInitSqlQueries.add("INSERT INTO `advertisement`.`new_table`(`test`)VALUES('"+i+"');");
//        }
        String advertisement="CREATE TABLE  if not exists  `advertisingdata` (\n" +
                "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                "  `date` int(11) DEFAULT NULL,\n" +
                "  `app_name` varchar(45) DEFAULT NULL,\n" +
                "  `channel` varchar(45) DEFAULT NULL,\n" +
                "  `advertising_type` varchar(45) DEFAULT NULL,\n" +
                "  `earned` double DEFAULT NULL,\n" +
                "  `click_rate` double DEFAULT NULL,\n" +
                "  `ecpm` double DEFAULT NULL,\n" +
                "  `impression` int(11) DEFAULT NULL,\n" +
                "  `click` int(11) DEFAULT NULL,\n" +
                "  `fill_rate` double DEFAULT NULL,\n" +
                "  `platform` varchar(45) DEFAULT NULL,\n" +
                "  `note` varchar(45) DEFAULT NULL,\n" +
                "  `sdk_name` varchar(45) DEFAULT NULL,\n" +
                "  PRIMARY KEY (`id`)\n" +
                ") ENGINE=InnoDB AUTO_INCREMENT=551183 DEFAULT CHARSET=utf8;";

        databaseInitSqlQueries.add(advertisement);
        return advertisement;

    }


    //数据库初始化语句
    List<String> databaseInitSqlQueries = Arrays.asList(
            "CREATE TABLE  if not exists  `advertisingtype` (\n" +
                    "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` varchar(45) DEFAULT NULL,\n" +
                    "  `program_mark` varchar(45) DEFAULT NULL,\n" +
                    "  `note` varchar(45) DEFAULT NULL,\n" +
                    "  `introduce` varchar(45) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`id`)\n" +
                    ") ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;",
            "CREATE TABLE  if not exists  `appname` (\n" +
                    "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` varchar(45) DEFAULT NULL,\n" +
                    "  `system` varchar(45) DEFAULT NULL,\n" +
                    "  `icon` varchar(45) DEFAULT NULL,\n" +
                    "  `project` varchar(45) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`id`)\n" +
                    ") ENGINE=InnoDB AUTO_INCREMENT=109 DEFAULT CHARSET=utf8;",
            "CREATE TABLE if not exists  `channel` (\n" +
                    "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` varchar(45) DEFAULT NULL,\n" +
                    "  `program_mark` varchar(45) DEFAULT NULL,\n" +
                    "  `note` varchar(45) DEFAULT '暂无',\n" +
                    "  PRIMARY KEY (`id`)\n" +
                    ") ENGINE=InnoDB AUTO_INCREMENT=77 DEFAULT CHARSET=utf8;\n","CREATE TABLE  if not exists  `paydata` (\n" +
                    "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `order_number` varchar(75) DEFAULT NULL,\n" +
                    "  `request_number` varchar(45) DEFAULT NULL,\n" +
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
                    "  `channel` varchar(45) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`id`)\n" +
                    ") ENGINE=InnoDB AUTO_INCREMENT=33219 DEFAULT CHARSET=utf8;",
            "CREATE TABLE if not exists  `project` (\n" +
                    "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `project_name` varchar(45) DEFAULT NULL,\n" +
                    "  `preheat` varchar(45) DEFAULT NULL,\n" +
                    "  `schedule` varchar(45) DEFAULT NULL,\n" +
                    "  `compete_good` varchar(45) DEFAULT NULL,\n" +
                    "  `version_plan` varchar(45) DEFAULT NULL,\n" +
                    "  `note` varchar(45) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`id`)\n" +
                    ") ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8;",
            "CREATE TABLE  if not exists  `project_list` (\n" +
                    "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `project_name` varchar(45) DEFAULT NULL,\n" +
                    "  `package_name` varchar(45) DEFAULT NULL,\n" +
                    "  `channel` varchar(45) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`id`)\n" +
                    ") ENGINE=InnoDB AUTO_INCREMENT=682 DEFAULT CHARSET=utf8;",
            "CREATE TABLE  if not exists  `userdata` (\n" +
                    "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `date` int(11) DEFAULT NULL,\n" +
                    "  `app_name` varchar(45) DEFAULT NULL,\n" +
                    "  `channel` varchar(45) DEFAULT NULL,\n" +
                    "  `dnu` int(11) DEFAULT NULL,\n" +
                    "  `dau` int(11) DEFAULT NULL,\n" +
                    "  `startup_time` int(11) DEFAULT NULL,\n" +
                    "  `single_use_time` varchar(255) DEFAULT NULL,\n" +
                    "  `retention` double DEFAULT NULL,\n" +
                    "  `version` varchar(45) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`id`)\n" +
                    ") ENGINE=InnoDB AUTO_INCREMENT=57627 DEFAULT CHARSET=utf8;");

}
