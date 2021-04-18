package com.yootk.action;

import com.yootk.task.DynmaicCronExpression;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cron/*")
@Slf4j
public class CronAction {
    @Autowired
    private DynmaicCronExpression expression; // 动态表达式
    @GetMapping("set")
    public Object setCron(String cron) {
        log.info("动态修改CRON配置：{}", cron);
        this.expression.setCron(cron);// 修改当前触发的CRON表达式
        return true;
    }
}
