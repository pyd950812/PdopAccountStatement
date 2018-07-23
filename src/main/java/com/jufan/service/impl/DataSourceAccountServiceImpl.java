package com.jufan.service.impl;

import com.jufan.dao.DataSourceDao;
import com.jufan.service.DataSourceAccountService;
import com.jufan.util.GenerateExcleUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * @Author gaokun
 * @Date 2018/7/17 17:47
 * @function:  数据源账单的具体实现类
 */
@Service
public class DataSourceAccountServiceImpl implements DataSourceAccountService {


    @Autowired
    private DataSourceDao dataSourceDao;

    /**
     * 该方法用于封装数据源一个月的调用情况
     * @param dataSourceId
     */
    public void getDataSourceAccountById(String dataSourceId, HttpServletResponse response) {

        String dataSourceName=dataSourceDao.getDataSourceNameByDid(dataSourceId);
        if(!dataSourceName.equals("")&&dataSourceName!=null) {
            List<String> productList = new ArrayList<String>();
            try {
                List<String> interfaceIdList = dataSourceDao.getInterfaceListByDid(dataSourceId);
                if (interfaceIdList.size() > 0) {
                    for (int i = 0; i < interfaceIdList.size(); i++) {
                        String productId = dataSourceDao.getProductById(interfaceIdList.get(i));
                        productList.add(productId);
                    }
                    //获取前一个月的表名
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
                    Date date = new Date();
                    date.setMonth(date.getMonth());
                    String startDate = sdf.format(date);
                    String jfTableName = "pdop_data_jfext_" + startDate;
                    String queryTableName = "pdop_data_queryext_" + startDate;
                    System.out.println("jf拓展表名"+jfTableName);
                    System.out.println("query拓展表名"+queryTableName);
                    Map map = new HashMap();
                    map.put("jfTableName", jfTableName);
                    map.put("queryTableName", queryTableName);
                    map.put("productList", productList);
                    List<Map<String, Object>> accountList = dataSourceDao.getDataSourceAccountByPid(map);
                    if (accountList.size() > 0) {
                        //由于生成账单需要接口名和价格
                        for (Map<String, Object> mapDetail : accountList) {
                            //此处备注：由于数据库返回的是map对象，我们这边没有用bean封装所以取值的key为数据库返回字段的key值
                            if (mapDetail.containsKey("product_id") && mapDetail.containsKey("num")) {
                                String count = mapDetail.get("num").toString();
                                Integer num = Integer.parseInt(count);
                                String pid = mapDetail.get("product_id").toString();
                                System.out.println("产品id ：" + pid);
                                String id = dataSourceDao.getInterfaceIdByPid(pid);
                                if (id != null && id != "") {
                                    Map<String,Object> map1 = dataSourceDao.getInterfaceDetailById(id);
                                    if (map1.containsKey("price") && map1.containsKey("name")) {
                                        String interfaceName = map1.get("name").toString();
                                        String price = map1.get("price").toString();
                                        Double interfacePrice = Double.parseDouble(price);
                                        mapDetail.put("productName", interfaceName);
                                        mapDetail.put("productPrice", interfacePrice);
                                        Double totalMonthPrice = num * interfacePrice;
                                        mapDetail.put("totalMonthPrice", totalMonthPrice);
                                    }
                                }
                            }
                        }
                        //生成excel报表
                        GenerateExcleUtil.creatExcle(accountList, dataSourceName,startDate,response);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List<Map<String, Object>> getCommonDataSource(List<String> datasourceList) {
        return dataSourceDao.getCommonDataSource(datasourceList);
    }
}
