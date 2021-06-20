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
@Table // 表名称与类名称相同，所以此处就简单编写注解了
public class Member implements UserDetails { // 保存认证数据信息
    @Id
    private String mid; // 用户ID
    private String name; // 用户姓名
    private String password; // 用户密码
    private Integer enabled; // 用户是否启用（1：true、0：false）
    @ManyToMany(targetEntity = Role.class) // 定义多对多的配置类
    @JoinTable(
            name = "member_role", // 定义表名称
            joinColumns = { @JoinColumn (name = "mid") }, // 两张表依靠mid字段进行关联
            inverseJoinColumns = { @JoinColumn (name = "rid") }
    ) // 进行中间关联表的配置
    @JsonBackReference // 防止Jacks组件在输出的时候进行递归调用
    private List<Role> roles; // 保存全部的角色
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles; // 返回全部的授权数据
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() { // SpringSecurity提供的用户名
        return this.mid; // 本次是通过mid的属性保存用户ID
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled == 1;
    }
}