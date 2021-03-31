package com.yootk.action;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;
import java.util.Set;

@Controller // 此时不是进行了Rest响应
@RequestMapping("/thymeleaf/*") // 设置父路径
public class ThymeleafAction {
    @RequestMapping("handle")
    public String handle(Model model) throws Exception {
        model.addAttribute("message", "www.YOOTK.com");
        model.addAttribute("language", Set.of("Java", "Python", "GoLang"));
        model.addAttribute("infos", Map.of("yootk", "yootk.com", "edu", "edu.yootk.com"));
        return "message/message_handle"; // 跳转路径
    }
}
