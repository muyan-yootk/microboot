package com.yootk.config;

import com.yootk.vo.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration // 配置类
@Slf4j
public class EventListenerConfig { // 事件监听配置类
    @EventListener // 条件触发
    public void handleYootkEventByContent(Message message) {
        log.info("【handleYootkEventByContent()】{}", message);
    }
}
