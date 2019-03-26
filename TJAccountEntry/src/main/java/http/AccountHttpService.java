package http;

import database.ConfigConstants;
import io.vertx.core.json.JsonArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class AccountHttpService {
    private static Logger logger = LoggerFactory.getLogger(AccountHttpService.class.getName());

    /**
     * 读取文件中sqlQueries
     * @return
     * @throws IOException
     */
    public HashMap<ConfigConstants, String> loadSqlQueries() {
        InputStream queriesInputStream=null;
        Properties queriesProps = new Properties();
        try {
            //queriesInputStream=new FileInputStream("conf.properties");
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

    /**
     * JsonArray 转list
     */
    public static List toList(JsonArray jsonArray){
        List list=new ArrayList();
        Iterator it = jsonArray.iterator();
        while (it.hasNext()){
            list.add(it.next());
        }
        return list;
    }

    /**
     * 定时任务
     */
    public static void timerTask(){
        Calendar calendar=Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,1);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);

        Date time=calendar.getTime();
        Timer timer=new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                CacheList.TOKEN_CACHE_BLACKLIST.clear();
            }
        },time,1000*60*60);
    }

    /**
     *判断token是否在黑名单
     */
    public static Boolean mach_TOKEN_BLACKLIST(String token){
        List<String> lists= CacheList.TOKEN_CACHE_BLACKLIST;
        if (lists!=null){
            Iterator iterator=lists.iterator();
            while (iterator.hasNext()){
                if (token.equals(iterator.next())){
                    return true;
                }
            }
        }
        return false;
    }
}
