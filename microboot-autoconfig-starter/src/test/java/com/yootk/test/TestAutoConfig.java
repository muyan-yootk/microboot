package com.yootk.test;

import com.yootk.StartAutoConfigApplication;
import com.yootk.vo.Dept;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

@ExtendWith(SpringExtension.class) // 使用JUnit5测试工具
@WebAppConfiguration // 启动WEB运行环境
@SpringBootTest(classes = StartAutoConfigApplication.class) // 配置程序启动类
public class TestAutoConfig { // 编写测试类
    @Autowired
    @Qualifier("muyan.yootk.dept-com.yootk.vo.Dept") // “前缀 + 类名称”
    private Dept dept;

    @Test
    public void testDept() {    // 进行响应测试
        System.out.println(this.dept);
    }
}
