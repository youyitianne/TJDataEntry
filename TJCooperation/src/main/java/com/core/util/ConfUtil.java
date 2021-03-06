package com.core.util;

import com.core.enums.ConfigConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

public class ConfUtil {
    private static Logger logger= LoggerFactory.getLogger(ConfUtil.class.getName());
    /**
     * 读取文件中sqlQueries
     * @return
     * @throws IOException
     */
    public HashMap<ConfigConstants, String> loadConfigQueries() {
        InputStream queriesInputStream=null;
        Properties queriesProps = new Properties();
        try {
            queriesInputStream=new FileInputStream("confTJCooperation/conf.properties");
            //queriesInputStream=this.getClass().getClassLoader().getResourceAsStream("conf.properties");
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
