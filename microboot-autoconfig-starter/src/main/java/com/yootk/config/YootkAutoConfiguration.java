package com.yootk.config;

import com.yootk.vo.Dept;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration // 需要进行Bean的注册
@EnableConfigurationProperties({Dept.class}) // Bean注册
public class YootkAutoConfiguration { // 自动装配类
}
