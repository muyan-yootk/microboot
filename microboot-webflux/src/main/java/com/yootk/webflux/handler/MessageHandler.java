package com.yootk.webflux.handler;

import com.yootk.vo.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component // 进行组件注册
@Slf4j // 日志输出
public class MessageHandler { // 这是一个WebFlux处理模块了
    public Flux<Message> list(Message message) {    // 返回集合数据
        List<Message> messageList = new ArrayList<>(); // 创建一个List集合
        for (int x = 0; x < 10; x++) {  // 10个长度的集合
            Message msg = new Message(); // 创建新的Message实例
            msg.setTitle("【" + x + "】" + message.getTitle());
            msg.setContent("【" + x + "】" + message.getContent());
            msg.setPubdate(message.getPubdate()); // 直接使用已经配置的日期时间
            messageList.add(msg); // 实现集合的存储
        }
        return Flux.fromIterable(messageList); // 实现了集合响应
    }
    // 每一次进行响应结果配置的时候，都只能够配置具体的类型
    public Flux<Map.Entry<String, Message>> map(Message message) {
        Map<String, Message> map = new HashMap<>();
        for (int x = 0; x < 10; x++) { // 通过迭代配置数据
            Message msg = new Message(); // 创建新的Message实例
            msg.setTitle("【" + x + "】" + message.getTitle());
            msg.setContent("【" + x + "】" + message.getContent());
            msg.setPubdate(message.getPubdate()); // 直接使用已经配置的日期时间
            map.put("yootk - " + x, msg); // 保存Map集合项
        }
        return Flux.fromIterable(map.entrySet());
    }
    public Mono<Message> echoHandler(Message message) { // 直接以最终的数据类型进行操作
        log.info("【{}】业务层接收处理数据：{}", Thread.currentThread().getName(), message);
        message.setTitle("【" + Thread.currentThread().getName() + "】" + message.getTitle());
        message.setContent("【" + Thread.currentThread().getName() + "】" + message.getContent());
        return Mono.create(monoSink -> monoSink.success(message)); // 实现数据响应
    }
}
