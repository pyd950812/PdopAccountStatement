package com.jufan.dao;

import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.jufan.model.PdopJfReqlog;
import org.springframework.stereotype.Repository;

@Repository
public interface PdopJfReqlogDao {

    int insert(@Param("pojo") PdopJfReqlog pojo);

    int insertList(@Param("pojos") List< PdopJfReqlog> pojo);

    List<PdopJfReqlog> select(@Param("pojo") PdopJfReqlog pojo);

    int update(@Param("pojo") PdopJfReqlog pojo);
    //查时间区间为一个小时的数据
    List<PdopJfReqlog> queryByHour(Map<String,Object> map);

}
