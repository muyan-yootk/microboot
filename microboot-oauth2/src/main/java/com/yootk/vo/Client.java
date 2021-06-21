package com.yootk.vo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data // Lombok结构生成
@Entity // JPA实体类
@Table // 表映射
public class Client {
    @Id // 主键字段
    private String cid; // 客户端ID
    private String secret; // 客户端密钥
    private String scope; // 授权范围
    private String grants; // 授权类型
    private String url; // 跳转回URL
}
