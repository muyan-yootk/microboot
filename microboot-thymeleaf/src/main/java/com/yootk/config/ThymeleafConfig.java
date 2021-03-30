package com.yootk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
public class ThymeleafConfig implements WebMvcConfigurer { // 进行WEB配置
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver(); // 进行当前Session对应的Locale配置
        slr.setDefaultLocale(Locale.SIMPLIFIED_CHINESE); // 设置默认的解析器
        return slr;
    }
    public LocaleChangeInterceptor localeChangeInterceptor() {  // 修改Locale实例的拦截器
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang"); // 根据lang的参数来设置不同的Locale实例
        return lci;
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) { // 通过拦截器来进行Locale处理
        registry.addInterceptor(localeChangeInterceptor()); // 添加拦截器
    }
}
