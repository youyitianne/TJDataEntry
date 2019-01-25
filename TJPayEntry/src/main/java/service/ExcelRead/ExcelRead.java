package service.ExcelRead;

import io.vertx.core.Vertx;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelRead {
    Logger logger= LoggerFactory.getLogger(ExcelRead.class);


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

}
