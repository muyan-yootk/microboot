package com.yootk.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class YootkThreadTask {
    @Async // 异步处理
    public void startTaskHandle() { // 这是一个普通的方法
        log.info("【异步线程】开启，执行线程：{}", Thread.currentThread().getName());
        try {   // 往往都是耗时任务进行异步处理，所以此时模拟一个延迟
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
        }
        log.info("【异步线程】结束，执行线程：{}", Thread.currentThread().getName());
    }
}
