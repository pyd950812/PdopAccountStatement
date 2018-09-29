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

    /**
     * 该方法查询的是每个小时插入query表的数据
     * @param map
     * @return
     */
    List<PdopQueryLog> selectByHour(Map<String,Object> map );

    /**
     * 该方式是将查询得到的一个小时中新增的数据保存到query拓展表里面
     * @param tableName
     * @param pojos
     * @return
     */
    int insertQueryExtList(@Param("tableName")String tableName ,@Param("pojos")List< PdopQueryLog> pojos);



    int insertQuery(@Param("tableName")String tableName ,@Param("pojo") PdopQueryLog pojo);
}
