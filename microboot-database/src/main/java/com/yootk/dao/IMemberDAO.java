package com.yootk.dao;

import com.yootk.vo.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IMemberDAO { // 定义DAO接口
    public List<Member> findAll(); // 查询全部
}
