package com.yootk.action;

import com.yootk.vo.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller // 此时不是进行了Rest响应
@RequestMapping("/member/*") // 设置父路径
public class MemberAction {
    @RequestMapping("map")
    public String map(Model model) throws Exception {
        Map<String, Member> memberMap = new HashMap<>();
        for (int x = 0; x < 10; x++) {
            Member member = new Member("yootk - " + x, "李兴华", 16 + x, 888.66 + x * 100,
                    new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("1997-07-15 21:10:32"));
            memberMap.put("muyan - " + x, member); // 将对象保存在集合之中
        }
        model.addAttribute("memberMap", memberMap);
        return "member/member_map"; // 跳转路径
    }
    @RequestMapping("list")
    public String list(Model model) throws Exception {
        List<Member> memberList = new ArrayList<>();
        for (int x = 0; x < 10; x++) {
            Member member = new Member("yootk - " + x, "李兴华", 16 + x, 888.66 + x * 100,
                    new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse("1997-07-15 21:10:32"));
            memberList.add(member); // 将对象保存在集合之中
        }
        model.addAttribute("memberList", memberList);
        return "member/member_list"; // 跳转路径
    }
}
