package com.jufan.controller;

import com.jufan.service.QiaoRongService;
import com.jufan.service.TableManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @Author pengyd
 * @Date 2018/7/10 17:20
 * @function:
 */
@RequestMapping("hello")
@Controller
public class HelloController {

    @Autowired
    private QiaoRongService qiaoRongService;
    @Autowired
    private TableManagerService tableManagerService;

    @RequestMapping("test")
    public String test(){
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!");
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -1);
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM");
        String time = format.format(c.getTime());

        boolean pdop_hd_reqlog = tableManagerService.checkTable("pdop_hd_reqlog");
        System.out.println(pdop_hd_reqlog);

        boolean qqqqqq = tableManagerService.createTable("qqqqqq");
        System.out.println(qqqqqq);

        qiaoRongService.selectQiaoRongCount(time);
        return "test";
    }




}
