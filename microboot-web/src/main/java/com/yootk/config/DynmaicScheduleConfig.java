package com.yootk.config;

import com.yootk.task.DynmaicCronExpression;
import com.yootk.task.YootkScheduleTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

@Configuration
@Slf4j // 在每次修改的时候实现一些输出
public class DynmaicScheduleConfig implements SchedulingConfigurer {// 动态配置
    @Autowired // 需要额外注入
    private DynmaicCronExpression expression; // 动态表达式
    @Autowired // 额外注入
    private YootkScheduleTask task; // 定时任务处理类
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(
                () -> task.task(), // 当前要执行的任务
                triggerContext -> { // 设置任务的触发表达式
                    log.info("设置当前的CRON表达式：{}", expression.getCron()); // 日志输出
                    String cron = expression.getCron(); // 获取当前已经设置的CRON表达式
                    return new CronTrigger((cron)).nextExecutionTime(triggerContext);
                }
        ); // 设置处罚任务
    }
}
