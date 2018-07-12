package com.jufan.dao;

import javafx.beans.binding.ObjectExpression;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.jufan.model.PdopQueryLog;
import org.springframework.stereotype.Repository;

@Repository
public interface PdopQueryLogDao {

    int insert(@Param("pojo") PdopQueryLog pojo);

    int insertList(@Param("pojos") List< PdopQueryLog> pojo);

    List<PdopQueryLog> select(@Param("pojo") PdopQueryLog pojo);

    int update(@Param("pojo") PdopQueryLog pojo);

    //用于查询时间区间一个小时的
    List<PdopQueryLog> selectByHour(Map<String,Object> map );

}
