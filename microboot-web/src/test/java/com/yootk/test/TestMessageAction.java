package com.yootk.test;

import com.yootk.StartSpringBootApplication;
import com.yootk.action.MessageAction;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

@ExtendWith(SpringExtension.class) // 使用JUnit5测试工具
@WebAppConfiguration // 启动WEB运行环境
@SpringBootTest(classes = StartSpringBootApplication.class) // 配置程序启动类
public class TestMessageAction { // 编写测试类
    @Autowired
    private MessageAction messageAction; // 注入MessageAction的对象实例
    @BeforeAll
    public static void init() {
        System.err.println("【@BeforeAll】TestMessageAction类开始执行测试操作。");
    }
    @AfterAll
    public static void after() {
        System.err.println("【@AfterAll】TestMessageAction类测试完成。");
    }
    @Test
    public void testEcho() {    // 进行响应测试
        String content = this.messageAction.echo("沐言科技：www.yootk.com");
        String value = "【ECHO】沐言科技：www.yootk.com";
        System.err.println("【@Test】测试echo()方法返回值，当前放回结果为：" + content);
        Assertions.assertEquals(content, value); // 测试相等
    }
}
