package com.yootk.webflux.handler;

import com.yootk.vo.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component // 进行组件注册
@Slf4j // 日志输出
public class MessageHandler { // 这是一个WebFlux处理模块了
    // 此时不再关注传统的ServerResponse以及ServletRequest类型了
    public Mono<Message> echoHandler(Message message) { // 直接以最终的数据类型进行操作
        log.info("【{}】业务层接收处理数据：{}", Thread.currentThread().getName(), message);
        message.setTitle("【" + Thread.currentThread().getName() + "】" + message.getTitle());
        message.setContent("【" + Thread.currentThread().getName() + "】" + message.getContent());
        return Mono.create(monoSink -> monoSink.success(message)); // 实现数据响应
    }
}
