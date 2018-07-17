package com.jufan.service.impl;

import com.google.common.collect.Maps;
import com.jufan.dao.QiaoRongDao;
import com.jufan.service.MerchantAccountService;
import com.jufan.service.QiaoRongService;
import com.jufan.util.MultipleDataSource;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author pengyd
 * @Date 2018/7/11 14:48
 * @function:
 */
@Service
public class QiaoRongServiceImpl implements QiaoRongService {

    @Resource
    private QiaoRongDao qiaoRongDao;
    @Resource
    private MerchantAccountService merchantAccountService;


    public List<Map<String, Object>> selectQiaoRongCount(String data) {
        System.out.println("!!!!!!!!!!!!");
        List<Map<String, Object>> merchantCount = merchantAccountService.getMerchantCountByOrgId("");

        //切换到risk库中
        MultipleDataSource.setDataSourceKey("dataSource_risk");

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MONTH, -1);
        Date m = c.getTime();
        String mon = format.format(m);

        //计算risk库中 乔融的 同盾调用量
        String num = qiaoRongDao.selectRiskCount(mon);
        Map<String,Object> map = Maps.newHashMap();
        map.put("productName", "同盾");
        map.put("productPrice", "0.4");
        map.put("num", num);
        Double countPri = Integer.parseInt(num)*0.4;
        map.put("totalMonthPrice", countPri);

        merchantCount.add(map);


        return merchantCount;
    }



}
