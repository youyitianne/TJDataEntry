package service.dataprocessing;

import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UmengHandler {
    Logger logger = LoggerFactory.getLogger(UmengHandler.class);

    /**
     * get请求  方法1
     *
     * @return
     */
    public String doGet(String url) {
        HttpClient client = new DefaultHttpClient();
        //发送get请求
        HttpGet request = new HttpGet(url);
        RequestConfig config = RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(2000).build();
        request.setConfig(config);
        HttpResponse response=null;
        try {
            response = client.execute(request);
            /**请求发送成功，并得到响应**/
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                /**读取服务器返回过来的json字符串数据**/
                String strResult = EntityUtils.toString(response.getEntity());
                return strResult;
            }
        } catch (IOException e) {
            logger.error("umeng request error   ==>", e);
            return null;
        }finally {
            ((DefaultHttpClient) client).close();
        }
        return null;
    }

    /**
     * get请求  方法2
     *
     * @return
     */
    public String doGetClient(String url) throws Exception{
        // 创建Httpclient对象,相当于打开了浏览器
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建HttpGet请求，相当于在浏览器输入地址
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        String content=null;
        try {
        // 执行请求，相当于敲完地址后按下回车。获取响应
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                // 解析响应，获取数据
                content = EntityUtils.toString(response.getEntity());
                return content;
            }
        }finally {
            if (response != null) {
                // 关闭资源
                response.close();
            }
            // 关闭浏览器
            httpclient.close();
        }
        return null;
    }

    /**
     * 获取umeng多日的渠道数据
     *
     * @param dates
     * @param name
     * @param appkey
     * @return
     */
    public JsonObject getUmeng(List<String> dates, String name, String appkey) {
        List<JsonObject> list = new ArrayList<>();
        String date = "2019-02-01";
        List<JsonObject> channelIdList = new ArrayList<>();
        for (int j = 0; j < dates.size(); j++) {
            date = dates.get(j);
            String response = null;
            try {
                response = this.doGetClient("http://api.umeng.com/channels?appkey=" + appkey + "&auth_token=mVAYlcwdFu2hpY8mb7B3&date=" + date);
                if (response == null) {
                    return new JsonObject().put("state", "bad").put("data", "未请求到数据---->" + name + "-" + date);
                }
            }catch (Exception e){
                logger.error("api出错++++++》"+e);
                return new JsonObject().put("state", "bad").put("data", "未请求到数据---->" + name + "-" + date);
            }
            JSONArray jsonArray = JSONArray.fromObject(response);
            if (j == 0) {
                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject jsonObject = JSONObject.fromObject(jsonArray.get(i) + "");
                    channelIdList.add(new JsonObject().put("id", jsonObject.getString("id")).put("channelName", jsonObject.getString("channel")));
                }
            }
            JsonObject jsonObject1 = new JsonObject().put("app_name", name).put("list", jsonArray);
            list.add(jsonObject1);
        }
        String startdate = dates.get(0);
        String enddate = dates.get(dates.size() - 1);
        List<JsonObject> channelDataList = new ArrayList<>();
        for (int i = 0; i < channelIdList.size(); i++) {
            String id = channelIdList.get(i).getString("id");
            String channelName=null;
            String channelData=null;
            try {
                channelName = channelIdList.get(i).getString("channelName");
                channelData = this.doGetClient("http://api.umeng.com/retentions?period_type=daily&appkey=" + appkey + "&start_date=" + startdate + "&end_date=" + enddate + "&auth_token=mVAYlcwdFu2hpY8mb7B3&" +
                        "channels=" + id);
                if (channelData == null) {
                    return new JsonObject().put("state", "bad").put("data", "未请求到数据---->" + name + "-" + date);
                }
            }catch (Exception e){
                logger.error("api出错++++++》"+e);
                return new JsonObject().put("state", "bad").put("data", "未请求到数据---->" + name + "-" + date);
            }
            JsonObject jsonObject = new JsonObject().put("channelName", channelName).put("list", new JsonObject().put("appName",name).put("channelList",JSONArray.fromObject(channelData)));
            channelDataList.add(jsonObject);
        }
        return new JsonObject().put("state", "success").put("data", list).put("channel", channelDataList);
    }

    /**
     *  获取友盟留存
     * @param startdate
     * @param enddate
     * @param appkey
     * @return
     */
    public JsonObject getUmengRetention(String startdate,String enddate, String appkey,String name,String channel) {
        JsonObject jsonObject=new JsonObject();
        String response = null;
        try {
            if ("全部".equals(channel)){
                response = this.doGetClient("http://api.umeng.com/retentions?appkey="+appkey+"&start_date="+startdate+"&end_date="+enddate+"&auth_token=mVAYlcwdFu2hpY8mb7B3");
            }else {
                response = this.doGetClient("http://api.umeng.com/retentions?appkey="+appkey+"&start_date="+startdate+"&end_date="+enddate+"&auth_token=mVAYlcwdFu2hpY8mb7B3&channels="+channel);
            }
            if (response == null) {
                return new JsonObject().put("state", "bad").put("data", "未请求到数据---->" + name + "-" + startdate+"-"+enddate);
            }
        }catch (Exception e){
            logger.error("api出错++++++》"+e);
            return new JsonObject().put("state", "bad").put("data", "未请求到数据---->" + name + "-" + startdate+"-"+enddate);
        }


        jsonObject.put("data",JSONArray.fromObject(response)).put("state", "success");
        return jsonObject;
    }

    /**
     * 获取umeng app列表
     *
     * @return
     */
    public JsonObject getUmengApp() {
        UmengHandler umengHandler = new UmengHandler();
        String string=null;
        try {
            string = umengHandler.doGetClient("http://api.umeng.com/apps?auth_token=mVAYlcwdFu2hpY8mb7B3&per_page=10000");
        }catch (Exception e){
            logger.error("api出错++++++》"+e);
            return new JsonObject().put("state", "bad").put("data", "未请求到数据---->app列表请求失败");
        }
        if (string == null) {
            return new JsonObject().put("state", "bad").put("data", "未请求到数据---->app列表请求失败");
        }
        JSONArray jsonArray = JSONArray.fromObject(string);
        return new JsonObject().put("state", "success").put("data", jsonArray);
    }

    /**
     * 获取umeng channels列表
     *
     * @return
     */
    public JsonObject getUmengChannel(String appkey) {
        UmengHandler umengHandler = new UmengHandler();
        String string=null;
        try {
            string = umengHandler.doGetClient("http://api.umeng.com/channels?appkey="+appkey+"&auth_token=mVAYlcwdFu2hpY8mb7B3");

        }catch (Exception e){
            return new JsonObject().put("state", "bad").put("data", "未请求到数据---->app列表请求失败");
        }
        if (string == null) {
            return new JsonObject().put("state", "bad").put("data", "未请求到数据---->app列表请求失败");
        }
        JSONArray jsonArray = JSONArray.fromObject(string);
        return new JsonObject().put("state", "success").put("data", jsonArray);
    }


}
