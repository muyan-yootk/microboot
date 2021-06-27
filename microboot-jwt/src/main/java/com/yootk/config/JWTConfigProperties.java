package com.yootk.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "muyan.config.jwt")
public class JWTConfigProperties { // 保存JWT配置项
    private String sign;
    private String issuer;
    private String secret;
    private long expire;
}
