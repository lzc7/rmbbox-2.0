package com.zipi.config.DataSource.dbconfig;

import com.zipi.config.DataSource.dynamic.DynamicDataSource;
import com.zipi.config.util.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 多数据源配置
 * @author liangyu
 * @date 2018/6/14
 */
@Configuration
@Slf4j
public class DataSourceConfiguration {
    @Autowired
    private SpringContextUtil springContextUtil;

    @Value("${spring.datasource.type}")
    private Class<? extends DataSource> dataSourceType;

    /**
     * 主库数据源配置
     * @return
     */
    @Bean(name = "masterDataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public DataSource writeDataSource() {
        log.info("-------------------- master DataSource init ---------------------");
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    /**
     * 从库
     * @return
     */
    @Bean(name = "clusterDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.cluster")
    public DataSource readDataSourceOne() {
        log.info("-------------------- cluster DataSourceOne init ---------------------");
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    @Bean(name = "dynamicDataSource")
    public DynamicDataSource dataSouceProxy(){
        Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
        //把所有数据库都放在targetDataSources中,注意key值要和determineCurrentLookupKey()中代码写的一至，
        //否则切换数据源时找不到正确的数据源
        targetDataSources.put(DataSourceType.MASTER.getType(), springContextUtil.getBean("masterDataSource"));
        targetDataSources.put(DataSourceType.CLUSTER.getType(), springContextUtil.getBean("clusterDataSource"));

        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setDefaultTargetDataSource(springContextUtil.getBean("masterDataSource"));//默认主库
        dynamicDataSource.setTargetDataSources(targetDataSources);
        return dynamicDataSource;
    }

    //事务管理
    @Bean
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return new DataSourceTransactionManager((DataSource) springContextUtil.getBean("dynamicDataSource"));
    }

}
