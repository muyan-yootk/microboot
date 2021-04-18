package com.yootk.event;

import com.yootk.vo.Message;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
@Getter
@Slf4j
public class YootkEvent extends ApplicationEvent { // 自定义事件类
    private Message message; // 数据保存
    public YootkEvent(Object source, Message message) { // 产生事件之后保存有具体的事件数据
        super(source);
        this.message = message; // 数据存储
    }
    public void fire() {    // 自定义方法
        log.info("message = {}", this.message); // 日志输出
    }
}
