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

    }

}
