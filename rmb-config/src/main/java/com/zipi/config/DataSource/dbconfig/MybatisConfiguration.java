package com.zipi.config.DataSource.dbconfig;

import com.zipi.config.DataSource.dynamic.DynamicDataSource;
import com.zipi.config.util.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * @author liangyu
 * @date 2018/6/14
 */
@Configuration
@MapperScan(basePackages="com.zipi.dao", sqlSessionFactoryRef="sqlSessionFactory")
@Slf4j
public class MybatisConfiguration {
    @Value("${mybatis.mapperLocations}")
    private String mapperLocations;

    //加载全局的配置文件
    @Value("${mybatis.configLocation}")
    private String configLocation;

    @Value("${mybatis.typeAliasesPackage}")
    private String typeAliasesPackage;

    @Autowired
    @Qualifier("masterDataSource")
    private DataSource masterDataSource;
    @Autowired
    @Qualifier("clusterDataSource")
    private DataSource clusterDataSource;

    @Autowired
    @Qualifier("dynamicDataSource")
    private DynamicDataSource dynamicDataSource;

    @Bean(name="sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactorys() throws Exception {
        log.info("--------------------  sqlSessionFactory init ---------------------");
        try {
            SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
            sessionFactoryBean.setDataSource(dynamicDataSource);
            // 读取配置
            sessionFactoryBean.setTypeAliasesPackage(typeAliasesPackage);
            //设置mapper.xml文件所在位置
            Resource[] resources = new PathMatchingResourcePatternResolver().getResources(mapperLocations);
            sessionFactoryBean.setMapperLocations(resources);
            //设置mybatis-config.xml配置文件位置
            sessionFactoryBean.setConfigLocation(new DefaultResourceLoader().getResource(configLocation));

            return sessionFactoryBean.getObject();
        } catch (Exception e) {
            log.error("mybatis sqlSessionFactoryBean create error",e);
            return null;
        }
    }

}
