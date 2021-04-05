package com.yootk.action;

import com.yootk.common.action.abs.AbstractBaseAction;
import com.yootk.vo.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController // 直接基于Rest架构进行处理，省略了@ResponseBody注解
@RequestMapping("/message/*") // 添加父路径
@Validated // 启用当前的JSR303注解
@Slf4j // 直接进行日志的启用
// 当使用了以上的注解之后就会自动的在当前的程序类里面出现有一个log的日志对象
public class MessageAction extends AbstractBaseAction { // 控制层的实现类
    @RequestMapping("echo") // 子路径
    public Object echo(Message message) { // 进行请求参数的接收以及请求内容的回应
        log.error("接收到了请求的参数：message = {}", message);
        log.warn("接收到了请求的参数：message = {}", message);
        log.info("接收到了请求的参数：message = {}", message);
        log.debug("接收到了请求的参数：message = {}", message);
        log.trace("接收到了请求的参数：message = {}", message);
        message.setTitle("【ECHO】" + message.getTitle());
        message.setContent("【ECHO】" + message.getContent());
        return message;
    }
}
// http://localhost/message/echo?title=沐言科技&content=www.yootk.com&pubdate=1998-09-19
// https://localhost/message/echo?title=沐言科技&content=www.yootk.com&pubdate=1998-09-19