package com.yootk.action;

import com.yootk.annotation.JWTCheckToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message/*") // 父路径
public class MessageAction {
    @RequestMapping("echo")
    @JWTCheckToken // 这个资源需要被检查
    public Object echo(String msg) {
        return "【ECHO】" + msg;
    }
}
