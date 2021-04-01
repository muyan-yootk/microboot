package com.yootk.actuator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component // 依然定义为组件
public class MicroHealthIndicator implements HealthIndicator {
    // 现在是一个独立的Bean组件，就可以直接注入其他依赖
    @Override
    public Health health() { // 返回健康状态
        int errorCode = 100; // 假设存在有一个错误编码
        if (errorCode != 0) {   // 触发返回错误的条件
            return Health.down().withDetail("MicroServiceErrorCode", errorCode)
                    .withException(new Exception("服务故障！")).build(); // 当前微服务不健康
        }
        return Health.up().build(); // 当前微服务健康
    }
}
