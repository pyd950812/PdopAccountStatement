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
        System.out.println("进入定时任务，每隔一个小时拉取数据");
        Calendar ca = Calendar.getInstance();
        System.out.println(ca.getTime());
        ca.set(Calendar.MINUTE, 0);
        ca.set(Calendar.SECOND, 0);
        //该simple用于格式化当前时间到秒这样可以取出整点
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //该simple用于取时间的年月  用于获取表名
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM");
        //当前前一天时间小时整点
        ca.set(Calendar.DAY_OF_MONTH,ca.get(Calendar.DAY_OF_MONTH) -1);
        Date day=ca.getTime();
        String suffix = sdf1.format(day);
        System.out.println(suffix);
        Date endDate = ca.getTime();
        String endTime = sdf.format(endDate);
        System.out.println("当前时间整点小时" + endTime);
        ca.set(Calendar.HOUR_OF_DAY, ca.get(Calendar.HOUR_OF_DAY) - 1);
        Date startDate = ca.getTime();
        String startTime = sdf.format(startDate);
        System.out.println("当前时间前一天前一小时整点" + startTime);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("startTime", startTime);
        map.put("endTime", endTime);
    }




}
