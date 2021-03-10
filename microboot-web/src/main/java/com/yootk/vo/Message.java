package com.yootk.vo;

import lombok.Data;

import java.util.Date;
@Data // 这就是Lombok注解，这个注解使用的是最频繁的
public class Message {
    private String title;
    private Date pubdate;
    private String content;
}
