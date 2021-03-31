package com.yootk.action;

import com.yootk.vo.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;

@Controller // 此时不是进行了Rest响应
@RequestMapping("/thymeleaf/*") // 设置父路径
public class ThymeleafAction {
    @RequestMapping("show")
    public String show(Model model) throws Exception {
        Member member = new Member("hello", "李兴华", 19, 888.66,
                new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("1997-07-15 21:10:32"));
        model.addAttribute("member", member);
        return "member/member_show"; // 跳转路径
    }
}
