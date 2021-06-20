package com.yootk.action;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class ResourceAction {
    @RequestMapping("/resource") // 资源路径
    public Principal resource(Principal principal) {
        return principal; // 保存了用户的全部配置信息
    }
}
