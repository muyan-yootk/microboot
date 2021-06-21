package com.yootk.vo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table
public class Member implements UserDetails { // 用户信息
    @Id
    private String mid; // 用户ID
    private String name; // 用户名
    private String password; // 密码
    private Integer enabled; // 启用状态
    @ManyToMany(targetEntity = Role.class)					// 启用延迟加载
    @JoinTable(											// 描述的是一个关联表
            name="member_role" ,								// 定义中间表名称
            joinColumns = { @JoinColumn(name = "mid") }	,	// member与member_role表的连接
            inverseJoinColumns = { @JoinColumn(name = "rid") }) // 通过Member找到Role中的rid的数据
    @JsonBackReference // Jackson防止数据递归处理
    private List<Role> roles; // 角色列表
    // 其他重复操作方法，略…
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles; // 获取全部角色
    }

    @Override
    public String getUsername() { // 返回用户名
        return this.mid;
    }

    @Override
    public boolean isAccountNonExpired() { // 账户是否过期
        return true;
    }

    @Override
    public boolean isAccountNonLocked() { // 账户是否锁定
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() { // 认证是否失效
        return true;
    }

    @Override
    public boolean isEnabled() { // 启用状态
        return this.enabled.equals(1);
    }
}
