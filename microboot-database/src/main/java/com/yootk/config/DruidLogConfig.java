package com.yootk.config;

import com.alibaba.druid.filter.logging.Slf4jLogFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DruidLogConfig {
    @Bean("logFilter")
    public Slf4jLogFilter getLogFilter() {
        Slf4jLogFilter logFilter = new Slf4jLogFilter();
        logFilter.setDataSourceLogEnabled(true); // 启用数据库的日志
        logFilter.setStatementExecutableSqlLogEnable(true); // 记录执行日志
        return logFilter;
    }
}
