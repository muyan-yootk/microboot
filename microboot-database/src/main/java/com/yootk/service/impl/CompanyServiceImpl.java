package com.yootk.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yootk.dao.muyan.IDeptDAO;
import com.yootk.dao.yootk.IEmpDAO;
import com.yootk.service.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CompanyServiceImpl implements ICompanyService {
    @Autowired
    private IDeptDAO deptDAO;
    @Autowired
    private IEmpDAO empDAO;
    @Override
    public Map<String, Object> list() {
        Map<String, Object> map = new HashMap<>();
        map.put("allDepts", this.deptDAO.selectList(new QueryWrapper<>()));
        map.put("allEmps", this.empDAO.selectList(new QueryWrapper<>()));
        return map;
    }
}
