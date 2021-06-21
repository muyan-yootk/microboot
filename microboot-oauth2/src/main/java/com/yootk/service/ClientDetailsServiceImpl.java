package com.yootk.service;

import com.yootk.dao.IClientDAO;
import com.yootk.vo.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
@Service
public class ClientDetailsServiceImpl implements ClientDetailsService {
    @Autowired
    private IClientDAO clientDAO; // 注入DAO接口实例
    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        Optional<Client> optional = this.clientDAO.findById(clientId);
        if (optional.isEmpty()) {   // 客户信息不存在
            throw new ClientRegistrationException("客户端信息不存在！"); // 抛出异常
        }
        Client client = optional.get(); // 获取Client实例
        BaseClientDetails clientDetails = new BaseClientDetails(); // 获取ClientDetails接口实例
        clientDetails.setClientId(clientId); // 固定ClientID
        clientDetails.setClientSecret(client.getSecret()); // 固定密钥（未加密）
        clientDetails.setAuthorizedGrantTypes(Arrays.asList(client.getGrants())); // 授权类型
        clientDetails.setScope(Arrays.asList(client.getScope())); // 应用范围
        clientDetails.setAccessTokenValiditySeconds(3000); // Token失效时间（30秒）
        clientDetails.setAutoApproveScopes(clientDetails.getScope()); // 自动授权处理
        Set<String> redirectSet = new HashSet<>(); // 返回路径
        redirectSet.addAll(Arrays.asList(client.getUrl())); // 添加路径
        clientDetails.setRegisteredRedirectUri(redirectSet); // 注册返回路径
        return clientDetails; // 返回ClientDetails实例
    }
}
