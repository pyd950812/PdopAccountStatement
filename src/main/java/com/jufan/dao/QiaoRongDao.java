package com.jufan.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author pengyd
 * @Date 2018/7/11 14:27
 * @function:  计算乔融的相关操作
 */
@Repository
public interface QiaoRongDao {

    //计算乔融每个月产品的调用量
    List<Map> selectQiaoRongCount(String date);

    //计算risk库中的调用量
    String selectRiskCount(String date);
}
