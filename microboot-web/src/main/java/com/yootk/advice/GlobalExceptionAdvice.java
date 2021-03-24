package com.yootk.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionAdvice {
    // 如果说你现在是一个包含有完整业务需求的项目，可以在此处填写上一些自定义的业务异常；
    @ExceptionHandler(Exception.class) // 可以捕获的异常类型
    @ResponseBody // 本次的处理是基于Rest风格完成的
    public Object exceptionHandler(Exception e) {  // 实现所有的异常处理
        Map<String, Object> map = new HashMap<>(); // 保存响应信息
        map.put("message", e.getMessage()); // 直接获取异常信息
        map.put("status", HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // 设置一个HTTP状态码
        map.put("exception", e.getClass().getName()); // 获取异常类型
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        map.put("path", request.getRequestURI()); // 异常发生的路径
        return map; // 直接返回对象
    }
}
