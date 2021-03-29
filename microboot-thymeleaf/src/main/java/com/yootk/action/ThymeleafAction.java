package com.yootk.action;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller // 此时不是进行了Rest响应
@RequestMapping("/thymeleaf/*") // 设置父路径
public class ThymeleafAction {
    //    @RequestMapping("view")
//    public ModelAndView view(String message) {
//        ModelAndView mav = new ModelAndView("message/message_show"); // 设置跳转路径
//        mav.addObject("message", message); // 用户发送的请求参数
//        mav.addObject("title", "沐言科技");// 由用户自定义传递的属性
//        mav.addObject("content", "www.yootk.com");// 由用户自定义传递的属性
//        return mav;
//    }
    @RequestMapping("view")
    public String view(String message, Model model) {
        model.addAttribute("message", message); // 用户发送的请求参数
        model.addAttribute("title", "沐言科技");// 由用户自定义传递的属性
        model.addAttribute("content", "www.yootk.com");// 由用户自定义传递的属性
        return "message/message_show";
    }
}
