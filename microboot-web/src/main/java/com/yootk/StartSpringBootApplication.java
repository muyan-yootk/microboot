package com.yootk; // 父包，这个包中的所有子包的类会被自动扫描

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication // 一个注解解决所有的问题
// 如果此时你项目中的过滤器使用了配置类的方式配置，则配置监听器的时候为了防止重复Bean注册，所以指定扫描包名称
@ServletComponentScan({"com.yootk.listener"}) // Servlet组件扫描
public class StartSpringBootApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(StartSpringBootApplication.class,args); // 运行SpringBoot程序
    }
}
