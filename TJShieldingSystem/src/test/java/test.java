import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import http.QiNiu;
import http.shieldingHttpVerticle;
import io.vertx.core.json.JsonObject;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Test;

import javax.rmi.CORBA.Util;
import javax.xml.bind.SchemaOutputResolver;
import java.util.UUID;

public class test {
    @Test
    public void test3() throws Exception {
        String string = "{\"code\":200,\"total\":177}";
        //JSONArray jsonArray=JSONArray.fromObject(string);
        JSONObject jsonObject=JSONObject.fromObject(string);
        System.out.println(jsonObject.toString());
    }

    @Test
    public void test2() throws Exception {
        Long TIME1= System.currentTimeMillis();
        String string1 = "山东人水电费水电费水电费山东人水电费水电费水电费山东人水电费水电费水电费山东人水电费水电费水电费山东人水电费水电费水电费山东人水电费水电费水电费山东人水电费水电费水电费山东人水电费水电费水电费山东人水电费水电费水电费山东人水电费水电费水电费山东人水电费水电费水电费山东人水电费水电费水电费";
        System.out.println(string1.hashCode());
        long time2=TIME1-System.currentTimeMillis();
        System.out.println(time2);

    }

    @Test
    public void test1() throws Exception {
        System.out.println(getToken());

    }

    /**
     * 获取七牛令牌
     * @return
     */
    public QiNiu getToken() {
        QiNiu qiNiu = new QiNiu();
        String accessKey = "HrDIPPIoEngeu4kvvXLzL39g0g7v664B6pIAdILP";
        String secretKey = "7tJcPP-_hsoS6Kg4f3NBnJIy0Svk06YBEYcl1-3p";
        String bucket = "promo";
        long expireSeconds = 3600;   //过期时间
        StringMap putPolicy = new StringMap();
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket,null, expireSeconds,putPolicy);
        qiNiu.setKey(UUID.randomUUID().toString().replaceAll("\\-", ""));
        qiNiu.setToken(upToken);
        return qiNiu;
    };

    @Test
    public void test4() throws Exception {
        String path="http://image.tomatojoy.5557d2df7367c03d477c19731fc4f27a";
        Integer url=path.indexOf(".cn/");
        if (url!=-1){
            System.out.println( path.substring(url+4));
        }
    }

}
