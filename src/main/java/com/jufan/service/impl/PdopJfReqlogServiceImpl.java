package com.jufan.service.impl;

import com.jufan.dao.PdopJfReqlogDao;
import com.jufan.model.PdopJfReqlog;
import com.jufan.service.PdopJfReqlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PdopJfReqlogServiceImpl implements PdopJfReqlogService {

    @Autowired
    private PdopJfReqlogDao pdopJfReqlogDao;

    public int insert(PdopJfReqlog pojo) {
        return pdopJfReqlogDao.insert(pojo);
    }

    public int insertList(List<PdopJfReqlog> pojos) {
        return pdopJfReqlogDao.insertList(pojos);
    }

    public List<PdopJfReqlog> select(PdopJfReqlog pojo) {
        return pdopJfReqlogDao.select(pojo);
    }

    public int update(PdopJfReqlog pojo) {
        return pdopJfReqlogDao.update(pojo);
    }

    public List<PdopJfReqlog> selectByHour(Map<String, Object> map) {
        return pdopJfReqlogDao.queryByHour(map);
    }

    public int insertJfExtList(String tableName, List<PdopJfReqlog> pojos) {
        return pdopJfReqlogDao.insertJfExtList(tableName,pojos);
    }

    public int insertJf(String tableName, PdopJfReqlog pojo) {
        return pdopJfReqlogDao.insertJf(tableName,pojo);
    }
}
