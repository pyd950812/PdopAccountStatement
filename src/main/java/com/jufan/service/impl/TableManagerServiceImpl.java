package com.jufan.service.impl;

import com.jufan.dao.TableManagerDao;
import com.jufan.service.TableManagerService;
import com.jufan.util.ReturnData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author pengyd
 * @Date 2018/7/11 17:47
 * @function:  表管理的实现类
 */
@Service
public class TableManagerServiceImpl implements TableManagerService {

    @Resource
    private TableManagerDao tableManagerDao;


    /**
     * 创建Jf表
     * @param tableName
     * @return
     */
    public ReturnData createJfTable(String tableName) {
        ReturnData rd = new ReturnData();
        try {
            tableManagerDao.createJfTable(tableName);
            rd.setCode("OK");
            rd.setMsg("创建"+tableName+"表成功");
        } catch (Exception e) {
            e.printStackTrace();
            rd.setCode("ERROE");
            rd.setMsg("创建表失败！");
        }
        return rd;
    }

    /**
     * 创建Query表
     * @param tableName
     * @return
     */
    public ReturnData createQueryTable(String tableName) {
        ReturnData rd = new ReturnData();
        try {
            tableManagerDao.createQueryTable(tableName);
            rd.setCode("OK");
            rd.setMsg("创建"+tableName+"表成功");
        } catch (Exception e) {
            e.printStackTrace();
            rd.setCode("ERROE");
            rd.setMsg("创建表失败！");
        }
        return rd;
    }

    /**
     * 删除表
     * @param tableName
     * @return
     */
    public ReturnData dropTable(String tableName) {
        ReturnData rd = new ReturnData();
        try {
            tableManagerDao.dropTable(tableName);
            rd.setCode("OK");
            rd.setMsg("删除"+tableName+"表成功");
        } catch (Exception e) {
            e.printStackTrace();
            rd.setCode("ERROE");
            rd.setMsg("删除表失败！");
        }
        return rd;
    }

    /**
     * 判断表是否存在
     * @param tableName
     * @return
     */
    public boolean checkTable(String tableName) {
        int i = tableManagerDao.checkTable(tableName);
        //如果返回1的话，说明表已经存在；  否则表不存在
        if(i == 1){
            return true;
        }else {
            return false;
        }
    }



}
