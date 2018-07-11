package com.jufan.service;

import com.jufan.model.PdopJfReqlog;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

import com.jufan.dao.PdopJfReqlogDao;

@Service
public class PdopJfReqlogService {

    @Resource
    private PdopJfReqlogDao pdopJfReqlogDao;

    public int insert(PdopJfReqlog pojo){
        return pdopJfReqlogDao.insert(pojo);
    }

    public int insertList(List< PdopJfReqlog> pojos){
        return pdopJfReqlogDao.insertList(pojos);
    }

    public List<PdopJfReqlog> select(PdopJfReqlog pojo){
        return pdopJfReqlogDao.select(pojo);
    }

    public int update(PdopJfReqlog pojo){
        return pdopJfReqlogDao.update(pojo);
    }

}
