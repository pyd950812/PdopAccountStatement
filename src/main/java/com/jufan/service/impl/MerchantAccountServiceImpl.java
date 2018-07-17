package com.jufan.service.impl;

import com.google.common.collect.Maps;
import com.jufan.dao.MerchantAccountDao;
import com.jufan.service.MerchantAccountService;
import com.jufan.service.TableManagerService;
import com.jufan.util.DoExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class MerchantAccountServiceImpl implements MerchantAccountService {

    @Autowired
    private MerchantAccountDao merchantAccountDao;
    @Autowired
    private TableManagerService tableManagerService;

    /**
     * 这里将会在每个月初调用此方法,查询上个月的账单 查询每个商户每个月的产品调用次数。
     *
     * @param orgId 商户Id
     */
    public List<Map<String, Object>> getMerchantCountByOrgId(String orgId) {
        //获取前一个月的表名
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Date date = new Date();
        date.setMonth(date.getMonth());
        String startDate = sdf.format(date);
        String jfTableName = "pdop_data_jfext_" + startDate;
        String queryTableName = "pdop_data_queryext_" + startDate;
        Map map = new HashMap();
        map.put("jfTableName", jfTableName);
        map.put("queryTableName", queryTableName);
        map.put("orgId", orgId);

        //用于存放这个商户所有产品的调用信息
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {

            Boolean j = tableManagerService.checkTable(jfTableName);
            Boolean q = tableManagerService.checkTable(queryTableName);
            if (j == true && q == true) {
                List<Map<String, Object>> mapList = merchantAccountDao.getAccountDetailByOrgId(map);
                if (mapList.size() > 0) {

                    //遍历每个商户调用每个产品的次数
                    for (Map<String, Object> result : mapList) {
                        System.out.println(result);
                        if (result.containsKey("product_id") && result.containsKey("num")) {
                            String productId = result.get("product_id") != null ? result.get("product_id").toString() : "";//产品ID
                            String count = result.get("num").toString();//调用次数
                            Integer num = Integer.parseInt(count);
                            if (productId != null && productId != "" && count != "" && count != null) {
                                Map<String, Object> productMap = merchantAccountDao.getProductNameById(productId);
                                if (productMap.containsKey("name") && productMap.containsKey("price")) {
                                    String productName = productMap.get("name").toString();//产品名称
                                    String price = productMap.get("price").toString();//产品调用价格
                                    //价格类型要转化
                                    Double productPrice = Double.parseDouble(price);
                                    Double totalMonthPrice = productPrice * num;
                                    System.out.println("产品名:" + productName + " ====价格：" + productPrice + " ===单个产品的月调用次数:" + num + " ===单个产品的月总账单:" + totalMonthPrice);

                                    //将商户的产品调用量、价格、总账单等
                                    Map proMap = Maps.newHashMap();
                                    proMap.put("productName", productName);
                                    proMap.put("productPrice", productPrice);
                                    proMap.put("num", num);
                                    proMap.put("totalMonthPrice", totalMonthPrice);
                                    list.add(proMap);
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("================================查询异常");
            e.printStackTrace();
        }
        return list;
    }


    /**
     * 将商户的信息生成Excle  入参商户Id
     */
    public void buildAccountExcelByOrgId(String id) {
        //获取本月上一个月的账单数据
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MONTH, -1);
        Date m = c.getTime();
        String mon = format.format(m);

        //该商户所有的产品调用详情
        List<Map<String, Object>> allProductDetails = getMerchantCountByOrgId(id);
        //获取商户名称
        String merchantName = merchantAccountDao.getMerchantNameById(id);
        //生成Excle
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(merchantName+"的账单");
        //创建第一栏
        HSSFRow headRow = sheet.createRow(0);
        String[] titleArray = new String[20];
        titleArray[0] = "产品名称";
        titleArray[1] = "产品调用量";
        titleArray[2] = "产品价格";
        titleArray[3] = "产品调用费用总和";
        DoExcelUtil.doExcel(titleArray, headRow, workbook, sheet);

        //写入第一表格数据  判断该商户本月是否有调用
        int num = 0;
        if(allProductDetails == null){
            HSSFRow row = sheet.createRow(num + 1);
            row.createCell(1);
            row.getCell(1).setCellValue("暂无数据");
        }else {
            int index = 0;
            //写入产品调用详情
            Double accountPrice = 0D;
            for (Map map : allProductDetails) {
                HSSFRow row = sheet.createRow(index + 1);
                for (int n = 0; n <= 3; n++) {
                    row.createCell(n);
                }
                //写入一行
                row.getCell(0).setCellValue(map.get("productName").toString());
                row.getCell(1).setCellValue(map.get("num").toString());
                row.getCell(2).setCellValue(map.get("productPrice").toString());
                row.getCell(3).setCellValue(map.get("totalMonthPrice").toString());
                accountPrice+=(Double) map.get("totalMonthPrice");
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
            path = "H:\\"+mon+merchantName+"账单.xls";
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
