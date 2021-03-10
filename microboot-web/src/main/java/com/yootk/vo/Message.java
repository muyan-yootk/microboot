package com.yootk.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;
@Data
@Accessors(fluent=true)
@Setter // 生成setter方法
@Getter // 生成getter方法
public class Message {
    private String title;
    private Date pubdate;
    private String content;
}
