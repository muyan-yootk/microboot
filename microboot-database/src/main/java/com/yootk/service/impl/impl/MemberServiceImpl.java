package com.yootk.service.impl.impl;

import com.yootk.dao.IMemberDAO;
import com.yootk.service.IMemberService;
import com.yootk.vo.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImpl implements IMemberService {
    @Autowired
    private IMemberDAO memberDAO;

    @Override
    public List<Member> list() {
        return this.memberDAO.findAll();
    }
}
