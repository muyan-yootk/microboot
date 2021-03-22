package com.yootk.config;

import com.yootk.interceptor.DefaultHandlerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration // 配置类
public class WebInterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) { // 追加拦截器
        registry.addInterceptor(this.getDefaultHandlerInterceptor()).addPathPatterns("/**"); // 配置拦截路径
    }
    @Bean
    public HandlerInterceptor getDefaultHandlerInterceptor() {
        return new DefaultHandlerInterceptor();
    }
}
