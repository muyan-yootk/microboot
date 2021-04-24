package com.yootk.webflux.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;
@Component
@Slf4j
public class EchoHandler implements WebSocketHandler { // 直接实现WebSocket支持
    @Override
    public Mono<Void> handle(WebSocketSession session) {
        log.info("WebSocket客户端握手信息：{}", session.getHandshakeInfo().getUri());
        return session.send(session.receive()
                .map(msg -> session.textMessage("【ECHO】" + msg.getPayloadAsText())));
    }
}
