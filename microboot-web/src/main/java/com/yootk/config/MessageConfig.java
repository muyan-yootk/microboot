package com.yootk.config;

import com.yootk.vo.Message;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfig {
    @Bean
    public Message getMessage() {
        Message message = new Message();
        message.setTitle("沐言科技");
        message.setContent("www.yootk.com");
        return message;
    }
}
