package com.yootk.vo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@PropertySource(value = "classpath:muyan.properties", encoding = "UTF-8")
@ConfigurationProperties(prefix = "object")
@Component
public class Dept {
    private Long deptno;
    private String dname;
    private Company company; // 关联结构
    private List<Emp> emps;
}
