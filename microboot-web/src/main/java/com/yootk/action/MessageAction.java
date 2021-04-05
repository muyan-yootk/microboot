package com.yootk.action;

import com.yootk.common.action.abs.AbstractBaseAction;
import com.yootk.service.IMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController // 直接基于Rest架构进行处理，省略了@ResponseBody注解
@RequestMapping("/message/*") // 添加父路径
@Slf4j // 直接进行日志的启用
public class MessageAction extends AbstractBaseAction { // 控制层的实现类
    @Autowired
    private IMessageService message;
    @RequestMapping("echo") // 子路径
    public Object echo(String msg) { // 进行请求参数的接收以及请求内容的回应
        log.info("控制层接收到用户请求，请求参数为：msg = {}", msg); // 日志输出
        return this.message.echo(msg);
    }
}