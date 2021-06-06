package com.yootk.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Slf4j // 为了便于观察，将所有的数据全部都以日志的形式输出
@Aspect // 是一个切面处理类
@Order(-100) // 让这个切面处理类的执行顺序可以高一些
public class DataSourceAspect { // 数据源的切面处理类
    @Before("execution(* com.yootk.dao.muyan..*.*(..))") // 在每次操作之前进行切换处理
    public void switchMuyanDataSource() {   // 切换指定的数据源
        DynamicDataSource.setDataSource(DynamicDataSource.DataSourceNames.MUYAN_DATASOURCE); // 设置数据源的名称
        log.info("数据源切换到“MUYAN”：{}", DynamicDataSource.getDataSource()); // 日志输出
    }
    @Before("execution(* com.yootk.dao.yootk..*.*(..))") // 在每次操作之前进行切换处理
    public void switchYootkDataSource() {   // 切换指定的数据源
        DynamicDataSource.setDataSource(DynamicDataSource.DataSourceNames.YOOTK_DATASOURCE); // 设置数据源的名称
        log.info("数据源切换到“YOOTK”：{}", DynamicDataSource.getDataSource()); // 日志输出
    }
}
