package com.yootk.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yootk.vo.Member;

import java.util.List;
import java.util.Set;

public interface IMemberService {
    public List<Member> list();
    public Member get(String mid); // 根据ID查询
    public boolean add(Member vo); // 增加数据
    public boolean delete(Set<String> ids); // 数据删除
    public IPage<Member> listSplit(String column, String keyword,
                                   Integer currentPage, Integer lineSize); // 分页查询
}
