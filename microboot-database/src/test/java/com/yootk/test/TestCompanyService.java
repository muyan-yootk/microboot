package com.yootk.test;

import com.yootk.StartSpringBootDatabaseApplication;
import com.yootk.service.ICompanyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

@ExtendWith(SpringExtension.class) // 使用JUnit5测试工具
@WebAppConfiguration // 启动WEB运行环境
@SpringBootTest(classes = StartSpringBootDatabaseApplication.class) // 配置程序启动类
public class TestCompanyService { // 编写测试类
    @Autowired
    private ICompanyService companyService;
    @Test
    public void testList() {    // 进行响应测试
        System.out.println(this.companyService.list());
    }
}
