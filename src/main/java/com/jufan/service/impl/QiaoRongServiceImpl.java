package com.jufan.service.impl;

import com.google.common.collect.Maps;
import com.jufan.dao.QiaoRongDao;
import com.jufan.service.MerchantAccountService;
import com.jufan.service.QiaoRongService;
import com.jufan.util.DoExcelUtil;
import com.jufan.util.GenerateExcleUtil;
import com.jufan.util.MultipleDataSource;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
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


    public List<Map<String, Object>> selectQiaoRongCount() {
        //这里需要传入乔融的orgId
        List<Map<String, Object>> merchantCount = merchantAccountService.getMerchantCountByOrgId("05a2dbcba9c94fcba144e5caa464e41a");

        //切换到risk库中
        MultipleDataSource.setDataSourceKey("dataSource_risk");

        //获取当前月份中上一个月的 年月
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MONTH, -1);
        Date m = c.getTime();
        String mon = format.format(m);

        //计算risk库中 乔融的 同盾调用量
        String num = qiaoRongDao.selectRiskCount(mon);
        Map<String,Object> map = Maps.newHashMap();
        map.put("productName", "risk中同盾");
        map.put("productPrice", "0.4");
        map.put("num", num);
        Double countPri = Integer.parseInt(num)*0.4;
        map.put("totalMonthPrice", countPri);

        merchantCount.add(map);

        //生成Excle
        GenerateExcleUtil.creatExcle(merchantCount,"乔融",mon);
        return merchantCount;
    }



}
