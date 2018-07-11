package com.jufan.dao;

import org.springframework.stereotype.Repository;

/**
 * @Author pengyd
 * @Date 2018/7/11 16:16
 * @function:  关于  表是否存在 、 创建表 、 删除表
 */
@Repository
public interface TableManagerDao {

    //创建表
    void createTable(String tableName);

    //删除表
    void dropTable(String tableName);

    //判断表是否存在
    int checkTable(String tableName);



}
