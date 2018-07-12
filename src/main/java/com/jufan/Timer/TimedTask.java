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

public class TimedTask {

    @Autowired
    private MerchantAccountService merchantAccountService;
    @Autowired
    private PdopQueryLogService pdopQueryLogService;
    @Autowired
    private PdopJfReqlogService pdopJfReqlogService;
    @Autowired
    private TableManagerService tableManagerService;

    public void qiaoRongTask() {
        System.out.println("进入乔融定时任务=================");

    }


    public void queryDataByAnHour() {
        System.out.println("进入定时任务，每隔一个小时拉取数据");
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.MINUTE, 0);
        ca.set(Calendar.SECOND, 0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        String suffix=sdf1.format(new Date());
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

        List<PdopJfReqlog> pdopJfReqlogList= pdopJfReqlogService.selectByHour(map);
        List<PdopQueryLog> pdopQueryLogList= pdopQueryLogService.selectByHour(map);
        if(pdopJfReqlogList.size()>0&&pdopQueryLogList.size()>0){
            String jfTableName="pdop_data_jfext_"+suffix;
            String queryTableName="pdop_data_queryext_"+suffix;

            try {
                Boolean jf =tableManagerService.checkTable(jfTableName);
                Boolean query=tableManagerService.checkTable(queryTableName);

                if(query==true){
                    pdopQueryLogService.insertQueryExtList(queryTableName,pdopQueryLogList);
                }else {
                    ReturnData returnData = tableManagerService.createQueryTable(queryTableName);
                    if (returnData.getCode().equals("OK")) {
                        pdopQueryLogService.insertQueryExtList(queryTableName, pdopQueryLogList);
                    }
                }

                if(jf==true){
                    pdopJfReqlogService.insertJfExtList(jfTableName,pdopJfReqlogList);
                }else{

                    ReturnData returnData1=tableManagerService.createJfTable(jfTableName);
                    if(returnData1.getCode().equals("OK")){
                        pdopJfReqlogService.insertJfExtList(jfTableName,pdopJfReqlogList);
                    }
                }

            }catch (Exception e){
                e.printStackTrace();
            }

        }





    }


    public static void main(String[] args) {
        Calendar ca = Calendar.getInstance();
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
        System.out.println(sdf1.format(d));

    }
}