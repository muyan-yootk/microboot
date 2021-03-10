package com.yootk.vo;

import lombok.Data;
import lombok.experimental.Accessors;

@Data // 本身不会生成构造（默认的无参构造）
@Accessors(chain = true)
public class Dept {
    private Long deptno;
    private String dname;
    private String loc;
}
