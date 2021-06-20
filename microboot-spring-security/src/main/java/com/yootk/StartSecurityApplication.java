package com.yootk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.yootk.dao") // 配置扫描包
@EntityScan("com.yootk.vo") // 配置实体类扫描包
@SpringBootApplication // 一个注解解决所有的问题
public class StartSecurityApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(StartSecurityApplication.class,args); // 运行SpringBoot程序
    }
}