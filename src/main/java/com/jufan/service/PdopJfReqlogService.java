package com.jufan.service;

import com.jufan.model.PdopJfReqlog;

import java.util.List;
import java.util.Map;

public interface PdopJfReqlogService {

    public int insert(PdopJfReqlog pojo);

    public int insertList(List< PdopJfReqlog> pojos);

    public List<PdopJfReqlog> select(PdopJfReqlog pojo);

    public int update(PdopJfReqlog pojo);

    List<PdopJfReqlog> selectByHour(Map<String,Object> map );

    public int insertJfExtList(String tableName,List< PdopJfReqlog> pojos);

    public int insertJf(String tableName,PdopJfReqlog pojo);
}
