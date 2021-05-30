package com.yootk.test;

import com.yootk.StartSpringBootDatabaseApplication;
import com.yootk.service.IMemberService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

@ExtendWith(SpringExtension.class) // 使用JUnit5测试工具
@WebAppConfiguration // 启动WEB运行环境
@SpringBootTest(classes = StartSpringBootDatabaseApplication.class) // 配置程序启动类
public class TestMemberService { // 编写测试类
    @Autowired
    private IMemberService memberService;
    @Test
    public void testFindAll() {
        System.out.println(this.memberService.list());
    }
}
