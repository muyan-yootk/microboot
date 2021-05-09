package com.yootk.rsocket.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // 自动生成类结构
@NoArgsConstructor // 无参构造
@AllArgsConstructor // 全参构造
public class Message {
    private String title;
    private String content;
}
