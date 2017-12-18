package com.demo.core.config;

import com.alibaba.druid.pool.DruidDataSource;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * Created by 胡超云 on 2016/11/27.
 */
@Data
@Slf4j
@Component
@ConfigurationProperties(prefix = "spring.datasource.read")
public class ReadDruidConfig {

    public DataSource readDataSource() throws Exception {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(this.url);
        druidDataSource.setUsername(this.username);
        druidDataSource.setPassword(this.password);
        druidDataSource.setDriverClassName(this.driverClassName);
        druidDataSource.setInitialSize(this.initialSize);
        druidDataSource.setMaxActive(this.maxActive);
        druidDataSource.setMinIdle(this.minIdle);
        druidDataSource.setFilters(this.filters);
        druidDataSource.setMaxWait(this.maxWait);
        druidDataSource.init();
        return druidDataSource;
    }

    private String url;

    private String username;

    private String password;

    private String driverClassName;

    private int initialSize;

    private int maxActive;

    private int minIdle;

    private String filters;

    private long maxWait;
}
