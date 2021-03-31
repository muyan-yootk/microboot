package com.yootk.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // 此时不是进行了Rest响应
@RequestMapping("/thymeleaf/*") // 设置父路径
public class ThymeleafAction {
    @RequestMapping("include")
    public String include() throws Exception {
        return "message/message_include"; // 跳转路径
    }
}
