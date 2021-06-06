package com.yootk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication // 一个注解解决所有的问题
public class StartSecurityApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(StartSecurityApplication.class,args); // 运行SpringBoot程序
    }
}