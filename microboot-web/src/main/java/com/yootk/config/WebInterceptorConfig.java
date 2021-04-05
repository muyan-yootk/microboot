package com.yootk.config;

import com.yootk.interceptor.MDCInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebInterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.getMDCInterceptor()).addPathPatterns("/**"); // 全链路追踪
    }
    @Bean
    public HandlerInterceptor getMDCInterceptor() {
        return new MDCInterceptor(); // 获取拦截器实例
    }
}
