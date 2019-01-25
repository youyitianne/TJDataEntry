package service;

import database.ConfigConstants;
import database.SqlConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
            queriesInputStream = this.getClass().getClassLoader().getResourceAsStream("conf.properties");
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
            queriesInputStream = this.getClass().getClassLoader().getResourceAsStream("sql.properties");
            queriesProps.load(queriesInputStream);
            queriesInputStream.close();
        } catch (IOException e) {
            logger.error("read databasecon.properties failed " + e);
        }
        HashMap<SqlConstants, String> sqlQueries = new HashMap<>();
        sqlQueries.put(SqlConstants.PROJECT_CONFIG_INFORMATION_INSERT, queriesProps.getProperty("project_config_information_insert"));
        sqlQueries.put(SqlConstants.PROJECT_CONFIG_INFORMATION_LIST, queriesProps.getProperty("project_config_information_list"));
        sqlQueries.put(SqlConstants.PROJECT_CONFIG_PARAMTER_INSERT, queriesProps.getProperty("project_config_list_insert"));
        sqlQueries.put(SqlConstants.PROJECT_CONFIG_PARAMTER_LIST, queriesProps.getProperty("project_config_list_list"));
        sqlQueries.put(SqlConstants.PROJECT_CONFIG_INFORMATION_UPDATE, queriesProps.getProperty("project_config_information_update"));
        sqlQueries.put(SqlConstants.PROJECT_CONFIG_PARAMTER_DELETE, queriesProps.getProperty("project_config_list_delete"));
        sqlQueries.put(SqlConstants.SDK_INFO_INSERT, queriesProps.getProperty("sdk_info_insert"));
        sqlQueries.put(SqlConstants.SDK_LIST_INSERT, queriesProps.getProperty("sdk_list_insert"));
        sqlQueries.put(SqlConstants.SDK_INFO_LIST, queriesProps.getProperty("sdk_info_list"));
        sqlQueries.put(SqlConstants.SDK_LIST_LIST, queriesProps.getProperty("sdk_list_list"));
        sqlQueries.put(SqlConstants.SDK_INFO_UPDATE, queriesProps.getProperty("sdk_info_update"));
        sqlQueries.put(SqlConstants.SDK_LIST_DELETE, queriesProps.getProperty("sdk_list_delete"));
        sqlQueries.put(SqlConstants.PROJECT_CONFIG_PUBLISH_LIST, queriesProps.getProperty("project_config_publish_list"));
        sqlQueries.put(SqlConstants.PROJECT_CONFIG_INFORMATION_DELETE, queriesProps.getProperty("project_config_information_delete"));
        sqlQueries.put(SqlConstants.PROJECT_CONFIG_LIST_DELETE_BYID, queriesProps.getProperty("project_config_list_delete_byid"));
        sqlQueries.put(SqlConstants.PROJECT_CONFIG_INFORMATION_COUNT, queriesProps.getProperty("project_config_information_count"));
        return sqlQueries;
    }

}


