package com.yootk.vo;

import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
@Data // 只要使用Lombok组件，99%的情况下就使用这一个注解
public class Message {
    @NotBlank(message = "{message.title.notblank.error}") // 该字段的内容不允许为空
    private String title; // 字符串的参数
    @NotNull(message = "{message.pubdate.notnull.error}") // 该字段的内容不允许为空
    private Date pubdate; // 日期参数
    @NotBlank(message = "{message.content.notblank.error}") // 该字段的内容不允许为空
    private String content;
    @Email(message = "{message.email.email.error}") // 邮箱检测
    @NotBlank(message = "{message.email.notblank.error}")
    private String email;
    @Digits(integer = 1, fraction = 0, message = "{message.level.digits.error}") // 1位整数，0位小数
    private Integer level; // 消息级别
}
