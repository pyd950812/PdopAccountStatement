package com.jufan.service.impl;

import com.jufan.dao.MerchantAccountDao;
import com.jufan.service.MerchantAccountService;
import com.jufan.service.TableManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class MerchantAccountServiceImpl implements MerchantAccountService {

    @Autowired
    private MerchantAccountDao merchantAccountDao;
    @Autowired
    private TableManagerService tableManagerService;

    /**
     * 这里将会在每个月初调用此方法,查询上个月的账单
     *
     * @param orgId
     */
    public void getMerchantCountByOrgId(String orgId) {
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

        try {
            Boolean j = tableManagerService.checkTable(jfTableName);
            Boolean q = tableManagerService.checkTable(queryTableName);
            if (j == true && q == true) {
                List<Map<String, Object>> mapList = merchantAccountDao.getAccountDetailByOrgId(map);
                if (mapList.size() > 0) {
                    for (Map<String, Object> result : mapList) {
                        System.out.println(result);
                        if (result.containsKey("product_id") && result.containsKey("num")) {
                            String productId = result.get("product_id") != null ? result.get("product_id").toString() : "";
                            String count = result.get("num").toString();
                            Integer num = Integer.parseInt(count);
                            if (productId != null && productId != "" && count != "" && count != null) {
                                Map<String, Object> productMap = merchantAccountDao.getProductNameById(productId);
                                if (productMap.containsKey("name") && productMap.containsKey("price")) {
                                    String productName = productMap.get("name").toString();
                                    String price = productMap.get("price").toString();
                                    //价格类型要转化
                                    Double productPrice = Double.parseDouble(price);
                                    Double totalMonthPrice = productPrice * num;
                                    System.out.println("产品名:" + productName + " ====价格：" + productPrice + " ===月调用次数:" + num + " ===月总账单:" + totalMonthPrice);
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

    }


    public void buildExcel() {


    }

}
