package com.yootk.task;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component // 是一个独立的组件
@Data // 生成相关的类结构
public class DynmaicCronExpression { // 动态的CRON表达式
    private String cron = "*/2 * * * * ?"; // 此时定义CRON表达式
}
