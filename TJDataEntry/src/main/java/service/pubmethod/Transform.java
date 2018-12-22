package service.pubmethod;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.entity.AdData;
import service.entity.UserData;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Transform {
    private static Logger logger = LoggerFactory.getLogger(Transform.class.getName());
    /**
     * 时间戳转日期
     * @param ms
     * @return
     */
    public static String transForDate(Integer ms){
        if(ms==null){
            ms=0;
        }
        long msl=(long)ms*1000;
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date temp=null;
        String date="";
        if(ms!=null){
            try {
                String str=sdf.format(msl);
                temp=sdf.parse(str);
                date=sdf.format(temp);
            } catch (ParseException e) {
                logger.error("时间戳转日期失败：", e);
            }
        }
        return date;
    }

    /**
     * 日期字符串转时间戳
     * 格式如"yyyy-mm-dd"
     *
     * @param dateStr
     * @return
     */
    public static Integer transForMilliSecondByTim(String dateStr, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            try{
                date = new SimpleDateFormat("yyyy/MM/dd").parse(dateStr);
            }catch (Exception E){
                logger.error("日期转时间戳失败：", E);
            }
        }
        return date == null ? 0 : transForMilliSecond(date);
    }

    /**
     * 联想日期转换成时间戳
     * @param value
     * @return
     */
    public static Integer lenovotransForMill(String value){
        String[] month={"一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"};
        String mon=value.split("-")[1];
        int intmonth=0;
        for (int i=0;i<month.length;i++){
            if (month[i].equals(mon)){
                intmonth=i+1;
            }
        }
        String months=value.split("-")[0]+"-"+intmonth+"-"+value.split("-")[2];
        SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2=new SimpleDateFormat("dd-MM-yyyy");
        String date = null;
        try{
            date=sdf1.format(sdf2.parse(months));
        }catch (ParseException e){
            logger.error("日期转时间戳失败：", e);
        }
        Integer integer=0;
        if (intmonth!=0){
            integer=transForMilliSecondByTim(date,"yyyy-MM-dd");
        }
        return integer;
    }

    /**
     * 日期转时间戳
     *
     * @param date
     * @return
     */
    private static Integer transForMilliSecond(Date date) {
        if (date == null) return null;
        return (int) (date.getTime() / 1000);
    }

    /**
     * md5加密字符串
     *
     * @param psw
     * @return
     * @throws Exception
     */
    public static String StringToMd5(String psw) {
        {
            try {
                MessageDigest md5 = MessageDigest.getInstance("MD5");
                md5.update(psw.getBytes("UTF-8"));
                byte[] encryption = md5.digest();

                StringBuffer strBuf = new StringBuffer();
                for (int i = 0; i < encryption.length; i++) {
                    if (Integer.toHexString(0xff & encryption[i]).length() == 1) {
                        strBuf.append("0").append(Integer.toHexString(0xff & encryption[i]));
                    } else {
                        strBuf.append(Integer.toHexString(0xff & encryption[i]));
                    }
                }
                return strBuf.toString();
            } catch (NoSuchAlgorithmException e) {
                return "";
            } catch (UnsupportedEncodingException e) {
                return "";
            }
        }
    }

    /**
     * 通过路径获取所有信息（String）
     *
     * @param path
     * @return
     * @throws IOException
     */
    public static String getData(String path) throws IOException {
        // 创建指定url的url对象
        URL url = new URL(path);
        // 创建http链接对象
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        // 设置请求方式
        con.setRequestMethod("GET");
        // 打开链接,上一步和该步骤作用相同，可以省略
        con.connect();
        // 获取请求返回内容并设置编码为UTF-8
        BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
        // 将返回数据拼接为字符串
        StringBuffer sb = new StringBuffer();
        // 临时字符串
        String temp = null;
        // 获取数据
        while ((temp = reader.readLine()) != null) {
            sb.append(temp);
        }
        // 关闭流
        reader.close();
        return sb.toString();
    }

    /**
     * List<AdData>转List<JsonArray>
     *
     * @param adDataList
     * @return
     */
    public static List<JsonArray> adDatatoJsonArrayList(List<AdData> adDataList) {
        List<JsonArray> jsonArrayList=new ArrayList<>();
        for (AdData adData:adDataList){
            JsonArray jsonArray=new JsonArray();
            jsonArray.add(adData.getDate());
            jsonArray.add(adData.getAppName());
            jsonArray.add(adData.getChannel());
            jsonArray.add(adData.getAdType());
            jsonArray.add(adData.getEarned());
            jsonArray.add(adData.getClickRate());
            jsonArray.add(adData.getEcpm());
            jsonArray.add(adData.getImpression());
            jsonArray.add(adData.getClick());
            jsonArray.add(adData.getFillRate());
            jsonArray.add(adData.getPlatform());
            jsonArray.add(adData.getNote());
            jsonArray.add(adData.getSdk_name());
            jsonArrayList.add(jsonArray);
        }
        return jsonArrayList;
    }

    /**
     * List<UserData> 转string
     *
     * @param userDataList
     * @return
     */
    public static List<JsonArray> userDatatoUserJsonArrayList(List<UserData> userDataList) {
       List<JsonArray> jsonArrays=new ArrayList<>();
       for (int i=0;i<userDataList.size();i++){
           JsonArray jsonArray=new JsonArray();
           jsonArray.add(userDataList.get(i).getDate());
           jsonArray.add(userDataList.get(i).getAppName());
           jsonArray.add(userDataList.get(i).getChannel());
           jsonArray.add(userDataList.get(i).getDailyNewUser());
           jsonArray.add(userDataList.get(i).getDailyActivityUser());
           jsonArray.add(userDataList.get(i).getStartupTime());
           jsonArray.add(" "+userDataList.get(i).getSingleUseTime());
           jsonArray.add(Judgement.formatDouble4(userDataList.get(i).getRetention()));
           jsonArray.add("1.0.0");
           jsonArrays.add(jsonArray);
       }
        return jsonArrays;
    }

    /**
     * url解码
     *
     * @param str url编码的字符串
     * @return
     */
    public static String getURLDecoderString(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLDecoder.decode(str, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 获取两个日期字符串之间的日期集合
     *
     * @param startTime:String
     * @param endTime:String
     * @return list:yyyy-MM-dd
     */
    public static List<String> getBetweenDate(String startTime, String endTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        // 声明保存日期集合
        List<String> list = new ArrayList<String>();
        try {
            // 转化成日期类型
            Date startDate = sdf.parse(startTime);
            Date endDate = sdf.parse(endTime);
            //用Calendar 进行日期比较判断
            Calendar calendar = Calendar.getInstance();
            while (startDate.getTime() <= endDate.getTime()) {
                // 把日期添加到集合
                list.add(sdf.format(startDate));
                // 设置日期
                calendar.setTime(startDate);
                //把日期增加一天
                calendar.add(Calendar.DATE, 1);
                // 获取增加后的日期
                startDate = calendar.getTime();
            }
        } catch (ParseException e) {
            logger.error("获取两个字符串之间的日期集合失败", e);
        }
        return list;
    }

    /**
     * 时间戳转日期
     *
     * @param ms
     * @return
     */
    public static String transForDate(Long ms) {
        if (ms == null) {
            ms = (long) 0;
        }
        long msl = (long) ms * 1000;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date temp = null;
        String date = null;
        if (ms != null) {
            try {
                String str = sdf.format(msl);
                temp = sdf.parse(str);
                date = sdf.format(temp);
            } catch (ParseException e) {
                logger.error("时间戳转成（yyyy-MM-dd）日期失败：", e);
            }
        }
        return date;
    }
}
