package com.lyp360.utils;

import com.lyp360.entity.Dictionary;
import com.lyp360.entity.Insurance;
import com.lyp360.service.IDictionaryService;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

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

    public static HSSFWorkbook writeInstanceToSheet(List<Insurance> list, Map<String, String> dicMap) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("codes");
        HSSFRow row = null;
        HSSFCell cell = null;
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sf1 = new SimpleDateFormat("yyyy-MM-dd");
        String[] headers = {"主键", "授权码", "品牌", "型号", "IMEI码", "智保卡号", "顾客姓名", "顾客生日",
                "顾客邮箱", "手机号码", "门店", "门店办理人", "激活时间", "状态", "备注", "省份", "城市", "供应商", "身份证号"};
        HSSFRow headerRow = sheet.createRow(0);
        for (int i=0; i<headers.length; i++){
            headerRow.createCell(i).setCellValue(headers[i]);
        }
        int rowNum = 1;
        for (Insurance insurance : list) {
            row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(insurance.getId());
            row.createCell(1).setCellValue(insurance.getCertificateCode());
            row.createCell(2).setCellValue(dicMap.get(insurance.getTelBrand()));
            row.createCell(3).setCellValue(dicMap.get(insurance.getTelModel()));
            row.createCell(4).setCellValue(insurance.getTelIMEI());
            row.createCell(5).setCellValue(insurance.getCard_num());
            row.createCell(6).setCellValue(insurance.getCustomerName());
            String birthday = insurance.getCustomerBirthday()==null?"":sf1.format(insurance.getCustomerBirthday());
            row.createCell(7).setCellValue(birthday);
            row.createCell(8).setCellValue(insurance.getCustomerEmail());
            row.createCell(9).setCellValue(insurance.getMobileNumber());
            row.createCell(10).setCellValue(dicMap.get(insurance.getStore()));
            row.createCell(11).setCellValue(insurance.getStoreTransactor());
            row.createCell(12).setCellValue(sf.format(insurance.getCreateTime()));
            row.createCell(13).setCellValue(dicMap.get(insurance.getStatus()));
            row.createCell(14).setCellValue(insurance.getMark());
            row.createCell(15).setCellValue(dicMap.get(insurance.getProvince()));
            row.createCell(16).setCellValue(dicMap.get(insurance.getCity()));
            row.createCell(17).setCellValue(dicMap.get(insurance.getProvider()));
            row.createCell(18).setCellValue(insurance.getIdCardNo());
        }
        return workbook;
    }
}
