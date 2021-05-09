package com.yootk.rsocket.server.handler;


import io.rsocket.Payload;
import io.rsocket.RSocket; // 原生的RSocket官方提供
import io.rsocket.util.DefaultPayload;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j // 日志组件
public class MessageRSocketHandler implements RSocket {
    @Override
    public Mono<Void> fireAndForget(Payload payload) { // 无响应
        // 一般这种无响应的操作可以应用于日志记录的模式上，例如：客户端发送一个日志，是不需要等待响应的
        // Payload表示所有的附加数据信息，对于RSocket通讯来讲，所有的数据项都通过此结构传输
        String message = payload.getDataUtf8();  // 获取数据
        log.info("【FireAndForget】接收请求数据：{}", message);
        return Mono.empty(); // 返回一个空消息
    }

    @Override
    public Mono<Payload> requestResponse(Payload payload) { // 传统模式，有请求有响应
        String message = payload.getDataUtf8();  // 获取数据
        log.info("【RequestAndResponse】接收请求数据：{}" , message);
        return Mono.just(DefaultPayload.create("【ECHO】" + message)); // 进行数据响应处理
    }

    @Override
    public Flux<Payload> requestStream(Payload payload) { // 处理流数据
        String message = payload.getDataUtf8();  // 获取数据
        log.info("【RequestStream】接收请求数据：{}" , message);
        return Flux.fromStream(message.chars()  // 将接收到的字符串转换为一个int流数据
                .mapToObj(c -> Character.toUpperCase((char) c)) // 获取里面的每一个字符的编码，并且转大写
                .map(Object::toString) // 利用toString()方法将字符转为String
                .map(DefaultPayload::create)); // 创建Payload的附加数据
    }

    @Override
    public Flux<Payload> requestChannel(Publisher<Payload> payloads) { // 双向流
        return Flux.from(payloads).map(Payload :: getDataUtf8).map(msg ->{
                log.info("【RequestChannel】接收请求数据：{}", msg);
                return msg; // 直接返回发送的数据内容
        }).map(DefaultPayload :: create);
    }
}
