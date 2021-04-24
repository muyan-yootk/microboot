package com.yootk.config;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync // 启用异步处理
public class DefaultThreadPoolConfig implements AsyncConfigurer { // 异步配置

    @Override
    public Executor getAsyncExecutor() { // 异步的执行者
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor(); // 创建一个线程池
        executor.setCorePoolSize(10); // 核心线程数
        executor.setMaxPoolSize(20); // 最大数量
        executor.setQueueCapacity(100); // 延迟队列长度
        executor.setThreadNamePrefix("muyan - "); // 设置线程名称后缀
        executor.initialize();
        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() { // 异常处理
        return new SimpleAsyncUncaughtExceptionHandler();
    }
}
