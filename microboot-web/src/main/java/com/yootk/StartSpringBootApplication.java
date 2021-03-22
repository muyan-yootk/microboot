package com.yootk; // 父包，这个包中的所有子包的类会被自动扫描

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication // 一个注解解决所有的问题
public class StartSpringBootApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(StartSpringBootApplication.class,args); // 运行SpringBoot程序
    }
}
