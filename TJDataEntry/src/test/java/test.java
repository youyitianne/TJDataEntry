import io.vertx.core.json.JsonObject;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.Test;
import service.dataprocessing.ExcelRead;
import service.dataprocessing.ExcelWrite;
import service.entity.AdData;
import service.pubmethod.InitConf;
import service.pubmethod.Judgement;
import service.pubmethod.Transform;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class test {
    @Test
    public void test1(){
        ExcelRead excelRead=new ExcelRead();
        List list=excelRead.readGuang("file-uploads/360_视频_运营报表20181020-20181021 视.csv","gbk");
        System.out.println(list);
    }

    @Test
    public void test4(){
        ExcelRead excelRead=new ExcelRead();
        List list=excelRead.readExcel("file-uploads/联想_横幅.xlsx");
        System.out.println(list);
    }

    @Test
    public void test5() throws Exception{
        String date="2018/8/8";
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
        Date date1=sdf.parse(date);
        SimpleDateFormat sdf11=new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(sdf11.format(date1));
    }

    @Test
    public void test6() throws Exception{
        Integer val=Transform.lenovotransForMill("22-月-2018");
        System.out.println(val);
    }

    @Test
    public void test2(){
        ExcelRead excelRead=new ExcelRead();
        List list=excelRead.readGuang("file-uploads/2018-11-18 至 2018-11-18 阿里游戏SSP平台-广告位数据报表.csv","gbk");
        System.out.println(list.toString());
    }

    @Test
    public void test3(){
        ExcelRead excelRead=new ExcelRead();
        List list=excelRead.readExcel("file-uploads/2018-11-01至2018-11-27_数据报表.xls");
        System.out.println(list);
    }

    @Test
    public void test7(){
        ExcelRead excelRead=new ExcelRead();
        List list=excelRead.readGuang("file-uploads/2018-11-18 至 2018-11-18 阿里游戏SSP平台-广告位数据报表.csv","gbk");
        System.out.println(list);
    }

    @Test
    public void test8(){
        Integer time=Transform.transForMilliSecondByTim("2018"+String.valueOf("11月1日"), "yyyyMM月dd日");
        System.out.println(Transform.transForDate(time));
    }

    @Test
    public void test9(){
        Integer time=Transform.transForMilliSecondByTim("2018/11/1", "yyyy/MM/dd");
        System.out.println(time);
    }

    @Test
    public void test10(){
        Date date1=new Date();
        date1.setTime(1544803200000L);
        String date=new SimpleDateFormat("yyyy-MM-dd").format(date1);
        System.out.println(date);
    }

    @Test
    public void test11(){
        ExcelRead excelRead=new ExcelRead();
        List list=excelRead.readGuang("file-uploads/数据查询 (1).csv","gbk");
        System.out.println(list);
    }

    @Test
    public void test12() throws Exception{
       String string="2019-01-03";
        string=string.replace(string.split("-")[2],"01");
        System.out.println(string);
    }

    @Test
    public void test13() throws Exception{
        String path="http://192.168.1.144:8091/arpufile/2018-12-12_2018-12-13_保护气球";
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
    public void test14() throws Exception{
        ExcelRead excelRead=new ExcelRead();
        List list=excelRead.readExcel("file-uploads/头条.xlsx");
        System.out.println(list);
    }

    @Test
    public void test15() throws Exception{
        String intw = "\"60.0\"";
        intw=intw.replace("\"","");
        System.out.println(intw);
    }

    @Test
    public void test16() throws Exception{
        try {
            FileInputStream is = new FileInputStream("file-uploads/2.xls");
            HSSFWorkbook workbook = new HSSFWorkbook(is);
            HSSFSheet sheet = workbook.getSheetAt(0);
            ArrayList<Integer> num = new ArrayList<Integer>(){{
                add(2);
                add(3);
                add(4);
                add(5);
            }};
            for (int i=0;i<num.size();i++){
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
    public void test17(){
        String starttime="55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77";
        String[] num=starttime.split(",");
        ArrayList<Integer> arrayList=new ArrayList();
        for(int i=0;i<num.length;i++){
            arrayList.add(Integer.valueOf(num[i]));
        }
        System.out.println(arrayList);
    }

    @Test
    public void test18(){
        ExcelRead excelRead=new ExcelRead();
        List list=excelRead.readExcel("file-uploads/九游例子.xls");
        System.out.println(list.toString());
    }

    @Test
    public void test19(){
        List<Integer> integerList = Arrays.asList(4, 5, 2, 3, 7, 9);
        Collections.sort(integerList);  //升序
        //collect.sort(Comparator.reverseOrder()); //倒序
        //collect.forEach(System.out::println);
        System.out.println(integerList);
    }

    @Test
    public void test20(){
        List<Integer> integerList = Arrays.asList(4, 5, 2, 3, 7, 9);
        for (int i=0;i<integerList.size();i++){
            System.out.println(i);
            if (integerList.get(i).equals(3)){
                return;
            }
        }
    }


    @Test
    public void test21(){
        ExcelRead excelRead=new ExcelRead();
        List list=excelRead.readGuang("file-uploads/4399.csv","gbk");
        System.out.println(list.toString());
    }

}