package com.jufan.Timer;

import com.jufan.model.PdopJfReqlog;
import com.jufan.model.PdopQueryLog;
import com.jufan.service.MerchantAccountService;
import com.jufan.service.PdopJfReqlogService;
import com.jufan.service.PdopQueryLogService;
import com.jufan.service.TableManagerService;
import com.jufan.util.ReturnData;
import org.apache.commons.lang.time.DateUtils;
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
        Date d=new Date();
        Date b= DateUtils.addDays(d,-1);
        Calendar ca = Calendar.getInstance();
        ca.setTime(b);
        ca.set(Calendar.MINUTE, 0);
        ca.set(Calendar.SECOND, 0);
        //该simple用于格式化当前时间到秒这样可以取出整点
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //该simple用于取时间的年月  用于获取表名
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM");
        //当前前一天时间小时整点
        //ca.set(Calendar.Day, ca.get(Calendar.DAY_OF_MONTH) - 1);
        Date day = ca.getTime();
        String suffix = sdf1.format(day);
        Date startDate = ca.getTime();
        String startTime = sdf.format(startDate);
        System.out.println("当前时间前一天整点小时" + startTime);
        Date endDate = DateUtils.addHours(startDate,1);
        String endTime = sdf.format(endDate);
        System.out.println("当前时间前一天后一小时整点" + endTime);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("startTime", startTime);
        map.put("endTime", endTime);

        List<PdopJfReqlog> pdopJfReqlogList = pdopJfReqlogService.selectByHour(map);
        System.out.println(pdopJfReqlogList.size());
        List<PdopQueryLog> pdopQueryLogList = pdopQueryLogService.selectByHour(map);
        System.out.println(pdopJfReqlogList.size());
        System.out.println(pdopQueryLogList.size());
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
        System.out.println("进入定时任务，每隔一个小时拉取数据");
        Date d=new Date();
        Calendar ca = Calendar.getInstance();
        ca.setTime(d);
        ca.set(Calendar.DATE, ca.get(Calendar.DATE) - 1);
        ca.set(Calendar.MINUTE, 0);
        ca.set(Calendar.SECOND, 0);
        //该simple用于格式化当前时间到秒这样可以取出整点
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //该simple用于取时间的年月  用于获取表名
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM");
        //当前前一天时间小时整点
        //ca.set(Calendar.Day, ca.get(Calendar.DAY_OF_MONTH) - 1);
        Date day = ca.getTime();
        String suffix = sdf1.format(day);
        System.out.println(suffix);
        Date startDate = ca.getTime();
        String startTime = sdf.format(startDate);
        System.out.println("当前时间前一天整点小时" + startTime);
        Date endDate = DateUtils.addHours(startDate,1);
        String endTime = sdf.format(endDate);
        System.out.println("当前时间前一天前一小时整点" + endTime);
    }
}