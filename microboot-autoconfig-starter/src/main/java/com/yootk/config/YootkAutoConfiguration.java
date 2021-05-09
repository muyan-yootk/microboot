package com.yootk.config;

import com.yootk.regist.DefaultImportBeanDefinitionRegistrar;
import com.yootk.vo.Dept;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.List;

@Configuration // 需要进行Bean的注册
@Import({DefaultImportBeanDefinitionRegistrar.class}) // Bean注册
public class YootkAutoConfiguration { // 自动装配类
    @Bean(name = "books")
    public List<String> getBookList() { // 自定义一个注册Bean对象
        return List.of("Java面向对象编程", "Java就业编程实战",
                "JavaWEB就业编程实战", "Spring就业编程实战",
                "SpringBoot就业编程实战", "SpringCloud就业编程实战");
    }
}