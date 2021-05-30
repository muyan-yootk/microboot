package com.yootk.test;

import com.yootk.StartSpringBootDatabaseApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.sql.DataSource;
import java.sql.SQLException;

@ExtendWith(SpringExtension.class) // 使用JUnit5测试工具
@WebAppConfiguration // 启动WEB运行环境
@SpringBootTest(classes = StartSpringBootDatabaseApplication.class) // 配置程序启动类
public class TestMultiDruidDataSource { // 编写测试类
    @Autowired
    @Qualifier("druidMuyanDataSource") // 设置要注入的Bean名称
    private DataSource muyanDataSource;
    @Autowired
    @Qualifier("druidYootkDataSource") // 设置要注入的Bean名称
    private DataSource yootkDataSource;
    @Test
    public void testDruid() {    // 进行响应测试
        try {
            System.out.println(this.muyanDataSource.getConnection());
            System.out.println(this.yootkDataSource.getConnection());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
