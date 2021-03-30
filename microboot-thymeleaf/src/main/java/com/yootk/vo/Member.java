package com.yootk.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data // 自动生成结构
@AllArgsConstructor // 全参构造
public class Member {
    private String mid;
    private String name;
    private Integer age;
    private Double salary;
    private Date birthday;
}
