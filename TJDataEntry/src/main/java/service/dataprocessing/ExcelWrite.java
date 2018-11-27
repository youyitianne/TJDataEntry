package service.dataprocessing;

import http.HttpServerVerticle;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

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
    public void writeall(String filePath, int x, int y, List<List> value) {
        try {
// 创建Excel的工作书册 Workbook,对应到一个excel文档
            HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(filePath));
            HSSFSheet sheet = wb.getSheetAt(0);
            HSSFCell cell = null;
            FileOutputStream os = null;

            for (int i = 0; i < value.size(); i++) {
                List stringList=value.get(i);
                for (int j=0;j<stringList.size();j++) {
                    cell = setcell(sheet, x + j, y+i, String.valueOf(stringList.get(j)));
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
}
