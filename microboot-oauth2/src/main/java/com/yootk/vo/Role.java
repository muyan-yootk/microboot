package com.yootk.vo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table
public class Role implements GrantedAuthority {
    @Id
    private String rid; // 角色ID
    private String title; // 角色名称
    @ManyToMany(mappedBy = "roles")		// 多对多关联
    @JsonBackReference // Jackson防止数据递归处理
    private List<Member> members;
    // 其他重复操作方法，略…
    @Override
    public String getAuthority() {
        return this.rid;
    }
}
