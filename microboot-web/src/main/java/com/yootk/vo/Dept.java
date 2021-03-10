package com.yootk.vo;

import lombok.Data;
import lombok.NonNull;

@Data // 本身不会生成构造（默认的无参构造）
public class Dept {
    @NonNull // 该属性不允许为空
    private Long deptno;
    @NonNull // 该属性不允许为空
    private String dname;
    private String loc; // loc属性没有是否强制为空的限制
}
