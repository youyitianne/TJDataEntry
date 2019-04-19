import http.HttpMethodClient;
import net.sf.json.JSONObject;
import org.junit.Test;

public class synchro {
    @Test
    public void test3() throws Exception {
        HttpMethodClient client=new HttpMethodClient();
        String result = client.doGetClient("http://console.tomatojoy.cn/redsystem/api/applist");
    }
}
