package com.core.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ExcelRead {
    private Logger logger= LoggerFactory.getLogger(ExcelRead.class.getName());

    /**
     * 根据路径读取excel   .xlsx
     * @param path excel所在路径
     * @return
     * 返回双层list ，
     * 按行读取
     */
    public List<List> readExcel(String path){
        FileInputStream fiStream=null;
        Workbook wb=null;
        try {
            File excel = new File(path);
            fiStream= new FileInputStream(excel);   //文件流对象
            //根据文件后缀（xls/xlsx）进行判断
            try {
                wb = new HSSFWorkbook(fiStream);
            }catch (Exception e){
                wb = new XSSFWorkbook(new FileInputStream(excel));
            }
            //开始解析
            Sheet sheet = wb.getSheetAt(0);     //读取sheet 0
            int firstRowIndex = sheet.getFirstRowNum() + 1;   //第一行是列名，所以不读
            int lastRowIndex = sheet.getLastRowNum();
             ArrayList outerList = new ArrayList();
            for (int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex++) {   //遍历行
                Row row = sheet.getRow(rIndex);
                if (row != null) {
                    int firstCellIndex = row.getFirstCellNum();
                    int lastCellIndex = row.getLastCellNum();
                    ArrayList inner = new ArrayList();
                    for (int cIndex = firstCellIndex; cIndex < lastCellIndex; cIndex++) {   //遍历列
                        Cell cell = row.getCell(cIndex);
                        if (cell != null) {
                            String value= cell.toString();
                            inner.add(cIndex,value);
                        }
                    }
                    outerList.add(rIndex-1,inner);
                }
            }
            return outerList;
        }catch (FileNotFoundException e){
            e.printStackTrace();
            logger.error("找不到该文件:" + e);
        }catch (IOException e){
            e.printStackTrace();
            logger.error("读取文件失败:" + e);
        }finally {
            try {
                if (wb!=null){
                    wb.close();
                }
                if (fiStream!=null){
                    fiStream.close();
                }
            }catch (IOException e){
                e.printStackTrace();
                logger.error("关闭文件失败:" + e);
            }
        }
        return null;
    }

    /**
     * 按行读取excel .csv
     * @param path 文件路径
     * @return
     */
    public List<List> readUser(String path) {
        String old = "";
        BufferedReader reader = null;
        ArrayList outerlist=new ArrayList();
        try {
            DataInputStream in = new DataInputStream(new FileInputStream(new File(path)));
            reader = new BufferedReader(new InputStreamReader(in, "utf-16"));
            reader.readLine();
            String line = null;
            int index=0;
            while ((line = reader.readLine()) != null) {
                //日趋势变化，有长度为1的空行，跳过
                if (line.length() == 1) {
                    continue;
                }
                String item[];
                item = line.split("\t");
                ArrayList innerlist=new ArrayList();
                for (int i = 0; i < item.length; i++) {
                    String value = item[i].trim();
                    innerlist.add(value);
                }
                outerlist.add(index,innerlist);
                index++;
            }
            return outerlist;
        } catch (FileNotFoundException e) {
            logger.error("找到该文件:"+e);
        }catch (IOException e) {
            logger.error("读取文件失败:"+e);
        }catch (Exception e) {
            logger.error("未知错误:"+e);
        }finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                logger.error("关闭BufferedReader失败:"+e);
            }
        }
        return null;
    }

    /**
     * 按行读取excel
     * @param path 文件路径
     * @param charset 字符集
     * @return
     */
    public List<List> readGuang(String path,String charset){
        String old = "";
        BufferedReader reader = null;
        try {
            DataInputStream in = new DataInputStream(new FileInputStream(new File(path)));
            reader = new BufferedReader(new InputStreamReader(in, charset));
            reader.readLine();
            String line = null;
            ArrayList outerlist = new ArrayList();
            int index = 0;
            while ((line = reader.readLine()) != null) {
                //日趋势变化，有长度为1的空行，跳过
                if (line.length() == 1) {
                    continue;
                }
                String item[];
                item = line.split(",");
                ArrayList innerlist = new ArrayList();
                for (int i = 0; i < item.length; i++) {
                    String value = item[i].trim();
                    innerlist.add(value);
                }
                outerlist.add(index, innerlist);
                index++;
            }
            return outerlist;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            logger.error("找到该文件:" + e);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("读取文件失败:" + e);
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                logger.error("关闭BufferedReader失败:" + e);
            }
        }
        return null;
    }


}
