import io.vertx.core.json.JsonObject;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Test;

public class test {
    @Test
    public void test3() throws Exception {
        String string = "{\"code\":200,\"total\":177}";
        //JSONArray jsonArray=JSONArray.fromObject(string);
        JSONObject jsonObject=JSONObject.fromObject(string);
        System.out.println(jsonObject.toString());
    }
}
