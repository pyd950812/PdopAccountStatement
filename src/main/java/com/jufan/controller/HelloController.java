package com.jufan.controller;

import com.jufan.service.MerchantAccountService;
import com.jufan.service.PdopJfReqlogService;
import com.jufan.service.PdopQueryLogService;
import com.jufan.service.QiaoRongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
    private PdopJfReqlogService pdopJfReqlogService;
    @Autowired
    private PdopQueryLogService pdopQueryLogService;
    @Autowired
    private MerchantAccountService merchantAccountService;



    @RequestMapping("test")
    public String test(){
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!");
        /*Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, -1);*/
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
        String time = format.format(new Date());
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("startTime","2018-01-01 00:00:00");
        map.put("endTime",time);

        pdopJfReqlogService.selectByHour(map);
        pdopQueryLogService.selectByHour(map);
        System.out.println(pdopJfReqlogService.selectByHour(map).size()+"!!"+pdopQueryLogService.selectByHour(map).size());

        qiaoRongService.selectQiaoRongCount(time);
        return "test";
    }



    @RequestMapping("ff")
    public String ff(){
//        merchantAccountService.buildAccountExcelByOrgId("c738ba0527f940c29847b893f3e33681");
        qiaoRongService.selectQiaoRongCount("ss");
        return "test";
    }

}
