package com.jufan.service.impl;

import com.google.common.collect.Maps;
import com.jufan.dao.MerchantAccountDao;
import com.jufan.service.MerchantAccountService;
import com.jufan.service.TableManagerService;
import com.jufan.util.GenerateExcleUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.text.SimpleDateFormat;
import java.util.*;


/**
 * @Author gaokun
 * @Date 2018/7/17 17:47
 * @function:  该类用于商户月账单查询
 */
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
                            if (productId != null && !productId.equals("") && !count.equals("")&& count != null) {
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
        GenerateExcleUtil.creatExcle(allProductDetails,merchantName,mon);

    }

}
