package service.pubmethod;

import database.ConfigConstants;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.entity.ArpuData;

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
    public HashMap<ConfigConstants, String> loadSqlQueries() {
        InputStream queriesInputStream=null;
        Properties queriesProps = new Properties();
        try {
            queriesInputStream=this.getClass().getClassLoader().getResourceAsStream("databasecon.properties");
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

    public static List<JsonObject> getPlatform(){
        List<JsonObject> list=new ArrayList<>();
        JsonObject jsonObject=new JsonObject();
        jsonObject.put("channel","oppo").put("platform","oppo");
        list.add(jsonObject);
        JsonObject jsonObject1=new JsonObject();
        jsonObject1.put("channel","vivo").put("platform","vivo");
        list.add(jsonObject1);
        JsonObject jsonObject2=new JsonObject();
        jsonObject2.put("channel","huawei").put("platform","华为");
        list.add(jsonObject2);
        JsonObject jsonObject3=new JsonObject();
        jsonObject3.put("channel","4399").put("platform","4399");
        list.add(jsonObject3);
        JsonObject jsonObject4=new JsonObject();
        jsonObject4.put("channel","qihoo").put("platform","qihoo");
        list.add(jsonObject4);
        JsonObject jsonObject5=new JsonObject();
        jsonObject5.put("channel","meizu").put("platform","魅族");
        list.add(jsonObject5);
        JsonObject jsonObject6=new JsonObject();
        jsonObject6.put("channel","xiaomi").put("platform","小米");
        list.add(jsonObject6);
        JsonObject jsonObject7=new JsonObject();
        jsonObject7.put("channel","toutiao").put("platform","头条");
        list.add(jsonObject7);
        JsonObject jsonObject8=new JsonObject();
        jsonObject8.put("channel","yyb").put("platform","应用宝");
        list.add(jsonObject8);
        JsonObject jsonObject9=new JsonObject();
        jsonObject9.put("channel","zhongxing").put("platform","中兴");
        list.add(jsonObject9);
        JsonObject jsonObject10=new JsonObject();
        jsonObject10.put("channel","haoyou").put("platform","好游");
        list.add(jsonObject10);
        JsonObject jsonObject11=new JsonObject();
        jsonObject11.put("channel","taptap").put("platform","taptap");
        list.add(jsonObject11);
        JsonObject jsonObject12=new JsonObject();
        jsonObject12.put("channel","samsung").put("platform","三星");
        list.add(jsonObject12);
        JsonObject jsonObject13=new JsonObject();
        jsonObject13.put("channel","lenovo").put("platform","联想");
        list.add(jsonObject13);
        JsonObject jsonObject14=new JsonObject();
        jsonObject14.put("channel","jiuyou").put("platform","九游");
        list.add(jsonObject14);
        JsonObject jsonObject15=new JsonObject();
        jsonObject15.put("channel","baidu").put("platform","百度");
        list.add(jsonObject15);
        JsonObject jsonObject16=new JsonObject();
        jsonObject16.put("channel","tianzi").put("platform","天资");
        list.add(jsonObject16);
        JsonObject jsonObject17=new JsonObject();
        jsonObject17.put("channel","nby").put("platform","努比亚");
        list.add(jsonObject17);
        return list;
    }

    public static List<List<ArpuData>> getAllArpuData(List<JsonObject> userlist,List<JsonObject> adlist,List<Long> longdates){
        List<JsonObject> condition=InitConf.getPlatform();
        List<List<ArpuData>> outerlist=new ArrayList<>();
        for (int i=0;i<longdates.size();i++){
            List<ArpuData> innerlist=new ArrayList<>();
            for (int j=0;j<condition.size();j++){
                ArpuData arpuData=new ArpuData();
                Iterator<JsonObject> user=userlist.iterator();
                Integer active_user=0;
                while (user.hasNext()){
                    JsonObject jsonObject=user.next();
                    if (jsonObject.getLong("date").equals(longdates.get(i))&&jsonObject.getString("channel").equals(condition.get(j).getString("channel"))){
                        active_user=jsonObject.getInteger("dau");
                        user.remove();
                    }
                }
                arpuData.setActive_user(active_user);
                Iterator<JsonObject> it=adlist.iterator();
                Double channel_earned=0.0;
                Double gdt_earned=0.0;
                Double tt_earned=0.0;
                while (it.hasNext()){
                    JsonObject jsonObject=it.next();
                    Boolean flag=false;
                    if (jsonObject.getLong("date").equals(longdates.get(i))&&
                            jsonObject.getString("channel").equals(condition.get(j).getString("channel"))&&
                            jsonObject.getString("platform").equals(condition.get(j).getString("platform"))){
                        channel_earned=channel_earned+jsonObject.getDouble("earned");
                        flag=true;
                    }
                    if (jsonObject.getLong("date").equals(longdates.get(i))&&
                            jsonObject.getString("channel").equals(condition.get(j).getString("channel"))&&
                            jsonObject.getString("platform").equals("广点通")){
                        gdt_earned=gdt_earned+jsonObject.getDouble("earned");
                        flag=true;
                    }
                    if (jsonObject.getLong("date").equals(longdates.get(i))&&
                            jsonObject.getString("channel").equals(condition.get(j).getString("channel"))&&
                            jsonObject.getString("platform").equals("头条")){
                        tt_earned=tt_earned+jsonObject.getDouble("earned");
                        flag=true;
                    }
                    if (flag){
                        it.remove();
                    }
                }
                Double channel=channel_earned;
                Double gdt=gdt_earned;

                Double all=gdt_earned+channel_earned+tt_earned;
                if (condition.get(j).getString("channel").equals("meizu")){
                    channel=channel_earned/2;
                    gdt=gdt_earned/2;
                    all=gdt_earned+channel_earned/2;
                }
                arpuData.setToutiao_earned(tt_earned);
                arpuData.setChannel_earned((channel));
                arpuData.setGdt_earned((gdt));
                arpuData.setAll_earned((all));
                arpuData.setToutiao_arpu(active_user==0?0:((tt_earned)/active_user));
                arpuData.setAll_arpu(active_user==0?0:((all)/active_user));
                arpuData.setChannel_arpu(active_user==0?0:(channel/active_user));
                arpuData.setGdt_arpu(active_user==0?0:(gdt/active_user));
                innerlist.add(arpuData);
            }
            outerlist.add(innerlist);
        }
        return outerlist;
    }

    public static List<List<ArpuData>> getAllArpuData1(List<JsonObject> userlist,List<JsonObject> adlist,List<Long> longdates,List name){
        List<JsonObject> condition=InitConf.getPlatform();
        List<List<ArpuData>> outerlist=new ArrayList<>();
        for (int i=0;i<longdates.size();i++){
            List<ArpuData> innerlist=new ArrayList<>();
            for (int j=0;j<condition.size();j++){
                ArpuData arpuData=new ArpuData();
                Iterator<JsonObject> user=userlist.iterator();
                Integer active_user=0;
                while (user.hasNext()){
                    JsonObject jsonObject=user.next();
                    if (jsonObject.getLong("date").equals(longdates.get(i))&&validNameList(name,jsonObject.getString("app_name"))&&jsonObject.getString("channel").equals(condition.get(j).getString("channel"))){
                        active_user=jsonObject.getInteger("dau")+active_user;
                        user.remove();
                    }
                }
                arpuData.setActive_user(active_user);
                Iterator<JsonObject> it=adlist.iterator();
                Double channel_earned=0.0;
                Double gdt_earned=0.0;
                Double tt_earned=0.0;
                while (it.hasNext()){
                    JsonObject jsonObject=it.next();
                    Boolean flag=false;
                    if (jsonObject.getLong("date").equals(longdates.get(i))&&
                            jsonObject.getString("channel").equals(condition.get(j).getString("channel"))&&
                            jsonObject.getString("platform").equals(condition.get(j).getString("platform"))
                            &&validNameList(name,jsonObject.getString("app_name"))){
                        channel_earned=channel_earned+jsonObject.getDouble("earned");
                        flag=true;
                    }
                    if (jsonObject.getLong("date").equals(longdates.get(i))&&
                            jsonObject.getString("channel").equals(condition.get(j).getString("channel"))&&
                            jsonObject.getString("platform").equals("广点通")
                            &&validNameList(name,jsonObject.getString("app_name"))){
                        gdt_earned=gdt_earned+jsonObject.getDouble("earned");
                        flag=true;
                    }
                    if (jsonObject.getLong("date").equals(longdates.get(i))&&
                            jsonObject.getString("channel").equals(condition.get(j).getString("channel"))&&
                            jsonObject.getString("platform").equals("头条")
                            &&validNameList(name,jsonObject.getString("app_name"))){
                        tt_earned=tt_earned+jsonObject.getDouble("earned");
                        flag=true;
                    }
                    if (flag){
                        it.remove();
                    }
                }
                Double channel=channel_earned;
                Double gdt=gdt_earned;

                Double all=gdt_earned+channel_earned+tt_earned;
                if (condition.get(j).getString("channel").equals("meizu")){
                    channel=channel_earned/2;
                    gdt=gdt_earned/2;
                    all=gdt_earned+channel_earned/2;
                }
                arpuData.setToutiao_earned(tt_earned);
                arpuData.setChannel_earned((channel));
                arpuData.setGdt_earned((gdt));
                arpuData.setAll_earned((all));
                arpuData.setToutiao_arpu(active_user==0?0:((tt_earned)/active_user));
                arpuData.setAll_arpu(active_user==0?0:((all)/active_user));
                arpuData.setChannel_arpu(active_user==0?0:(channel/active_user));
                arpuData.setGdt_arpu(active_user==0?0:(gdt/active_user));
                innerlist.add(arpuData);
            }
            outerlist.add(innerlist);
        }
        return outerlist;
    }

    /**
     * 判断集合是否包含一个字符串
     * @param appName
     * @param name
     * @return
     */
    public static Boolean validNameList(List appName,String name){
        for (int i=0;i<appName.size();i++){
            if (appName.get(i).equals(name)){
                return true;
            }
        }
        return false;
    }


}
