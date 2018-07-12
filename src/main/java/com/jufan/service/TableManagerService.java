package com.jufan.service;


/**
 * @Author pengyd
 * @Date 2018/7/11 14:47
 * @function:
 */
public interface TableManagerService {

    //创建表
    boolean createTable(String tableName);

    //删除表
    boolean dropTable(String tableName);

    //判断表是否存在
    boolean checkTable(String tableName);

}
