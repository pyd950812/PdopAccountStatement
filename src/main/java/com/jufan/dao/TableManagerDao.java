package com.jufan.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author pengyd
 * @Date 2018/7/11 16:16
 * @function:  关于  表是否存在 、 创建表 、 删除表
 */
@Repository
public interface TableManagerDao {

    //创建Jf表
    void createJfTable(@Param("tableName")String tableName);

    //创建Query表
    void createQueryTable(@Param("tableName")String tableName);

    //删除表
    void dropTable(@Param("tableName") String tableName);

    //判断表是否存在
    int checkTable(String tableName);



}
