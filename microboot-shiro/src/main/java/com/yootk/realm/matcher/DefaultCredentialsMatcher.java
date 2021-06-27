package com.yootk.realm.matcher;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

public class DefaultCredentialsMatcher extends SimpleCredentialsMatcher { // 密码匹配器

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String defaultPassword = super.toString(token.getCredentials()); // 获取原始的输入密码
        return "yootk".equals(defaultPassword); // 实现密码匹配
    }
}
