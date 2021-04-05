package com.yootk.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Slf4j
public class MDCInterceptor implements HandlerInterceptor { // 拦截器
    private final static String REQUEST_ID = "requestId"; // 名称不重要

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler)
            throws Exception {   // 每次请求前进行MDC绑定
        String forward = request.getHeader("X-Forwarded-For");
        String clientIp = request.getRemoteAddr();
        String uuid = UUID.randomUUID().toString();
        log.info("MDC操作记录开始：requestId = {}", uuid); // 日志输出
        log.info("requestId = {}, clientIp = {}, X-Forwarded-For = {}", uuid, clientIp, forward);
        MDC.put(REQUEST_ID, uuid); // 保存了MDC数据
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {  // 每次请求后进行MDC清除
        String uuid = MDC.get(REQUEST_ID);
        log.info("MDC操作记录结束，requestId = {}", uuid);
        MDC.remove(REQUEST_ID);
    }
}
