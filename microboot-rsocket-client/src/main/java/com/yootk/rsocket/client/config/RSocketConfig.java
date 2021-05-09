package com.yootk.rsocket.client.config;

import io.rsocket.transport.netty.client.TcpClientTransport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.codec.cbor.Jackson2CborDecoder;
import org.springframework.http.codec.cbor.Jackson2CborEncoder;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.RSocketStrategies;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

@Configuration // 配置类
public class RSocketConfig { // RSocket配置类
    @Bean // 注册Bean
    public RSocketStrategies getRSocketStrategies() {
        return RSocketStrategies.builder()
                .encoders(encoders -> encoders.add(new Jackson2CborEncoder())) // 定义传输编码器
                .decoders(decoders -> decoders.add(new Jackson2CborDecoder())) // 解码器
                .build();
    }
    @Bean
    public Mono<RSocketRequester> getRSocketRequester(RSocketRequester.Builder builder) {
        return Mono.just(
                builder.rsocketConnector(rSocketConnector -> rSocketConnector.reconnect( // 失败重连
                        Retry.fixedDelay(2, Duration.ofSeconds(2))))
                        .dataMimeType(MediaType.APPLICATION_CBOR) // 设置数据的传输类型
                        .transport(TcpClientTransport.create(6869))); // 设置连接端口
    }
}
