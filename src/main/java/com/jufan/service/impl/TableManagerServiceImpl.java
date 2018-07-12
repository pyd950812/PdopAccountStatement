package com.jufan.service.impl;

import com.jufan.dao.TableManagerDao;
import com.jufan.service.TableManagerService;
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


    public boolean createTable(String tableName) {
        tableManagerDao.createTable(tableName);
        System.out.println("表创建成功");
        return true;
    }

    public boolean dropTable(String tableName) {
        return false;
    }

    /**
     * 判断表是否存在
     * @param tableName
     * @return
     */
    public boolean checkTable(String tableName) {
        System.out.println("!!!!!!!!!!");
        int i = tableManagerDao.checkTable(tableName);
        //如果返回1的话，说明表已经存在；  否则表不存在
        if(i == 1){
            return true;
        }else {
            return false;
        }
    }



}
