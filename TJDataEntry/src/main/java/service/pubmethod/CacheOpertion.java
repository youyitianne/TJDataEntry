package service.pubmethod;

import service.Constant.CacheList;

public class CacheOpertion {
    /**
     * 将数据库缓存文件清空
     * APP_NAME         应用名
     * APP_MARK         渠道标记
     * CHANNEL      渠道
     * AD_TYPE   广告类型
     */
    public static void removeCacheLists(){
        CacheList.APP_NAME=null;
        CacheList.APP_MARK=null;
        CacheList.CHANNEL=null;
        CacheList.AD_TYPE=null;
        CacheList.AD_TYPE_JSON=null;
        CacheList.CHANNEL_JSON=null;
        CacheList.APP_NAME_JSON=null;
    }


}
