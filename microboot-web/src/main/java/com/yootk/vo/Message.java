package com.yootk.vo;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
@Data // 只要使用Lombok组件，99%的情况下就使用这一个注解
public class Message {
    private String title; // 字符串的参数
    @JsonFormat(pattern = "yyyy年MM月dd日") // FastJSON组件所提供的转换格式
//    @JSONField(format = "yyyy年MM月dd日") // FastJSON组件所提供的转换格式
    private Date pubdate; // 日期参数
    private String content;
}
