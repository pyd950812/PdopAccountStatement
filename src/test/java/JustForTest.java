import com.jufan.service.TableManagerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author pengyd
 * @Date 2018/7/11 11:37
 * @function:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml" })
public class JustForTest {

    @Autowired
    private TableManagerService tableManagerService;

    public static void main(String[] args) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, -3);
        date = calendar.getTime();
        System.out.println(sdf.format(date));
    }

    @Test
    public void test(){
//        tableManagerService.createTable("qqqqq");
    tableManagerService.dropTable("qqqqq");

    }



}
