package com.jufan.service.impl;

import com.jufan.dao.QiaoRongDao;
import com.jufan.service.QiaoRongService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    public List<Map> selectQiaoRongCount(String data) {
        System.out.println("!!!!!!!!!!!!");
        List<Map> maps = qiaoRongDao.selectQiaoRongCount(data);
        System.out.println(maps);
        return maps;
    }
}
