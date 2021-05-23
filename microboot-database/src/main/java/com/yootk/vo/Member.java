package com.yootk.vo;

import lombok.Data;

import java.util.Date;

@Data
public class Member {
    private String mid;
    private String name;
    private Integer age;
    private Double salary;
    private Date birthday;
    private String content;
}
