package database;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.pubmethod.InitConf;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class AdvertisementDatabaseVerticle extends AbstractVerticle {
    private static Logger logger= LoggerFactory.getLogger(AdvertisementDatabaseVerticle.class.getName());
    private AdvertisementService advertisementService;
    @Override
    public void start(Future<Void> startFuture){
        InitConf initConf=new InitConf();
        HashMap<ConfigConstants,String> conf=initConf.loadSqlQueries();
        JsonObject config=new JsonObject()
                .put("url",conf.get(ConfigConstants.DATABASE_URL))
                .put("driver_class",conf.get(ConfigConstants.DATABASE_DRIVER_CLASS))
                .put("max_pool_size",Integer.valueOf(conf.get(ConfigConstants.DATABASE_MAX_POOL_SIZE)))
                .put("user",conf.get(ConfigConstants.DATABASE_USER))
                .put("password",conf.get(ConfigConstants.DATABASE_PASSWORD));

        advertisementService=new AdvertisementService(vertx,config);


        advertisementService.batch(databaseInitSqlQueries).setHandler(rs->{
            if (rs.succeeded()){
                logger.info("database is ready");
                startFuture.complete();
            }else {
                logger.error("Database initialization failed ",rs.cause());
                startFuture.fail(rs.cause());
            }
        });
}



    //数据库初始化语句
    List<String> databaseInitSqlQueries = Arrays.asList(
            "CREATE TABLE if not exists `advertisement`.`advertisingdata`(\n" +
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
                    ") ENGINE=InnoDB AUTO_INCREMENT=1703 DEFAULT CHARSET=utf8;",
            "CREATE TABLE if not exists `advertisement`.`advertisingtype` (\n" +
                    "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` varchar(45) DEFAULT NULL,\n" +
                    "  `program_mark` varchar(45) DEFAULT NULL,\n" +
                    "  `note` varchar(45) DEFAULT NULL,\n" +
                    "  `introduce` varchar(45) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`id`)\n" +
                    ") ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;\n","\n" +
            "CREATE TABLE if not exists `advertisement`.`appname` (\n" +
                    "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` varchar(45) DEFAULT NULL,\n" +
                    "  `system` varchar(45) DEFAULT NULL,\n" +
                    "  `icon` varchar(45) DEFAULT NULL,\n" +
                    "  `introduce` varchar(45) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`id`)\n" +
                    ") ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;",
            "CREATE TABLE if not exists `advertisement`.`channel` (\n" +
                    "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `name` varchar(45) DEFAULT NULL,\n" +
                    "  `program_mark` varchar(45) DEFAULT NULL,\n" +
                    "  `note` varchar(45) DEFAULT '暂无',\n" +
                    "  PRIMARY KEY (`id`)\n" +
                    ") ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;",
            "CREATE TABLE if not exists `advertisement`.`userdata` (\n" +
                    "  `id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                    "  `date` int(11) DEFAULT NULL,\n" +
                    "  `app_name` varchar(45) DEFAULT NULL,\n" +
                    "  `channel` varchar(45) DEFAULT NULL,\n" +
                    "  `dnu` int(11) DEFAULT NULL,\n" +
                    "  `dau` int(11) DEFAULT NULL,\n" +
                    "  `startup_time` int(11) DEFAULT NULL,\n" +
                    "  `single_use_time` varchar(45) DEFAULT NULL,\n" +
                    "  `retention` double DEFAULT NULL,\n" +
                    "  `version` varchar(45) DEFAULT NULL,\n" +
                    "  PRIMARY KEY (`id`)\n" +
                    ") ENGINE=InnoDB AUTO_INCREMENT=931 DEFAULT CHARSET=utf8;");
}
