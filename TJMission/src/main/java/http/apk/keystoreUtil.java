package http.apk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class keystoreUtil {
    Logger logger= LoggerFactory.getLogger(keystoreUtil.class);
    //执行cmd命令，获取返回结果
    public ApkInfo execCMD(String[] command,ApkInfo apkInfo) {
        StringBuilder sb = new StringBuilder();
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(command);
            BufferedReader getInput = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));
            BufferedReader getError = new BufferedReader(new InputStreamReader(process.getErrorStream(), "UTF-8"));
            String line;
            while ((line = getInput.readLine()) != null) {
                System.out.println(line.trim());
                if (line.trim().startsWith("MD5:")){
                    apkInfo.setMD5(line.substring(line.indexOf(":")+1).trim());
                }else if (line.trim().startsWith("SHA1:")){
                    apkInfo.setSHA1(line.substring(line.indexOf(":")+1).trim());
                }else if (line.trim().startsWith("SHA256")){
                    apkInfo.setSHA256(line.substring(line.indexOf(":")+1).trim());
                }
            }
            while ((line = getError.readLine()) != null) {
                sb.append(line + "\n");
            }
            if (sb.length()==0){
                logger.error("解析keystore出错----》",sb.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return apkInfo;
        } finally {
            process.destroy();
        }
        return apkInfo;
    }
}
//keytool -printcert -file G:\newfile\1\e32c4cdf-73f1-4184-893d-779cc82b83bd.RSA