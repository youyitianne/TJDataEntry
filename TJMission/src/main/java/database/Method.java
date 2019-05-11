package database;

import database.SqlConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

public class Method {
    private static Logger logger = LoggerFactory.getLogger(Method.class.getName());

    public HashMap<ConfigConstants, String> loadConfigQueries() {
        InputStream queriesInputStream = null;
        Properties queriesProps = new Properties();
        try {
            queriesInputStream=new FileInputStream("confTJMission/conf.properties");
            //queriesInputStream = this.getClass().getClassLoader().getResourceAsStream("conf.properties");
            queriesProps.load(queriesInputStream);
            queriesInputStream.close();
        } catch (IOException e) {
            logger.error("read databasecon.properties failed " + e);
        }
        HashMap<ConfigConstants, String> sqlQueries = new HashMap<>();
        sqlQueries.put(ConfigConstants.DATABASE_URL, queriesProps.getProperty("url"));
        sqlQueries.put(ConfigConstants.DATABASE_DRIVER_CLASS, queriesProps.getProperty("driver_class"));
        sqlQueries.put(ConfigConstants.DATABASE_MAX_POOL_SIZE, queriesProps.getProperty("max_pool_size"));
        sqlQueries.put(ConfigConstants.DATABASE_USER, queriesProps.getProperty("user"));
        sqlQueries.put(ConfigConstants.DATABASE_PASSWORD, queriesProps.getProperty("password"));
        sqlQueries.put(ConfigConstants.HTTP_PORT, queriesProps.getProperty("http_port"));
        sqlQueries.put(ConfigConstants.DOWNLOAD_CACHE_PATH, queriesProps.getProperty("download_cache_path"));
        return sqlQueries;
    }

    public HashMap<SqlConstants, String> loadSqlQueries() {
        InputStream queriesInputStream = null;
        Properties queriesProps = new Properties();
        try {
            queriesInputStream=new FileInputStream("confTJMission/sql.properties");
            //queriesInputStream = this.getClass().getClassLoader().getResourceAsStream("sql.properties");
            queriesProps.load(queriesInputStream);
            queriesInputStream.close();
        } catch (IOException e) {
            logger.error("read databasecon.properties failed " + e);
        }
        HashMap<SqlConstants, String> sqlQueries = new HashMap<>();
        sqlQueries.put(SqlConstants.KEYSTORE_LIST, queriesProps.getProperty("keystore_list"));
        sqlQueries.put(SqlConstants.KEYSTORE_UPDATE, queriesProps.getProperty("keystore_update"));
        sqlQueries.put(SqlConstants.KEYSTORE_INSERT, queriesProps.getProperty("keystore_insert"));
        return sqlQueries;
    }

}


