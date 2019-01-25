package service.pubmethod;

import io.vertx.core.json.JsonArray;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Judgement {
    /**
     * 判断字符串是否为数字
     *
     * @param temp
     * @return
     */
    public static boolean IsNumber(String temp) {
        Pattern pattern = Pattern.compile("^(\\-|\\+)?\\d+(\\.\\d+)?$");
        Matcher isNum = pattern.matcher(temp);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 防止科学计数法显示
     *
     * @param num
     * @return
     */
    public static String NonScientificNotation(String num) {
        if (num.indexOf("E") > -1) {
            BigDecimal decimal = new BigDecimal(num);
            num = decimal.toPlainString();
        }
        return num;
    }

    /**
     * 四舍五入保留2位
     *
     * @param d double
     * @return double
     */
    public static double formatDouble2(double d) {
        return (double) Math.round(d * 100) / 100;
    }

    /**
     * 四舍五入保留4位
     *
     * @param d double
     * @return double
     */
    public static double formatDouble4(double d) {
        return (double) Math.round(d * 10000) / 10000;
    }

    /**
     * 四舍五入保留3位
     *
     * @param d double
     * @return double
     */
    public static double formatDouble3(double d) {
        return (double) Math.round(d * 1000) / 1000;
    }

    /**
     *将小数转为百分比保留两位小数
     */
    public static String toPercent(double d) {
        return formatDouble2(d*100)+"%";
    }

    /**
     * 匹配游戏
     * @param name
     * @return
     */
    public static String matchName(String name,List<String> gamelist) {
        List names = gamelist;
        for (int i = 0; i < names.size(); i++) {
            String onename = String.valueOf(names.get(i));
            if (name.contains(onename)) {
                return onename;
            }
        }
        return "未知";
    }

    /**
     * 匹配游戏
     * @param name
     * @return
     */
    public static String meizumatchName(String name,List<String> gamelist) {
        List names = gamelist;
        if (name.contains("全屏")) {
            return "开屏";
        }
        for (int i = 0; i < names.size(); i++) {
            String onename = String.valueOf(names.get(i));
            if (name.contains(onename)) {
                return onename;
            }
        }
        return "未知";
    }

    /**
     * 匹配渠道
     * @param channel
     * @return
     */
    public static String matchChannel(String channel,List<String> channel_list,List<String> channel_mark_list) {
        for (int i = 0; i < channel_list.size(); i++) {
            String onechannel = String.valueOf(channel_list.get(i));
            String channel_mark = String.valueOf(channel_mark_list.get(i));
            if (channel.contains(onechannel)) {
                //return onechannel;
                return channel_mark;
            }
        }
        return "未知";
    }

    /**
     * 匹配广告类型
     * @param type
     * @return
     */
    public static String matchType(String type,List<String> adtypelist) {
        List types =adtypelist;
        for (int i = 0; i < types.size(); i++) {
            String onetype = String.valueOf(types.get(i)).toLowerCase();
            //
            if (type.toLowerCase().contains("banner")){
                return "横幅";
            }
            if (type.toLowerCase().contains(onetype)) {
                if (type.toLowerCase().contains("插屏视频")) {
                    return "插屏视频";
                }
                return onetype;
            }
        }
        return "未知";
    }

    /**
     * 匹配异常数据，获取异常数据行数
     * @param jsonArrays
     * @return
     */
    public static List<Integer> getMismatch(List<JsonArray> jsonArrays){
        List<Integer> list=new ArrayList();
        for (int i=0;i<jsonArrays.size();i++){
            JsonArray jsonArray=jsonArrays.get(i);
            if (("未知".equals(jsonArray.getString(2))||"未知".equals(jsonArray.getString(3)))&&!"未知".equals(jsonArray.getString(1))){
                list.add(i+2);
            }
        }
        return list;
    }

    /**
     * 删除无用数据
     * @param jsonArrays
     * @return
     */
    public static List<JsonArray> removeUseless(List<JsonArray> jsonArrays){
        Iterator<JsonArray> it = jsonArrays.iterator();
        while(it.hasNext()){
            JsonArray jsonArray = it.next();
            Boolean value=false;
            try {
                value=Double.valueOf(jsonArray.getString(7))==0.0;
            }catch (ClassCastException e){
                value=jsonArray.getInteger(7)==0;
            }catch (Exception e){
                value=false;
            }
            if("未知".equals(jsonArray.getString(1))||"未知".equals(jsonArray.getString(2))||"未知".equals(jsonArray.getString(3))||value){

                it.remove();
            }
        }
        return jsonArrays;
    }

    /**
     * 去除千位分割符
     * @param list
     * @return
     */
    public static List removeSeparator(List list){
        String old=list.toString();
        old = old.replace(" ", "");
        //开始广点通数字格式调整  如下
        //  '"6,555"' 调整稳  6555
        //正则为  找到所有 '"  "' 格式的字符串，贪婪
        Pattern p1 = Pattern.compile("\"(.*?)\"");
        Matcher m = p1.matcher(old);
        while (m.find()) {
            String oldsub = m.group().trim();
            String newsub = oldsub.replace("\"", "");
            newsub = newsub.replace(",", "");
            newsub = newsub.replace("'", "");
            old = old.replace(oldsub, newsub);
        }
        old = old.replace("[", "");
        old = old.replace("]", "");
        String[] newlist=old.split(",");
        return Arrays.asList(newlist);
    }

}
