import http.apk.ApkInfo;
import http.apk.IconUtil;
import http.apk.keystoreUtil;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.jdbc.JDBCClient;
import org.junit.Test;

import java.io.File;
import java.util.UUID;

public class test {
    @Test
    public void test1() throws Exception{
        new IconUtil().zipFileReadRSA("C:\\Users\\jin\\Desktop\\apk"+File.separator+"com.snakedash.casual.tomato-1.2.0-12.apk",
                "G:"+ File.separator+"newfile"+File.separator+"1"+File.separator, UUID.randomUUID().toString());
 //   "cd C:\\Program Files"+File.separator+"Java"+File.separator+"jdk1.8.0_181"+File.separator+"bin"
        //keytool –printcert –file G:\\newfile\\1"+File.separator+"e32c4cdf-73f1-4184-893d-779cc82b83bd.RSA
    };

    @Test
    public void test2() throws Exception {
        String [] cmds=new String[]{"cmd","/c","keytool -printcert -file e32c4cdf-73f1-4184-893d-779cc82b83bd.RSA"};
        ApkInfo apkInfo = new keystoreUtil().execCMD(cmds,new ApkInfo());
        System.out.println(apkInfo);

    }

    ;
}
