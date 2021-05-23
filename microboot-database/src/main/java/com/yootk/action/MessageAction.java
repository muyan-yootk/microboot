package com.yootk.action;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/message/*")
public class MessageAction {
    @RequestMapping("echo")
    public Object echo(String msg) {
        List<String> list = new ArrayList<>();
        for (int x = 0; x < 10; x++) {
            list.add("【ECHO】" + msg);
        }
        return list;
    }
}
