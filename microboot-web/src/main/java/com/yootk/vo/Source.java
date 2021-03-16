package com.yootk.vo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
@Data
@ConfigurationProperties("muyan")  // 配置了注入KEY的头部定义
@Component
public class Source {
    private String mysql; // 属性名称为key名称
    private String redis;
    private List<String> messages;
    private Map<String, String> books;
}
