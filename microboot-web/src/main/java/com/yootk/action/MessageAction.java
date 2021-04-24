package com.yootk.action;

import com.yootk.common.action.abs.AbstractBaseAction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;

@RestController // 直接基于Rest架构进行处理，省略了@ResponseBody注解
@RequestMapping("/async/*") // 添加父路径
@Slf4j // 直接进行日志的启用
public class MessageAction extends AbstractBaseAction { // 控制层的实现类
    @RequestMapping("callable") // 子路径
    public Object echo(String msg) { // 进行请求参数的接收以及请求内容的回应
        log.info("外部线程：{}", Thread.currentThread().getName()); // 日志输出
        // 所有的子线程都是由主线程启动的，所以此时要观察主从两个线程的名称
        return new Callable<String>() { // 返回一个异步线程
            @Override
            public String call() throws Exception {
                log.info("内部线程：{}", Thread.currentThread().getName()); // 日志输出
                return "【ECHO】" + msg; // 数据响应
            }
        };
    }
}