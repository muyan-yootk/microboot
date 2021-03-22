package com.yootk.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class WebServletListener implements ServletContextListener { // 配置监听器
    @Override
    public void contextInitialized(ServletContextEvent sce) { // 上下文初始化
        System.out.println("【WebServletListener】Servlet初始化：" + sce.getServletContext().getServerInfo());
        System.out.println("【WebServletListener】Servlet初始化：" + sce.getServletContext().getRealPath("/"));
        System.out.println("【WebServletListener】Servlet初始化：" + sce.getServletContext().getVirtualServerName());
    }
}
