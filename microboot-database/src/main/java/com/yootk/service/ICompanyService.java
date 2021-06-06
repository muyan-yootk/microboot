package com.yootk.service;

import com.yootk.vo.Dept;
import com.yootk.vo.Emp;

import java.util.List;
import java.util.Map;

public interface ICompanyService {
    public Map<String, Object> list() ;
    public boolean add(Map<Dept, List<Emp>>infos); // 增加新的数据
}
