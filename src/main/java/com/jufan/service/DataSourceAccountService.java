package com.jufan.service;


import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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

    /**
     * 查询几个常用的的数据源的名称和id
     * @return
     */
    List<Map<String,Object>> getCommonDataSource(List<String> datasourceList);

}
