package com.yootk.action;

import com.yootk.common.action.abs.AbstractBaseAction;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.WebAsyncTask;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

@RestController // 直接基于Rest架构进行处理，省略了@ResponseBody注解
@RequestMapping("/async/*") // 添加父路径
@Slf4j // 直接进行日志的启用
public class MessageAction extends AbstractBaseAction { // 控制层的实现类
    @Autowired // 实例注入
    private ThreadPoolTaskExecutor threadPoolTaskExecutor; // 获取线程池
    @RequestMapping("runnable") // 子路径
    public Object echo(String msg) { // 进行请求参数的接收以及请求内容的回应
        log.info("外部线程：{}", Thread.currentThread().getName()); // 日志输出
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        DeferredResult<String> result = new DeferredResult<>(6000L); // 设置异步响应
        result.onTimeout(new Runnable() { // 超时处理
            @Override
            public void run() {
                log.info("超时线程：{}", Thread.currentThread().getName()); // 日志输出
                result.setResult("【请求超时】" + request.getRequestURL()); // 超时路径
            }
        });
        result.onCompletion(new Runnable() { // 完成处理线程
            @Override
            public void run() {
                log.info("完成线程：{}", Thread.currentThread().getName()); // 日志输出
            }
        });
        this.threadPoolTaskExecutor.execute(new Runnable() { //线程核心任务
            @Override
            public void run() {
                log.info("内部线程：{}", Thread.currentThread().getName()); // 日志输出
                try {
                    TimeUnit.SECONDS.sleep(2); // 模拟延迟
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                result.setResult("【ECHO】" + msg); // 执行最终的响应
            }
        });
        return result;
    }
}