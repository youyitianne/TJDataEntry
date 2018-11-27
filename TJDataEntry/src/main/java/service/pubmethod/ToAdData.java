package service.pubmethod;

import service.entity.AdData;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ToAdData {
    /**
     * 广点通转addata
     *
     * @param list
     * @return
     */
    public static AdData guangdiantongToAdData(List list,List<List<String>> matchinglist) {
        String old = list.toString();
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
        String[] newString = old.split(",");
        list = Arrays.asList(newString);
        AdData adData = new AdData();
        String date = String.valueOf(list.get(0));
        Integer time = Transform.transForMilliSecondByTim(date, "yyyy-MM-dd");
        adData.setDate(time);
        String advertisement = String.valueOf(list.get(2));
        adData.setAppName(Judgement.matchName(advertisement,matchinglist.get(0)));
        adData.setAdType(Judgement.matchType(advertisement,matchinglist.get(2)));
        adData.setChannel(Judgement.matchChannel(advertisement,matchinglist.get(1),matchinglist.get(3)));
        adData.setNote(advertisement);
        adData.setSdk_name("广点通");
        String pvs = String.valueOf(list.get(3));
        pvs = pvs.replace(",", "");
        int pv = Integer.valueOf(pvs);
        adData.setImpression(pv);
        String clicks = String.valueOf(list.get(4));
        clicks = clicks.replace(",", "");
        int click = Integer.valueOf(clicks);
        adData.setClick(click);
        String earneds = String.valueOf(list.get(5));
        earneds = earneds.replace(",", "");
        Double earned = Double.valueOf(earneds);
        adData.setEarned(earned);
        adData.setEcpm(Double.valueOf(String.valueOf(list.get(6))));
        String clickRate = String.valueOf(list.get(7)).substring(0, String.valueOf(list.get(7)).indexOf("%"));
        Double doubleClickRate;
        if (Judgement.IsNumber(clickRate)) {
            doubleClickRate = Double.valueOf(clickRate) / 100;
            doubleClickRate = Judgement.formatDouble4(doubleClickRate);
        } else {
            doubleClickRate = 0.0;
        }
        adData.setClickRate(doubleClickRate);
        adData.setPlatform("广点通");
        adData.setFillRate(0.0);
        return adData;
    }

    /**
     * oppo转addata
     *
     * @param list
     * @return
     */
    public static AdData oppoToAdData(List list, String date,List<List<String>> matchinglist) {
        AdData adData = new AdData();
        adData.setDate(Transform.transForMilliSecondByTim(date, "yyyy-MM-dd"));
        String advertisementposition = String.valueOf(list.get(0));
        adData.setNote(advertisementposition);
        adData.setSdk_name("oppo");
        adData.setAppName(Judgement.matchName(advertisementposition,matchinglist.get(0)));
        adData.setAdType(Judgement.matchType(advertisementposition,matchinglist.get(2)));
        adData.setChannel("oppo");
        adData.setPlatform("oppo");
        adData.setImpression(Integer.valueOf(String.valueOf(list.get(2))));
        adData.setClick(Integer.valueOf(String.valueOf(list.get(3))));
        adData.setEarned(Double.valueOf(String.valueOf(list.get(4))));
        adData.setEcpm(Judgement.formatDouble2(Double.valueOf(String.valueOf(list.get(5)))));
        adData.setFillRate(Double.valueOf(String.valueOf(list.get(6))));
        adData.setClickRate(Double.valueOf(String.valueOf(list.get(7))));
        return adData;
    }

    /**
     * 小米转addata
     * @param list
     * @return
     */
    public static AdData xiaomiToAdData(List list,List<List<String>> matchinglist) {
       AdData adData = new AdData();
        adData.setDate(Transform.transForMilliSecondByTim(String.valueOf(list.get(0)).trim(), "yyyyMMdd"));
        String old = String.valueOf(list.get(2));
        adData.setNote(old);
        adData.setSdk_name("小米");
        adData.setAppName(Judgement.matchName(old,matchinglist.get(0)));
        adData.setAdType(Judgement.matchType(old,matchinglist.get(2)));
        adData.setChannel("xiaomi");
        adData.setImpression(Integer.valueOf(String.valueOf(list.get(3)).trim()));
        adData.setClick(Integer.valueOf(String.valueOf(list.get(4)).trim()));
        adData.setClickRate(Judgement.formatDouble4(Double.valueOf(String.valueOf(list.get(5)).trim()) / 100));
        adData.setEarned(Judgement.formatDouble4(Double.valueOf(String.valueOf(list.get(6)))));
        adData.setEcpm(Judgement.formatDouble4(Double.valueOf(String.valueOf(list.get(7)))));
        adData.setFillRate(Judgement.formatDouble4(Double.valueOf(String.valueOf(list.get(8)).trim()) / 100));
        adData.setPlatform("小米");
        return adData;
    }

    /**
     * vivo转addata
     * @param list
     * @return
     */
    public static AdData vivoToAdData(List list, String date,List<List<String>> matchinglist) {
        AdData adData = new AdData();
        adData.setDate(Transform.transForMilliSecondByTim(date, "yyyy-MM-dd"));
        String advertisement = String.valueOf(list.get(0));
        adData.setNote(advertisement);
        adData.setSdk_name("vivo");
        adData.setAppName(Judgement.matchName(advertisement,matchinglist.get(0)));
        adData.setAdType(Judgement.matchType(advertisement,matchinglist.get(2)));
        adData.setImpression(Integer.valueOf(String.valueOf(list.get(2))));
        adData.setClick(Integer.valueOf(String.valueOf(list.get(3))));
        adData.setClickRate(Judgement.formatDouble4(Double.valueOf(String.valueOf(list.get(4)).trim()) / 100));
        adData.setEcpm(Judgement.formatDouble4(Double.valueOf(String.valueOf(list.get(5)))));
        adData.setEarned(Judgement.formatDouble4(Double.valueOf(String.valueOf(list.get(7)))));
        adData.setChannel("vivo");
        adData.setPlatform("vivo");
        return adData;
    }


    /**
     * meizu转addata
     * @param list
     * @return
     */
    public static AdData meizuToAdData(List list,List<List<String>> matchinglist,Integer year) {
        AdData adData = new AdData();
//        String[] type=adType.split("-");
//        adData.setAppName(type[0]);
//        adData.setAdType(type[1]);
//        adData.setNote(adType);
          adData.setAppName(Judgement.matchName(String.valueOf(list.get(1)),matchinglist.get(0)));
          adData.setAdType(Judgement.matchName(String.valueOf(list.get(1)),matchinglist.get(2)));
          adData.setNote(String.valueOf(list.get(1))+"-"+String.valueOf(list.get(2)));


        adData.setSdk_name("魅族");

        adData.setDate(Transform.transForMilliSecondByTim(year+String.valueOf(list.get(0)).trim(), "yyyyMM月dd日"));

        adData.setImpression(Integer.valueOf(String.valueOf(list.get(4)).substring(0,String.valueOf(list.get(4)).indexOf("."))));
        adData.setClick(Integer.valueOf(String.valueOf(list.get(5)).substring(0,String.valueOf(list.get(5)).indexOf("."))));
        String clickRate=String.valueOf(list.get(6));
        clickRate=clickRate.substring(0,clickRate.indexOf("%"));
        adData.setClickRate(Judgement.formatDouble4(Double.valueOf(clickRate)/100));
        adData.setEcpm(Judgement.formatDouble4(Double.valueOf(String.valueOf(list.get(8)))));
        String income=String.valueOf(list.get(7));
        if (income.indexOf("￥")!=-1){
            adData.setEarned(Judgement.formatDouble2(Double.valueOf(income.substring(income.indexOf("￥")+1))));
        }else {
            adData.setEarned(Judgement.formatDouble2(Double.valueOf(income)));
        }
        adData.setChannel("meizu");
        adData.setPlatform("魅族");
        return adData;


//        AdData adData = new AdData();
//        String[] type=adType.split("-");
//        adData.setAppName(type[0]);
//        adData.setAdType(type[1]);
//        adData.setNote(adType);
//        adData.setSdk_name("魅族");
//        adData.setDate(Transform.transForMilliSecondByTim(String.valueOf(list.get(0)).trim(), "yyyy-MM-dd"));
//        adData.setImpression(Integer.valueOf(String.valueOf(list.get(1)).substring(0,String.valueOf(list.get(1)).indexOf("."))));
//        adData.setClick(Integer.valueOf(String.valueOf(list.get(2)).substring(0,String.valueOf(list.get(2)).indexOf("."))));
//        String clickRate=String.valueOf(list.get(3));
//        clickRate=clickRate.substring(0,clickRate.indexOf("%"));
//        adData.setClickRate(Judgement.formatDouble4(Double.valueOf(clickRate)/100));
//        adData.setEcpm(Judgement.formatDouble4(Double.valueOf(String.valueOf(list.get(5)))));
//        String income=String.valueOf(list.get(6));
//        if (income.indexOf("￥")!=-1){
//            adData.setEarned(Judgement.formatDouble2(Double.valueOf(income.substring(income.indexOf("￥")+1))));
//        }else {
//            adData.setEarned(Judgement.formatDouble2(Double.valueOf(income)));
//        }
//        adData.setChannel("meizu");
//        adData.setPlatform("魅族");
//        return adData;
    }

    /**
     * 奇虎转addata
     * @param list
     * @param matchinglist
     * @return
     */
    public static AdData qihooToAdData(List list,List<List<String>> matchinglist){
        AdData adData=new AdData();
        adData.setDate(Transform.transForMilliSecondByTim(String.valueOf(list.get(0)), "yyyy-MM-dd"));
        adData.setNote(String.valueOf(list.get(3)));
        adData.setSdk_name("qihoo");
        adData.setAppName(Judgement.matchName(String.valueOf(list.get(1)),matchinglist.get(0)));
        adData.setAdType(Judgement.matchName(String.valueOf(list.get(2)),matchinglist.get(2)));
    //    adData.setAdType(adtype);
        adData.setImpression(Integer.valueOf(String.valueOf(list.get(3)).trim()));
        adData.setClick(Integer.valueOf(String.valueOf(list.get(4)).trim()));
        String sclickrate=String.valueOf(list.get(5));
        Double clickrate=0.0;
        if (sclickrate.indexOf("%")>-1){
            sclickrate=sclickrate.substring(0,sclickrate.indexOf("%"));
            clickrate=Judgement.formatDouble4(Double.valueOf(sclickrate)/100);
        }else {
            clickrate=Judgement.formatDouble4(Judgement.formatDouble4(Double.valueOf(sclickrate)));
        }
        adData.setClickRate(clickrate);
        adData.setEarned(Judgement.formatDouble2(Double.valueOf(String.valueOf(list.get(6)))));
        adData.setEcpm(Double.valueOf(String.valueOf(list.get(8))));
        adData.setChannel("qihoo");
        adData.setPlatform("qihoo");
        return adData;
    }

    /**
     * 三星数据转addata
     * @param list
     * @param name
     * @param adtype
     * @return
     */
    public static AdData samsungToAdData(List list,String name,String adtype){
        list=Judgement.removeSeparator(list);
        AdData adData=new AdData();
        adData.setDate(Transform.transForMilliSecondByTim(String.valueOf(list.get(0)), "yyyy-MM-dd"));
        adData.setNote(adtype+name);
        adData.setSdk_name("三星");
        adData.setAppName(name);
        adData.setAdType(adtype);
        Integer impression=Integer.valueOf(String.valueOf(list.get(2)).trim());
        adData.setImpression(impression);
        Integer click=Integer.valueOf(String.valueOf(list.get(3)).trim());
        adData.setClick(click);
        adData.setClickRate(Judgement.formatDouble4(impression==0?0:click/impression));
        adData.setEarned(Double.valueOf(String.valueOf(list.get(1))));
        adData.setEcpm(Double.valueOf(String.valueOf(list.get(4))));
        adData.setChannel("samsung");
        adData.setPlatform("三星");
        return adData;
    }

    /**
     * 九游数据转addata
     * @param list
     * @param matchinglist
     * @return
     */
    public static AdData jiuyouToAdData(List list,List<List<String>> matchinglist){
        AdData adData=new AdData();
        adData.setDate(Transform.transForMilliSecondByTim(String.valueOf(list.get(0)), "yyyy/MM/dd"));
        String note=String.valueOf(list.get(1));
        adData.setNote(note);
        adData.setSdk_name("九游");
        adData.setAppName(Judgement.matchName(note,matchinglist.get(0)));
        adData.setAdType(Judgement.matchType(note,matchinglist.get(2)));
        Integer impression=Integer.valueOf(String.valueOf(list.get(3)).trim());
        adData.setImpression(impression);
        adData.setClick(Integer.valueOf(String.valueOf(list.get(4)).trim()));
        String sclickrate=String.valueOf(list.get(5));
        Double clickrate=0.0;
        if (sclickrate.indexOf("%")>-1){
            sclickrate=sclickrate.substring(0,sclickrate.indexOf("%"));
            clickrate=Judgement.formatDouble4(Double.valueOf(sclickrate)/100);
        }else {
            clickrate=Judgement.formatDouble4(Judgement.formatDouble4(Double.valueOf(sclickrate)));
        }
        adData.setClickRate(clickrate);
        Double earn=Double.valueOf(String.valueOf(list.get(6)).trim());
        adData.setEarned(earn);
        adData.setEcpm(Judgement.formatDouble4(clickrate/impression*1000));
        adData.setChannel("jiuyou");
        adData.setPlatform("九游");
        return adData;
    }

    /**
     * 联想数据转addata
     * @param list
     * @param name
     * @param adtype
     * @return
     */
    public static AdData lenovoToAdData(List list,String name,String adtype){
        AdData adData=new AdData();
        adData.setDate(Transform.lenovotransForMill(String.valueOf(list.get(0))));
        adData.setNote(adtype+name);
        adData.setSdk_name("联想");
        adData.setAppName(name);
        adData.setAdType(adtype);
        Integer impression=Integer.valueOf(String.valueOf(list.get(2)).trim().substring(0,String.valueOf(list.get(2)).trim().indexOf(".")));
        adData.setImpression(impression);
        Integer click=Integer.valueOf(String.valueOf(list.get(3)).trim().substring(0,String.valueOf(list.get(3)).trim().indexOf(".")));
        adData.setClick(click);
        String sclickrate=String.valueOf(list.get(5)).trim();
        adData.setClickRate(Double.valueOf(sclickrate));
        Double earn=Double.valueOf(String.valueOf(list.get(6)));
        adData.setEarned(earn);
        adData.setEcpm(Judgement.formatDouble4(earn/impression*1000));
        adData.setChannel("lenovo");
        adData.setPlatform("联想");
        return adData;
    }



}
