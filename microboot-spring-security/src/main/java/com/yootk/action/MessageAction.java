package com.yootk.action;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message/*")
public class MessageAction {
    @GetMapping("show")
    public Object show() {
        return "沐言科技：www.yootk.com";
    }
}
