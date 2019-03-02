package service.dataprocessing;

import io.vertx.core.json.JsonObject;
import java.util.ArrayList;
import java.util.List;

public class DailyEarnedHandler {
    /**
     * （获取userlists时经过排序,遍历一次就能整合大量数据）
     * 整合同一项目的用户数据，不区分渠道
     * @param userlists 用户数据对象
     * @param nameJsonObject    应用名json
     * @return  jsonObjectList
     */
    public static List<JsonObject> countUserdata(List<JsonObject> userlists,JsonObject nameJsonObject){
        List<JsonObject> jsonObjects=new ArrayList<>();
        for (int i=0;i<userlists.size();i++){
            String app_name=userlists.get(i).getString("app_name");
            Integer dau=userlists.get(i).getInteger("dau");
            Integer date=userlists.get(i).getInteger("date");
            if (jsonObjects.size()==0){
                JsonObject jsonObject=new JsonObject();
                jsonObject.put("project",nameJsonObject.getString(app_name));
                jsonObject.put("date",date);
                jsonObject.put("dau",dau);
                jsonObjects.add(jsonObject);
            }else {
                String name1=jsonObjects.get(jsonObjects.size()-1).getString("project");
                if (name1==null){
                    continue;
                }
                if (name1.equals(nameJsonObject.getString(app_name))&&
                        jsonObjects.get(jsonObjects.size()-1).getInteger("date").equals(date)){
                    Integer original=jsonObjects.get(jsonObjects.size()-1).getInteger("dau");
                    jsonObjects.get(jsonObjects.size()-1).put("dau",original+dau);
                }else {
                    JsonObject jsonObject=new JsonObject();
                    jsonObject.put("project",nameJsonObject.getString(app_name));
                    jsonObject.put("date",date);
                    jsonObject.put("dau",dau);
                    jsonObjects.add(jsonObject);
                }
            }
        }
        return jsonObjects;
    }

    /**
     * （获取userlists时经过排序,遍历一次就能整合大量数据）
     * 整合同一项目的广告数据，不区分渠道
     * @param adlists 广告数据对象
     * @param nameJsonObject    应用名json
     * @return  jsonObjectList
     */
    public static List<JsonObject> countAddata(List<JsonObject> adlists,JsonObject nameJsonObject){
        List<JsonObject> jsonObjects=new ArrayList<>();
        for (int i=0;i<adlists.size();i++){
            String app_name=adlists.get(i).getString("app_name");
            double earned=adlists.get(i).getDouble("earned");
            Integer date=adlists.get(i).getInteger("date");
            if (jsonObjects.size()==0){
                JsonObject jsonObject=new JsonObject();
                //jsonObject.put("app_name",app_name);
                jsonObject.put("project",nameJsonObject.getString(app_name));
                jsonObject.put("date",date);
                jsonObject.put("earned",earned);
                jsonObjects.add(jsonObject);
            }else {
                if (jsonObjects.get(jsonObjects.size()-1).getString("project").equals(nameJsonObject.getString(app_name))&&
                        jsonObjects.get(jsonObjects.size()-1).getInteger("date").equals(date)){
                    double original=jsonObjects.get(jsonObjects.size()-1).getDouble("earned");
                    jsonObjects.get(jsonObjects.size()-1).put("earned",original+earned);
                }else {
                    JsonObject jsonObject=new JsonObject();
                    //jsonObject.put("app_name",app_name);
                    jsonObject.put("project",nameJsonObject.getString(app_name));
                    jsonObject.put("date",date);
                    jsonObject.put("earned",earned);
                    jsonObjects.add(jsonObject);
                }
            }
        }
        return jsonObjects;
    }


}
