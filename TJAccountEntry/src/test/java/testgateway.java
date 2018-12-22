import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class testgateway {
    @Test
    public void test1() throws Exception{
        // 创建指定url的url对象
        URL url = new URL("http://localhost:8330/app");
        //System.out.println(url.toString());
        // 创建http链接对象
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        // 设置请求方式
        con.setRequestMethod("GET");
        con.setRequestProperty("Authorization","Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6ImFkbWluIiwicm9sZXMiOlsiYWRtaW4iXSwicGVybXMiOlsicmVzb3VyY2UtY2FuTGlzdCJdLCJyZXNvdXJjZXMiOlsi5L2g5aW9MSJdLCJpYXQiOjE1NDM5MjUwNzEsImV4cCI6MTU0Mzk4NTAxMX0.OPfWKkXBA5cWu4m37BJ_4ciqv9Ygf032b6xeS1XIvtY");
        con.setRequestProperty("username","admin");
        // 打开链接,上一步和该步骤作用相同，可以省略
        con.connect();
        // 获取请求返回内容并设置编码为UTF-8
        BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
        // 将返回数据拼接为字符串
        StringBuffer sb = new StringBuffer();
        // 临时字符串
        String temp = null;
        // 获取数据
        while ((temp = reader.readLine()) != null) {
            sb.append(temp);
        }
        // 关闭流
        reader.close();
        System.out.println(sb.toString());
    }
}
