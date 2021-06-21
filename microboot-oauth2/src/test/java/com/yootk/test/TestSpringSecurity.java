package com.yootk.test;

import com.yootk.StartOAuth2Application;
import com.yootk.dao.IMemberDAO;
import com.yootk.vo.Member;
import com.yootk.vo.Role;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Optional;

@ExtendWith(SpringExtension.class) // Junit5测试工具
@WebAppConfiguration    // 表示需要启动Web配置才可以进行测试
@SpringBootTest(classes = StartOAuth2Application.class)  // 定义要测试的启动类
public class TestSpringSecurity {
    @Autowired
    private IMemberDAO memberDAO;

    @Test
    public void testSelect() {
        Optional<Member> optional = this.memberDAO.findById("admin");
        if (optional.isPresent()) {
            Member member = optional.get();
            if (member != null) {
                System.out.println("用户姓名：" + member.getName());
                for (Role role : member.getRoles()) {
                    System.out.println("\t|- 【角色】角色ID = " + role.getRid() + "、角色名称 = " + role.getTitle());
                }
            }
        }
    }
}
