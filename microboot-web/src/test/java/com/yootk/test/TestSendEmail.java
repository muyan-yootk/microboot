package com.yootk.test;

import com.yootk.StartSpringBootApplication;
import com.yootk.service.IMessageService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

@ExtendWith(SpringExtension.class) // 使用JUnit5测试工具
@WebAppConfiguration // 启动WEB运行环境
@SpringBootTest(classes = StartSpringBootApplication.class) // 配置程序启动类
public class TestSendEmail { // 编写测试类
    @Autowired
    private JavaMailSender javaMailSender; // 发送邮件工具类
    @Test
    public void testSend() {    // 进行响应测试
        SimpleMailMessage message = new SimpleMailMessage(); // 建立一个简单的邮件结构
        message.setFrom("784420216@qq.com");
        message.setTo("2200934106@qq.com"); // 邮件的接收者
        message.setSubject("来自爆可爱的小李老师给你的祝福。");
        message.setText("沐言科技（www.yootk.com）");
        this.javaMailSender.send(message); // 邮件发送
    }
}
