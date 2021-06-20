package com.yootk.config;

import com.yootk.service.ClientDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

@Configuration
@EnableAuthorizationServer
public class YootkAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private ClientDetailsServiceImpl clientDetailsService;
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception { // 客户端配置
        clients.withClientDetails(this.clientDetailsService); // 接入处理
    }
}
