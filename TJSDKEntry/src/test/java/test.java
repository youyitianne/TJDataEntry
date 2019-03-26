import database.SqlConstants;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class test {
    @Test
    public void test1() throws Exception{
        String starttime="2018-12-01";
        String endtime="2018-12-12";
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Long startdate=sdf.parse(starttime).getTime();
        Long enddate=sdf.parse(endtime).getTime()+86399999;
        System.out.println(startdate);
        System.out.println(enddate);

        SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date test=new Date(enddate);
        System.out.println(sdf1.format(test));
    }

    @Test
    public void test2() throws Exception{
        JsonObject jsonObject=new JsonObject();
        jsonObject.put("test1","test1").put("test2","test2").put("test3","test13");
        JsonArray jsonArray=new JsonArray();
        jsonArray.add(jsonObject).add(jsonObject).add(jsonObject);
        System.out.println(jsonArray);
    }

    @Test
    public void test3() throws Exception{
        JsonObject jsonObject=new JsonObject();
        jsonObject.put("1","true");
        jsonObject.put("2",true);
        System.out.println(jsonObject);
    }

    @Test
    public void test4() throws Exception{
            String  string="地理位置\n" +
                    "var returnCitySN = cip: 183.61.51.58 cid: 440100 cname: 广东省广州市\n" +
                    "var returnCitySN = cip: 101.81.78.212 cid: 310000 cname: 上海市\n" +
                    "var returnCitySN = cip: 101.81.226.85 cid: 310000 cname: 上海市\n" +
                    "var returnCitySN = cip: 101.81.78.212 cid: 310000 cname: 上海市\n" +
                    "var returnCitySN = cip: 183.15.245.196 cid: 440300 cname: 广东省深圳市\n" +
                    "var returnCitySN = cip: 183.15.247.79 cid: 440300 cname: 广东省深圳市\n" +
                    "var returnCitySN = cip: 183.232.175.3 cid: 440000 cname: 广东省\n" +
                    "var returnCitySN = cip: 183.232.175.3 cid: 440000 cname: 广东省\n" +
                    "var returnCitySN = cip: 116.226.152.206 cid: 310000 cname: 上海市\n" +
                    "var returnCitySN = cip: 101.81.226.85 cid: 310000 cname: 上海市\n" +
                    "var returnCitySN = cip: 101.81.226.85 cid: 310000 cname: 上海市\n" +
                    "var returnCitySN = cip: 183.15.240.62 cid: 440300 cname: 广东省深圳市\n" +
                    "var returnCitySN = cip: 101.81.226.85 cid: 310000 cname: 上海市\n" +
                    "var returnCitySN = cip: 101.81.226.85 cid: 310000 cname: 上海市\n" +
                    "var returnCitySN = cip: 101.81.226.85 cid: 310000 cname: 上海市\n" +
                    "var returnCitySN = cip: 183.14.30.3 cid: 440300 cname: 广东省深圳市\n" +
                    "var returnCitySN = cip: 183.15.244.232 cid: 440300 cname: 广东省深圳市\n" +
                    "var returnCitySN = cip: 183.15.245.73 cid: 440300 cname: 广东省深圳市\n" +
                    "var returnCitySN = cip: 183.14.30.3 cid: 440300 cname: 广东省深圳市\n" +
                    "var returnCitySN = cip: 183.14.30.3 cid: 440300 cname: 广东省深圳市\n" +
                    "var returnCitySN = cip: 183.15.240.62 cid: 440300 cname: 广东省深圳市\n" +
                    "var returnCitySN = cip: 183.15.240.62 cid: 440300 cname: 广东省深圳市\n" +
                    "var returnCitySN = cip: 183.14.30.2 cid: 440300 cname: 广东省深圳市\n" +
                    "var returnCitySN = cip: 183.232.175.2 cid: 440000 cname: 广东省\n" +
                    "var returnCitySN = cip: 183.232.175.2 cid: 440000 cname: 广东省\n" +
                    "var returnCitySN = cip: 101.81.226.85 cid: 310000 cname: 上海市\n" +
                    "var returnCitySN = cip: 101.81.226.85 cid: 310000 cname: 上海市\n" +
                    "var returnCitySN = cip: 116.226.152.206 cid: 310000 cname: 上海市\n" +
                    "var returnCitySN = cip: 183.15.240.62 cid: 440300 cname: 广东省深圳市\n" +
                    "var returnCitySN = cip: 183.15.245.121 cid: 440300 cname: 广东省深圳市\n" +
                    "var returnCitySN = cip: 183.15.240.62 cid: 440300 cname: 广东省深圳市\n" +
                    "var returnCitySN = cip: 183.15.247.223 cid: 440300 cname: 广东省深圳市\n" +
                    "var returnCitySN = cip: 183.15.240.62 cid: 440300 cname: 广东省深圳市\n" +
                    "var returnCitySN = cip: 124.202.226.10 cid: 110000 cname: 北京市\n" +
                    "var returnCitySN = cip: 124.202.226.10 cid: 110000 cname: 北京市\n" +
                    "var returnCitySN = cip: 124.202.226.10 cid: 110000 cname: 北京市\n" +
                    "var returnCitySN = cip: 124.202.226.10 cid: 110000 cname: 北京市\n" +
                    "var returnCitySN = cip: 124.202.226.10 cid: 110000 cname: 北京市\n" +
                    "var returnCitySN = cip: 124.202.226.10 cid: 110000 cname: 北京市\n" +
                    "var returnCitySN = cip: 124.202.226.10 cid: 110000 cname: 北京市\n" +
                    "var returnCitySN = cip: 124.202.226.10 cid: 110000 cname: 北京市\n" +
                    "var returnCitySN = cip: 124.202.226.10 cid: 110000 cname: 北京市\n" +
                    "var returnCitySN = cip: 101.81.226.85 cid: 310000 cname: 上海市\n" +
                    "var returnCitySN = cip: 116.226.152.206 cid: 310000 cname: 上海市\n" +
                    "var returnCitySN = cip: 116.226.152.206 cid: 310000 cname: 上海市\n" +
                    "var returnCitySN = cip: 183.15.247.79 cid: 440300 cname: 广东省深圳市\n" +
                    "var returnCitySN = cip: 183.15.245.121 cid: 440300 cname: 广东省深圳市\n" +
                    "var returnCitySN = cip: 183.14.30.2 cid: 440300 cname: 广东省深圳市\n" +
                    "var returnCitySN = cip: 183.15.245.121 cid: 440300 cname: 广东省深圳市\n";
        for (int i=0;i<250;i++){
            if (string.indexOf("cip")!=-1){
                String newStrin=string;
                System.out.println(newStrin.substring(newStrin.indexOf("cip")+4,newStrin.indexOf("cid")));
                string=string.substring(string.indexOf("cid")+4);
            }
        }

    }

}
