package com.yootk.action;

import com.yootk.common.action.abs.AbstractBaseAction;
import com.yootk.service.IMemberService;
import com.yootk.vo.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/member/*")
@Slf4j
public class MemberAction extends AbstractBaseAction {
    @Autowired
    private IMemberService memberService;
    @RequestMapping("list")
    public Object list() {
        log.info("查询全部Member数据。");
        return this.memberService.list();
    }
    @RequestMapping("get")
    public Object get(String mid) {
        log.info("查询用户数据：{}", mid);
        return this.memberService.get(mid);
    }
    @RequestMapping("add")
    public Object add(Member member) {
        log.info("增加新的用户数据：{}", member);
        return this.memberService.add(member);
    }
    @RequestMapping("delete")
    public Object delete(String ... id) {
        log.info("根据ID删除数据：{}", Arrays.asList(id));
        Set<String> ids = new HashSet<>();
        ids.addAll(Arrays.asList(id));
        return this.memberService.delete(ids);
    }
    @RequestMapping("split")
    public Object split(String column, String keyword, int currentPage, int lineSize) {
        log.info("数据分页显示，查询模糊列：{}、查询关键字：{}、当前页：{}、每页行数：{}");
        return this.memberService.listSplit(column, keyword, currentPage, lineSize);
    }
}
