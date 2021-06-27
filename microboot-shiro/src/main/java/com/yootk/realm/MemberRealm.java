package com.yootk.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.Set;

public class MemberRealm extends AuthorizingRealm { // 定义Real处理
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {    // 登录认证处理
        // 此时匹配的用户密码为“yootk”，这个密码直接采用明文的方式传递到后面的密码匹配器之中
        return new SimpleAuthenticationInfo(token.getPrincipal(), "yootk", "memberRealm");
    }
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) { // 授权处理
        // 这个操作是需要通过数据库进行加载的，同时考虑到性能问题又需要通过缓存进行数据存储
        Set<String> roles = Set.of("message", "member");
        Set<String> actions = Set.of("message:echo", "message:list", "member:add", "member:list", "member:delete", "member:edit");
        SimpleAuthorizationInfo authz = new SimpleAuthorizationInfo();
        authz.setRoles(roles); // 保存角色
        authz.setStringPermissions(actions); // 保存权限
        return authz;
    }
}
