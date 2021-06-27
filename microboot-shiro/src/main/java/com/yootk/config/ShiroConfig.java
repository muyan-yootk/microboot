package com.yootk.config;

import com.yootk.realm.MemberRealm;
import com.yootk.realm.matcher.DefaultCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig { // 定义Shiro的配置类
    @Bean(name = "shiroFilterFactoryBean")
    public ShiroFilterFactoryBean shiroFilter(
            org.apache.shiro.mgt.SecurityManager securityManager) {   // 定义Shiro过滤器
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        factoryBean.setSecurityManager(securityManager);// 配置
        Map<String, String> filterChain = new LinkedHashMap<>(); // 过滤器是有顺序的
        filterChain.put("/admin/**", "authc"); // 进行过滤路径配置
        factoryBean.setFilterChainDefinitionMap(filterChain); // 配置访问路径
        return factoryBean;
    }
    @Bean(name = "authorizer")
    public MemberRealm getMemberRealm() {
        MemberRealm realm = new MemberRealm();
        realm.setCredentialsMatcher(new DefaultCredentialsMatcher()); // 密码匹配器
        return realm;
    }
}
