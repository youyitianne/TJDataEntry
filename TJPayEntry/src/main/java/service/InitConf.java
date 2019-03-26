package service;

import database.SqlConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class InitConf {
    private static Logger logger= LoggerFactory.getLogger(InitConf.class.getName());
    /**
     * 读取文件中sqlQueries
     * @return
     * @throws IOException
     */
    public HashMap<ConfigConstants, String> loadConfigQueries() {
        InputStream queriesInputStream=null;
        Properties queriesProps = new Properties();
        try {
            queriesInputStream=new FileInputStream("conf.properties");
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

    /**
     * 读取文件中sqlQueries
     * @return
     * @throws IOException
     */
    public HashMap<SqlConstants, String> loadSqlQueries() {
        InputStream queriesInputStream=null;
        Properties queriesProps = new Properties();
        try {
            queriesInputStream=new FileInputStream("sql.properties");
            //queriesInputStream=this.getClass().getClassLoader().getResourceAsStream("sql.properties");
            queriesProps.load(queriesInputStream);
            queriesInputStream.close();
        }catch (IOException e){
            logger.error("read databasecon.properties failed "+e);
        }
        HashMap<SqlConstants, String> sqlQueries = new HashMap<>();
        sqlQueries.put(SqlConstants.PAY_INSERT, queriesProps.getProperty("pay_insert"));
        sqlQueries.put(SqlConstants.PAY_LIST, queriesProps.getProperty("pay_list"));
        sqlQueries.put(SqlConstants.PAY_REPEAT_SELECT, queriesProps.getProperty("pay_repeat_select"));
        sqlQueries.put(SqlConstants.PAY_REPEAT_DELETE, queriesProps.getProperty("pay_repeat_delete"));
        sqlQueries.put(SqlConstants.OPERATION_LOG, queriesProps.getProperty("operation_log"));
        return sqlQueries;
    }


}
