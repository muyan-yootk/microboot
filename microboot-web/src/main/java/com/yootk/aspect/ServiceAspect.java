package com.yootk.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect // 实现切面处理类
public class ServiceAspect { // 实现AOP切面管理
    @Around("execution(* com.yootk..service..*.*(..))") // 定义好了当前的切面
    public Object arroundInvoke(ProceedingJoinPoint point) throws Throwable {
        System.out.println("【ServiceInvokeBefore】执行参数：" + Arrays.toString(point.getArgs()));
        Object obj = point.proceed(point.getArgs()); // 调用真实业务主题
        System.out.println("【ServiceInvokeAfter】返回结果：" + obj);
        return obj; // 需要返回执行的结果
    }
}
