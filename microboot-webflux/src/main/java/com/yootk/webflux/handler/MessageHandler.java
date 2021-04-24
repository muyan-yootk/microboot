package com.yootk.webflux.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
@Component // 进行组件注册
public class MessageHandler { // 这是一个WebFlux处理模块了
    // 在WebFlux编程里面如果要进行响应的话会区分单个响应（单一对象）以及多个响应（集合）
    public Mono<ServerResponse> echoHandler(ServerRequest request) {    // 请求接收以及响应
        return ServerResponse.ok()
                .header("Content-Type", "text/html;charset=UTF-8")  // 响应头信息
                .body(BodyInserters.fromValue("沐言科技：www.yootk.com"));
    }
}