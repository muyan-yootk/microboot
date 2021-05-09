package com.yootk.test;

import com.yootk.StartSpringBootApplication;
import com.yootk.vo.Dept;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@ExtendWith(SpringExtension.class) // 使用JUnit5测试工具
@WebAppConfiguration // 启动WEB运行环境
@SpringBootTest(classes = StartSpringBootApplication.class) // 配置程序启动类
public class TestAutoConfig { // 编写测试类
    @Autowired // 是由Spring容器提供的
    private Dept dept;
    @Autowired
    private List<String> books;
    @Test
    public void testConfig() {    // 进行响应测试
        System.out.println(this.dept);
        System.out.println(this.books);
    }
}
