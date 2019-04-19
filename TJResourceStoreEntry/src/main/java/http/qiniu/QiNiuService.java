package http.qiniu;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import io.netty.util.internal.StringUtil;
import java.io.File;

public class QiNiuService {
    UploadManager uploadManager =null;
    {
        uploadManager =new UploadManager(new Configuration());
    }
    /**
     * 获取七牛令牌
     * @return
     */
    public String getToken() {
        String accessKey = "HrDIPPIoEngeu4kvvXLzL39g0g7v664B6pIAdILP";
        String secretKey = "7tJcPP-_hsoS6Kg4f3NBnJIy0Svk06YBEYcl1-3p";
        String bucket = "promo";
        long expireSeconds = 3600;   //过期时间
        StringMap putPolicy = new StringMap();
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket,null, expireSeconds,putPolicy);
        return upToken;
    };

    /**
     * 上传文件方法
     *
     * @param FilePath 文件路径
     * @param key 上传文件名（上传后文件名会被替换成key）
     * @return
     */
    public String upload(String  FilePath, String key){
        try {
            File file=new File(FilePath);
            if (!file.exists()){
                return null;
            }
            String token=getToken();
            if (StringUtil.isNullOrEmpty(token)){
                return null;
            }
            //调用put方法上传
            Response res = uploadManager.put(FilePath, key, token);
            //打印返回的信息
            if (res.isOK()) {
                Ret ret = res.jsonToObject(Ret.class);
                System.err.println("----" + ret.key);
                return ret.key;
            }
        } catch (QiniuException e) {
            Response r = e.response;
            // 请求失败时打印的异常的信息
            System.out.println(r.toString());
            try {
                //响应的文本信息
                System.out.println(r.bodyString());
            } catch (QiniuException e1) {
                //ignore
            }
        }
        return null;
    }

    class Ret {
        public long fsize;
        public String key;
        public String hash;
        public int width;
        public int height;
    }

}


