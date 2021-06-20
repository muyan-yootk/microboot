package com.yootk.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@Configuration
@EnableAuthorizationServer
public class YootkAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception { // 客户端配置
        clients.inMemory()
                .withClient("client_muyan") // 定义注册的客户端ID
                .secret("hello") // 注册的密码
                .authorizedGrantTypes("authorization_code") // 响应类型
                .redirectUris("https://www.yootk.com") // 返回的路径，如果没有配置，那么无法使用
                .scopes("webapp"); // 授权范围
    }
}
