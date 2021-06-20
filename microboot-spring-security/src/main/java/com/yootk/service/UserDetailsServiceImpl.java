package com.yootk.service;

import com.yootk.vo.Member;
import com.yootk.vo.Role;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserDetailsServiceImpl implements UserDetailsService { // 实现全部的认证与授权数据加载
    // 本次的开发暂时不基于数据库实现用户的信息管理，本次账户采用固定的密码为“hello”
    private static final String PASSWORD =
            "{bcrypt}$2a$10$2ddAwTKN4ZZ8cNB1YgQmNeOqSLcqcTNDOF0hAxQkRWBIij1XlMvae"; // 加密后的密码
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 以下的操作暂时使用固定的数据项进行处理，随后将其再修改为基于数据库的开发
        if (!"admin".equals(username)) {    // 用户输入的用户名会传递过来
            throw new UsernameNotFoundException("用户信息不存在。"); // 这个时候会自动的触发登录异常
        }
        Member member = new Member(); // UserDetails接口的子类
        member.setMid("admin"); // 固定的账户信息
        member.setPassword(PASSWORD); // 加密后的密码数据
        member.setName("沐言科技管理员"); // 纯粹是一个打酱油的信息
        member.setEnabled(1);// 该账户为启用状态
        Role roleAdmin = new Role(); // 用户的角色数据
        roleAdmin.setRid("ROLE_ADMIN"); // 必须使用“ROLE_”开头
        roleAdmin.setTitle("管理员"); // 【打酱油的信息】仅仅做为一个标注
        Role roleUser = new Role(); // 用户的角色数据
        roleUser.setRid("ROLE_USER"); // 必须使用“ROLE_”开头
        roleUser.setTitle("用户"); // 【打酱油的信息】仅仅做为一个标注
        // 所有的角色数据一定要与UserDetails进行匹配
        member.setRoles(Arrays.asList(roleAdmin, roleUser)); // 用户的角色数据
        return member;
    }
}
