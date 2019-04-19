package http;

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
            queriesInputStream=new FileInputStream("confTJShieldingSystem/conf.properties");
            //queriesInputStream = this.getClass().getClassLoader().getResourceAsStream("confTJShieldingSystem/conf.properties");
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
        return sqlQueries;
    }

    public HashMap<SqlConstants, String> loadSqlQueries() {
        InputStream queriesInputStream = null;
        Properties queriesProps = new Properties();
        try {
            queriesInputStream=new FileInputStream("confTJShieldingSystem/sql.properties");
            //queriesInputStream = this.getClass().getClassLoader().getResourceAsStream("sql.properties");
            queriesProps.load(queriesInputStream);
            queriesInputStream.close();
        } catch (IOException e) {
            logger.error("read databasecon.properties failed " + e);
        }
        HashMap<SqlConstants, String> sqlQueries = new HashMap<>();
        sqlQueries.put(SqlConstants.OPERATION_LOG, queriesProps.getProperty("operation_log"));
        sqlQueries.put(SqlConstants.FODDERINFO_INSERT, queriesProps.getProperty("fodderinfo_insert"));
        sqlQueries.put(SqlConstants.FODDERINFO_LIST, queriesProps.getProperty("fodderinfo_list"));
        sqlQueries.put(SqlConstants.EDIT_CHANNEL_PROMO_EDIT, queriesProps.getProperty("edit_channel_promo_edit"));
        sqlQueries.put(SqlConstants.ADD_CHANNEL_PROMO_EDIT, queriesProps.getProperty("add_channel_promo_edit"));
        sqlQueries.put(SqlConstants.GET_CHANNEL_PROMO_EDIT, queriesProps.getProperty("get_channel_promo_edit"));
        sqlQueries.put(SqlConstants.EDIT_PROMO_CHANNEL, queriesProps.getProperty("edit_promo_channel"));
        sqlQueries.put(SqlConstants.ADD_PROMO_CHANNEL, queriesProps.getProperty("add_promo_channel"));
        sqlQueries.put(SqlConstants.GET_PROMO_CHANNEL, queriesProps.getProperty("get_promo_channel"));
        sqlQueries.put(SqlConstants.GET_PROMO_LIST, queriesProps.getProperty("get_promo_list"));
        sqlQueries.put(SqlConstants.GET_MAX_ID, queriesProps.getProperty("get_max_id"));
        sqlQueries.put(SqlConstants.ADD_PROMO_LIST, queriesProps.getProperty("add_promo_list"));
        sqlQueries.put(SqlConstants.EDIT_PROMO_LIST, queriesProps.getProperty("edit_promo_list"));
        sqlQueries.put(SqlConstants.DEL_PROMO_LIST, queriesProps.getProperty("del_promo_list"));
        sqlQueries.put(SqlConstants.ADD_PROMO_LIST_RECORD, queriesProps.getProperty("add_promo_list_record"));
        sqlQueries.put(SqlConstants.GET_MAX_IDS, queriesProps.getProperty("get_max_ids"));
        sqlQueries.put(SqlConstants.GET_ROW_PROMO_LIST, queriesProps.getProperty("get_row_promo_list"));
        //删除七牛云本地上传记录
        sqlQueries.put(SqlConstants.DEL_QINIU_FILE_RECORD, queriesProps.getProperty("del_qiniu_file_record"));
        //获取渠道互推发布记录
        sqlQueries.put(SqlConstants.LIST_CHANNELPROMO_LIST_RECORD, queriesProps.getProperty("list_channelpromo_list_record"));
        return sqlQueries;
    }


}


