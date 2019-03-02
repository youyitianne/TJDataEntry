package service.dataprocessing;

import http.HttpServerVerticle;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.entity.dailyAdtype.DailyAdtype;
import service.entity.dailyAdtype.DailyAdtypeChannel;
import service.pubmethod.Judgement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.jar.JarEntry;

public class DailyAdtypeHandler {
    private static Logger logger = LoggerFactory.getLogger(DailyAdtypeHandler.class.getName());

    /**
     * @param project  项目名和应用名 //{"project_name":"保护气球","applist":[{"app_name":"保护气球"}]}
     * @param userList 用户数据
     * @param adList   广告数据
     * @param dateList 时间戳
     * @return
     */
    public static List<List<DailyAdtype>> dailyAdtypeList(JsonObject project, List<JsonObject> userList, List<JsonObject> adList, List<Integer> dateList) {
        List<JsonObject> channelList = Arrays.asList(
                new JsonObject().put("name", "oppo").put("program_mark", "oppo"),
                new JsonObject().put("name", "华为").put("program_mark", "huawei"));
        List<List<DailyAdtype>> outerList = new ArrayList<>();
        for (int i = 0; i < dateList.size(); i++) {
            List<DailyAdtype> daList = new ArrayList<>();
            for (int j = 0; j < channelList.size(); j++) {
                DailyAdtypeChannel gdt_dac = new DailyAdtypeChannel();
                DailyAdtypeChannel channel_dac = new DailyAdtypeChannel();
                DailyAdtypeChannel tt_dac = new DailyAdtypeChannel();
                DailyAdtype da = new DailyAdtype();
                Iterator userList1 = userList.iterator();
                while (userList1.hasNext()) {
                    JsonObject userJson = JsonObject.mapFrom(userList1.next());
                    if (userJson.getInteger("date").equals(dateList.get(i)) && userJson.getString("channel").equals(channelList.get(j).getString("program_mark"))) {
                        //TODO
                        da.setDau(userJson.getInteger("dau"));
                        da.setDnu(userJson.getInteger("dnu"));
                        da.setRetention(userJson.getDouble("retention"));
                        da.setSingle_use_time(userJson.getString("single_use_time"));
                        da.setStartup_time(userJson.getInteger("startup_time"));
                        userList1.remove();
                        break;
                    }
                }
                Iterator adList1 = adList.iterator();
                while (adList1.hasNext()) {
                    JsonObject adJson = JsonObject.mapFrom(adList1.next());
                    if (adJson.getInteger("date").equals(dateList.get(i)) &&
                            adJson.getString("channel").equals(channelList.get(j).getString("program_mark"))) {
                        if (adJson.getString("platform").equals("广点通")) {
                            gdt_dac = matchDailyAdtypeChannel(adJson, gdt_dac);
                            adList1.remove();
                            continue;
                        }
                        if (adJson.getString("platform").equals(channelList.get(j).getString("name"))) {
                            channel_dac = matchDailyAdtypeChannel(adJson, channel_dac);
                            adList1.remove();
                            continue;
                        }
                        if (adJson.getString("platform").equals("头条")) {
                            tt_dac = matchDailyAdtypeChannel(adJson, tt_dac);
                            adList1.remove();
                            continue;
                        }
                    }
                }
                gdt_dac=setTotalDac(gdt_dac);
                da.setGdt_dac(gdt_dac);
                channel_dac=setTotalDac(channel_dac);
                da.setChannel_dac(channel_dac);
                tt_dac=setTotalDac(tt_dac);
                da.setTt_dac(tt_dac);
                da=setTotalDa(da);
                daList.add(da);
            }
            outerList.add(daList);
        }
        return outerList;
    }

    /**
     * 将每条广告数据按照具体的广告形式细分添加到DailyAdtypeChannel
     * @param adJson   广告数据json
     * @param dailyAdtypeChannel    DailyAdtypeChannel
     * @return DailyAdtypeChannel
     */
    private static DailyAdtypeChannel matchDailyAdtypeChannel(JsonObject adJson, DailyAdtypeChannel dailyAdtypeChannel) {
        String note = adJson.getString("note");
        if (note.contains("横幅-普通")) {
            dailyAdtypeChannel.setBanner_pt_click(adJson.getInteger("click")+dailyAdtypeChannel.getBanner_pt_click());
            dailyAdtypeChannel.setBanner_pt_earned(adJson.getDouble("earned")+dailyAdtypeChannel.getBanner_pt_earned());
            dailyAdtypeChannel.setBanner_pt_impression(adJson.getInteger("impression")+dailyAdtypeChannel.getBanner_pt_impression());
        }
        if (note.contains("插屏-普通")) {
            dailyAdtypeChannel.setInline_pt_click(adJson.getInteger("click")+dailyAdtypeChannel.getInline_pt_click());
            dailyAdtypeChannel.setInline_pt_earned(adJson.getDouble("earned")+dailyAdtypeChannel.getInline_pt_earned());
            dailyAdtypeChannel.setInline_pt_impression(adJson.getInteger("impression")+dailyAdtypeChannel.getInline_pt_impression());
        }
        if (note.contains("插屏-渲染")) {
            dailyAdtypeChannel.setInline_xr_click(adJson.getInteger("click")+dailyAdtypeChannel.getInline_xr_click());
            dailyAdtypeChannel.setInline_xr_earned(adJson.getDouble("earned")+dailyAdtypeChannel.getInline_xr_earned());
            dailyAdtypeChannel.setInline_xr_impression(adJson.getInteger("impression")+dailyAdtypeChannel.getInline_xr_impression());
        }
        if (note.contains("插屏-模板")) {
            dailyAdtypeChannel.setInline_mb_click(adJson.getInteger("click")+dailyAdtypeChannel.getInline_mb_click());
            dailyAdtypeChannel.setInline_mb_earned(adJson.getDouble("earned")+dailyAdtypeChannel.getInline_mb_earned());
            dailyAdtypeChannel.setInline_mb_impression(adJson.getInteger("impression")+dailyAdtypeChannel.getInline_mb_impression());
        }
        if (note.contains("开屏-普通")) {
            dailyAdtypeChannel.setSplash_pt_click(adJson.getInteger("click")+dailyAdtypeChannel.getSplash_pt_click());
            dailyAdtypeChannel.setSplash_pt_earned(adJson.getDouble("earned")+dailyAdtypeChannel.getSplash_pt_earned());
            dailyAdtypeChannel.setSplash_pt_impression(adJson.getInteger("impression")+dailyAdtypeChannel.getSplash_pt_impression());
        }
        if (note.contains("开屏-渲染")) {
            dailyAdtypeChannel.setSplash_xr_click(adJson.getInteger("click")+dailyAdtypeChannel.getSplash_xr_click());
            dailyAdtypeChannel.setSplash_xr_earned(adJson.getDouble("earned")+dailyAdtypeChannel.getSplash_xr_earned());
            dailyAdtypeChannel.setSplash_xr_impression(adJson.getInteger("impression")+dailyAdtypeChannel.getSplash_xr_impression());
        }
        if (note.contains("开屏-模板")) {
            dailyAdtypeChannel.setSplash_mb_click(adJson.getInteger("click")+dailyAdtypeChannel.getSplash_mb_click());
            dailyAdtypeChannel.setSplash_mb_earned(adJson.getDouble("earned")+dailyAdtypeChannel.getSplash_mb_earned());
            dailyAdtypeChannel.setSplash_mb_impression(adJson.getInteger("impression")+dailyAdtypeChannel.getSplash_mb_impression());
        }
        if (note.contains("视频-普通")) {
            dailyAdtypeChannel.setVideo_pt_click(adJson.getInteger("click")+dailyAdtypeChannel.getVideo_pt_click());
            dailyAdtypeChannel.setVideo_pt_earned(adJson.getDouble("earned")+dailyAdtypeChannel.getVideo_pt_earned());
            dailyAdtypeChannel.setVideo_pt_impression(adJson.getInteger("impression")+dailyAdtypeChannel.getVideo_pt_impression());
        }
        if (note.contains("视频-激励")) {
            dailyAdtypeChannel.setVideo_jl_click(adJson.getInteger("click")+dailyAdtypeChannel.getVideo_jl_click());
            dailyAdtypeChannel.setVideo_jl_earned(adJson.getDouble("earned")+dailyAdtypeChannel.getVideo_jl_earned());
            dailyAdtypeChannel.setVideo_jl_impression(adJson.getInteger("impression")+dailyAdtypeChannel.getVideo_jl_impression());
        }
        return dailyAdtypeChannel;
    }

    /**
     * 汇总对象中的各个广告形式的数据click,earned,impression
     * @param dac DailyAdtypeChannelObject
     * @return DailyAdtypeChannelObJect
     */
    public static DailyAdtypeChannel setTotalDac(DailyAdtypeChannel dac) {
        dac.setTotalClick(
                dac.getBanner_pt_click() +
                        dac.getInline_pt_click() +
                        dac.getInline_xr_click() +
                        dac.getInline_mb_click() +
                        dac.getSplash_pt_click() +
                        dac.getSplash_xr_click() +
                        dac.getSplash_mb_click() +
                        dac.getVideo_pt_click() +
                        dac.getVideo_jl_click()
        );
        dac.setTotalEarned(
                dac.getBanner_pt_earned() +
                        dac.getInline_pt_earned() +
                        dac.getInline_xr_earned() +
                        dac.getInline_mb_earned() +
                        dac.getSplash_pt_earned() +
                        dac.getSplash_xr_earned() +
                        dac.getSplash_mb_earned()+
                        dac.getVideo_pt_earned() +
                        dac.getVideo_jl_earned()
        );
        dac.setTotalImpression(
                dac.getBanner_pt_impression() +
                        dac.getInline_pt_impression() +
                        dac.getInline_xr_impression() +
                        dac.getInline_mb_impression() +
                        dac.getSplash_pt_impression() +
                        dac.getSplash_xr_impression() +
                        dac.getSplash_mb_impression()+
                        dac.getVideo_pt_impression() +
                        dac.getVideo_jl_impression()
        );
        return dac;
    }

    /**
     *  汇总DailyAdtype中的Earned，arpu
     * @param da DailyAdtype
     * @return DailyAdtype
     */
    public static DailyAdtype setTotalDa(DailyAdtype da) {
        da.setEarned(
                da.getGdt_dac().getTotalEarned()+
                        da.getChannel_dac().getTotalEarned()+
                        da.getTt_dac().getTotalEarned()
        );
        da.setArpu(
                da.getDau()==0?0:da.getEarned()/da.getDau()
        );

        return da;
    }

    /**
     * 日常广告形式细分收益对象转list
     *
     * @param list DailyAdtypeList
     * @param datelist 时间list
     * @return list
     */
    public static List<List> toList(List<List<DailyAdtype>> list, List<String> datelist) {
        List<List> outerList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            List<String> innerList = new ArrayList<>();
            innerList.add(datelist.get(i));
            List<DailyAdtype> dailyAdtypeList = list.get(i);
            for (int j=0;j<dailyAdtypeList.size();j++){
                DailyAdtype dailyAdtype=dailyAdtypeList.get(j);
                innerList.add(Judgement.formatDouble2(dailyAdtype.getEarned())+"");
                innerList.add(Judgement.formatDouble3(dailyAdtype.getArpu())+"");
                innerList.add(dailyAdtype.getStartup_time()+"");
                innerList.add(dailyAdtype.getSingle_use_time());
                innerList.add(dailyAdtype.getDnu()+"");
                innerList.add(dailyAdtype.getDau()+"");
                innerList.add(dailyAdtype.getRetention()+"");
                innerList=setGdtList(dailyAdtype.getGdt_dac(),innerList,dailyAdtype.getDau());
                innerList=setChanneltList(dailyAdtype.getChannel_dac(),innerList,dailyAdtype.getDau());
                innerList=setTtList(dailyAdtype.getTt_dac(),innerList,dailyAdtype.getDau());
            }
            outerList.add(innerList);
        }
        return outerList;
    }

    /**
     * 将DailyAdtypeChannel中的值按照模版转换成list
     * @param gdt_dac DailyAdtypeChannel
     * @param list 需要赋值的list
     * @param dau  dau
     * @return  list
     */
    public static List<String> setGdtList(DailyAdtypeChannel gdt_dac,List<String> list,Integer dau){
        list.add(Judgement.formatDouble2(gdt_dac.getTotalEarned())+"");
        list.add(dau==0?"0":Judgement.NonScientificNotation(Judgement.formatDouble3(gdt_dac.getTotalEarned()/dau)+""));
        //横幅普通
        list=setAdtypeMsg(list,dau,gdt_dac.getBanner_pt_earned(),gdt_dac.getBanner_pt_impression(),gdt_dac.getBanner_pt_click());
        //插屏普通
        list=setAdtypeMsg(list,dau,gdt_dac.getInline_pt_earned(),gdt_dac.getInline_pt_impression(),gdt_dac.getInline_pt_click());
        //插屏渲染
        list=setAdtypeMsg(list,dau,gdt_dac.getInline_xr_earned(),gdt_dac.getInline_xr_impression(),gdt_dac.getInline_xr_click());
        //插屏模版
        list=setAdtypeMsg(list,dau,gdt_dac.getInline_mb_earned(),gdt_dac.getInline_mb_impression(),gdt_dac.getInline_mb_click());
        //开屏普通
        list=setAdtypeMsg(list,dau,gdt_dac.getSplash_pt_earned(),gdt_dac.getSplash_pt_impression(),gdt_dac.getSplash_pt_click());
        //开屏渲染
        list=setAdtypeMsg(list,dau,gdt_dac.getSplash_xr_earned(),gdt_dac.getSplash_xr_impression(),gdt_dac.getSplash_xr_click());
        //开屏模版
        list=setAdtypeMsg(list,dau,gdt_dac.getSplash_mb_earned(),gdt_dac.getSplash_mb_impression(),gdt_dac.getSplash_mb_click());
        //视频激励
        list=setAdtypeMsg(list,dau,gdt_dac.getVideo_jl_earned(),gdt_dac.getVideo_jl_impression(),gdt_dac.getVideo_jl_click());
        return list;
    }

    /**
     * 将DailyAdtypeChannel中的值按照模版转换成list
     * @param gdt_dac DailyAdtypeChannel
     * @param list 需要赋值的list
     * @param dau  dau
     * @return  list
     */
    public static List<String> setChanneltList(DailyAdtypeChannel gdt_dac,List<String> list,Integer dau){
        list.add(Judgement.formatDouble2(gdt_dac.getTotalEarned())+"");
        list.add(dau==0?"0":Judgement.NonScientificNotation(Judgement.formatDouble3(gdt_dac.getTotalEarned()/dau)+""));
        //横幅普通
        list=setAdtypeMsg(list,dau,gdt_dac.getBanner_pt_earned(),gdt_dac.getBanner_pt_impression(),gdt_dac.getBanner_pt_click());
        //插屏普通
        list=setAdtypeMsg(list,dau,gdt_dac.getInline_pt_earned(),gdt_dac.getInline_pt_impression(),gdt_dac.getInline_pt_click());
        //插屏渲染
        list=setAdtypeMsg(list,dau,gdt_dac.getInline_xr_earned(),gdt_dac.getInline_xr_impression(),gdt_dac.getInline_xr_click());
        //插屏模版
        list=setAdtypeMsg(list,dau,gdt_dac.getInline_mb_earned(),gdt_dac.getInline_mb_impression(),gdt_dac.getInline_mb_click());
        //开屏普通
        list=setAdtypeMsg(list,dau,gdt_dac.getSplash_pt_earned(),gdt_dac.getSplash_pt_impression(),gdt_dac.getSplash_pt_click());
        //开屏渲染
        list=setAdtypeMsg(list,dau,gdt_dac.getSplash_xr_earned(),gdt_dac.getSplash_xr_impression(),gdt_dac.getSplash_xr_click());
        //开屏模版
        list=setAdtypeMsg(list,dau,gdt_dac.getSplash_mb_earned(),gdt_dac.getSplash_mb_impression(),gdt_dac.getSplash_mb_click());
        //视频普通
        list=setAdtypeMsg(list,dau,gdt_dac.getVideo_pt_earned(),gdt_dac.getVideo_pt_impression(),gdt_dac.getVideo_pt_click());
        //视频激励
        list=setAdtypeMsg(list,dau,gdt_dac.getVideo_jl_earned(),gdt_dac.getVideo_jl_impression(),gdt_dac.getVideo_jl_click());
        return list;
    }

    /**
     * 将DailyAdtypeChannel中的值按照模版转换成list
     * @param gdt_dac DailyAdtypeChannel
     * @param list 需要赋值的list
     * @param dau  dau
     * @return  list
     */
    public static List<String> setTtList(DailyAdtypeChannel gdt_dac,List<String> list,Integer dau){
        list.add(Judgement.formatDouble2(gdt_dac.getTotalEarned())+"");
        list.add(dau==0?"0":Judgement.NonScientificNotation(Judgement.formatDouble3(gdt_dac.getTotalEarned()/dau)+""));
        //横幅普通
        list=setAdtypeMsg(list,dau,gdt_dac.getBanner_pt_earned(),gdt_dac.getBanner_pt_impression(),gdt_dac.getBanner_pt_click());
        //插屏普通
        list=setAdtypeMsg(list,dau,gdt_dac.getInline_pt_earned(),gdt_dac.getInline_pt_impression(),gdt_dac.getInline_pt_click());
        //插屏渲染
        list=setAdtypeMsg(list,dau,gdt_dac.getInline_xr_earned(),gdt_dac.getInline_xr_impression(),gdt_dac.getInline_xr_click());
        //插屏模版
        list=setAdtypeMsg(list,dau,gdt_dac.getInline_mb_earned(),gdt_dac.getInline_mb_impression(),gdt_dac.getInline_mb_click());
        //开屏普通
        list=setAdtypeMsg(list,dau,gdt_dac.getSplash_pt_earned(),gdt_dac.getSplash_pt_impression(),gdt_dac.getSplash_pt_click());
        //开屏渲染
        list=setAdtypeMsg(list,dau,gdt_dac.getSplash_xr_earned(),gdt_dac.getSplash_xr_impression(),gdt_dac.getSplash_xr_click());
        //开屏模版
        list=setAdtypeMsg(list,dau,gdt_dac.getSplash_mb_earned(),gdt_dac.getSplash_mb_impression(),gdt_dac.getSplash_mb_click());
        //视频普通
        list=setAdtypeMsg(list,dau,gdt_dac.getVideo_pt_earned(),gdt_dac.getVideo_pt_impression(),gdt_dac.getVideo_pt_click());
        //视频激励
        list=setAdtypeMsg(list,dau,gdt_dac.getVideo_jl_earned(),gdt_dac.getVideo_jl_impression(),gdt_dac.getVideo_jl_click());
        return list;
    }

    /**
     *  根据模版添加每个广告类型的值到list
     * @param list  需要赋值的list
     * @param dau   活跃人数
     * @param earned    收益
     * @param impression    展示次数
     * @param click     总点击
     * @return  原list
     */
    public static List<String> setAdtypeMsg(List<String> list,Integer dau,Double earned,Integer impression,Integer click){
        list.add(earned+"");
        list.add(impression==0?"0":Judgement.formatDouble4(((double)click)/impression)+"");
        list.add(impression==0?"0":Judgement.formatDouble3(earned/impression*1000)+"");
        list.add(impression+"");
        list.add(dau==0?"0":Judgement.formatDouble3(((double)impression)/dau)+"");
       return list;
    }
}
