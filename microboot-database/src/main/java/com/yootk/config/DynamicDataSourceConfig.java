package com.yootk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DynamicDataSourceConfig { // 动态数据源配置
    @Bean("dataSource")
    @Primary // 注入DataSource实例的时候此为优先考虑
    @DependsOn({"druidMuyanDataSource", "druidYootkDataSource"}) // 依赖数据源
    public DynamicDataSource getDataSource(
            @Autowired DataSource druidMuyanDataSource,
            @Autowired DataSource druidYootkDataSource
    ) {  // 动态数据源的切换
        Map<Object, Object> targetDataSources = new HashMap<>(5); // 配置一个初始化大小
        targetDataSources.put(DynamicDataSource.DataSourceNames.MUYAN_DATASOURCE, druidMuyanDataSource); // 绑定数据源
        targetDataSources.put(DynamicDataSource.DataSourceNames.YOOTK_DATASOURCE, druidYootkDataSource); // 绑定数据源
        return new DynamicDataSource(druidMuyanDataSource, targetDataSources); // 获取一个动态DataSource实例
    }
}
