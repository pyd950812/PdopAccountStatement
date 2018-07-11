package com.jufan.dao;

import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.jufan.model.PdopJfReqlog;
import org.springframework.stereotype.Repository;

@Repository
public interface PdopJfReqlogDao {

    int insert(@Param("pojo") PdopJfReqlog pojo);

    int insertList(@Param("pojos") List< PdopJfReqlog> pojo);

    List<PdopJfReqlog> select(@Param("pojo") PdopJfReqlog pojo);

    int update(@Param("pojo") PdopJfReqlog pojo);

}
