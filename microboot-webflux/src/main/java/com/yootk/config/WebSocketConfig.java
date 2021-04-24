package com.yootk.config;

import com.yootk.webflux.websocket.EchoHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class WebSocketConfig { // 配置WebSocket
    @Bean
    public HandlerMapping websocketMapping(
            @Autowired EchoHandler echoHandler) {
        Map<String, WebSocketHandler> map = new HashMap<>(); // 定义所有的映射集合
        map.put("/websocket/{token}", echoHandler); // 配置映射模型
        SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping(); // 映射处理
        mapping.setOrder(Ordered.HIGHEST_PRECEDENCE); // 优先配置
        mapping.setUrlMap(map); // 映射路径
        return mapping; // 返回最终的映射
    }
    @Bean
    public WebSocketHandlerAdapter handlerAdapter() {
        return new WebSocketHandlerAdapter(); // 处理配置器
    }
}
