package com.yootk.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // 此时不是进行了Rest响应
@RequestMapping("/thymeleaf/*") // 设置父路径
public class ThymeleafAction {
    @RequestMapping("i18n")
    public String path() {
        return "message/message_i18n"; // 跳转路径
    }
}
