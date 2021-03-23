package com.yootk.config;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Configuration
public class ErrorPageConfig implements ErrorPageRegistrar { // 错误页注册
    @Override
    public void registerErrorPages(ErrorPageRegistry registry) { // 页面注册
        ErrorPage errorPage404 = new ErrorPage(HttpStatus.NOT_FOUND, "/errors/error_404"); // 定义404错误页
        ErrorPage errorPage500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/errors/error_500"); // 定义500错误页
        registry.addErrorPages(errorPage404, errorPage500); // 已经添加了新的错误页
    }
}
