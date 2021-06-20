package com.yootk.vo;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
@Data
public class Role implements GrantedAuthority {// 保存授权信息
    private String rid; // 保存角色ID（一般都是字符串）
    private String title; // 保存角色的名称（仅仅是为了一些标注）
    @Override
    public String getAuthority() {
        return this.rid;
    }
}
