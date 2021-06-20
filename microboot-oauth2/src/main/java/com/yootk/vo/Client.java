package com.yootk.vo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table
public class Client {
    @Id
    private String cid; // 注册的账户ID
    private String secret;
    private String scope;
    private String grants;
    private String url;
}
