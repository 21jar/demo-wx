package com.demo.core.config;


import com.demo.core.utils.mybatis.DatabaseType;
import com.demo.core.utils.mybatis.DynamicDataSource;
import com.github.pagehelper.PageHelper;

import lombok.extern.slf4j.Slf4j;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by 胡超云 on 2016/11/21.
 */
@Slf4j
@Component
public class DatabaseConfig {

    @Autowired
    private WriteDruidConfig writeDruidConfig;

    @Autowired
    private ReadDruidConfig readDruidConfig;

    @Bean
    @Primary
    @Scope("singleton")
    public DynamicDataSource dataSource() throws Exception {
        Map<Object, Object> targetDataSources = new HashMap<Object, Object>();

        DataSource readDb = readDruidConfig.readDataSource();
        DataSource writeDb = writeDruidConfig.writeDruidConfig();

        targetDataSources.put(DatabaseType.masterDatabase, writeDb);
        targetDataSources.put(DatabaseType.slaveDatabase, readDb);

        DynamicDataSource dataSource = new DynamicDataSource();
        // 该方法是AbstractRoutingDataSource的方法
        dataSource.setTargetDataSources(targetDataSources);
        // 默认的datasource设置为writeDb
        dataSource.setDefaultTargetDataSource(writeDb);
        return dataSource;
    }

    /**
     * 根据数据源创建SqlSessionFactory
     */
    @Bean
    public SqlSessionFactory sqlSessionFactory(DynamicDataSource ds) throws Exception {
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(ds);

        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("dialect", "mysql");
        properties.setProperty("pageSizeZero", "true");
        properties.setProperty("reasonable", "false");
        properties.setProperty("params", "pageNum=pageHelperStart;pageSize=pageHelperRows;");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "none");
        pageHelper.setProperties(properties);
        Interceptor[] interceptors = new Interceptor[]{pageHelper};
        sessionFactoryBean.setPlugins(interceptors);

        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactoryBean.setTypeAliasesPackage("com.demo.domain");
        try {
            sessionFactoryBean.setMapperLocations(resolver.getResources("classpath*:com/demo/mapper/**/*Mapper.xml"));
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return sessionFactoryBean.getObject();
    }

    /**
     * 配置事务管理器
     * Order小于{com.demo.core.aop.DataSourceAop}的顺序，是其在动态切换数据库后在确定事务提交
     */
    @Bean
    @Order(2)
    public DataSourceTransactionManager transactionManager(DynamicDataSource dataSource) throws Exception {
        return new DataSourceTransactionManager(dataSource);
    }
}
