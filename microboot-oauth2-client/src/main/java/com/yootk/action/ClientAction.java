package com.yootk.action;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Rest响应
public class ClientAction {
    @GetMapping("/client")
    public Object client() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); // 获取认证数据
        return authentication; // 数据响应
    }
}
