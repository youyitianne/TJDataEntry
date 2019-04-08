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
