package com.yootk.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yootk.dao.muyan.IDeptDAO;
import com.yootk.dao.yootk.IEmpDAO;
import com.yootk.service.ICompanyService;
import com.yootk.vo.Dept;
import com.yootk.vo.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
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

    @Override
    public boolean add(Map<Dept, List<Emp>> infos) {
        for (Map.Entry<Dept, List<Emp>> entry : infos.entrySet()) {
            this.deptDAO.insert(entry.getKey()); // 增加部门数据
            for (Emp emp : entry.getValue()) {
                this.empDAO.insert(emp); // 增加雇员数据
            }
        }
        return true;
    }
}
