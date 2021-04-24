package com.yootk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.context.request.async.TimeoutCallableProcessingInterceptor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class CustomeAsyncPoolConfig implements WebMvcConfigurer { // 自定义的线程池配置

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) { // 异步配置
        configurer.setDefaultTimeout(10000); // 配置超时时间
        configurer.registerCallableInterceptors(this.getTimeoutInterceptor()); // 设置Callable拦截器
        configurer.setTaskExecutor(this.getAysncThreadPoolTaskExecutor()); // 异步任务执行配置
    }
    @Bean(name = "asyncPoolTaskExecutor") // SpringBoot内部本身就有线程池提供
    public ThreadPoolTaskExecutor getAysncThreadPoolTaskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(20); // 内核线程的个数（物理线程个数 * 2）
        taskExecutor.setMaxPoolSize(200); // 工作线程池大小
        taskExecutor.setQueueCapacity(25); // 设置一个延迟队列大小
        taskExecutor.setKeepAliveSeconds(200); // 存活时间
        taskExecutor.setThreadNamePrefix("yootk - "); // 配置前缀，自定义的个性化配置
        taskExecutor.setRejectedExecutionHandler( // 配置拒绝策略
                new ThreadPoolExecutor.CallerRunsPolicy());
        taskExecutor.initialize(); // 线程池初始化配置
        return taskExecutor;
    }
    @Bean
    public TimeoutCallableProcessingInterceptor getTimeoutInterceptor() {   // 超时处理
        return new TimeoutCallableProcessingInterceptor();
    }
}
