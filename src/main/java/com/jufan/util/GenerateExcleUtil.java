package com.jufan.util;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

/**
 * @Author pengyd
 * @Date 2018/7/17 11:23
 * @function:   封装生成Excle
 */
public class GenerateExcleUtil {

    /**
     *  传入产品调用的List 商户名称 查询的年月  生成Excle
     * @param merchantCount
     */
    public static void creatExcle(List<Map<String,Object>> merchantCount , String merchantName ,String date){
        //生成Excle
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(merchantName+"账单");
        //创建第一栏
        HSSFRow headRow = sheet.createRow(0);
        String[] titleArray = new String[20];
        titleArray[0] = "产品名称";
        titleArray[1] = "产品调用量";
        titleArray[2] = "产品价格";
        titleArray[3] = "产品调用费用总和";
        DoExcelUtil.doExcel(titleArray, headRow, workbook, sheet);

        //写入第一表格数据  判断该商户本月是否有调用
        int num1 = 0;
        if(merchantCount == null){
            HSSFRow row = sheet.createRow(num1 + 1);
            row.createCell(1);
            row.getCell(1).setCellValue("暂无数据");
        }else {
            int index = 0;
            //写入产品调用详情
            Double accountPrice = 0D;
            for (Map productMap : merchantCount) {
                HSSFRow row = sheet.createRow(index + 1);
                for (int n = 0; n <= 3; n++) {
                    row.createCell(n);
                }
                //写入一行
                row.getCell(0).setCellValue(productMap.get("productName").toString());
                row.getCell(1).setCellValue(productMap.get("num").toString());
                row.getCell(2).setCellValue(productMap.get("productPrice").toString());
                row.getCell(3).setCellValue(productMap.get("totalMonthPrice").toString());
                accountPrice+=(Double) productMap.get("totalMonthPrice");
                index++;
            }
            //最后一行写入 总计
            HSSFRow row = sheet.createRow(index + 1);
            for (int n = 0; n <= 1; n++) {
                row.createCell(n);
            }
            row.getCell(0).setCellValue("费用总和");
            row.getCell(1).setCellValue(accountPrice);
        }


        String path = "";
        try {
            path = "H:\\"+date+merchantName+"账单.xls";
            File file = new File(path);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            workbook.write(fileOutputStream);
            fileOutputStream.close();
            JOptionPane.showMessageDialog(null, "下载完成！", "提示", JOptionPane.WARNING_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
