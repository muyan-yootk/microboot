package com.yootk.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller // 此时不是进行了Rest响应
@RequestMapping("/thymeleaf/*") // 设置父路径
public class ThymeleafAction {
    @RequestMapping("attribute")
    public String attribute(HttpServletRequest request, HttpServletResponse response) {
        // 考虑到讲解问题，本次将设置重名的属性，但是属性的范围不同
        request.setAttribute("message", "【REQUEST】沐言科技：www.yootk.com");
        request.getSession().setAttribute("message", "【SESSION】沐言科技：www.yootk.com");
        request.getServletContext().setAttribute("message", "【APPLICATION】沐言科技：www.yootk.com");
        return "message/message_attribute"; // 跳转路径
    }
}
