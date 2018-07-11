package com.jufan.service;


import java.util.List;
import java.util.Map;

/**
 * @Author pengyd
 * @Date 2018/7/11 14:47
 * @function:
 */
public interface QiaoRongService {

    //计算乔融每个月产品的调用量
    List<Map> selectQiaoRongCount(String data);
}
