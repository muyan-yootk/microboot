package com.yootk.config;

import com.yootk.interceptor.MessageValidateInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.getMessageInterceptor()).addPathPatterns("/message/**");
    }
    @Bean
    public HandlerInterceptor getMessageInterceptor() {
        return new MessageValidateInterceptor();
    }
}
