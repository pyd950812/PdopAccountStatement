package com.jufan.service;


/**
 * @Author gaokun
 * @Date 2018/7/17 17:47
 * @function:  数据源账单的相关操作
 */
public interface DataSourceAccountService {
    /**
     * 根据数据源id查询数据我们这边对应产品的调用量
     * @param dataSourceId
     */
    public void getDataSourceAccountById(String dataSourceId);

}
