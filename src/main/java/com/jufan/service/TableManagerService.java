package com.jufan.service;


import com.jufan.util.ReturnData;

/**
 * @Author pengyd
 * @Date 2018/7/11 14:47
 * @function:
 */
public interface TableManagerService {

    //创建Jf表
    ReturnData createJfTable(String tableName);

    //创建Query表
    ReturnData createQueryTable(String tableName);

    //删除表
    ReturnData dropTable(String tableName);

    //判断表是否存在
    boolean checkTable(String tableName);

}
