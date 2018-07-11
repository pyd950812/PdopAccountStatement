package com.jufan.dao;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author pengyd
 * @Date 2018/7/11 14:27
 * @function:
 */
@Repository
public interface QiaoRongDao {

    //计算乔融每个月产品的调用量
    List<Map> selectQiaoRongCount(String data);
}
