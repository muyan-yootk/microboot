package com.yootk.actuator;

import org.springframework.boot.actuate.health.HealthContributor;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

@Component // 进行组件的注册
public class MicroServiceInfoContributor implements InfoContributor { // 自定义Info构建器
    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("company.name", "muyan-yootk"); // 添加info项
        builder.withDetail("company.url", "www.yootk.com"); // 添加info项
    }
}
