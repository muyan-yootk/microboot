package com.yootk.action;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin/message/*") // 定义父路径
public class MessageAction {
    @RequestMapping("echo")
    @RequiresPermissions("message:echo") // 授权判断
    public Object echo(String msg) {
        return "【ECHO】" + msg;
    }
    @RequiresPermissions("message:list") // 授权判断
    public Object list(String msg) {
        List<String> list = new ArrayList<>();
        for (int x = 0; x < 10; x ++) {
            list.add("【LIST】" + msg);
        }
        return list;
    }
}
