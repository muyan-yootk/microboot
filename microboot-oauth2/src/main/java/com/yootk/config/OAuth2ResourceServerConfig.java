package com.yootk.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableResourceServer // 启用资源服务
@Order(30) // 启动顺序靠后
@Slf4j
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.stateless(true); // 无状态存储
    }
    @Override
    public void configure(HttpSecurity http) throws Exception { // 配置URL访问权限
        http.csrf().disable() // 禁用CSRF校验
                .exceptionHandling() // 异常处理
                .authenticationEntryPoint((request,response,authException)->
                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED)) // HTTP状态码
                .and()
                .authorizeRequests().anyRequest().authenticated(); // 认证访问
    }
}
