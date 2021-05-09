package com.yootk.rsocket.server.acceptor;

import com.yootk.rsocket.server.handler.MessageRSocketHandler;
import io.rsocket.ConnectionSetupPayload;
import io.rsocket.RSocket;
import io.rsocket.SocketAcceptor;
import reactor.core.publisher.Mono;

public class MessageRSocketAcceptor implements SocketAcceptor {
    @Override
    public Mono<RSocket> accept(ConnectionSetupPayload setup, RSocket sendingSocket) { // 实现了RSocket连接处理
        return Mono.just(new MessageRSocketHandler()); // 配置自己的处理类
    }
}
