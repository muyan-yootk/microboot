package com.yootk.task;

import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component // 任务的处理类需要提供有一个组件
@Slf4j // 直接通过日志输出任务信息
public class YootkScheduleTask { // 定义任务处理类
    @SchedulerLock(name = "yootk-task", lockAtLeastFor = "5000")
    public void task() { // CRON任务
        log.info("【ShedLock任务】{}", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date()));
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
