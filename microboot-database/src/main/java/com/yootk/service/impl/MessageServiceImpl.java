package com.yootk.service.impl;

import com.yootk.service.IMessageService;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements IMessageService {
    @Override
    public String echo(String msg) {
        return "【ECHO】" + msg;
    }
}
