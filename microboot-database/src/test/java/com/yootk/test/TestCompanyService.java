package com.yootk.test;

import com.yootk.StartSpringBootDatabaseApplication;
import com.yootk.service.ICompanyService;
import com.yootk.vo.Dept;
import com.yootk.vo.Emp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Test
    public void testAdd() {
        Map<Dept, List<Emp>> map = new HashMap<>(); // 准备要添加的数据集合
        for (int x = 0; x < 3; x ++) {  // 循环数据配置
            Dept dept = new Dept();
            dept.setDname("资源部 - " + x);
            dept.setLoc("天津 - " + x);
            Emp emp = new Emp();
            emp.setEid("muyan-yootk-lixinghua"); // 如果重复增加，这个数据一定是重复的
            emp.setEname("小李老师");
            map.put(dept, List.of(emp)); // 一个部门只设计有一个员工
        }
        System.out.println(this.companyService.add(map)); // 调用业务处理
    }
    @Test
    public void testAdd2() {
        Map<Dept, List<Emp>> map = new HashMap<>(); // 准备要添加的数据集合
        for (int x = 0; x < 3; x ++) {  // 循环数据配置
            Dept dept = new Dept();
            dept.setDname("资源部 - " + x);
            dept.setLoc("天津 - " + x);
            Emp emp = new Emp();
            emp.setEid("muyan-yootk-lixinghua - " + x); // 如果重复增加，这个数据一定是重复的
            emp.setEname("小李老师");
            map.put(dept, List.of(emp)); // 一个部门只设计有一个员工
        }
        System.out.println(this.companyService.add(map)); // 调用业务处理
    }
}
