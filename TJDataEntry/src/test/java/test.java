import org.junit.Test;
import service.dataprocessing.ExcelRead;
import service.pubmethod.Judgement;
import service.pubmethod.Transform;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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



}