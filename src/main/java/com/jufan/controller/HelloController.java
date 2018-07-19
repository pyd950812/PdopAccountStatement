package com.jufan.controller;

import com.jufan.dao.MerchantAccountDao;
import com.jufan.service.MerchantAccountService;
import com.jufan.service.PdopJfReqlogService;
import com.jufan.service.PdopQueryLogService;
import com.jufan.service.QiaoRongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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
    @Autowired
    private MerchantAccountDao merchantAccountDao;

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

        return "test";
    }


    /**
     * 首页
     * @return
     */
    @RequestMapping("login")
    public String login(){

        return "login/login";
    }

    /**
     * 进入商户操作界面
     * @return
     */
    @RequestMapping("toMerchant")
    public String toMerchant(HttpServletRequest request){
        //查询所有商户
        List<Map<String, Object>> list = merchantAccountDao.selectAllMerchant();

        request.setAttribute("merchantList",list);
        return "merchant/merchant";
    }

    /**
     *   下载对应商户的Excle
     */
    @RequestMapping("downloadExcle")
    public String downloadExcle(String merchantList){
        System.out.println(merchantList);
        //乔融和其他商户账单需要 分开生成
        //乔融
        if(merchantList.equals("6e2e452c4a0a4ccf9b08bcf59432c937")){
            qiaoRongService.selectQiaoRongCount();
        }else {
            merchantAccountService.buildAccountExcelByOrgId(merchantList);
        }
        return "test";
    }


}
