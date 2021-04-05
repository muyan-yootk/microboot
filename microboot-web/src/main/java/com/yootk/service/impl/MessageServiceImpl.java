package com.yootk.service.impl;

import com.yootk.service.IMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MessageServiceImpl implements IMessageService {
    @Override
    public String echo(String msg) {
        log.info("消息回应业务处理，传递的消息数据为：msg = {}", msg);
        return "【ECHO】" + msg;
    }
}
