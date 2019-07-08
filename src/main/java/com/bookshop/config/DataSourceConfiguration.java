package com.bookshop.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.bookshop.util.DESUtils;
import java.beans.PropertyVetoException;

/*
配置DataSource到ioc容器
 */
@Configuration
@MapperScan("com.bookshop.dao")
public class DataSourceConfiguration {
    @Value("${jdbc.driver}")
    private String jdbcDriver;
    @Value("${jdbc.url}")
    private String jdbcUrl;
    @Value("${jdbc.username}")
    private String jdbcUsername;
    @Value("${jdbc.password}")
    private String jdbcPassword;

    @Bean(name="dataSource")
    public ComboPooledDataSource createDataSource() throws PropertyVetoException {
//生成datasource实例
        ComboPooledDataSource dataSource =new ComboPooledDataSource();

//驱动
        dataSource.setDriverClass(jdbcDriver);
//数据库连接URL
        dataSource.setJdbcUrl(jdbcUrl);
//设置用户名
        dataSource.setUser(DESUtils.getDecryptString(jdbcUsername));
//设置用户密码
        dataSource.setPassword(DESUtils.getDecryptString(jdbcPassword));
        //配置c3pO连接池的私有属性
//连接池最大线程数
        dataSource.setMaxPoolSize(30);
//连接池最小线程数
        dataSource.setMinPoolSize(10);
//关闭连接后不自动commit
        dataSource.setAutoCommitOnClose(false);
//连接超时时间
        dataSource.setCheckoutTimeout(10000);
//连接失败重试次数
        dataSource.setAcquireRetryAttempts(2);
        return dataSource;
      }
}