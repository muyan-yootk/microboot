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
    private IClientDAO clientDAO;
    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        Optional<Client> optional = this.clientDAO.findById(clientId);
        if (optional.isEmpty()) {
            throw new ClientRegistrationException("此客户端的信息未注册！");
        }
        Client client = optional.get(); // 获取数据库中的Client信息
        BaseClientDetails clientDetails = new BaseClientDetails(); // 系统默认实现的子类
        clientDetails.setClientId(client.getCid()); // 这个ID可以随机生成，不使用铭文
        clientDetails.setClientSecret(client.getSecret()); // 密码也需要进行加密处理
        clientDetails.setAuthorizedGrantTypes(Arrays.asList(client.getGrants())); // 授权类型
        clientDetails.setScope(Arrays.asList(client.getScope()));
        clientDetails.setAccessTokenValiditySeconds(30);
        clientDetails.setAutoApproveScopes(clientDetails.getScope()); // 自动处理
        Set<String> redirectSet = new HashSet<>(); // 返回路径的配置
        redirectSet.addAll(Arrays.asList(client.getUrl()));
        clientDetails.setRegisteredRedirectUri(redirectSet); // 如果没有配置正确的路径是无法authcode获取的
        return clientDetails;
    }
}
