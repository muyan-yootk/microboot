package com.yootk.service.impl.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yootk.dao.IMemberDAO;
import com.yootk.service.IMemberService;
import com.yootk.vo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

// @Service
public class MemberServiceImpl implements IMemberService {
    @Autowired
    private IMemberDAO memberDAO;

    @Override
    public List<Member> list() {
        return this.memberDAO.findAll();
    }

    @Override
    public Member get(String mid) {
        return this.memberDAO.selectById(mid);
    }

    @Override
    public boolean add(Member vo) {
        return this.memberDAO.insert(vo) > 0;
    }

    @Override
    public boolean delete(Set<String> ids) {
        return this.memberDAO.deleteBatchIds(ids) == ids.size();
    }

    @Override
    public IPage<Member> listSplit(String column, String keyword, Integer currentPage, Integer lineSize) {
        QueryWrapper<Member> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(column, keyword); // 模糊查询
        int count = this.memberDAO.selectCount(queryWrapper); // 统计数据行数
        IPage<Member> page = new Page<>(currentPage, lineSize, count);
        return this.memberDAO.selectPage(page, queryWrapper);
    }

    @Override
    public boolean addBatch(String... mid) {
        for (String id : mid) {
            Member vo = new Member();
            vo.setMid(id); // 如果id重复则会出现更新异常
            vo.setName("爆可爱的小李老师");
            this.memberDAO.insert(vo);
        }
        return true;
    }
}
