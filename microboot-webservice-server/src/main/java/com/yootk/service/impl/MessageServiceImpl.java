package com.yootk.service.impl;

import com.yootk.service.IMessageService;
import org.springframework.stereotype.Service;

import javax.jws.WebService;

@WebService(serviceName = "Messageservice",
        targetNamespace = "http://service.yootk.com/", // 接口命名空间
        endpointInterface = "com.yootk.service.IMessageService") // 接口的名称
@Service // 自动进行Bean注册
public class MessageServiceImpl implements IMessageService {
    @Override
    public String echo(String msg) {
        return "【ECHO】" + msg;
    }
}
