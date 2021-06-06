package com.yootk.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

//@Configuration
public class DruidMultiDataSourceConfiguration { // 自定义的Druid配置类
//    @Bean("druidMuyanDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.muyan")
    public DataSource getMuyanDataSource() {
        return DruidDataSourceBuilder.create().build();
    }
//    @Bean("druidYootkDataSource")
//    @ConfigurationProperties(prefix = "spring.datasource.yootk")
    public DataSource getYootkDataSource() {
        return DruidDataSourceBuilder.create().build();
    }
}
