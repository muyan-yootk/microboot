package com.yootk.action;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController // Rest数据响应
public class ResourceAction {
    @RequestMapping("/resource")
    public Principal resource(Principal user) {
        return user; // 获取用户详情
    }
}
