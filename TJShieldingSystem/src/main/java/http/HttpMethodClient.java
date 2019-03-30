package http;

import io.vertx.core.json.JsonObject;
import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class HttpMethodClient {
    Logger logger = LoggerFactory.getLogger(HttpMethodClient.class);

    /**
     * get请求
     *
     * @param url
     * @return
     * @throws Exception
     */
    public String doGetClient(String url) throws Exception {
        logger.info(url);
        // 创建Httpclient对象,相当于打开了浏览器
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建HttpGet请求，相当于在浏览器输入地址
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        String content = null;
        try {
            // 执行请求，相当于敲完地址后按下回车。获取响应
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                // 解析响应，获取数据
                content = EntityUtils.toString(response.getEntity());
//                logger.info(content);
//                JSONObject jsonObject=JSONObject.fromObject(content);
                return content;
            }
        } finally {
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
     * post 请求
     *
     * @param url
     * @param jsonObjects list内存放参数  key va
     * @return
     */
    public String doPostClient(String url, List<JsonObject> jsonObjects) {
        logger.info(url);
        //httpClient
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // get method
        HttpPost httpPost = new HttpPost(url);
        //set params
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        for (int i = 0; i < jsonObjects.size(); i++) {
            String key = jsonObjects.get(i).getString("key");
            String value = jsonObjects.get(i).getString("value");
            params.add(new BasicNameValuePair(key, value));
        }
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params));
        } catch (Exception e) {
        }

        //response
        HttpResponse response = null;
        String temp = null;
        try {
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                temp = EntityUtils.toString(entity, "UTF-8");
                return temp;
            }
        } catch (Exception e) {
            logger.error("post请求失败" + e);
            return null;
        }
        return null;
    }

    /**
     * put 请求
     *
     * @param url
     * @param jsonObjects list内存放参数  key value
     * @return
     */
    public String doPutClient(String url, List<JsonObject> jsonObjects) {
        logger.info(url);
        //httpClient
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // get method
        HttpPut httpput = new HttpPut(url);
        //set params
        if (jsonObjects.size() != 0) {
            List<NameValuePair> params = new ArrayList<NameValuePair>();
            for (int i = 0; i < jsonObjects.size(); i++) {
                String key = jsonObjects.get(i).getString("key");
                String value = jsonObjects.get(i).getString("value");
                params.add(new BasicNameValuePair(key, value));
            }
            try {
                httpput.setEntity(new UrlEncodedFormEntity(params));
            } catch (Exception e) {
                logger.error("put请求设置参数" + e);
                e.printStackTrace();
            }
        }
        //response
        HttpResponse response = null;
        String temp = null;
        try {
            response = httpClient.execute(httpput);
            HttpEntity entity = response.getEntity();
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                temp = EntityUtils.toString(entity, "UTF-8");
                logger.info(temp);
                System.out.println(temp);
                return temp;
            }
        } catch (Exception e) {
            logger.error("put请求失败" + e);
            return null;
        }
        return null;

    }


}
