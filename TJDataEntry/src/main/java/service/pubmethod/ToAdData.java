package service.pubmethod;

import service.entity.AdData;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ToAdData {
    /**
     *
     * @param list 从excel表内读取的一行数据
     * @param matchinglist  对比数据
     * @return AdDataObject
     * 从list中获取AdDataObject中所需数据，检查是否存在于matchinglist中，不存在则标记未知
     */
    public static AdData guangdiantongToAdData(List list, List<List<String>> matchinglist) {
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
        adData.setAppName(Judgement.matchName(advertisement, matchinglist.get(0)));
        adData.setAdType(Judgement.matchType(advertisement, matchinglist.get(2)));
        adData.setChannel(Judgement.matchChannel(advertisement, matchinglist.get(1), matchinglist.get(3)));
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
     *
     * @param list 从excel表内读取的一行数据
     * @param matchinglist  对比数据
     * @return AdDataObject
     * 从list中获取AdDataObject中所需数据，检查是否存在于matchinglist中，不存在则标记未知
     */
    public static AdData fourthreeToAdData(List list, List<List<String>> matchinglist) {
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
        adData.setAppName(Judgement.matchName(advertisement, matchinglist.get(0)));
        adData.setAdType(Judgement.matchType(String.valueOf(list.get(3)), matchinglist.get(2)));
        adData.setChannel("4399");
        adData.setNote(advertisement);
        adData.setSdk_name("4399");
        String pvs = String.valueOf(list.get(4));
        pvs = pvs.replace(",", "");
        int pv = Integer.valueOf(pvs);
        adData.setImpression(pv);
        String clicks = String.valueOf(list.get(5));
        clicks = clicks.replace(",", "");
        int click = Integer.valueOf(clicks);
        adData.setClick(click);
        String earneds = String.valueOf(list.get(6));
        earneds = earneds.replace(",", "");
        Double earned = Double.valueOf(earneds);
        adData.setEarned(earned);
        adData.setEcpm(Double.valueOf(String.valueOf(list.get(7))));
        String clickRate = "0";
        try {
            clickRate = String.valueOf(list.get(8)).substring(0, String.valueOf(list.get(8)).indexOf("%"));
        } catch (Exception e) {
            clickRate = "0";
        }
        Double doubleClickRate;
        if (Judgement.IsNumber(clickRate)) {
            doubleClickRate = Double.valueOf(clickRate) / 100;
            doubleClickRate = Judgement.formatDouble4(doubleClickRate);
        } else {
            doubleClickRate = 0.0;
        }
        adData.setClickRate(doubleClickRate);
        adData.setPlatform("4399");
        adData.setFillRate(0.0);
        return adData;
    }

    /**
     *
     * @param list 从excel表内读取的一行数据
     * @param date 时间 excel表内没有记录时间
     * @param matchinglist  对比数据
     * @return AdDataObject
     * 从list中获取AdDataObject中所需数据，检查是否存在于matchinglist中，不存在则标记未知
     */
    public static AdData oppoToAdData(List list, String date, List<List<String>> matchinglist) {
        AdData adData = new AdData();
        adData.setDate(Transform.transForMilliSecondByTim(date, "yyyy-MM-dd"));
        String advertisementposition = String.valueOf(list.get(0));
        adData.setNote(advertisementposition);
        adData.setSdk_name("oppo");
        adData.setAppName(Judgement.matchName(advertisementposition, matchinglist.get(0)));
        adData.setAdType(Judgement.matchType(advertisementposition, matchinglist.get(2)));
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
     *
     * @param list 从excel表内读取的一行数据
     * @param matchinglist  对比数据
     * @return AdDataObject
     * 从list中获取AdDataObject中所需数据，检查是否存在于matchinglist中，不存在则标记未知
     */
    public static AdData xiaomiToAdData(List list, List<List<String>> matchinglist) {
        AdData adData = new AdData();
        adData.setDate(Transform.transForMilliSecondByTim(String.valueOf(list.get(0)).trim(), "yyyyMMdd"));
        String old = String.valueOf(list.get(2));
        adData.setNote(old);
        adData.setSdk_name("小米");
        adData.setAppName(Judgement.matchName(old, matchinglist.get(0)));
        adData.setAdType(Judgement.matchType(old, matchinglist.get(2)));
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
     *
     * @param list 从excel表内读取的一行数据
     * @param matchinglist  对比数据
     * @return AdDataObject
     * 从list中获取AdDataObject中所需数据，检查是否存在于matchinglist中，不存在则标记未知
     */
    public static AdData toutiaoToAdData(List list, List<List<String>> matchinglist) {
        AdData adData = new AdData();
        adData.setDate(Transform.transForMilliSecondByTim(String.valueOf(list.get(0)).trim(), "yyyy-MM-dd"));
        String old = String.valueOf(list.get(2));
        adData.setNote(old);
        adData.setSdk_name("头条");
        adData.setAppName(Judgement.matchName(old, matchinglist.get(0)));
        adData.setAdType(Judgement.matchType(old, matchinglist.get(2)));
        adData.setChannel(Judgement.matchChannel(old, matchinglist.get(1), matchinglist.get(3)));
//        adData.setChannel("toutiao");
        String impression = String.valueOf(list.get(3)).trim();
        try {
            adData.setImpression(Double.valueOf(impression).intValue());
        } catch (Exception e) {
            adData.setImpression(0);
        }
        try {
            adData.setClick(Double.valueOf(String.valueOf(list.get(4)).trim()).intValue());
        } catch (Exception e) {
            adData.setClick(0);
        }
        try {
            String clickrate = String.valueOf(list.get(5)).trim();
            clickrate = clickrate.substring(0, clickrate.indexOf("%"));
            adData.setClickRate(Judgement.formatDouble4(Double.valueOf(clickrate) / 100));
        } catch (Exception e) {
            adData.setClickRate(0.0);
        }
        try {
            adData.setEarned(Judgement.formatDouble4(Double.valueOf(String.valueOf(list.get(7)))));
        } catch (Exception e) {
            adData.setEarned(0);

        }
        try {
            adData.setEcpm(Judgement.formatDouble4(Double.valueOf(String.valueOf(list.get(6)))));
        } catch (Exception e) {
            adData.setEcpm(0.0);
        }
        adData.setFillRate(0);
        adData.setPlatform("头条");
        return adData;
    }

    /**
     *
     * @param list 从excel表内读取的一行数据
     * @param matchinglist  对比数据
     * @param date 时间 excel表内不包含时间
     * @return AdDataObject
     * 从list中获取AdDataObject中所需数据，检查是否存在于matchinglist中，不存在则标记未知
     */
    public static AdData vivoToAdData(List list, String date, List<List<String>> matchinglist) {
        AdData adData = new AdData();
        adData.setDate(Transform.transForMilliSecondByTim(date, "yyyy-MM-dd"));
        String advertisement = String.valueOf(list.get(0));
        adData.setNote(advertisement);
        adData.setSdk_name("vivo");
        adData.setAppName(Judgement.matchName(advertisement, matchinglist.get(0)));
        adData.setAdType(Judgement.matchType(advertisement, matchinglist.get(2)));
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
     *
     * @param list 从excel表内读取的一行数据
     * @param matchinglist  对比数据
     * @param year 年份  原表内数据没有记录年份
     * @return AdDataObject
     * 从list中获取AdDataObject中所需数据，检查是否存在于matchinglist中，不存在则标记未知
     */
    public static AdData meizuToAdData(List list, List<List<String>> matchinglist, Integer year) {
        AdData adData = new AdData();
        adData.setAppName(Judgement.matchName(String.valueOf(list.get(1)), matchinglist.get(0)));
        adData.setAdType(Judgement.meizumatchName(String.valueOf(list.get(2)), matchinglist.get(2)));
        adData.setNote(String.valueOf(list.get(1)) + "-" + String.valueOf(list.get(2)));
        adData.setSdk_name("魅族");
        adData.setDate(Transform.transForMilliSecondByTim(year + String.valueOf(list.get(0)).trim(), "yyyyMM月dd日"));
        adData.setImpression(Integer.valueOf(String.valueOf(list.get(4)).substring(0, String.valueOf(list.get(4)).indexOf("."))));
        adData.setClick(Integer.valueOf(String.valueOf(list.get(5)).substring(0, String.valueOf(list.get(5)).indexOf("."))));
        String clickRate = String.valueOf(list.get(6));
        clickRate = clickRate.substring(0, clickRate.indexOf("%"));
        adData.setClickRate(Judgement.formatDouble4(Double.valueOf(clickRate) / 100));
        adData.setEcpm(Judgement.formatDouble4(Double.valueOf(String.valueOf(list.get(8)))));
        String income = String.valueOf(list.get(7));
        if (income.indexOf("￥") != -1) {
            adData.setEarned(Judgement.formatDouble2(Double.valueOf(income.substring(income.indexOf("￥") + 1))));
        } else {
            adData.setEarned(Judgement.formatDouble2(Double.valueOf(income)));
        }
        adData.setChannel("meizu");
        adData.setPlatform("魅族");
        return adData;
    }

    /**
     *
     * @param list 从excel表内读取的一行数据
     * @param matchinglist  对比数据
     * @param year 年份  原表内数据没有记录年份
     * @return AdDataObject
     * 从list中获取AdDataObject中所需数据，检查是否存在于matchinglist中，不存在则标记未知
     */
    public static AdData jinliToAdData(List list, List<List<String>> matchinglist, Integer year) {
        AdData adData = new AdData();
        adData.setAppName(Judgement.matchName(String.valueOf(list.get(1)), matchinglist.get(0)));
        adData.setAdType(Judgement.meizumatchName(String.valueOf(list.get(2)), matchinglist.get(2)));
        adData.setNote(String.valueOf(list.get(1)) + "-" + String.valueOf(list.get(2)));
        adData.setSdk_name("金立");
        adData.setDate(Transform.transForMilliSecondByTim(year + String.valueOf(list.get(0)).trim(), "yyyyMM月dd日"));
        adData.setImpression(Integer.valueOf(String.valueOf(list.get(4)).substring(0, String.valueOf(list.get(4)).indexOf("."))));
        adData.setClick(Integer.valueOf(String.valueOf(list.get(5)).substring(0, String.valueOf(list.get(5)).indexOf("."))));
        String clickRate = String.valueOf(list.get(6));
        clickRate = clickRate.substring(0, clickRate.indexOf("%"));
        adData.setClickRate(Judgement.formatDouble4(Double.valueOf(clickRate) / 100));
        adData.setEcpm(Judgement.formatDouble4(Double.valueOf(String.valueOf(list.get(8)))));
        String income = String.valueOf(list.get(7));
        if (income.indexOf("￥") != -1) {
            adData.setEarned(Judgement.formatDouble2(Double.valueOf(income.substring(income.indexOf("￥") + 1))));
        } else {
            adData.setEarned(Judgement.formatDouble2(Double.valueOf(income)));
        }
        adData.setChannel("jinli");
        adData.setPlatform("金立");
        return adData;
    }

    /**
     *
     * @param list 从excel表内读取的一行数据
     * @param matchinglist  对比数据
     * @return AdDataObject
     * 从list中获取AdDataObject中所需数据，检查是否存在于matchinglist中，不存在则标记未知
     */
    public static AdData qihooToAdData(List list, List<List<String>> matchinglist) {
        AdData adData = new AdData();
        adData.setDate(Transform.transForMilliSecondByTim(String.valueOf(list.get(0)), "yyyy-MM-dd"));
        adData.setNote(String.valueOf(list.get(1))+"-qihoo-"+String.valueOf(list.get(2)));
        adData.setSdk_name("qihoo");
        adData.setAppName(Judgement.matchName(String.valueOf(list.get(1)), matchinglist.get(0)));
        adData.setAdType(Judgement.matchName(String.valueOf(list.get(2)), matchinglist.get(2)));
        adData.setImpression(Integer.valueOf(String.valueOf(list.get(3)).trim()));
        adData.setClick(Integer.valueOf(String.valueOf(list.get(4)).trim()));
        String sclickrate = String.valueOf(list.get(5));
        Double clickrate = 0.0;
        if (sclickrate.indexOf("%") > -1) {
            sclickrate = sclickrate.substring(0, sclickrate.indexOf("%"));
            clickrate = Judgement.formatDouble4(Double.valueOf(sclickrate) / 100);
        } else {
            clickrate = Judgement.formatDouble4(Judgement.formatDouble4(Double.valueOf(sclickrate)));
        }
        adData.setClickRate(clickrate);
        adData.setEarned(Judgement.formatDouble2(Double.valueOf(String.valueOf(list.get(6)))));
        adData.setEcpm(Double.valueOf(String.valueOf(list.get(8))));
        adData.setChannel("qihoo");
        adData.setPlatform("qihoo");
        return adData;
    }

    /**
     *
     * @param list 从excel表内读取的一行数据
     * @param name  应用名
     * @param adtype 广告类型
     * @return AdDataObject
     */
    public static AdData samsungToAdData(List list, String name, String adtype) {
        list = Judgement.removeSeparator(list);
        AdData adData = new AdData();
        adData.setDate(Transform.transForMilliSecondByTim(String.valueOf(list.get(0)), "yyyy-MM-dd"));
        adData.setNote(adtype + name);
        adData.setSdk_name("三星");
        adData.setAppName(name);
        adData.setAdType(adtype);
        Integer impression = Integer.valueOf(String.valueOf(list.get(2)).trim());
        adData.setImpression(impression);
        Integer click = Integer.valueOf(String.valueOf(list.get(3)).trim());
        adData.setClick(click);
        adData.setClickRate(Judgement.formatDouble4(impression == 0 ? 0 : click / impression));
        adData.setEarned(Double.valueOf(String.valueOf(list.get(1))));
        adData.setEcpm(Double.valueOf(String.valueOf(list.get(4))));
        adData.setChannel("samsung");
        adData.setPlatform("三星");
        return adData;
    }

    /**
     *
     * @param list 从excel表内读取的一行数据
     * @param matchinglist  对比数据
     * @return AdDataObject
     * 从list中获取AdDataObject中所需数据，检查是否存在于matchinglist中，不存在则标记未知
     */
    public static AdData jiuyouToAdData(List list, List<List<String>> matchinglist) {
        AdData adData = new AdData();
        adData.setDate(Transform.transForMilliSecondByTim(String.valueOf(list.get(0)), "yyyy/MM/dd"));
        String note = String.valueOf(list.get(1));
        adData.setNote(note);
        adData.setSdk_name("九游");
        adData.setAppName(Judgement.matchName(note, matchinglist.get(0)));
        adData.setAdType(Judgement.matchType(note, matchinglist.get(2)));
        Integer impression = Integer.valueOf(String.valueOf(list.get(3)).trim());
        adData.setImpression(impression);
        adData.setClick(Integer.valueOf(String.valueOf(list.get(4)).trim()));
        String sclickrate = String.valueOf(list.get(5));
        Double clickrate = 0.0;
        if (sclickrate.indexOf("%") > -1) {
            sclickrate = sclickrate.substring(0, sclickrate.indexOf("%"));
            clickrate = Judgement.formatDouble4(Double.valueOf(sclickrate) / 100);
        } else {
            clickrate = Judgement.formatDouble4(Judgement.formatDouble4(Double.valueOf(sclickrate)));
        }
        adData.setClickRate(clickrate);
        Double earn = Double.valueOf(String.valueOf(list.get(6)).trim());
        adData.setEarned(earn);
        adData.setEcpm(Judgement.formatDouble4(clickrate / impression * 1000));
        adData.setChannel("jiuyou");
        adData.setPlatform("九游");
        return adData;
    }

    /**
     *
     * @param list 从excel表内读取的一行数据
     * @param matchinglist  对比数据
     * @return AdDataObject
     * 从list中获取AdDataObject中所需数据，检查是否存在于matchinglist中，不存在则标记未知
     */
    public static AdData newjiuyouToAdData(List list, List<List<String>> matchinglist) {
        AdData adData = new AdData();
        adData.setDate(Transform.transForMilliSecondByTim(String.valueOf(list.get(0)), "yyyy-MM-dd"));
        String note = String.valueOf(list.get(1));
        adData.setNote(note);
        adData.setSdk_name("九游");
        adData.setAppName(Judgement.matchName(note, matchinglist.get(0)));
        adData.setAdType(Judgement.matchType(note, matchinglist.get(2)));
        Integer impression = Integer.valueOf(new BigDecimal(Double.valueOf(String.valueOf(list.get(3)).trim())).setScale(0, BigDecimal.ROUND_HALF_UP).toString());
        adData.setImpression(impression);
        adData.setClick(Integer.valueOf(new BigDecimal(Double.valueOf(String.valueOf(list.get(4)).trim())).setScale(0, BigDecimal.ROUND_HALF_UP).toString()));
        Double clickrate = Double.valueOf(list.get(5).toString());
        adData.setClickRate(clickrate);
        Double earn = Double.valueOf(String.valueOf(list.get(6)).trim());
        adData.setEarned(earn);
        adData.setEcpm(Judgement.formatDouble4(clickrate / impression * 1000));
        adData.setChannel("jiuyou");
        adData.setPlatform("九游");
        return adData;
    }

    /**
     *
     * @param list 从excel表内读取的一行数据
     * @param matchinglist  对比数据
     * @return AdDataObject
     * 从list中获取AdDataObject中所需数据，检查是否存在于matchinglist中，不存在则标记未知
     */
    public static AdData lenovoToAdData(List list, String name, String adtype) {
        AdData adData = new AdData();
        adData.setDate(Transform.lenovotransForMill(String.valueOf(list.get(0))));
        adData.setNote(adtype + name);
        adData.setSdk_name("联想");
        adData.setAppName(name);
        adData.setAdType(adtype);
        Integer impression = Integer.valueOf(String.valueOf(list.get(2)).trim().substring(0, String.valueOf(list.get(2)).trim().indexOf(".")));
        adData.setImpression(impression);
        Integer click = Integer.valueOf(String.valueOf(list.get(3)).trim().substring(0, String.valueOf(list.get(3)).trim().indexOf(".")));
        adData.setClick(click);
        String sclickrate = String.valueOf(list.get(5)).trim();
        adData.setClickRate(Double.valueOf(sclickrate));
        Double earn = Double.valueOf(String.valueOf(list.get(6)));
        adData.setEarned(earn);
        adData.setEcpm(Judgement.formatDouble4(earn / impression * 1000));
        adData.setChannel("lenovo");
        adData.setPlatform("联想");
        return adData;
    }


}
