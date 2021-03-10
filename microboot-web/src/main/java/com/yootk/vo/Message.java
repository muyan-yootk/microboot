package com.yootk.vo;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
@Data
@Builder // 构建者模式
public class Message {
    private String title;
    private Date pubdate;
    private String content;
}
