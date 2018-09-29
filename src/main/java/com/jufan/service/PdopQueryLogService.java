package com.jufan.service;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import com.jufan.model.PdopQueryLog;
import com.jufan.dao.PdopQueryLogDao;

public interface PdopQueryLogService {

    public int insert(PdopQueryLog pojo);

    public int insertList(List< PdopQueryLog> pojos);

    public List<PdopQueryLog> select(PdopQueryLog pojo);

    public int update(PdopQueryLog pojo);

    List<PdopQueryLog> selectByHour(Map<String,Object> map );

    public int insertQueryExtList(String tableName,List< PdopQueryLog> pojos);

    public int insertQuery(String tableName,PdopQueryLog pojo);
}


