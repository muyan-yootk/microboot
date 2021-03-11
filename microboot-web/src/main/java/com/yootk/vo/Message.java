package com.yootk.vo;

import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
@Data // 只要使用Lombok组件，99%的情况下就使用这一个注解
@XmlRootElement // 配置XML根元素
public class Message {
    @XmlElement
    private String title; // 字符串的参数
    @XmlElement
    private Date pubdate; // 日期参数
    @XmlElement
    private String content;
}
