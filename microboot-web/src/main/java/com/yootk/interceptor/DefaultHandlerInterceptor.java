package com.yootk.interceptor;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DefaultHandlerInterceptor implements HandlerInterceptor { // 自定义拦截器

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) { // 判断是否为指定类型实例
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            System.err.println("【Action对象】" + handlerMethod.getBean()); // 控制器实例
            System.err.println("【Action类型】" + handlerMethod.getBeanType());
            System.err.println("【Action方法】" + handlerMethod.getMethod());
        }
        return true; // 放行
    }
}