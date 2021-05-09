package com.yootk.test;

import com.yootk.rsocket.server.MessageServer;
import io.rsocket.Payload;
import io.rsocket.RSocket;
import io.rsocket.core.RSocketConnector;
import io.rsocket.transport.netty.client.TcpClientTransport;
import io.rsocket.util.DefaultPayload;
import org.junit.jupiter.api.*;
import reactor.core.publisher.Flux;

import java.time.Duration;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // 手工配置方法执行顺序
public class TestMessageServer { // 编写测试类
    private static RSocket rsocket;
    @BeforeAll // 在所有测试开始之前执行
    public static void setUpClient() {
        MessageServer.start(); // 进行服务启动
        rsocket = RSocketConnector.connectWith(TcpClientTransport.create(6565)).block(); // 客户端需要进行连接
    }
    @Test
    public void testFireAndForget() {    // 测试RSocket模式
        getRequestPayload().flatMap(payload -> rsocket.fireAndForget(payload))
                .blockLast(Duration.ofMinutes(1));
    }
    @Test
    public void testRequestAndResponse() {    // 测试RSocket模式
        getRequestPayload().flatMap(payload -> rsocket.requestResponse(payload))
                .doOnNext(response -> System.out.println("【RSocket测试类】接收服务端响应数据：" + response.getDataUtf8()))
                .blockLast(Duration.ofMinutes(1));
    }
    @Test
    public void testRequestStream() {    // 测试RSocket模式
        getRequestPayload().flatMap(payload -> rsocket.requestStream(payload))
                .doOnNext(response -> System.out.println("【RSocket测试类】接收服务端响应数据：" + response.getDataUtf8()))
                .blockLast(Duration.ofMinutes(1));
    }
    @Test
    public void testRequestChannel() {    // 测试RSocket模式
        rsocket.requestChannel(getRequestPayload())
                .doOnNext(response -> System.out.println("【RSocket测试类】接收服务端响应数据：" + response.getDataUtf8()))
                .blockLast(Duration.ofMinutes(1));

    }
    private static Flux<Payload> getRequestPayload() {  // 传递所有的附加数据内容
        return Flux.just("yootk.com", "springboot", "edu.yootk.com", "springcloud", "redis", "netty", "elasticsearch")
                .delayElements(Duration.ofSeconds(1))
                .map(DefaultPayload :: create);
    }
    @AfterAll
    public static void testStopServer() {
        MessageServer.stop();
    }
}
