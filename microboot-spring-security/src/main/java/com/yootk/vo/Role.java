package com.yootk.vo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@Entity
@Table
public class Role implements GrantedAuthority {// 保存授权信息
    @Id
    private String rid; // 保存角色ID（一般都是字符串）
    private String title; // 保存角色的名称（仅仅是为了一些标注）
    @ManyToMany(mappedBy = "roles") // Member类中的属性
    @JsonBackReference // 防止Jacks组件在输出的时候进行递归调用
    private List<Member> members; // 必须按照此类方式进行设置
    @Override
    public String getAuthority() {
        return this.rid;
    }
}
