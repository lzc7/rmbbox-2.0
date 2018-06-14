package com.zipi.config.DataSource.aop;

import com.zipi.config.DataSource.dbconfig.DataSourceType;
import com.zipi.config.DataSource.holder.DataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

/**
 * @author liangyu
 * @date 2018/6/14
 */
@Aspect
@Component
@Slf4j
public class DataSourceInterceptor implements PriorityOrdered {

    @Pointcut("execution(* com.zipi.dao.master.*.*(..))")
    public void masterDataSource(){}

    @Pointcut("execution(* com.zipi.dao.cluster.*.*(..))")
    public void clusterDataSource(){}

    @Pointcut("execution(* com.zipi.dao.*.*.*(..))")
    public void resetDataSource(){}

    @Before(value = "masterDataSource()")
    public void setMasterDataSourceType(JoinPoint joinPoint){
        log.info("set master dataSource");
        DataSourceContextHolder.setCustomerType(DataSourceType.MASTER.getType());
    }

    @Before(value = "clusterDataSource()")
    public void setClusterDataSourceType(JoinPoint joinPoint){
        log.info("set cluster dataSource");
        DataSourceContextHolder.setCustomerType(DataSourceType.CLUSTER.getType());
    }

    @After(value = "resetDataSource()")
    public void resetDataSourceType(){
        log.info("set reset dataSource");
        DataSourceContextHolder.clearCustomerType();
    }


    @Override
    public int getOrder() {
        return 1;
    }
}
