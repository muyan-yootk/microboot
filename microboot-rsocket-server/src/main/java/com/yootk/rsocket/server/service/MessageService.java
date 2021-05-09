package com.yootk.rsocket.server.service;

import com.yootk.rsocket.vo.Message;
import org.springframework.stereotype.Service;

import java.util.List;

// 本次的开发为了节约代码编写的成本，不再创建繁琐的业务接口了，直接进行业务实现类的处理了
@Service
public class MessageService {
    public List<Message> list() { // 响应集合数据
        return List.of(
                new Message("yootk", "沐言优拓：www.yootk.com"),
                new Message("muyan", "沐言科技：www.yootk.com"),
                new Message("edu", "李兴华高薪就业编程训练营：edu.yootk.com"));
    }

    public Message get(String title) {  // 响应单个数据
        return new Message(title, "【" + title + "】www.yootk.com");
    }
    public Message echo(Message message) {  // 响应单个数据
        message.setTitle("【ECHO】" + message.getTitle());
        message.setContent("【ECHO】" + message.getContent());
        return message;
    }
}
