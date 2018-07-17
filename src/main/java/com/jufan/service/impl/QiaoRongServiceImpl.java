package com.jufan.service.impl;

import com.google.common.collect.Maps;
import com.jufan.dao.QiaoRongDao;
import com.jufan.service.MerchantAccountService;
import com.jufan.service.QiaoRongService;
import com.jufan.util.DoExcelUtil;
import com.jufan.util.GenerateExcleUtil;
import com.jufan.util.MultipleDataSource;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author pengyd
 * @Date 2018/7/11 14:48
 * @function:
 */
@Service
public class QiaoRongServiceImpl implements QiaoRongService {

    @Resource
    private QiaoRongDao qiaoRongDao;
    @Resource
    private MerchantAccountService merchantAccountService;


    public List<Map<String, Object>> selectQiaoRongCount(String data) {
        System.out.println("!!!!!!!!!!!!");
        List<Map<String, Object>> merchantCount = merchantAccountService.getMerchantCountByOrgId("");

        //切换到risk库中
        MultipleDataSource.setDataSourceKey("dataSource_risk");

        //获取当前月份中上一个月的 年月
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MONTH, -1);
        Date m = c.getTime();
        String mon = format.format(m);

        //计算risk库中 乔融的 同盾调用量
        String num = qiaoRongDao.selectRiskCount(mon);
        Map<String,Object> map = Maps.newHashMap();
        map.put("productName", "risk中同盾");
        map.put("productPrice", "0.4");
        map.put("num", num);
        Double countPri = Integer.parseInt(num)*0.4;
        map.put("totalMonthPrice", countPri);

        merchantCount.add(map);

        //生成Excle
        GenerateExcleUtil.creatExcle(merchantCount,"乔融",mon);


//        //生成Excle
//        HSSFWorkbook workbook = new HSSFWorkbook();
//        HSSFSheet sheet = workbook.createSheet("乔融账单");
//        //创建第一栏
//        HSSFRow headRow = sheet.createRow(0);
//        String[] titleArray = new String[20];
//        titleArray[0] = "产品名称";
//        titleArray[1] = "产品调用量";
//        titleArray[2] = "产品价格";
//        titleArray[3] = "产品调用费用总和";
//        DoExcelUtil.doExcel(titleArray, headRow, workbook, sheet);
//
//        //写入第一表格数据  判断该商户本月是否有调用
//        int num1 = 0;
//        if(merchantCount == null){
//            HSSFRow row = sheet.createRow(num1 + 1);
//            row.createCell(1);
//            row.getCell(1).setCellValue("暂无数据");
//        }else {
//            int index = 0;
//            //写入产品调用详情
//            Double accountPrice = 0D;
//            for (Map productMap : merchantCount) {
//                HSSFRow row = sheet.createRow(index + 1);
//                for (int n = 0; n <= 3; n++) {
//                    row.createCell(n);
//                }
//                //写入一行
//                row.getCell(0).setCellValue(productMap.get("productName").toString());
//                row.getCell(1).setCellValue(productMap.get("num").toString());
//                row.getCell(2).setCellValue(productMap.get("productPrice").toString());
//                row.getCell(3).setCellValue(productMap.get("totalMonthPrice").toString());
//                accountPrice+=(Double) productMap.get("totalMonthPrice");
//                index++;
//            }
//            //最后一行写入 总计
//            HSSFRow row = sheet.createRow(index + 1);
//            for (int n = 0; n <= 1; n++) {
//                row.createCell(n);
//            }
//            row.getCell(0).setCellValue("费用总和");
//            row.getCell(1).setCellValue(accountPrice);
//        }
//
//
//        String path = "";
//        try {
//            path = "H:\\"+mon+"乔融账单.xls";
//            File file = new File(path);
//            FileOutputStream fileOutputStream = new FileOutputStream(file);
//            workbook.write(fileOutputStream);
//            fileOutputStream.close();
//            JOptionPane.showMessageDialog(null, "下载完成！", "提示", JOptionPane.WARNING_MESSAGE);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        return merchantCount;
    }



}
