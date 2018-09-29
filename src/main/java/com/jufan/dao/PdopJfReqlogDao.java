package com.jufan.dao;

import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.jufan.model.PdopJfReqlog;
import org.springframework.stereotype.Repository;

/**
 * @Author gaokun
 * @Date 2018/7/17 17:47
 * @function:  jf表数据扩展功能
 */
@Repository
public interface PdopJfReqlogDao {

    int insert(@Param("pojo") PdopJfReqlog pojo);

    int insertList(@Param("pojos") List< PdopJfReqlog> pojo);

    List<PdopJfReqlog> select(@Param("pojo") PdopJfReqlog pojo);

    int update(@Param("pojo") PdopJfReqlog pojo);

    /**
     * 该方法查询的是每个小时插入jf表的数据
     * @param map
     * @return
     */
    List<PdopJfReqlog> queryByHour(Map<String,Object> map);

    /**
     * 该方式是将查询得到的一个小时中新增的数据保存到jf拓展表里面
     * @param tableName
     * @param pojos
     * @return
     */
    int insertJfExtList(@Param("tableName") String tableName ,@Param("pojos")List<PdopJfReqlog> pojos);



    int insertJf(@Param("tableName") String tableName ,@Param("pojo") PdopJfReqlog pojo);
}
