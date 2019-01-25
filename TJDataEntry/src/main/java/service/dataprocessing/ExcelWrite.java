package service.dataprocessing;

import http.HttpServerVerticle;
import net.sf.jxls.util.Util;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.List;

import static io.netty.handler.codec.http.websocketx.WebSocketScheme.WS;

public class ExcelWrite {
    private Logger logger = LoggerFactory.getLogger(ExcelWrite.class.getName());

    /**
     * list 写入excel
     *
     * @param filePath
     * @param x
     * @param y
     * @param value
     */
    public void writeall(String filePath,Integer n, int x, int y, List<List> value,String name) {
        try {
// 创建Excel的工作书册 Workbook,对应到一个excel文档
            HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(filePath));
            wb.setSheetName(n,name);
            HSSFSheet newsheet=wb.createSheet();
            copySheet(wb.getSheetAt(0),newsheet);
            HSSFSheet sheet = wb.getSheetAt(n);
            HSSFCell cell = null;
            HSSFCellStyle style = wb.createCellStyle();
            FileOutputStream os = null;
            for (int i = 0; i < value.size(); i++) {
                List stringList=value.get(i);
                for (int j=0;j<stringList.size();j++) {
                    cell = setcell(sheet, x + j, y+i, String.valueOf(stringList.get(j)));
                    //样式设置
                    HSSFRow row = sheet.getRow(x + j);
                    if (row == null) {
                        row = sheet.createRow(x + j);
                    }
                    HSSFCell newcell = row.getCell(y+i-1);
                    if (cell == null) {
                        cell = row.createCell(y+i-1);
                    }
                    style=newcell.getCellStyle();
                    cell.setCellStyle(style);
                }
            }
            os = new FileOutputStream(filePath);
            wb.write(os);
            if (os != null) {
                os.close();
            }
        } catch (Exception e) {
            logger.error("内容写入错误", e);
        }
    }

    /**
     * list 写入excel
     *
     * @param filePath
     * @param x
     * @param y
     * @param value
     */
    public void writeall2(String filePath,Integer n, int x, int y, List<List> value,String name) {
        try {
// 创建Excel的工作书册 Workbook,对应到一个excel文档
            HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(filePath));
            wb.setSheetName(n,name);
            HSSFSheet sheet = wb.getSheetAt(n);
            HSSFCell cell = null;
            HSSFCellStyle style = wb.createCellStyle();
            FileOutputStream os = null;
            for (int i = 0; i < value.size(); i++) {
                List stringList=value.get(i);
                for (int j=0;j<stringList.size();j++) {
                    cell = setcell(sheet, x + j, y+i, String.valueOf(stringList.get(j)));
                    //样式设置
                    HSSFRow row = sheet.getRow(x + j);
                    if (row == null) {
                        row = sheet.createRow(x + j);
                    }
                    HSSFCell newcell = row.getCell(y+i-1);
                    if (cell == null) {
                        cell = row.createCell(y+i-1);
                    }
                    style=newcell.getCellStyle();
                    cell.setCellStyle(style);
                }
            }
            os = new FileOutputStream(filePath);
            wb.write(os);
            if (os != null) {
                os.close();
            }
        } catch (Exception e) {
            logger.error("内容写入错误", e);
        }
    }


    /**
     * copy all rows include styles from source sheet to target sheet
     * @param srcSheet
     * @param targetSheet
     */
    public static void copySheet(HSSFSheet srcSheet, HSSFSheet targetSheet){
        Iterator it = srcSheet.rowIterator();
        while(it.hasNext()){
            HSSFRow srcRow = (HSSFRow) it.next();
            Util.copyRow(srcSheet, targetSheet, srcRow, targetSheet.createRow(srcRow.getRowNum()));
        }
    }



    /**
     * list 写入excel
     *
     * @param filePath
     * @param x
     * @param y
     * @param value
     */
    public void writeall1(String filePath, int x, int y, List<List> value) {
        try {
// 创建Excel的工作书册 Workbook,对应到一个excel文档
            XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(filePath));
            XSSFSheet sheet = wb.getSheetAt(0);
            XSSFCell cell = null;
            XSSFCellStyle style = wb.createCellStyle();
            FileOutputStream os = null;
            for (int i = 0; i < value.size(); i++) {
                List stringList=value.get(i);
                for (int j=0;j<stringList.size();j++) {
                    cell = setcell1(sheet, x + j, y+i, String.valueOf(stringList.get(j)));

                    //样式设置
                    XSSFRow row = sheet.getRow(x + j);
                    if (row == null) {
                        row = sheet.createRow(x + j);
                    }
                    XSSFCell newcell = row.getCell(y+i-1);
                    if (cell == null) {
                        cell = row.createCell(y+i-1);
                    }
                    style=newcell.getCellStyle();
                    cell.setCellStyle(style);
                }
            }
            os = new FileOutputStream(filePath);
            wb.write(os);
            if (os != null) {
                os.close();
            }
        } catch (Exception e) {
            logger.error("内容写入错误", e);
        }
    }


    /**
     * 设置某一单元格的值
     *
     * @param sheet 选择工作表
     * @param x     x坐标
     * @param y     y坐标
     * @param value 值
     * @return
     */
    private HSSFCell setcell(HSSFSheet sheet, int x, int y, String value) {
        HSSFRow row = sheet.getRow(x);
        if (row == null) {
            row = sheet.createRow(x);
        }
        HSSFCell cell = row.getCell(y);
        if (cell == null) {
            cell = row.createCell(y);
        }
        cell.setCellValue(value);
        return cell;
    }

    /**
     * 设置某一单元格的值
     *
     * @param sheet 选择工作表
     * @param x     x坐标
     * @param y     y坐标
     * @param value 值
     * @return
     */
    private XSSFCell setcell1(XSSFSheet sheet, int x, int y, String value) {
        XSSFRow row = sheet.getRow(x);
        if (row == null) {
            row = sheet.createRow(x);
        }
        XSSFCell cell = row.getCell(y);
        if (cell == null) {
            cell = row.createCell(y);
        }
        cell.setCellValue(value);
        return cell;
    }


}
