package com.yootk.config;

import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

// @Configuration
public class DruidSpringConfig { // Spring监控配置类
    @Bean("druidStatInterceptor")
    public DruidStatInterceptor getDruidStatInterceptor() {
        DruidStatInterceptor interceptor = new DruidStatInterceptor(); // 创建Druid拦截器
        return interceptor;
    }
    @Bean("druidSpringStatPointcut")
    @Scope("prototype")
    public JdkRegexpMethodPointcut getDruidSpringStatPointcut() {   // 获取切面
        JdkRegexpMethodPointcut pointcut = new JdkRegexpMethodPointcut();
        pointcut.setPatterns("com.yootk.service.*", "com.yootk.action.*", "com.yootk.dao.*");
        return pointcut;
    }
    @Bean("druidSpringStatAdvisor")
    public DefaultPointcutAdvisor getDruidSpringStatAdvisor(
            DruidStatInterceptor druidStatInterceptor,
            JdkRegexpMethodPointcut jdkRegexpMethodPointcut
    ) {
        DefaultPointcutAdvisor defaultPointcutAdvisor = new DefaultPointcutAdvisor();
        defaultPointcutAdvisor.setPointcut(jdkRegexpMethodPointcut);
        defaultPointcutAdvisor.setAdvice(druidStatInterceptor);
        return defaultPointcutAdvisor;
    }
}
