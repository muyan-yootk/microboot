package com.yootk.config;

import com.yootk.regist.DefaultImportBeanDefinitionRegistrar;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration // 需要进行Bean的注册
@Import({DefaultImportBeanDefinitionRegistrar.class}) // Bean注册
public class YootkAutoConfiguration { // 自动装配类
}
