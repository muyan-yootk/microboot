package com.yootk.vo;

import lombok.Data;

import java.util.Date;
@Data // 只要使用Lombok组件，99%的情况下就使用这一个注解
public class Message {
    private String title; // 字符串的参数
    private Date pubdate; // 日期参数
    private String content;
}
