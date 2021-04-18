package com.yootk.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
@Data // 只要使用Lombok组件，99%的情况下就使用这一个注解
@AllArgsConstructor
public class Message {
    private String title; // 字符串的参数
    private String url;
}
