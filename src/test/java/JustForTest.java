import com.jufan.dao.QiaoRongDao;
import com.jufan.model.PdopJfReqlog;
import com.jufan.model.PdopQueryLog;
import com.jufan.service.*;
import com.jufan.util.ReturnData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author pengyd
 * @Date 2018/7/11 11:37
 * @function:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class JustForTest {

    @Autowired
    private MerchantAccountService merchantAccountService;
    @Autowired
    private PdopQueryLogService pdopQueryLogService;
    @Autowired
    private PdopJfReqlogService pdopJfReqlogService;
    @Autowired
    private TableManagerService tableManagerService;
    @Autowired
    private QiaoRongDao qiaoRongDao;
    @Autowired
    private DataSourceAccountService dataSourceAccountService;

    public static void main(String[] args) {
/*        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, -3);
        date = calendar.getTime();
        System.out.println(sdf.format(date));*/

    }

    @Test
    public void test() {
//        tableManagerService.createTable("qqqqq");
//        tableManagerService.createJfTable("qqqqq");
       /* System.out.println("进入定时任务，每隔一个小时拉取数据");
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.MINUTE, 0);
        ca.set(Calendar.SECOND, 0);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM");
        String suffix = sdf1.format(new Date());
        //当前时间小时整点
        Date endDate = ca.getTime();
        String endTime = sdf.format(endDate);
        System.out.println("当前时间整点小时" + endTime);

        ca.set(Calendar.MONTH, ca.get(Calendar.MONTH) - 1);
        Date startDate = ca.getTime();
        String startTime = sdf.format(startDate);
        System.out.println("当前时间前一小时整点" + startTime);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("startTime", startTime);
        map.put("endTime", endTime);

        List<PdopJfReqlog> pdopJfReqlogList = pdopJfReqlogService.selectByHour(map);
        List<PdopQueryLog> pdopQueryLogList = pdopQueryLogService.selectByHour(map);
        if (pdopJfReqlogList.size() > 0 && pdopQueryLogList.size() > 0) {
            String jfTableName = "pdop_data_jfext_" +suffix;
            String queryTableName = "pdop_data_queryext_" +suffix;

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

*/
/*
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Date date = new Date();
        date.setMonth(date.getMonth() - 1);
        String startDate = sdf.format(date);
        String jfTableName = "pdop_data_jfext_" + startDate;
        String queryTableName = "pdop_data_queryext_" + startDate;
        System.out.println(jfTableName + "" + queryTableName);

        merchantAccountService.getMerchantCountByOrgId("10001");*/

        dataSourceAccountService.getDataSourceAccountById("e1087d464682403b9b84d1e8a885b6e7");
    }




}
