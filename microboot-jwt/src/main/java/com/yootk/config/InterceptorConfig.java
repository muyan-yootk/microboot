package com.yootk.config;

import com.yootk.interceptor.JWTAuthenticationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class InterceptorConfig implements WebMvcConfigurer { // 拦截配置类

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.getJWTAuthenticationInterceptor()).addPathPatterns("/**");
    }

    @Bean
    public HandlerInterceptor getJWTAuthenticationInterceptor() {
        return new JWTAuthenticationInterceptor();
    }
}
