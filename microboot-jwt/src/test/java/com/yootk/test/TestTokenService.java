package com.yootk.test;

import com.alibaba.fastjson.JSONObject;
import com.yootk.StartJWTApplication;
import com.yootk.config.JWTConfigProperties;
import com.yootk.service.ITokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@ExtendWith(SpringExtension.class) // Junit5测试工具
@WebAppConfiguration    // 表示需要启动Web配置才可以进行测试
@SpringBootTest(classes = StartJWTApplication.class)  // 定义要测试的启动类
public class TestTokenService {
    @Autowired
    private ITokenService tokenService;
    private String token = "eyJhdXRob3IiOiLniIblj6_niLHnmoTlsI_mnY7ogIHluIgiLCJtb2R1bGUiOiJtdXlhbi15b290ay10b2tlbiIsImFsZyI6IkhTMjU2IiwiZGVzYyI6IuaIkeaYr-S4gOS4quW-iOaZrumAmueahOiAgeW4iO-8jOWWnOasouaVmeWtpu-8jOiupOecn-aQnuecn-ato-eahOaVmeiCsuOAgiJ9.eyJzdWIiOiJ7XCJyaWRzXCI6XCJVU0VSO0FETUlOO0RFUFQ7RU1QO1JPTEVcIixcIm5hbWVcIjpcIuaykOiogOenkeaKgC3mnY7lhbTljY5cIixcIm1pZFwiOlwibXV5YW5cIn0iLCJzaXRlIjoid3d3Lnlvb3RrLmNvbSIsImJvb2siOiJTcHJpbmdCb2905bCx5Lia57yW56iL5a6e5oiYIiwiaXNzIjoiTXV5YW5Zb290ayIsImNvbXBhbnkiOiLmspDoqIDnp5HmioAiLCJleHAiOjE2MjQ3Njk5NzgsImlhdCI6MTYyNDc1OTk3OCwianRpIjoieW9vdGstNGQ2YzdkMzItZmE5Mi00ZTc4LWJkN2YtNzE1MGMxMDA3MDRlIn0.B7f11ckb4etMTcxzdzTh_1VubQSHnifl43t2-3atrD4";

    @Test
    public void testCreate() { // 创建Token数据
        Map<String, Object> map = new HashMap<>(); // 保存subject数据信息
        map.put("mid", "muyan");
        map.put("name", "沐言科技-李兴华");
        map.put("rids", "USER;ADMIN;DEPT;EMP;ROLE"); // 保存角色数据
        String id = "yootk-" + UUID.randomUUID(); // 随机生成ID
        System.out.println(this.tokenService.createToken(id, map));
    }
    @Test
    public void testParse() {
        Jws<Claims> claims = this.tokenService.parseToken(token); // 解析得到的Token数据
        claims.getHeader().forEach((name, value) -> {
            System.out.println("【JWT头信息】name = " + name + "、value = " + value);
        });
        System.err.println("------------------------------------------------------------------");
        claims.getBody().forEach((name, value) -> {
            System.out.println("【JWT主题信息】name = " + name + "、value = " + value);
        });
        System.err.println("------------------------------------------------------------------");
        Map<String, Object> map = JSONObject.parseObject(claims.getBody().get("sub").toString(), Map.class); // 用户配置的信息
        map.entrySet().forEach(entry -> {
            System.out.println("【用户数据】key = " + entry.getKey() + "、value = " + entry.getValue());
        });
    }
    @Test
    public void testVerifyJWT() {
        System.out.println(this.tokenService.verifyToken(token));
    }
    @Test
    public void testRefreshToken() {
        System.out.println(this.tokenService.refreshToken(token));
    }
}
