package com.yootk.action;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // Rest响应
@RequestMapping("/message/*") // 访问父路径
public class MessageAction {
    @GetMapping("show") // 子路径
    public Object show() {  // 信息显示
        return "www.yootk.com"; // 响应信息
    }
}