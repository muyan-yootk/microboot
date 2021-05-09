package com.yootk.test;

import com.yootk.StartAutoConfigApplication;
import com.yootk.config.YootkAutoConfiguration;
import com.yootk.vo.Dept;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

@ExtendWith(SpringExtension.class) // 使用JUnit5测试工具
@WebAppConfiguration // 启动WEB运行环境
@SpringBootTest(classes = StartAutoConfigApplication.class) // 配置程序启动类
public class TestSpringContextBeanInfo { // 编写测试类
    @Test
    public void testBeanInfo() {    // 进行响应测试
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(YootkAutoConfiguration.class);
        String names[] = context.getBeanDefinitionNames(); // 获取Bean名称
        for (String name : names) {
            System.out.println("【"+name+"】" + context.getBean(name).getClass().getName());
        }
    }
}
