import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.ocean.rawsdk.ApiExecutor;
import com.alibaba.ocean.rawsdk.client.exception.OceanException;
import com.umeng.uapp.param.UmengUappGetAllAppDataParam;
import com.umeng.uapp.param.UmengUappGetAllAppDataResult;
import com.umeng.uapp.param.UmengUappGetChannelDataParam;
import com.umeng.uapp.param.UmengUappGetChannelDataResult;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;
import service.dataprocessing.ExcelRead;
import service.dataprocessing.UmengHandler;
import service.pubmethod.Transform;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class test {
    @Test
    public void test1() {
        ExcelRead excelRead = new ExcelRead();
        List list = excelRead.readGuang("file-uploads/360_视频_运营报表20181020-20181021 视.csv", "gbk");
        System.out.println(list);
    }

    @Test
    public void test4() {
        ExcelRead excelRead = new ExcelRead();
        List list = excelRead.readExcel("file-uploads/联想_横幅.xlsx");
        System.out.println(list);
    }

    @Test
    public void test5() throws Exception {
        String date = "2018/8/8";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date date1 = sdf.parse(date);
        SimpleDateFormat sdf11 = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(sdf11.format(date1));
    }

    @Test
    public void test6() throws Exception {
        Integer val = Transform.lenovotransForMill("22-月-2018");
        System.out.println(val);
    }

    @Test
    public void test2() {
        ExcelRead excelRead = new ExcelRead();
        List list = excelRead.readGuang("file-uploads/2018-11-18 至 2018-11-18 阿里游戏SSP平台-广告位数据报表.csv", "gbk");
        System.out.println(list.toString());
    }

    @Test
    public void test3() {
        ExcelRead excelRead = new ExcelRead();
        List list = excelRead.readExcel("file-uploads/2018-11-01至2018-11-27_数据报表.xls");
        System.out.println(list);
    }

    @Test
    public void test7() {
        ExcelRead excelRead = new ExcelRead();
        List list = excelRead.readGuang("file-uploads/2018-11-18 至 2018-11-18 阿里游戏SSP平台-广告位数据报表.csv", "gbk");
        System.out.println(list);
    }

    @Test
    public void test8() {
        Integer time = Transform.transForMilliSecondByTim("2018" + String.valueOf("11月1日"), "yyyyMM月dd日");
        System.out.println(Transform.transForDate(time));
    }

    @Test
    public void test9() {
        Integer time = Transform.transForMilliSecondByTim("2018/11/1", "yyyy/MM/dd");
        System.out.println(time);
    }

    @Test
    public void test10() {
        Date date1 = new Date();
        date1.setTime(1544803200000L);
        String date = new SimpleDateFormat("yyyy-MM-dd").format(date1);
        System.out.println(date);
    }

    @Test
    public void test11() {
        ExcelRead excelRead = new ExcelRead();
        List list = excelRead.readGuang("file-uploads/数据查询 (1).csv", "gbk");
        System.out.println(list);
    }

    @Test
    public void test12() throws Exception {
        String string = "2019-01-03";
        string = string.replace(string.split("-")[2], "01");
        System.out.println(string);
    }

    @Test
    public void test13() throws Exception {
        String path = "http://192.168.1.144:8091/arpufile/2018-12-12_2018-12-13_保护气球";
        try {
            String initialdata = Transform.getData(path);
            if (initialdata != null) {
                JsonObject js = new JsonObject(initialdata);
                if (js.getValue("msg").equals("success")) {
                    System.out.println(1);
                } else {
                    System.out.println(2);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(3);
        }
    }

    @Test
    public void test14() throws Exception {
        ExcelRead excelRead = new ExcelRead();
        List list = excelRead.readExcel("file-uploads/头条.xlsx");
        System.out.println(list);
    }

    @Test
    public void test15() throws Exception {
        String intw = "\"60.0\"";
        intw = intw.replace("\"", "");
        System.out.println(intw);
    }

    @Test
    public void test16() throws Exception {
        try {
            FileInputStream is = new FileInputStream("file-uploads/2.xls");
            HSSFWorkbook workbook = new HSSFWorkbook(is);
            HSSFSheet sheet = workbook.getSheetAt(0);
            ArrayList<Integer> num = new ArrayList<Integer>() {{
                add(2);
                add(3);
                add(4);
                add(5);
            }};
            for (int i = 0; i < num.size(); i++) {
                HSSFRow row = sheet.getRow(num.get(i));
                sheet.removeRow(row);
            }
            //sheet.shiftRows(1, 4, -1);//删除第一行到第四行，然后使下方单元格上移
            FileOutputStream os = new FileOutputStream("file-uploads/2.xls");
            workbook.write(os);
            is.close();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test17() {
        String starttime = "55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77";
        String[] num = starttime.split(",");
        ArrayList<Integer> arrayList = new ArrayList();
        for (int i = 0; i < num.length; i++) {
            arrayList.add(Integer.valueOf(num[i]));
        }
        System.out.println(arrayList);
    }

    @Test
    public void test18() {
        ExcelRead excelRead = new ExcelRead();
        List list = excelRead.readExcel("file-uploads/九游例子.xls");
        System.out.println(list.toString());
    }

    @Test
    public void test19() {
        List<Integer> integerList = Arrays.asList(4, 5, 2, 3, 7, 9);
        Collections.sort(integerList);  //升序
        //collect.sort(Comparator.reverseOrder()); //倒序
        //collect.forEach(System.out::println);
        System.out.println(integerList);
    }

    @Test
    public void test20() {
        List<Integer> integerList = Arrays.asList(4, 5, 2, 3, 7, 9);
        for (int i = 0; i < integerList.size(); i++) {
            System.out.println(i);
            if (integerList.get(i).equals(3)) {
                return;
            }
        }
    }


    @Test
    public void test21() {
        ExcelRead excelRead = new ExcelRead();
        List list = excelRead.readGuang("file-uploads/4399.csv", "gbk");
        System.out.println(list.toString());
    }

    @Test
    public void test22() {
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(new JsonObject()).add(new JsonObject()).add(new JsonObject());
        System.out.println(jsonArray.getJsonObject(3));
    }

    @Test
    public void test23() {
        Date date = new Date();
        date.setTime(2147483647L * 1000);
        System.out.println(date);
    }

    @Test
    public void test24() {


        Long long1 = 10L;
        Integer long2 = 10;
        System.out.println(long1.equals(long2.longValue()));
    }

    @Test
    public void test25() {
        JsonObject jsonObject = new JsonObject();
        System.out.println(jsonObject.getInteger("hell") == null);
    }

    @Test
    public void test26() {
        System.out.println(Fribonacci(34, 0));
    }

    private Integer n = 1;

    public static int Fribonacci(int n, Integer sum) {
        if (n <= 2)
            return 1;
        else
            return Fribonacci(n - 1, sum++) + Fribonacci(n - 2, sum++);

    }


    public static void umengUappGetChannelData(ApiExecutor apiExecutor) {
        UmengUappGetChannelDataParam param = new UmengUappGetChannelDataParam();
        // 测试环境只支持http
        // param.getOceanRequestPolicy().setUseHttps(false);
        param.setAppkey("5c0a1b6cf1f5561e320000cb");
        param.setDate("2019-01-01");
        param.setPerPage(10);
        param.setPage(1);
        try {
            UmengUappGetChannelDataResult result = apiExecutor.execute(param);
            System.out.println(JSONObject.toJSONString(result));
        } catch (OceanException e) {
            System.out.println("errorCode=" + e.getErrorCode() + ", errorMessage=" + e.getErrorMessage());
        }
    }

    @Test
    public void test27() {
        // 请替换apiKey和apiSecurity
        ApiExecutor apiExecutor = new ApiExecutor("7995983", "TndelMKtgL");
        apiExecutor.setServerHost("gateway.open.umeng.com");
        umengUappGetChannelData(apiExecutor);
    }

    public static void umengUappGetAllAppData(ApiExecutor apiExecutor) {
        UmengUappGetAllAppDataParam param = new UmengUappGetAllAppDataParam();
        // 测试环境只支持http
        // param.getOceanRequestPolicy().setUseHttps(false);

        try {
            UmengUappGetAllAppDataResult result = apiExecutor.execute(param);
            System.out.println(JSONObject.toJSONString(result));
        } catch (OceanException e) {
            System.out.println("errorCode=" + e.getErrorCode() + ", errorMessage=" + e.getErrorMessage());
        }
    }

    @Test
    public void test28() {
        // 请替换apiKey和apiSecurity
        ApiExecutor apiExecutor = new ApiExecutor("7995983", "TndelMKtgL");
        apiExecutor.setServerHost("gateway.open.umeng.com");
        umengUappGetAllAppData(apiExecutor);
    }

    @Test
    public void test29() throws IOException {
        UmengHandler umengHandler=new UmengHandler();
        System.out.println(new Date().getTime());
        String id1="5c2364d9b465f55c0a0001ff";
        String id2="5c0f6972f1f5562c400002aa,5c2364d9b465f55c0a0001ff,5c23717eb465f518550001e9";
        System.out.println(umengHandler.doGet("http://api.umeng.com/retentions?period_type=daily&appkey=5c0a1b6cf1f5561e320000cb&start_date=2019-02-10&end_date=2019-02-11&auth_token=mVAYlcwdFu2hpY8mb7B3&" +
                "channels="+id2));
        System.out.println(new Date().getTime());

    }

    @Test
    public void test30() {
        String string = "[{\"appkey\":\"5c0a1b6cf1f5561e320000cb\",\"category\":\"动作格斗\",\"platform\":\"android\",\"use_game_sdk\":true,\"created_at\":\"2018-12-07T07:04:12Z\",\"updated_at\":\"2019-02-15T09:41:51Z\",\"popular\":0,\"name\":\"拥挤城市\"},{\"appkey\":\"5b6126cea40fa317d100000f\",\"category\":\"休闲趣味\",\"platform\":\"android\",\"use_game_sdk\":true,\"created_at\":\"2018-08-01T03:19:42Z\",\"updated_at\":\"2018-11-10T15:21:03Z\",\"popular\":0,\"name\":\"吃鸡大英雄\"},{\"appkey\":\"5bced6f0b465f5d62d000109\",\"category\":\"休闲趣味\",\"platform\":\"android\",\"use_game_sdk\":true,\"created_at\":\"2018-10-23T08:08:16Z\",\"updated_at\":\"2018-12-22T06:28:54Z\",\"popular\":0,\"name\":\"贪吃大作战\"},{\"appkey\":\"5af2c8458f4a9d617000003f\",\"category\":\"休闲趣味\",\"platform\":\"android\",\"use_game_sdk\":true,\"created_at\":\"2018-05-09T10:07:01Z\",\"updated_at\":\"2018-10-01T02:19:05Z\",\"popular\":0,\"name\":\"保护气球\"},{\"appkey\":\"5b99d5fb8f4a9d4853000043\",\"category\":\"休闲趣味\",\"platform\":\"android\",\"use_game_sdk\":true,\"created_at\":\"2018-09-13T03:14:03Z\",\"updated_at\":\"2018-10-15T06:53:11Z\",\"popular\":0,\"name\":\"一笔画_开心玻璃杯\"},{\"appkey\":\"5b596d78b27b0a3406000066\",\"category\":\"休闲趣味\",\"platform\":\"android\",\"use_game_sdk\":true,\"created_at\":\"2018-07-26T06:43:04Z\",\"updated_at\":\"2018-12-07T05:35:23Z\",\"popular\":0,\"name\":\"致命漂移\"},{\"appkey\":\"5b14e722f43e484c6b00007d\",\"category\":\"小游戏\",\"platform\":\"iphone\",\"use_game_sdk\":true,\"created_at\":\"2018-06-04T07:15:46Z\",\"updated_at\":\"2018-07-26T01:51:35Z\",\"popular\":0,\"name\":\"飞刀射击-ios\"},{\"appkey\":\"5b0e3d69f29d9810b0000113\",\"category\":\"小游戏\",\"platform\":\"iphone\",\"use_game_sdk\":true,\"created_at\":\"2018-05-30T05:58:01Z\",\"updated_at\":\"2018-06-16T06:21:57Z\",\"popular\":0,\"name\":\"保护气球-ios\"},{\"appkey\":\"598290094544cb17150008bf\",\"category\":\"休闲趣味\",\"platform\":\"android\",\"use_game_sdk\":true,\"created_at\":\"2017-08-03T02:52:57Z\",\"updated_at\":\"2018-06-16T06:10:37Z\",\"popular\":0,\"name\":\"疯狂投篮\"},{\"appkey\":\"5acd83daa40fa30518000101\",\"category\":\"休闲趣味\",\"platform\":\"android\",\"use_game_sdk\":true,\"created_at\":\"2018-04-11T03:41:14Z\",\"updated_at\":\"2018-05-03T01:38:15Z\",\"popular\":0,\"name\":\"十字绣\"},{\"appkey\":\"595f3d63a40fa334ab000819\",\"category\":\"益智游戏\",\"platform\":\"android\",\"use_game_sdk\":true,\"created_at\":\"2017-07-07T07:50:59Z\",\"updated_at\":\"2018-04-29T00:34:08Z\",\"popular\":0,\"name\":\"贪吃蛇大战2048\"},{\"appkey\":\"59ad01864544cb11b2000d62\",\"category\":\"休闲趣味\",\"platform\":\"android\",\"use_game_sdk\":true,\"created_at\":\"2017-09-04T07:32:22Z\",\"updated_at\":\"2018-04-29T00:32:37Z\",\"popular\":0,\"name\":\"纽扣的游戏-新版\"},{\"appkey\":\"5a6afd68f29d981a480000dd\",\"category\":\"益智游戏\",\"platform\":\"android\",\"use_game_sdk\":true,\"created_at\":\"2018-01-26T10:05:28Z\",\"updated_at\":\"2018-02-26T14:32:56Z\",\"popular\":0,\"name\":\"全民跳一跳\"},{\"appkey\":\"58ad461b8f4a9d04b7001433\",\"category\":\"休闲娱乐\",\"platform\":\"android\",\"use_game_sdk\":false,\"created_at\":\"2017-02-22T08:04:43Z\",\"updated_at\":\"2018-10-12T17:38:38Z\",\"popular\":0,\"name\":\"八分音符酱-安卓\"},{\"appkey\":\"58bcfb47f43e484db9002210\",\"category\":\"小游戏\",\"platform\":\"iphone\",\"use_game_sdk\":true,\"created_at\":\"2017-03-06T06:01:43Z\",\"updated_at\":\"2018-07-23T03:23:25Z\",\"popular\":0,\"name\":\"贪吃蛇遇上打方块\"},{\"appkey\":\"59573055cae7e75e39002738\",\"category\":\"小游戏\",\"platform\":\"iphone\",\"use_game_sdk\":true,\"created_at\":\"2017-07-01T05:17:09Z\",\"updated_at\":\"2018-12-03T10:38:26Z\",\"popular\":0,\"name\":\"球与白块-ios\"},{\"appkey\":\"58ad48d8717c194ccb000560\",\"category\":\"小游戏\",\"platform\":\"iphone\",\"use_game_sdk\":true,\"created_at\":\"2017-02-22T08:16:24Z\",\"updated_at\":\"2017-08-29T11:54:45Z\",\"popular\":0,\"name\":\"八分音符酱-ios\"},{\"appkey\":\"587edd20cae7e72e25001726\",\"category\":\"小游戏\",\"platform\":\"iphone\",\"use_game_sdk\":true,\"created_at\":\"2017-01-18T03:12:32Z\",\"updated_at\":\"2017-03-08T09:42:00Z\",\"popular\":0,\"name\":\"纽扣的游戏ios版本2017\"},{\"appkey\":\"57a2b19867e58ec20c0038ae\",\"category\":\"智力游戏\",\"platform\":\"iphone\",\"use_game_sdk\":true,\"created_at\":\"2016-08-04T03:08:08Z\",\"updated_at\":\"2018-07-23T03:23:58Z\",\"popular\":0,\"name\":\"六角拼拼IOS\"},{\"appkey\":\"5c6fb4a4b465f5471f00060a\",\"category\":\"休闲趣味\",\"platform\":\"android\",\"use_game_sdk\":true,\"created_at\":\"2019-02-22T08:36:52Z\",\"updated_at\":\"2019-02-22T08:36:52Z\",\"popular\":0,\"name\":\"割草大作战\"}]\n";
        JSONArray jsonArray = JSONArray.parseArray(string);
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = JSONObject.parseObject(String.valueOf(jsonArray.get(i)));
            String name = jsonObject.getString("name");
            System.out.println(name);
        }
        System.out.println(jsonArray.size());
    }


    @Test
    public void test31() throws Exception {
        UmengHandler umengHandler=new UmengHandler();
        String asd = "545";
        List<JsonObject> list = new ArrayList<>();
        String date = "2019-02-01";
        List<String> list1 = Arrays.asList("2019-02-01", "2019-02-02", "2019-02-03", "2019-02-04", "2019-02-05", "2019-02-06", "2019-02-07");
        //String string="[{\"appkey\":\"5c0a1b6cf1f5561e320000cb\",\"category\":\"动作格斗\",\"platform\":\"android\",\"use_game_sdk\":true,\"created_at\":\"2018-12-07T07:04:12Z\",\"updated_at\":\"2019-02-15T09:41:51Z\",\"popular\":0,\"name\":\"拥挤城市\"},{\"appkey\":\"5b6126cea40fa317d100000f\",\"category\":\"休闲趣味\",\"platform\":\"android\",\"use_game_sdk\":true,\"created_at\":\"2018-08-01T03:19:42Z\",\"updated_at\":\"2018-11-10T15:21:03Z\",\"popular\":0,\"name\":\"吃鸡大英雄\"},{\"appkey\":\"5bced6f0b465f5d62d000109\",\"category\":\"休闲趣味\",\"platform\":\"android\",\"use_game_sdk\":true,\"created_at\":\"2018-10-23T08:08:16Z\",\"updated_at\":\"2018-12-22T06:28:54Z\",\"popular\":0,\"name\":\"贪吃大作战\"},{\"appkey\":\"5af2c8458f4a9d617000003f\",\"category\":\"休闲趣味\",\"platform\":\"android\",\"use_game_sdk\":true,\"created_at\":\"2018-05-09T10:07:01Z\",\"updated_at\":\"2018-10-01T02:19:05Z\",\"popular\":0,\"name\":\"保护气球\"},{\"appkey\":\"5b99d5fb8f4a9d4853000043\",\"category\":\"休闲趣味\",\"platform\":\"android\",\"use_game_sdk\":true,\"created_at\":\"2018-09-13T03:14:03Z\",\"updated_at\":\"2018-10-15T06:53:11Z\",\"popular\":0,\"name\":\"一笔画_开心玻璃杯\"},{\"appkey\":\"5b596d78b27b0a3406000066\",\"category\":\"休闲趣味\",\"platform\":\"android\",\"use_game_sdk\":true,\"created_at\":\"2018-07-26T06:43:04Z\",\"updated_at\":\"2018-12-07T05:35:23Z\",\"popular\":0,\"name\":\"致命漂移\"},{\"appkey\":\"5b14e722f43e484c6b00007d\",\"category\":\"小游戏\",\"platform\":\"iphone\",\"use_game_sdk\":true,\"created_at\":\"2018-06-04T07:15:46Z\",\"updated_at\":\"2018-07-26T01:51:35Z\",\"popular\":0,\"name\":\"飞刀射击-ios\"},{\"appkey\":\"5b0e3d69f29d9810b0000113\",\"category\":\"小游戏\",\"platform\":\"iphone\",\"use_game_sdk\":true,\"created_at\":\"2018-05-30T05:58:01Z\",\"updated_at\":\"2018-06-16T06:21:57Z\",\"popular\":0,\"name\":\"保护气球-ios\"},{\"appkey\":\"598290094544cb17150008bf\",\"category\":\"休闲趣味\",\"platform\":\"android\",\"use_game_sdk\":true,\"created_at\":\"2017-08-03T02:52:57Z\",\"updated_at\":\"2018-06-16T06:10:37Z\",\"popular\":0,\"name\":\"疯狂投篮\"},{\"appkey\":\"5acd83daa40fa30518000101\",\"category\":\"休闲趣味\",\"platform\":\"android\",\"use_game_sdk\":true,\"created_at\":\"2018-04-11T03:41:14Z\",\"updated_at\":\"2018-05-03T01:38:15Z\",\"popular\":0,\"name\":\"十字绣\"},{\"appkey\":\"595f3d63a40fa334ab000819\",\"category\":\"益智游戏\",\"platform\":\"android\",\"use_game_sdk\":true,\"created_at\":\"2017-07-07T07:50:59Z\",\"updated_at\":\"2018-04-29T00:34:08Z\",\"popular\":0,\"name\":\"贪吃蛇大战2048\"},{\"appkey\":\"59ad01864544cb11b2000d62\",\"category\":\"休闲趣味\",\"platform\":\"android\",\"use_game_sdk\":true,\"created_at\":\"2017-09-04T07:32:22Z\",\"updated_at\":\"2018-04-29T00:32:37Z\",\"popular\":0,\"name\":\"纽扣的游戏-新版\"},{\"appkey\":\"5a6afd68f29d981a480000dd\",\"category\":\"益智游戏\",\"platform\":\"android\",\"use_game_sdk\":true,\"created_at\":\"2018-01-26T10:05:28Z\",\"updated_at\":\"2018-02-26T14:32:56Z\",\"popular\":0,\"name\":\"全民跳一跳\"},{\"appkey\":\"58ad461b8f4a9d04b7001433\",\"category\":\"休闲娱乐\",\"platform\":\"android\",\"use_game_sdk\":false,\"created_at\":\"2017-02-22T08:04:43Z\",\"updated_at\":\"2018-10-12T17:38:38Z\",\"popular\":0,\"name\":\"八分音符酱-安卓\"},{\"appkey\":\"58bcfb47f43e484db9002210\",\"category\":\"小游戏\",\"platform\":\"iphone\",\"use_game_sdk\":true,\"created_at\":\"2017-03-06T06:01:43Z\",\"updated_at\":\"2018-07-23T03:23:25Z\",\"popular\":0,\"name\":\"贪吃蛇遇上打方块\"},{\"appkey\":\"59573055cae7e75e39002738\",\"category\":\"小游戏\",\"platform\":\"iphone\",\"use_game_sdk\":true,\"created_at\":\"2017-07-01T05:17:09Z\",\"updated_at\":\"2018-12-03T10:38:26Z\",\"popular\":0,\"name\":\"球与白块-ios\"},{\"appkey\":\"58ad48d8717c194ccb000560\",\"category\":\"小游戏\",\"platform\":\"iphone\",\"use_game_sdk\":true,\"created_at\":\"2017-02-22T08:16:24Z\",\"updated_at\":\"2017-08-29T11:54:45Z\",\"popular\":0,\"name\":\"八分音符酱-ios\"},{\"appkey\":\"587edd20cae7e72e25001726\",\"category\":\"小游戏\",\"platform\":\"iphone\",\"use_game_sdk\":true,\"created_at\":\"2017-01-18T03:12:32Z\",\"updated_at\":\"2017-03-08T09:42:00Z\",\"popular\":0,\"name\":\"纽扣的游戏ios版本2017\"},{\"appkey\":\"57a2b19867e58ec20c0038ae\",\"category\":\"智力游戏\",\"platform\":\"iphone\",\"use_game_sdk\":true,\"created_at\":\"2016-08-04T03:08:08Z\",\"updated_at\":\"2018-07-23T03:23:58Z\",\"popular\":0,\"name\":\"六角拼拼IOS\"},{\"appkey\":\"5c6fb4a4b465f5471f00060a\",\"category\":\"休闲趣味\",\"platform\":\"android\",\"use_game_sdk\":true,\"created_at\":\"2019-02-22T08:36:52Z\",\"updated_at\":\"2019-02-22T08:36:52Z\",\"popular\":0,\"name\":\"割草大作战\"}]\n";
        String string = umengHandler.doGet("http://api.umeng.com/apps?auth_token=mVAYlcwdFu2hpY8mb7B3");
        JSONArray jsonArray = JSONArray.parseArray(string);
        for (int j = 0; j < list1.size(); j++) {
            date = list1.get(j);
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = JSONObject.parseObject(String.valueOf(jsonArray.get(i)));
                String platform = jsonObject.getString("platform");
                if (!platform.equals("android")) {
                    continue;
                }
                String name = jsonObject.getString("name");
                String appkey = jsonObject.getString("appkey");
                String response = null;
                synchronized (asd) {
                    response = umengHandler.doGet("http://api.umeng.com/channels?appkey=" + appkey + "&auth_token=mVAYlcwdFu2hpY8mb7B3&date=" + date);
                    JsonObject jsonObject1 = new JsonObject().put("app_name", name).put("list", response);
                    list.add(jsonObject1);
                }

            }
        }
        System.out.println(list);
    }


    @Test
    public void test32(){
        String string="14857.0";
        System.out.println(string.substring(0,string.length()-1));
    }

}