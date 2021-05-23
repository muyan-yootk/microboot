package com.yootk; // 父包，这个包中的所有子包的类会被自动扫描

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication // 一个注解解决所有的问题
public class StartSpringBootDatabaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(StartSpringBootDatabaseApplication.class, args); // 运行SpringBoot程序
    }
}
