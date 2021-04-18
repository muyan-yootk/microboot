package com.yootk.test;

import com.yootk.StartSpringBootApplication;
import com.yootk.event.YootkEvent;
import com.yootk.vo.Message;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

@ExtendWith(SpringExtension.class) // 使用JUnit5测试工具
@WebAppConfiguration // 启动WEB运行环境
@SpringBootTest(classes = StartSpringBootApplication.class) // 配置程序启动类
public class TestEvent { // 编写测试类
    @Autowired // 是由Spring容器提供的
    private ApplicationEventPublisher publisher; // 事件发布类
    @Test
    public void testEvent() {    // 进行响应测试
        Message message = new Message(); // 实例化Message对象
        message.setTitle("沐言科技");
        message.setUrl("www.yootk.com");
        this.publisher.publishEvent(new YootkEvent(this, message));
    }
}
