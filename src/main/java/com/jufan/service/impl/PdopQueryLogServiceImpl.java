package com.jufan.service.impl;

import com.jufan.dao.PdopQueryLogDao;
import com.jufan.model.PdopQueryLog;
import com.jufan.service.PdopQueryLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PdopQueryLogServiceImpl  implements PdopQueryLogService{

    @Autowired
    private PdopQueryLogDao pdopQueryLogDao;

    public int insert(PdopQueryLog pojo) {
        return pdopQueryLogDao.insert(pojo);
    }

    public int insertList(List<PdopQueryLog> pojos) {
        return pdopQueryLogDao.insertList(pojos);
    }

    public List<PdopQueryLog> select(PdopQueryLog pojo) {
        return pdopQueryLogDao.select(pojo);
    }

    public int update(PdopQueryLog pojo) {
        return pdopQueryLogDao.update(pojo);
    }

    public List<PdopQueryLog> selectByHour(Map<String, Object> map) {
        return pdopQueryLogDao.selectByHour(map);
    }

    public int insertQueryExtList(String tableName, List<PdopQueryLog> pojos) {
        return pdopQueryLogDao.insertQueryExtList(tableName,pojos);
    }
}
