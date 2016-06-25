package com.lyp360.utils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;

/**
 * Created by junhao on 2016/6/25.
 */
public class PoiUtils {

    public static HSSFWorkbook writeCodeToSheet(List<String> codes) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("codes");
        HSSFRow row = null;
        HSSFCell cell = null;
        int rowNum = 0;
        for (String code : codes) {
            row = sheet.createRow(rowNum++);
            cell = row.createCell(0);
            cell.setCellValue("赤膜王激活码");
            cell = row.createCell(1);
            cell.setCellValue(code);
        }
        return workbook;
    }
}
