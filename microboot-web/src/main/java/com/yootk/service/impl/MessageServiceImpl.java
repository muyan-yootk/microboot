package com.yootk.service.impl;

import com.yootk.service.IMessageService;
import org.springframework.stereotype.Service;

@Service // 原始的Spring开发需要配置扫描包
public class MessageServiceImpl implements IMessageService {
    @Override
    public String echo(String msg) {
        return "【ECHO】" + msg;
    }
}
