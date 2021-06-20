package com.yootk.service;

import com.yootk.dao.IMemberDAO;
import com.yootk.vo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService { // 实现全部的认证与授权数据加载
    @Autowired
    private IMemberDAO memberDAO; // 注入JPA接口
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> optional = this.memberDAO.findById(username); // 根据ID查询数据
        if (optional.isEmpty()) {   // 此时无法获取到数据信息
            throw new UsernameNotFoundException("用户信息不存在！");
        }
        return optional.get(); // 获取认证与授权数据
    }
}
