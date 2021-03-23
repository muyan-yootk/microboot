package com.yootk.action;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/errors/*") // 父路径
public class ErrorPageAction { // 用于进行错误页的处理
    @RequestMapping("error_404")
    public Object errorCode404() {  // 没有发现请求路径
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        response.setStatus(HttpServletResponse.SC_NOT_FOUND); // 设置响应编码
        Map<String, Object> map = new HashMap<>(); // 即便是错误页实际上也是一个正常响应
        // map.put("status", HttpServletResponse.SC_NOT_FOUND); // 响应编码
        map.put("status", 404); // 响应编码
        map.put("content", "无法找到用户访问路径。"); // 适当性的带一点文字描述
        map.put("referer", request.getHeader("Referer")); // 获取之前的来源
        map.put("path", request.getRequestURI()); // 访问路径
        return map;
    }
    @RequestMapping("error_500")
    public Object errorCode500() {  // 没有发现请求路径
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // 设置响应编码
        Map<String, Object> map = new HashMap<>(); // 即便是错误页实际上也是一个正常响应
        map.put("status", HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // 响应编码
        map.put("content", "服务器端程序出错。"); // 适当性的带一点文字描述
        map.put("referer", request.getHeader("Referer")); // 获取之前的来源
        map.put("path", request.getRequestURI()); // 访问路径
        return map;
    }
}
