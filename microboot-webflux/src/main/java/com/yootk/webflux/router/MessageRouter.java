package com.yootk.webflux.router;

import com.yootk.webflux.handler.MessageHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.*;

@Configuration
public class MessageRouter { // 消息路由处理
    @Bean // 进行路由的功能注册
    public RouterFunction<ServerResponse> routeEcho(MessageHandler messageHandler) {
        // 此时绑定的访问模式为GET请求模式，而后设置了具体的访问地址“/echo”
        return RouterFunctions.route(RequestPredicates.GET("/echo") // 匹配最终的处理路由地址
                .and(RequestPredicates.accept(MediaType.TEXT_PLAIN)) // 设置了匹配的请求类型
                , messageHandler::echoHandler); // 匹配最终的处理路由地址
    }
}
