package com.yootk.rsocket.action;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Controller
@Slf4j
public class MessageAction {
    @MessageMapping("message.echo")
    public Mono<String> echo(Mono<String> messageMono) { // Request-And-Response模式
        return messageMono.map(msg -> "【ECHO】" + msg); // 数据响应
    }
    @MessageMapping("message.repeat")
    public Flux<String> repeat(Mono<String> mono) { // Request-Response-Stream
        return mono.flatMapMany(message -> Flux.range(0, 3).map(count -> "【ECHO - " + count + "】" + message))
                .delayElements(Duration.ofSeconds(1)); // 间隔1秒响应
    }
}
