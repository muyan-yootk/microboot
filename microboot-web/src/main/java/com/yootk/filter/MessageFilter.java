package com.yootk.filter;

import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*") // 过滤处理路径
public class MessageFilter extends HttpFilter { // 过滤器定义

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        if ("/message/echo".equals(request.getRequestURI())) {  // 判断当前的路径
            String title = request.getParameter("title"); // 接收title参数
            if (StringUtils.hasLength(title)) { // 存在有数据
                System.err.println("【MessageFilter】title参数内容为：" + title);
            }
        }
        chain.doFilter(request, response);
    }
}
