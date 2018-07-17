package com.jufan.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Author gaokun
 * @Date 2018/7/17 17:47
 * @function:  数据源账单的相关操作
 */
@Repository
public interface DataSourceDao {
    /**
     *根据数据源id查询我们这边对应的接口id
     * @return 接口id
     */
    List<String> getInterfaceListByDid(@Param("dataSourceId") String dataSourceId);

    /**
     *根据接口id查询我们这边对应的接口id
     * @return 接口id
     */
    String getProductById(@Param("interfaceId") String interfaceId);

    /**
     *通过产品id获取该产品月调用情况
     * @return
     */
    List<Map<String,Object>>  getDataSourceAccountByPid(Map<String,Object> map);

    /**
     *通过产品id获取对应的接口id
     * @return
     */
    String getInterfaceIdByPid(@Param("productId")String productId);
    /**
     *通过接口id获取对应的接口名和价格
     * @return
     */
    Map<String,Object> getInterfaceDetailById(@Param("interfaceId")String interfaceId);

    /**
     *通过数据源id获取对应的数据源名称
     * @return
     */
    String getDataSourceNameByDid(@Param("dataSourceId")String dataSourceId);
}
