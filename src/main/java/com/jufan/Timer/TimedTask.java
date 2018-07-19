package com.jufan.Timer;

import com.jufan.model.PdopJfReqlog;
import com.jufan.model.PdopQueryLog;
import com.jufan.service.MerchantAccountService;
import com.jufan.service.PdopJfReqlogService;
import com.jufan.service.PdopQueryLogService;
import com.jufan.service.TableManagerService;
import com.jufan.util.ReturnData;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author gaokun
 * @Date 2018/7/17 17:47
 * @function: 该类用于定时任务获取数据
 */
public class TimedTask {

    @Autowired
    private MerchantAccountService merchantAccountService;
    @Autowired
    private PdopQueryLogService pdopQueryLogService;
    @Autowired
    private PdopJfReqlogService pdopJfReqlogService;
    @Autowired
    private TableManagerService tableManagerService;

    /**
     * 每个小时将query和jf表里的数据拉到新的拓展表里
     */
    public void queryDataByAnHour() {
        System.out.println("进入定时任务，每隔一个小时拉取数据");
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.MINUTE, 0);
        ca.set(Calendar.SECOND, 0);
        //该simple用于格式化当前时间到秒这样可以取出整点
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //该simple用于取时间的年月  用于获取表名
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM");
        String suffix = sdf1.format(new Date());
        //当前时间小时整点
        Date endDate = ca.getTime();
        String endTime = sdf.format(endDate);
        System.out.println("当前时间整点小时" + endTime);

        ca.set(Calendar.HOUR_OF_DAY, ca.get(Calendar.HOUR_OF_DAY) - 1);
        Date startDate = ca.getTime();
        String startTime = sdf.format(startDate);
        System.out.println("当前时间前一小时整点" + startTime);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("startTime", startTime);
        map.put("endTime", endTime);

        List<PdopJfReqlog> pdopJfReqlogList = pdopJfReqlogService.selectByHour(map);
        List<PdopQueryLog> pdopQueryLogList = pdopQueryLogService.selectByHour(map);
        if (pdopJfReqlogList.size() > 0 && pdopQueryLogList.size() > 0) {
            String jfTableName = "pdop_data_jfext_" + suffix;
            String queryTableName = "pdop_data_queryext_" + suffix;

            try {
                Boolean jf = tableManagerService.checkTable(jfTableName);
                Boolean query = tableManagerService.checkTable(queryTableName);

                if (query == true) {
                    pdopQueryLogService.insertQueryExtList(queryTableName, pdopQueryLogList);
                } else {
                    ReturnData returnData = tableManagerService.createQueryTable(queryTableName);
                    if (returnData.getCode().equals("OK")) {
                        pdopQueryLogService.insertQueryExtList(queryTableName, pdopQueryLogList);
                    }
                }

                if (jf == true) {
                    pdopJfReqlogService.insertJfExtList(jfTableName, pdopJfReqlogList);
                } else {

                    ReturnData returnData1 = tableManagerService.createJfTable(jfTableName);
                    if (returnData1.getCode().equals("OK")) {
                        pdopJfReqlogService.insertJfExtList(jfTableName, pdopJfReqlogList);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }


    /**
     * 每个月初删掉三个月之前的拓展表表  每月1号1点执行
     */
    public void dropTableByMonth() {
        //获取当前月的时间
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.MINUTE, 0);
        ca.set(Calendar.SECOND, 0);
        ca.set(Calendar.MONTH, ca.get(Calendar.MONTH) - 3);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        String suffix = sdf.format(ca.getTime());
        System.out.println(suffix);
        String jfTableName = "pdop_data_jfext_" + suffix;
        String queryTableName = "pdop_data_queryext_" + suffix;

        try {
            Boolean jf = tableManagerService.checkTable(jfTableName);
            Boolean query = tableManagerService.checkTable(queryTableName);

            if (query == true) {
                tableManagerService.dropTable(queryTableName);
            }

            if (jf == true) {
                tableManagerService.dropTable(jfTableName);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
      /*  Calendar ca = Calendar.getInstance();
        ca.set(Calendar.MINUTE, 0);
        ca.set(Calendar.SECOND, 0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        //当前时间小时整点
        Date endDate = ca.getTime();
        String endTime=sdf.format(endDate);
        System.out.println("当前时间整点小时"+endTime);

        ca.set(Calendar.HOUR_OF_DAY, ca.get(Calendar.HOUR_OF_DAY)-1);
        Date startDate = ca.getTime();
        String startTime=sdf.format(startDate);
        System.out.println("当前时间前一小时整点"+startTime);

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("startTime",startTime);
        map.put("endTime",endTime);

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM");
        Date d =new Date();
        System.out.println(sdf1.format(d));*/

    }
}