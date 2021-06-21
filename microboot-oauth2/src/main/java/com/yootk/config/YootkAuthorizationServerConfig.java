package com.yootk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

@Configuration
@EnableAuthorizationServer // 启用授权服务
public class YootkAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private RedisConnectionFactory redisConnectionFactory ; // Redis连接工厂
    @Autowired
    private PasswordEncoder passwordEncoder; // 注入密码编码器
    @Autowired
    @Qualifier("clientDetailsServiceImpl") // 注意：需要标注Bean名称
    private ClientDetailsService clientDetailsService; // 注入接口实例

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(new RedisTokenStore(this.redisConnectionFactory));// 通过Redis保存Token
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(this.clientDetailsService); // 注入业务接口实例
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // 允许客户端通过FORM表单实现客户端认证，客户端需要传递client_id和client_secret获取Token数据
        security.allowFormAuthenticationForClients()
                .checkTokenAccess("isAuthenticated()") //通过验证返回token信息
                .tokenKeyAccess("permitAll()") // 获取token请求不进行拦截
                .passwordEncoder(this.passwordEncoder); // 密码编码器
    }
}