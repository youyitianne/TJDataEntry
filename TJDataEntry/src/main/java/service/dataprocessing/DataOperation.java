package service.dataprocessing;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.entity.*;
import service.pubmethod.Judgement;
import service.pubmethod.ToAdData;
import service.pubmethod.Transform;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataOperation {
    private Logger logger = LoggerFactory.getLogger(DataOperation.class.getName());
    private ExcelRead excelRead = new ExcelRead();
    private Vertx vertx;

    public DataOperation(Vertx vertx) {
        this.vertx = vertx;
    }

    /**
     * 用户数据表信息并转换成UserData
     *
     * @param path    路径
     * @param name    游戏名
     * @param channel 渠道
     * @return <List<UserData>
     */
    public Future<List<UserData>> userDataOpertion(String path, String name, String channel) {
        if ("m4399".equals("4399")){
            channel="";
        }
        if ("xiaomi".equals("小米")){
            channel="";
        }
        if ("meizu".equals("魅族")){
            channel="";
        }
        String finalchannel=channel;
        Future<List<UserData>> future = io.vertx.core.Future.future();
        vertx.executeBlocking(future1 -> {
            List user = excelRead.readUser(path);
            future1.complete(user);
        }, asyncResult -> {
            if (asyncResult.succeeded()) {
                List user = (List) asyncResult.result();
                if (user == null || user.isEmpty()) {
                    future.fail("读取失败");
                    logger.error("读取失败");
                } else {
                    List<UserData> userDataList = new ArrayList<>();
                    for (int i = 0; i < user.size(); i++) {
                        List arrayList = (ArrayList) user.get(i);
                        UserData userData = new UserData();
                        //[[2018-10-20, 4129, 7625, 24857, 00:05:29, 24.92%], [2018-10-20, 4129, 7625, 24857, 00:05:29, 24.92%], [2018-10-20, 4129, 7625, 24857, 00:05:29, 24.92%], [2018-10-20, 4129, 7625, 24857, 00:05:29, 24.92%]]
                        userData.setDate(Transform.transForMilliSecondByTim(String.valueOf(arrayList.get(0)), "yyyy-MM-dd"));
                        userData.setAppName(name);
                        userData.setChannel(finalchannel);
                        try {
                            userData.setDailyNewUser(Integer.valueOf(String.valueOf(arrayList.get(1)).trim()));
                        }catch (Exception e){
                            userData.setDailyNewUser(0);
                        }
                        try {
                            userData.setDailyActivityUser(Integer.valueOf(String.valueOf(arrayList.get(2)).trim()));
                        }catch (Exception e){
                            userData.setDailyActivityUser(0);
                        }
                        try {
                            userData.setStartupTime(Integer.valueOf(String.valueOf(arrayList.get(3)).trim()));
                        }catch (Exception e){
                            userData.setStartupTime(0);
                        }
                        userData.setSingleUseTime(String.valueOf(arrayList.get(4)));
                        String retention = String.valueOf(arrayList.get(5)).substring(0, String.valueOf(arrayList.get(5)).indexOf("%"));
                        Double doubleRentention;
                        if (Judgement.IsNumber(retention)) {
                            doubleRentention = Double.valueOf(retention) / 100;
                        } else {
                            doubleRentention = 0.0;
                        }
                        userData.setRetention(doubleRentention);
                        userDataList.add(i, userData);
                    }
                    future.complete(userDataList);
                }
            }
        });
        return future;
    }

    /**
     * 移信数据API获取转AdData
     * 根据开始时间和结束时间获取移信数据
     * 存入数据库
     * @param starttime 开始时间
     * @param endtime   截止日期
     * @return Future<Boolean>
     */
    public Future<List<AdData>> FindYixin(String starttime, String endtime,List<List<String>> matchinglist) {
        Future<List<AdData>> future = Future.future();
        vertx.executeBlocking(future1 -> {
            String sign = Transform.StringToMd5("app_id=&end=" + endtime + "&skey=0f00c0c7a96f23d2e28cff8e510134ac&start=" + starttime + "&time=1539260528879&unit_id=");
            String path = "https://adnet.app.qnread.com/api/report?skey=0f00c0c7a96f23d2e28cff8e510134ac&sign=" + sign + "&time=1539260528879&app_id=&unit_id=&start=" + starttime + "&end=" + endtime;
            try {
                String initialdata = Transform.getData(path);
                if (initialdata != null) {
                    JsonObject js = new JsonObject(initialdata);
                    if (js.getValue("msg").equals("success")) {
                        String data = js.getValue("data").toString();
                        List<AdData> yixinData = changeyixin(data,matchinglist);
                        future1.complete(yixinData);
                    } else {
                        logger.error("无法获取,请查看api");
                        future1.fail("无法获取,请查看api");
                    }
                }
            } catch (IOException e) {
                logger.error("无法获取,请查看api", e);
                future1.fail(e);
            }
        }, asyncResult -> {
            if (asyncResult.succeeded()) {
                future.complete((List<AdData>) asyncResult.result());
            } else {
                future.fail(asyncResult.cause());
            }
        });
        return future;
    }

    /**
     * 移信json数据转换成AdData
     *
     * @param str
     * @return
     */
    public List<AdData> changeyixin(String str,List<List<String>> matchinglist) {

        JsonArray data = new JsonArray(str);
        List<AdData> adDataList = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            AdData adData = new AdData();
            JsonObject jsons = data.getJsonObject(i);
            String date = jsons.getString("date");
            date = date.substring(0, 4) + "-" + date.substring(4, 6) + "-" + date.substring(6);
            adData.setDate(Transform.transForMilliSecondByTim(date, "yyyy-MM-dd"));
            String medium_name = jsons.getString("medium_name");
            adData.setNote(medium_name);
            adData.setSdk_name("移信");
            adData.setAppName(Judgement.matchName(medium_name,matchinglist.get(0)));
            adData.setChannel(Judgement.matchChannel(medium_name,matchinglist.get(1),matchinglist.get(3)));
            String adtype = jsons.getString("unit_type");
            switch (adtype) {
                case "Banner":
                    adData.setAdType("横幅");
                    break;
                case "Native":
                    adData.setAdType("视频");
                    break;
                case "Splash":
                    adData.setAdType("开屏");
                    break;
                case "Inline":
                    adData.setAdType("插屏");
                    break;
                case "RewardVideo":
                    adData.setAdType("视频");
                    break;
                default:
                    adData.setAdType("未知");
                    break;
            }
            String fill_rate = jsons.getString("fill_rate");
            fill_rate = fill_rate.substring(0, fill_rate.indexOf("%"));
            double fillrate;
            if (Judgement.IsNumber(fill_rate)) {
                fillrate = Judgement.formatDouble4(Double.valueOf(fill_rate) / 100);
            } else {
                fillrate = 0.0;
            }
            adData.setFillRate(fillrate);
            Integer click = jsons.getInteger("click");
            adData.setClick(click);
            Integer pv = jsons.getInteger("pv");
            adData.setImpression(pv);
            Integer request_count = jsons.getInteger("request_count");
            String money = jsons.getString("money");
            adData.setEarned(Double.valueOf(money));
            String eCpm = jsons.getString("eCpm");
            if (eCpm == null || eCpm.length() == 0) {
                adData.setEcpm(0.0);
            } else {
                adData.setEcpm(Double.valueOf(eCpm));
            }
            String click_rate = jsons.getString("click_rate");
            if (click_rate == null || click_rate.length() == 0) {
                adData.setClickRate(0.0);
            } else {
                click_rate = click_rate.substring(0, click_rate.indexOf("%"));
                double clickrate;
                if (Judgement.IsNumber(click_rate)) {
                    clickrate = Judgement.formatDouble4(Double.valueOf(click_rate) / 100);

                } else {
                    clickrate = 0.0;
                }
                adData.setClickRate(clickrate);
            }
            adData.setPlatform("移信");
            adDataList.add(adData);
        }
        return adDataList;
    }

    /**
     * 广点通数据转换成AdData
     *
     * @param path 文件路径
     * @return Future<List<AdData>>
     */
    public Future<List<AdData>> guangdiantongOperation(String path,List<List<String>> matchinglist) {
        Future<List<AdData>> future = Future.future();
        vertx.executeBlocking(future1 -> {
            List<AdData> adDataList = new ArrayList<>();
            List list = excelRead.readGuang(path,"utf-8");
            if (list == null || list.isEmpty()) {
                future.fail("读取失败");
                logger.error("读取失败");
            } else {
                for (int i = 0; i < list.size(); i++) {
                    List array = (ArrayList) list.get(i);
                    AdData adData = ToAdData.guangdiantongToAdData(array,matchinglist);
                    adDataList.add(adData);
                }
            }
            future1.complete(adDataList);
        }, asyncResult -> {
            if (asyncResult.succeeded()) {
                List<AdData> list = (List<AdData>) asyncResult.result();
                future.complete(list);
            } else {
                logger.error("读取失败",asyncResult.cause());
                future.fail(asyncResult.cause());
            }
        });
        return future;
    }

    /**
     * oppo数据转换成addata
     *
     * @param path
     * @return
     */
    public Future<List<AdData>> oppoOperation(String path, String date,List<List<String>> matchinglist) {
        Future<List<AdData>> future = Future.future();
        vertx.executeBlocking(future1 -> {
            List<AdData> adDataList = new ArrayList<>();
            List list = excelRead.readExcel(path);
            if (list == null || list.isEmpty()) {
                future.fail("读取失败");
                logger.error("读取失败");
            } else {
                for (int i = 0; i < list.size(); i++) {
                    List array = (ArrayList) list.get(i);
                    AdData adData = ToAdData.oppoToAdData(array, date,matchinglist);
                    adDataList.add(i, adData);
                }
            }
            future1.complete(adDataList);
        }, asyncResult -> {
            if (asyncResult.succeeded()) {
                List<AdData> list = (List<AdData>) asyncResult.result();
                future.complete(list);
            } else {
                logger.error("读取失败",asyncResult.cause());
                future.fail(asyncResult.cause());
            }
        });
        return future;
    }

    /**
     * 小米数据转addata
     *
     * @return
     */
    public Future<List<AdData>> xiaomiOperation(String path,List<List<String>> matchinglist) {
        Future<List<AdData>> future = Future.future();
        vertx.executeBlocking(future1 -> {
            List<AdData> adDataList = new ArrayList<>();
            List list = excelRead.readUser(path);
            if (list == null || list.isEmpty()) {
                future.fail("读取失败");
                logger.error("读取失败");
            } else {
                for (int i = 0; i < list.size(); i++) {
                    List array = (ArrayList) list.get(i);
                    adDataList.add(i, ToAdData.xiaomiToAdData(array,matchinglist));
                }
            }
            future1.complete(adDataList);
        }, asyncResult -> {
            if (asyncResult.succeeded()) {
                List<AdData> list = (List<AdData>) asyncResult.result();
                future.complete(list);
            } else {
                logger.error("读取失败",asyncResult.cause());
                future.fail(asyncResult.cause());
            }
        });
        return future;
    }


    /**
     * vivo数据转addata
     *
     * @return
     */
    public Future<List<AdData>> vivoOperation(String path, String date,List<List<String>> matchinglist) {
        Future<List<AdData>> future = Future.future();
        vertx.executeBlocking(future1 -> {
            List<AdData> adDataList = new ArrayList<>();
            List list = excelRead.readExcel(path);
            if (list == null || list.isEmpty()) {
                future.fail("读取失败");
                logger.error("读取失败");
            } else {
                for (int i = 0; i < list.size(); i++) {
                    List array = (ArrayList) list.get(i);
                    AdData adData = ToAdData.vivoToAdData(array, date,matchinglist);
                    adDataList.add(i, adData);
                }
            }
            future1.complete(adDataList);
        }, asyncResult -> {
            if (asyncResult.succeeded()) {
                List<AdData> list = (List<AdData>) asyncResult.result();
                future.complete(list);
            } else {
                logger.error("读取失败",asyncResult.cause());
                future.fail(asyncResult.cause());
            }
        });
        return future;
    }

    /**
     * 魅族数据转addata
     *
     * @return
     */
    public Future<List<AdData>> meizuOperation(String path,List<List<String>> matchinglist,Integer year) {
        Future<List<AdData>> future = Future.future();
        vertx.executeBlocking(future1 -> {
            List<AdData> adDataList = new ArrayList<>();
            List list = excelRead.readExcel(path);
            if (list == null || list.isEmpty()) {
                future.fail("读取失败");
                logger.error("读取失败");
            } else {
                for (int i = 0; i < list.size(); i++) {
                    List array = (ArrayList) list.get(i);
                    if (i == list.size() - 1) {
                        continue;
                    }
                    AdData adData = ToAdData.meizuToAdData(array,matchinglist,year);
                    adDataList.add(i, adData);
                }
            }
            future1.complete(adDataList);
        }, asyncResult -> {
            if (asyncResult.succeeded()) {
                List<AdData> list = (List<AdData>) asyncResult.result();
                future.complete(list);
            } else {
                logger.error("读取失败",asyncResult.cause());
                future.fail(asyncResult.cause());
            }
        });
        return future;
    }

    /**
     * 360数据转addata
     * @param path
     * @param adType
     * @return
     */
    public Future<List<AdData>> qihooOperation(String path,List<List<String>> matchinglist){
        Future<List<AdData>> future=Future.future();
        vertx.executeBlocking(resout->{
            List<AdData> adDataList = new ArrayList<>();
            List list = excelRead.readGuang(path,"gbk");
            if (list == null || list.isEmpty()) {
                future.fail("读取失败");
                logger.error("读取失败");
            } else {
                for (int i = 0; i < list.size(); i++) {
                    List array = (ArrayList) list.get(i);
                    AdData adData=ToAdData.qihooToAdData(array,matchinglist);
                    adDataList.add(i, adData);
                }
            }
            future.complete(adDataList);
        },asyncResult -> {
            if (asyncResult.succeeded()) {
                List<AdData> list = (List<AdData>) asyncResult.result();
                future.complete(list);
            } else {
                logger.error("读取失败",asyncResult.cause());
                future.fail(asyncResult.cause());
            }
        });
        return future;
    }

    /**
     * 三星转addata
     * @param path
     * @param adType
     * @param name
     * @return
     */
    public Future<List<AdData>> samsungOperation(String path, String adType,String name){
        Future<List<AdData>> future=Future.future();
        vertx.executeBlocking(resout->{
            List<AdData> adDataList = new ArrayList<>();
            List list = excelRead.readGuang(path,"utf-8");
            if (list == null || list.isEmpty()) {
                future.fail("读取失败");
                logger.error("读取失败");
            } else {
                for (int i = 0; i < list.size(); i++) {
                    if (i==list.size()-1){
                        continue;
                    }
                    List array = (ArrayList) list.get(i);
                    AdData adData=ToAdData.samsungToAdData(array,name,adType);
                    adDataList.add(i, adData);
                }
            }
            future.complete(adDataList);
        },asyncResult -> {
            if (asyncResult.succeeded()) {
                List<AdData> list = (List<AdData>) asyncResult.result();
                future.complete(list);
            } else {
                logger.error("读取失败",asyncResult.cause());
                future.fail(asyncResult.cause());
            }
        });
        return future;
    }

    /**
     * 联想转addata
     * @param path
     * @param adType
     * @param name
     * @return
     */
    public Future<List<AdData>> lenovoOperation(String path, String adType,String name){
        Future<List<AdData>> future=Future.future();
        vertx.executeBlocking(resout->{
            List<AdData> adDataList = new ArrayList<>();
            List list = excelRead.readExcel(path);
            if (list == null || list.isEmpty()) {
                future.fail("读取失败");
                logger.error("读取失败");
            } else {
                for (int i = 1; i < list.size(); i++) {
                    List array = (ArrayList) list.get(i);
                    AdData adData=ToAdData.lenovoToAdData(array,name,adType);
                    adDataList.add(adData);
                }
            }
            future.complete(adDataList);
        },asyncResult -> {
            if (asyncResult.succeeded()) {
                List<AdData> list = (List<AdData>) asyncResult.result();
                future.complete(list);
            } else {
                logger.error("读取失败",asyncResult.cause());
                future.fail(asyncResult.cause());
            }
        });
        return future;
    }

    /**
     * 九游转addata
     * @param path
     * @param matchinglist
     * @return
     */
    public Future<List<AdData>> jiuyouOperation(String path,List<List<String>> matchinglist){
        Future<List<AdData>> future = Future.future();
        vertx.executeBlocking(future1 -> {
            List<AdData> adDataList = new ArrayList<>();
            List list = excelRead.readGuang(path,"gbk");
            if (list == null || list.isEmpty()) {
                future.fail("读取失败");
                logger.error("读取失败");
            } else {
                for (int i = 0; i < list.size(); i++) {
                    List array = (ArrayList) list.get(i);
                    AdData adData=ToAdData.jiuyouToAdData(array,matchinglist);
                    adDataList.add(adData);
                }
            }
            future1.complete(adDataList);
        }, asyncResult -> {
            if (asyncResult.succeeded()) {
                List<AdData> list = (List<AdData>) asyncResult.result();
                future.complete(list);
            } else {
                logger.error("读取失败",asyncResult.cause());
                future.fail(asyncResult.cause());
            }
        });
        return future;
    }


    public List<TotalVO> listToTotalVO(List<JsonObject> userList, List<JsonObject> adList, List<Long> longdates, String name) {
        List<TotalVO> list=new ArrayList<>();
        for (int j = 0; j < longdates.size(); j++) {
            TotalVO totalVO=new TotalVO();
            List<ChannelVO> channelVOS=new ArrayList<>();
            Long oneday = longdates.get(j);
            String channel = "oppo";
            channelVOS.add(ToChannelVO(name, oneday, channel, userList, adList,"oppo"));
            channel = "xiaomi";
            channelVOS.add(ToChannelVO(name, oneday, channel, userList, adList,"小米"));
            channel = "vivo";
            channelVOS.add(ToChannelVO(name, oneday, channel, userList, adList,"vivo"));
            channel = "meizu";
            channelVOS.add(ToChannelVO(name, oneday, channel, userList, adList,"魅族"));
            channel = "qihoo";
            channelVOS.add(ToChannelVO(name, oneday, channel, userList, adList,"qihoo"));
            channel = "samsung";
            channelVOS.add(ToChannelVO(name, oneday, channel, userList, adList,"三星"));
            channel = "lenovo";
            channelVOS.add(ToChannelVO(name, oneday, channel, userList, adList,"联想"));
            channel = "jiuyou";
            channelVOS.add(ToChannelVO(name, oneday, channel, userList, adList,"九游"));
            channel = "4399";
            channelVOS.add(ToChannelVO(name, oneday, channel, userList, adList,"4399"));
            channel = "yyh";
            channelVOS.add(ToChannelVO(name, oneday, channel, userList, adList,"应用汇"));
            channel = "yyb";
            channelVOS.add(ToChannelVO(name, oneday, channel, userList, adList,"应用宝"));
            channel = "baidu";
            channelVOS.add(ToChannelVO(name, oneday, channel, userList, adList,"百度"));
            channel = "coolpad";
            channelVOS.add(ToChannelVO(name, oneday, channel, userList, adList,"酷派"));
            channel = "jinli";
            channelVOS.add(ToChannelVO(name, oneday, channel, userList, adList,"金立游戏"));
            channel = "huawei";
            channelVOS.add(ToChannelVO(name, oneday, channel, userList, adList,"华为"));
            channel = "zhongxing";
            channelVOS.add(ToChannelVO(name, oneday, channel, userList, adList,"中兴"));
            channel = "taptap";
            channelVOS.add(ToChannelVO(name, oneday, channel, userList, adList,"taptap"));
            channel = "haoyou";
            channelVOS.add(ToChannelVO(name, oneday, channel, userList, adList,"好游快爆"));

            totalVO.setDate(Transform.transForDate(oneday));
            Double income=0.0;
            Integer newUser=0;
            Integer activityUser=0;
            for (int i=0;i<channelVOS.size();i++){
                income=income+channelVOS.get(i).getChannel_income_all();
                newUser=newUser+channelVOS.get(i).getUserVO().getNewUser();
                activityUser=activityUser+channelVOS.get(i).getUserVO().getActivityUser();
            }
            totalVO.setIncome(Judgement.formatDouble4(income));
            totalVO.setNewUser(newUser);
            totalVO.setActivityUser(activityUser);
            totalVO.setRetention(0.0);
            totalVO.setDauarpu(activityUser==0?0:income/activityUser);
            totalVO.setChannelVOList(channelVOS);
            list.add(totalVO);
        }
        return list;
    }

    /**
     * 一天的User表的List<JsonObject>转UserVO
     *
     * @param userList user表数据
     * @param channel  渠道
     * @param oneday   时间戳
     * @return UserVO
     */
    public UserVO userJsonToUserVO(List<JsonObject> userList, String channel, Long oneday) {
        UserVO userVO = new UserVO();
        for (int i = 0; i < userList.size(); i++) {
            JsonObject jsonObject = userList.get(i);
            if (channel.equals(jsonObject.getString("channel")) && oneday.equals(jsonObject.getLong("date"))) {
                Integer dnu = jsonObject.getInteger("dnu");
                Integer dau = jsonObject.getInteger("dau");
                Integer startup_time = jsonObject.getInteger("startup_time");
                String single_use_time = jsonObject.getString("single_use_time");
                Double retention = jsonObject.getDouble("retention");
                userVO.setNewUser(dnu == null ? 0 : dnu);
                userVO.setActivityUser(dau == null ? 0 : dau);
                userVO.setStartupTime(startup_time == null ? 0 : startup_time);
                userVO.setSingleUseTime(single_use_time == null ? "--%" : single_use_time);
                userVO.setRetention(Judgement.formatDouble4(retention == null ? 0 : retention));
            }
        }
        return userVO;
    }

    /**
     * JsonObject转AdDataVO
     *
     * @param jsonObject 广告jsonObject
     * @return AdDataVO
     */
    public AdDataVO adJsonToAdDataVO(JsonObject jsonObject) {
        AdDataVO adDataVO = new AdDataVO();
        Integer impression = jsonObject.getInteger("impression");
        impression = impression == null ? 0 : impression;
        Double earned = jsonObject.getDouble("earned");
        earned = earned == null ? 0 : earned;
        Integer click = jsonObject.getInteger("click");
        click = click == null ? 0 : click;
        Double clickrate = 0.0;
        try {
            clickrate = Judgement.formatDouble4((double)click / impression);
        } catch (Exception e) {
            clickrate = 0.0;
        }
        Double ecpm = 0.0;
        try {
            ecpm = Judgement.formatDouble2(earned / impression * 1000);
        } catch (Exception e) {
            ecpm = 0.0;
        }
        adDataVO.setImpressions(impression);
        adDataVO.setIncome(earned);
        adDataVO.setClickRate(clickrate);
        adDataVO.setEcpm(ecpm);

        return adDataVO;
    }

    /**
     * AD表的List<JsonObject>   List<AdDataVO>
     * 一天的一个渠道的一个平台的多个广告形式
     *
     * @param adList   ad表的Jsonobject
     * @param oneday   时间戳
     * @param channel  渠道
     * @param platform 平台
     * @return
     */
    public List<AdDataVO> adDataVOToAdDataVOlist(List<JsonObject> adList, Long oneday, String channel, String platform) {

        List<AdDataVO> adDataVOList = new ArrayList<>();
        adDataVOList.add(0,new AdDataVO());
        adDataVOList.add(1,new AdDataVO());
        adDataVOList.add(2,new AdDataVO());
        adDataVOList.add(3,new AdDataVO());
        for (int i = 0; i < adList.size(); i++) {
            AdDataVO adDataVO = null;
            JsonObject jsonObject = adList.get(i);
            Boolean banner_bool = oneday.equals(jsonObject.getLong("date")) && jsonObject.getString("channel").contains(channel) && platform.equals(jsonObject.getString("platform")) && jsonObject.getString("advertising_type").contains("横幅");
            if (banner_bool) {
                if (adDataVOList.get(0).getIncome()==0.0){
                    adDataVO = adJsonToAdDataVO(jsonObject);
                    adDataVOList.set(0, adDataVO);
                }else {
                    adDataVOList.set(0,adJsonToAdDataVOs(jsonObject,adDataVOList.get(0)));
                }
            }
            Boolean inline_bool = oneday.equals(jsonObject.getLong("date")) && jsonObject.getString("channel").contains(channel) && platform.equals(jsonObject.getString("platform")) && jsonObject.getString("advertising_type").contains("插屏");
        //    System.out.println("inline_bool:"+inline_bool);
            if (inline_bool) {
                if (adDataVOList.get(1).getIncome()==0.0){
                    adDataVO = adJsonToAdDataVO(jsonObject);
                    adDataVOList.set(1, adDataVO);
                }else {
                    adDataVOList.set(1,adJsonToAdDataVOs(jsonObject,adDataVOList.get(1)));
                }
            }
            Boolean splish_bool = oneday.equals(jsonObject.getLong("date")) && jsonObject.getString("channel").contains(channel) && platform.equals(jsonObject.getString("platform")) && jsonObject.getString("advertising_type").contains("开屏");
        //    System.out.println("splish_bool:"+splish_bool);
            if (splish_bool) {
                if (adDataVOList.get(2).getIncome()==0.0){
                    adDataVO = adJsonToAdDataVO(jsonObject);
                    adDataVOList.set(2, adDataVO);
                }else {
                    adDataVOList.set(2,adJsonToAdDataVOs(jsonObject,adDataVOList.get(2)));
                }
            }
            Boolean video_bool = oneday.equals(jsonObject.getLong("date")) && jsonObject.getString("channel").contains(channel) && platform.equals(jsonObject.getString("platform")) && jsonObject.getString("advertising_type").contains("视频");
        //    System.out.println("video_bool:"+video_bool);
            if (video_bool) {
                if (adDataVOList.get(3).getIncome()==0.0){
                    adDataVO = adJsonToAdDataVO(jsonObject);
                    adDataVOList.set(3, adDataVO);
                }else {
                    adDataVOList.set(3,adJsonToAdDataVOs(jsonObject,adDataVOList.get(3)));
                }
            }
        }
        return adDataVOList;
    }

    /**
     * 获取一个游戏一天的一个渠道的数据
     * @param name     游戏名
     * @param oneday   时间戳
     * @param channel  渠道
     * @param userList user表的Jsonobject
     * @param adList   advertise表的Jsonobject
     * @return ChannelVO
     */
    public ChannelVO ToChannelVO(String name, Long oneday, String channel, List<JsonObject> userList, List<JsonObject> adList,String plastform) {
        ChannelVO channelVO = new ChannelVO();
        String onedaysdf = Transform.transForDate(oneday);
        UserVO userVO = userJsonToUserVO(userList, channel, oneday);
        List<AdDataVO> guang = adDataVOToAdDataVOlist(adList, oneday, channel, "广点通");
        List<AdDataVO> qudao = adDataVOToAdDataVOlist(adList, oneday, channel, plastform);
        List<AdDataVO> yixin = adDataVOToAdDataVOlist(adList, oneday, channel, "移信");

        channelVO.setDate(onedaysdf);
        channelVO.setName(name);
        channelVO.setUserVO(userVO);

        Integer activity = userVO.getActivityUser();
        //广点通
        channelVO.setGuang(guang);
        Double guangdiantong_income = 0.0;
        for (int i = 0; i < guang.size(); i++) {
            AdDataVO adDataVO = guang.get(i);
            if(adDataVO!=null){
                adDataVO.setPerImpression(activity == 0 ? 0 : Judgement.formatDouble2((double)adDataVO.getImpressions() / activity));
                guangdiantong_income = guangdiantong_income + adDataVO.getIncome();
            }
        }

        Double guangdiantong_dauarpu = activity == 0 ? 0 : guangdiantong_income / activity;

        channelVO.setGuangdiantong_income(guangdiantong_income);
        channelVO.setGuangdiantong_dauarpu(guangdiantong_dauarpu);

        //渠道
        channelVO.setQudao(qudao);
        Double qudao_income = 0.0;
        for (int i = 0; i < qudao.size(); i++) {
            AdDataVO adDataVO = qudao.get(i);
            if (adDataVO!=null){
                adDataVO.setPerImpression(activity == 0 ? 0 : Judgement.formatDouble2((double)adDataVO.getImpressions() / activity));
                qudao_income = qudao_income + adDataVO.getIncome();
            }
        }
        Double qudao_dauarpu = activity == 0 ? 0 : qudao_income / activity;

        channelVO.setQudao_income(qudao_income);
        channelVO.setQudao_dauarpu(qudao_dauarpu);
        //移信
        channelVO.setYixin(yixin);
        Double yixin_income = 0.0;
        for (int i = 0; i < yixin.size(); i++) {
            AdDataVO adDataVO = yixin.get(i);
//            if (channel.equals("yyh")) {
//                System.out.println("---------");
//                System.out.println(adDataVO);
//                System.out.println("---------");
//            }
            if (adDataVO!=null){
                adDataVO.setPerImpression(activity == 0 ? 0 : Judgement.formatDouble2((double)adDataVO.getImpressions() / activity));
                yixin_income = yixin_income + adDataVO.getIncome();
            }

        }
        Double yixin_dauarpu = activity == 0 ? 0 : yixin_income / activity;

        channelVO.setYixin_income(yixin_income);
        channelVO.setYixin_dauarpu(yixin_dauarpu);
//        if (channel.equals("yyh")){
//            System.out.println("guangdiantong_income"+guangdiantong_income);
//            System.out.println("qudao_income"+qudao_income);
//            System.out.println("yixin_income"+yixin_income);
//
//        }
        Double channel_income_all = guangdiantong_income + qudao_income + yixin_income;
        channelVO.setChannel_income_all(channel_income_all);
        channelVO.setChhannel_dauarpu_all(activity == 0 ? 0 : channel_income_all / activity);
        return channelVO;
    }

    /**
     * 把List<TotalVO> 转 List<List<String>>
     * @param value
     * @return
     */
    public List<List> listToTotalVOToList(List<TotalVO> value){
        List<List> aaaa=new ArrayList();
        for (int i=0;i<value.size();i++){
            TotalVO totalVO=value.get(i);
            List list=new ArrayList<>();
            list.add(totalVO.getDate());
            list.add(totalVO.getIncome());
            list.add(totalVO.getNewUser());
            list.add(totalVO.getActivityUser());
            list.add(totalVO.getRetention());
            list.add(Judgement.NonScientificNotation(String.valueOf(Judgement.formatDouble4(totalVO.getDauarpu()))));
            List<ChannelVO> list1=totalVO.getChannelVOList();
            //添加各渠道数据
            for (int j=0;j<list1.size();j++){
                ChannelVO channelVO=list1.get(j);
                list.add(Judgement.formatDouble2(channelVO.getChannel_income_all()));
                list.add(Judgement.NonScientificNotation(String.valueOf(Judgement.formatDouble4(channelVO.getChhannel_dauarpu_all()))));
               //广点通
                list.add(Judgement.formatDouble2(channelVO.getGuangdiantong_income()));
                UserVO userVO=channelVO.getUserVO();
                list.add(userVO.getNewUser());
                list.add(userVO.getActivityUser());
                list.add(userVO.getStartupTime());
                list.add(userVO.getSingleUseTime()==null?"0":userVO.getSingleUseTime());
                list.add(Judgement.toPercent(userVO.getRetention()));
                list.add(Judgement.NonScientificNotation(String.valueOf(Judgement.formatDouble4(channelVO.getGuangdiantong_dauarpu()))));
                List<AdDataVO> adDataVOS=channelVO.getGuang();
                list.addAll(adDataListToList(adDataVOS));
                //渠道
                if (j<=7){
                list.add(Judgement.formatDouble2(channelVO.getQudao_income()));
                list.add(userVO.getNewUser());
                list.add(userVO.getActivityUser());
                list.add(Judgement.toPercent(userVO.getRetention()));
                list.add(Judgement.NonScientificNotation(String.valueOf(Judgement.formatDouble4(channelVO.getQudao_dauarpu()))));
                List<AdDataVO> adDataVOS1 = channelVO.getQudao();
                list.addAll(adDataListToLists(adDataVOS1));
                }
                if (j<=9) {
                    //移信
                    list.add(Judgement.formatDouble2(channelVO.getYixin_income()));
                    list.add(userVO.getNewUser());
                    list.add(userVO.getActivityUser());
                    list.add(Judgement.toPercent(userVO.getRetention()));
                    list.add(Judgement.NonScientificNotation(String.valueOf(Judgement.formatDouble4(channelVO.getYixin_dauarpu()))));
                    List<AdDataVO> adDataVOS2 = channelVO.getYixin();
                    list.addAll(adDataListToLists(adDataVOS2));
                }
            }
            aaaa.add(list);
        }
        return aaaa;
    }

    /**
     * 横  插  开
     * @param value
     * @return
     */
    private List adDataListToList (List<AdDataVO> value){
        List list=new ArrayList();
        for (int z=0;z<value.size()-1;z++){
            list.add(Judgement.formatDouble2(value.get(z).getIncome()));
            list.add(Judgement.toPercent(value.get(z).getClickRate()));
            list.add(Judgement.formatDouble4(value.get(z).getEcpm()));
            list.add(value.get(z).getImpressions());
            list.add(value.get(z).getPerImpression());
        }
        return list;
    }

    /**
     * 横  插  开  视
     * @param value
     * @return
     */
    private List adDataListToLists (List<AdDataVO> value){
        List list=new ArrayList();
        for (int z=0;z<value.size();z++){
            list.add(Judgement.formatDouble2(value.get(z).getIncome()));
            list.add(Judgement.toPercent(value.get(z).getClickRate()));
            list.add(Judgement.formatDouble4(value.get(z).getEcpm()));
            list.add(value.get(z).getImpressions());
            list.add(value.get(z).getPerImpression());
        }
        return list;
    }

    /**
     * JsonObject转AdDataVO
     *
     * @param jsonObject 广告jsonObject
     * @return AdDataVO
     */
    public AdDataVO adJsonToAdDataVOs(JsonObject jsonObject,AdDataVO adDataVOs) {
        AdDataVO adDataVO = new AdDataVO();
        Integer impression = jsonObject.getInteger("impression");
        impression = impression == null ? 0 : impression;
        Double earned = jsonObject.getDouble("earned");
        earned = earned == null ? 0 : earned;
        Integer click = jsonObject.getInteger("click");
        click = click == null ? 0 : click;
        Double clickrate = 0.0;
        try {
            clickrate = Judgement.formatDouble4(click / impression);
        } catch (Exception e) {
            clickrate = 0.0;
        }
        Double ecpm = 0.0;
        try {
            ecpm = Judgement.formatDouble2(earned / impression * 1000);
        } catch (Exception e) {
            ecpm = 0.0;
        }
        adDataVO.setImpressions(impression+adDataVOs.getImpressions());
        adDataVO.setIncome(earned+adDataVOs.getIncome());
        Integer clicks=(int)(adDataVOs.getImpressions()*adDataVOs.getClickRate());
        System.out.println("--------------------------");
        System.out.println("click:"+click);
        System.out.println("clicks:"+clicks);
        System.out.println("impression:"+impression);
        System.out.println("adDataVOs.getImpressions():"+adDataVOs.getImpressions());
        System.out.println("--------------------------");
        adDataVO.setClickRate((impression+adDataVOs.getImpressions())==0?0:Double.valueOf(click+clicks)/(adDataVOs.getImpressions()+impression));
        adDataVO.setEcpm((impression+adDataVOs.getImpressions())==0?0:Double.valueOf(earned+adDataVOs.getIncome())/(impression+adDataVOs.getImpressions())*1000);
        return adDataVO;
    }

}
