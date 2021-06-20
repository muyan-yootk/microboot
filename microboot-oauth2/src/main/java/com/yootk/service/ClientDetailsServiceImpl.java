package com.yootk.service;

import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Service
public class ClientDetailsServiceImpl implements ClientDetailsService {
    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        BaseClientDetails clientDetails = new BaseClientDetails(); // 系统默认实现的子类
        clientDetails.setClientId("client_muyan"); // 这个ID可以随机生成，不使用铭文
        clientDetails.setClientSecret("hello"); // 密码也需要进行加密处理
        clientDetails.setAuthorizedGrantTypes(Arrays.asList("authorization_code")); // 授权类型
        clientDetails.setScope(Arrays.asList("webapp"));
        clientDetails.setAccessTokenValiditySeconds(30);
        clientDetails.setAutoApproveScopes(clientDetails.getScope()); // 自动处理
        Set<String> redirectSet = new HashSet<>(); // 返回路径的配置
        redirectSet.addAll(Arrays.asList("https://www.yootk.com"));
        clientDetails.setRegisteredRedirectUri(redirectSet); // 如果没有配置正确的路径是无法authcode获取的
        return clientDetails;
    }
}
