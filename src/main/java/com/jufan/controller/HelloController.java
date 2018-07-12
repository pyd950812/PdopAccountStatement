package com.jufan.controller;

import com.jufan.service.PdopJfReqlogService;
import com.jufan.service.QiaoRongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
        System.out.println(pdopJfReqlogService.selectByHour(map).size());

        qiaoRongService.selectQiaoRongCount(time);
        return "test";
    }
}
