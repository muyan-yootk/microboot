package com.yootk.test;

import com.yootk.StartJWTApplication;
import com.yootk.config.JWTConfigProperties;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

@ExtendWith(SpringExtension.class) // Junit5测试工具
@WebAppConfiguration    // 表示需要启动Web配置才可以进行测试
@SpringBootTest(classes = StartJWTApplication.class)  // 定义要测试的启动类
public class TestJWTConfigProperties {
    @Autowired
    private JWTConfigProperties configProperties;
    @Test
    public void testConfig() {
        System.out.println(configProperties);
    }
}
