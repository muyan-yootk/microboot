package com.yootk.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yootk.vo.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IMemberDAO extends BaseMapper<Member> { // 定义DAO接口
    public List<Member> findAll(); // 查询全部，这个方法一定要存在有映射实现
}
