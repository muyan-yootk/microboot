package com.yootk.interceptor;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class MessageValidateInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Map<String, String> errors = new HashMap<>();
        if (request.getParameter("title") == null || "".equals(request.getParameter("title"))) {  // 当前的title属性为空
            errors.put("title", "请求提交的title参数内容为空！"); // KEY就是参数名称
        }
        if (request.getParameter("content") == null || "".equals(request.getParameter("title"))) {  // 当前的content属性为空
            errors.put("content", "请求提交的content参数内容为空！"); // KEY就是参数名称
        }
        if (request.getParameter("pubdate") == null) {  // 当前的pubdate属性为空
            errors.put("pubdate", "请求提交的pubdate参数内容为空！"); // KEY就是参数名称
        }
        if (errors.size() == 0) {   // 没有错误信息
            return true;
        } else {    // 在拦截器里面响应rest数据
            // 本次为了简化起见，直接使用SpringBoot内部自带的Jackson组件来将Map集合转为JSON数据
            ObjectMapper objectMapper = new ObjectMapper(); // Jackson组件
            String errorJSONData = objectMapper.writeValueAsString(errors); // 获取错误的JSON数据
            response.setContentType("text/html;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR); // 设置HTTP状态码
            response.getWriter().print(errorJSONData); // 响应错误数据
            return false;   // 不跳转到控制层
        }
    }
}
