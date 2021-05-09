package com.yootk.test;

import com.yootk.rsocket.client.StartRSocketClientApplication;
import com.yootk.rsocket.vo.Message;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@SpringBootTest(classes = StartRSocketClientApplication.class)
public class TestMessageRSocket { // 编写测试类
    @Autowired
    private Mono<RSocketRequester> requesterMono; // 来进行服务调用
    @Test
    public void testEchoMessage() { // 测试服务响应
        this.requesterMono.map(r -> r.route("message.echo")
        .data(new Message("李兴华", "沐言科技编程讲师"))) // 配置请求数据
        .flatMap(r -> r.retrieveMono(Message.class))
                .doOnNext(o -> System.out.println(o)).block();
    }
    @Test
    public void testDeleteMessage() { // 测试服务响应
        this.requesterMono.map(r -> r.route("message.delete")
                .data("yootk")) // 配置请求数据
                .flatMap(RSocketRequester.RetrieveSpec :: send).block(); // 发送不接收数据
    }
    @Test
    public void testListMessage() { // 测试消息列表
        this.requesterMono.map(r -> r.route("message.list"))
                .flatMapMany(r -> r.retrieveFlux(Message.class))
                .doOnNext(o -> System.out.println(o)).blockLast();
    }
    @Test
    public void testGetMessage() {
        Flux<String> titles = Flux.just("muyan", "yootk", "edu"); // 要获取消息的标题
        Flux<Message> messageFlux = this.requesterMono.map(r -> r.route("message.get").data(titles))
                .flatMapMany(r -> r.retrieveFlux(Message.class))
                .doOnNext(o -> System.out.println(o));
        messageFlux.blockLast();
    }
}
