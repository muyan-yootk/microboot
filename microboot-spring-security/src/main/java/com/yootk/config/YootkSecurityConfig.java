package com.yootk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class YootkSecurityConfig extends WebSecurityConfigurerAdapter {
    // 本次的开发暂时不基于数据库实现用户的信息管理，本次账户采用固定的密码为“hello”
    private static final String PASSWORD =
            "{bcrypt}$2a$10$2ddAwTKN4ZZ8cNB1YgQmNeOqSLcqcTNDOF0hAxQkRWBIij1XlMvae"; // 加密后的密码
    @Bean // 如果要想使用密码，则必须配置有一个密码的编码器
    public PasswordEncoder getPasswordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 添加三个账户，用户名分别为：admin、muyan、yootk
        auth.inMemoryAuthentication().withUser("admin").password(PASSWORD).roles("USER", "ADMIN");
        auth.inMemoryAuthentication().withUser("muyan").password(PASSWORD).roles("ADMIN");
        auth.inMemoryAuthentication().withUser("yootk").password(PASSWORD).roles("USER");
    }
}
