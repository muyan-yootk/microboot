package com.yootk.test;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yootk.StartSpringBootDatabaseApplication;
import com.yootk.service.IMemberService;
import com.yootk.vo.Member;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Date;
import java.util.Set;

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

    @Test
    public void testGet() {
        System.out.println(this.memberService.get("yootk"));
    }

    @Test
    public void testAddBatchRepeatID() {
        this.memberService.addBatch("muyan", "yootk", "lixinghua", "happy-summery");
    }
    @Test
    public void testAddBatchNoRepeatID() {
        this.memberService.addBatch("muyan-happy", "yootk-happy", "lixinghua-happy", "happy-summery");
    }
    @Test
    public void testAdd() {
        Member vo = new Member();
        vo.setMid("yootk - " + Math.random());
        vo.setName("沐言科技");
        vo.setBirthday(new Date());
        vo.setSalary(865.0);
        vo.setContent("www.yootk.com");
        vo.setAge(16);
        System.out.println(this.memberService.add(vo));
    }

    @Test
    public void testSplit() {
        IPage<Member> page = this.memberService.listSplit("name", "沐言", 1, 5);
        System.out.println("总页数：" + page.getPages());
        System.out.println("总记录数：" + page.getTotal());
        System.out.println("响应内容：" + page.getRecords());
    }
    @Test
    public void testDelete() {
        Set<String> ids = Set.of("muyan", "yootk", "lixinghua");
        this.memberService.delete(ids);
    }
}
