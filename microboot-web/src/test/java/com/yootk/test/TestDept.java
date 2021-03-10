package com.yootk.test;

import com.yootk.vo.Dept;

public class TestDept {
    public static void main(String[] args) {
        Dept dept = new Dept();
        dept.setDname("沐言科技教学部").setLoc("北京").setDeptno(10L);
        System.out.println(dept);
    }
}
