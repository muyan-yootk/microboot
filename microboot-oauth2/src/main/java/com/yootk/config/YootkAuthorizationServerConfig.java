package com.yootk.config;

import com.yootk.service.ClientDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

@Configuration
@EnableAuthorizationServer
public class YootkAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;
    @Autowired
    private PasswordEncoder passwordEncoder; // 引入了和用户管理部分相同的密码加密器
    @Autowired
    private ClientDetailsServiceImpl clientDetailsService;
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(new RedisTokenStore(this.redisConnectionFactory)); // 要通过Redis保存Token
    }
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.allowFormAuthenticationForClients() // 允许通过Form表单来实现客户端认证
            .checkTokenAccess("isAuthenticated()") // 认证之后才可以实现Token查询
            .tokenKeyAccess("permitAll()") // 获取Token的时候不进行拦截
            .passwordEncoder(this.passwordEncoder); // 客户端登录的时候是需要进行密码输入的
    }
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception { // 客户端配置
        clients.withClientDetails(this.clientDetailsService); // 接入处理
    }
}
