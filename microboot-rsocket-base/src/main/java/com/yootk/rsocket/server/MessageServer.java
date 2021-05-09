package com.yootk.rsocket.server;

import com.yootk.rsocket.server.acceptor.MessageRSocketAcceptor;
import io.rsocket.core.RSocketServer;
import io.rsocket.frame.decoder.PayloadDecoder;
import io.rsocket.transport.netty.server.TcpServerTransport;
import reactor.core.Disposable;

public class MessageServer { // 实现RSocket状态的操作控制
    private static Disposable disposable; // 用于释放任务
    public static void start() {    // 服务启动
        RSocketServer rSocketServer = RSocketServer.create(); // 创建RSocket服务端
        rSocketServer.acceptor(new MessageRSocketAcceptor()); // 创建连接器
        rSocketServer.payloadDecoder(PayloadDecoder.ZERO_COPY); // 采用零拷贝技术实现
        disposable = rSocketServer.bind(TcpServerTransport.create(6565)).subscribe(); // 开启订阅
    }
    public static void stop() { // 服务启动
        disposable.dispose(); // 释放
    }
}
