package com.yootk.vo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "muyan.yootk.dept")
public class Dept {
    private Long deptno;
    private String dname;
    private String loc;
}
