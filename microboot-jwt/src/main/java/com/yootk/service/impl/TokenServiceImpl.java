package com.yootk.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.yootk.config.JWTConfigProperties;
import com.yootk.service.ITokenService;
import io.jsonwebtoken.*;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class TokenServiceImpl implements ITokenService {
    @Autowired
    private JWTConfigProperties jwtConfigProperties; // JWT的相关配置属性
    @Value("${application.application.name?:muyan-yootk-token}") // Groovy表达式
    private String applicationName;
    private SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256; // 签名算法
    @Override
    public SecretKey generalKey() { // 获取加密KEY
        byte[] encodedKey = Base64.decodeBase64(Base64.encodeBase64(this.jwtConfigProperties.getSecret().getBytes()));
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
        return key;
    }

    @Override
    public String createToken(String id, Map<String, Object> subject) {
        Date nowDate = new Date(); // 获取当前的日期时间
        // 当前的时间 + 失效时间配置的秒数 = 最终失效的日期时间
        Date expireDate = new Date(nowDate.getTime() + this.jwtConfigProperties.getExpire() * 1000);
        Map<String, Object> claims = new HashMap<>(); // 附加的Claims信息
        claims.put("site", "www.yootk.com"); // 添加信息内容
        claims.put("book", "SpringBoot就业编程实战");  // 添加信息内容
        claims.put("company", "沐言科技");  // 添加信息内容
        Map<String, Object> headers = new HashMap<>(); // 保存的头信息
        headers.put("author", "爆可爱的小李老师");
        headers.put("module", this.applicationName); // 保存应用的名称
        headers.put("desc", "我是一个很普通的老师，喜欢教学，认真搞真正的教育。");
        JwtBuilder builder = Jwts.builder().setClaims(claims)    // 保存Claims信息
                .setHeader(headers) // 保存Headedr信息
                .setId(id) // 保存ID内容
                .setIssuedAt(nowDate) // 证书签发日期时间
                .setIssuer(this.jwtConfigProperties.getIssuer()) // 证书签发者
                .setSubject(JSONObject.toJSONString(subject)) // 附加信息
                .signWith(this.signatureAlgorithm, this.generalKey()) // 签名算法
                .setExpiration(expireDate); // Token失效时间
        return builder.compact(); // 生成Token
    }

    @Override
    public Jws<Claims> parseToken(String token) throws JwtException {
        if (this.verifyToken(token)) {  // 检查当前的Token是否正确
            Jws<Claims> claims = Jwts.parser().setSigningKey(this.generalKey()).parseClaimsJws(token);
            return claims;
        }
        return null;
    }

    @Override
    public boolean verifyToken(String token) {
        try {
            Jwts.parser().setSigningKey(this.generalKey()).parseClaimsJws(token).getBody();
            return true;// 没有异常，解析成功
        } catch (JwtException exception) {
            return false;
        }
    }

    @Override
    public String refreshToken(String token) {
        if (this.verifyToken(token)) {  // 正确的Token是可以进行刷新的
            Jws<Claims> claimsJws = this.parseToken(token); // 解析数据
            return this.createToken(claimsJws.getBody().getId(), JSONObject.parseObject(claimsJws.getBody().getSubject(), Map.class));
        }
        return null;
    }
}
