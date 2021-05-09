package com.yootk.rsocket.server.action;

import com.yootk.rsocket.server.service.MessageService;
import com.yootk.rsocket.vo.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Controller // 别使用那个RestController
@Slf4j // 日志组件
public class MessageAction {
    @Autowired
    private MessageService messageService; // 注入业务接口实例
    @MessageMapping("message.echo")
    public Mono<Message> echoMessage(Mono<Message> message) {
        return message.doOnNext(msg -> this.messageService.echo(msg)) // 响应处理
                .doOnNext(msg -> log.info("【消息接收】{}", message)); // 日志处理
    }
    @MessageMapping("message.delete")
    public void deleteMessage(Mono<String> title) {
        title.doOnNext(msg -> log.info("【消息删除】{}", msg)).subscribe();// 日志输出
    }
    @MessageMapping("message.list")
    public Flux<Message> listMessage() {
        return Flux.fromStream(this.messageService.list().stream());
    }
    @MessageMapping("message.get")
    public Flux<Message> getMessage(Flux<String> title) {
        return title.doOnNext(t -> log.info("【消息查询】title = {}", t)) // 日志输出
        .map(titleInfo -> titleInfo.toLowerCase()) // 数据转小写
        .map(this.messageService :: get) // 加载业务数据
        .delayElements(Duration.ofSeconds(1)); // 数据延缓一下
    }
}
