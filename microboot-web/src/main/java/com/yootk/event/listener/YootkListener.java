package com.yootk.event.listener;

import com.yootk.event.YootkEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;

@Slf4j
public class YootkListener implements ApplicationListener<YootkEvent> { // 事件绑定
    @Override
    public void onApplicationEvent(YootkEvent event) { // 事件监听后得到事件对象
        log.info("事件处理：{}", event);
        event.fire(); // 自定义的事件操作
    }
}
