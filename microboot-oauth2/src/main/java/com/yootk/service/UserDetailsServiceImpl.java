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
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private IMemberDAO memberDAO;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> optional = this.memberDAO.findById(username); // 数据查询
        if (optional.isEmpty()) {
            throw new UsernameNotFoundException("用户信息不存在！"); // 手工抛出异常
        }
        return optional.get(); // 返回用户数据
    }
}
