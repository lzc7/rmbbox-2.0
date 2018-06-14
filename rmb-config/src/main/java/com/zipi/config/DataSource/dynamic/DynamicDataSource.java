package com.zipi.config.DataSource.dynamic;

import com.zipi.config.DataSource.holder.DataSourceContextHolder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 创建动态数据源
 * 根据路由返回对应数据源
 * @author liangyu
 * @date 2018/6/14
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getCustomerType();
    }
}
