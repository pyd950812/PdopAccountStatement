package com.jufan.util;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;

/**
 * @Author pengyd
 * @Date 2018/7/11 13:48
 * @function:
 */
public class MultipleDataSource extends AbstractRoutingDataSource{
    /**
     * 数据源标识保存在线程变量中，避免多线程操作数据源时互相干扰
     */
    private static final ThreadLocal<String> dataSourceKey = new InheritableThreadLocal<String>();

    //设置数据源
    public static void setDataSourceKey(String dataSource){
        dataSourceKey.set(dataSource);
    }

    @Override
    protected Object determineCurrentLookupKey() {
        return dataSourceKey.get();
    }

    //清除数据源
    public static void clearDataSource(){
        dataSourceKey.remove();
    }
}
