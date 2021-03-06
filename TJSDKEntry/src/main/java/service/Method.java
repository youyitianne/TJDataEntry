package service;

import database.ConfigConstants;
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
            queriesInputStream=new FileInputStream("confTJSDK/conf.properties");
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
        return sqlQueries;
    }

    public HashMap<SqlConstants, String> loadSqlQueries() {
        InputStream queriesInputStream = null;
        Properties queriesProps = new Properties();
        try {
            queriesInputStream=new FileInputStream("confTJSDK/sql.properties");
            //queriesInputStream = this.getClass().getClassLoader().getResourceAsStream("sql.properties");
            queriesProps.load(queriesInputStream);
            queriesInputStream.close();
        } catch (IOException e) {
            logger.error("read databasecon.properties failed " + e);
        }
        HashMap<SqlConstants, String> sqlQueries = new HashMap<>();
        sqlQueries.put(SqlConstants.PROJECT_CONFIG_INFORMATION_INSERT, queriesProps.getProperty("project_config_information_insert"));
        sqlQueries.put(SqlConstants.PROJECT_CONFIG_INFORMATION_LIST, queriesProps.getProperty("project_config_information_list"));
        sqlQueries.put(SqlConstants.PROJECT_CONFIG_PARAMTER_INSERT, queriesProps.getProperty("project_config_list_insert"));
        sqlQueries.put(SqlConstants.PROJECT_CONFIG_PARAMTER_LIST, queriesProps.getProperty("project_config_paramter_list"));
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
        sqlQueries.put(SqlConstants.OPERATION_LOG, queriesProps.getProperty("operation_log"));
        sqlQueries.put(SqlConstants.API_PROJECT_LIST, queriesProps.getProperty("api_project_list"));
        sqlQueries.put(SqlConstants.API_SDK_LIST, queriesProps.getProperty("api_sdk_list"));
        sqlQueries.put(SqlConstants.API_PROJECT_LIST_NEW, queriesProps.getProperty("api_project_list_new"));
        sqlQueries.put(SqlConstants.API_SDK_LIST_NEW, queriesProps.getProperty("api_sdk_list_new"));
        //公司信息
        sqlQueries.put(SqlConstants.ADD_COMPANY_INFO, queriesProps.getProperty("add_company_info"));
        sqlQueries.put(SqlConstants.EDIT_COMPANY_INFO, queriesProps.getProperty("edit_company_info"));
        sqlQueries.put(SqlConstants.LIST_COMPANY_INFO, queriesProps.getProperty("list_company_info"));
        return sqlQueries;
    }

}


