package com.yootk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@SpringBootApplication // SpringBoot启动注解
@EnableJpaRepositories("com.yootk.dao") // 启用SpringDataJPA
@EntityScan("com.yootk.vo") // JPA实体类扫描包
//@EnableAuthorizationServer // 启用授权服务
public class StartOAuth2Application {
    public static void main(String[] args) { // 沐言科技：www.yootk.com
        SpringApplication.run(StartOAuth2Application.class, args); // 程序启动
    }
}
