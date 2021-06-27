package com.yootk.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;

import javax.crypto.SecretKey;
import java.util.Map;

public interface ITokenService { // 实现JWT的相关操作接口
    public SecretKey generalKey(); // 获取当前JWT数据加密KEY

    /**
     * 生成一个合法的Token数据
     * @param id 这个Token的唯一ID（随意存储，本次可以考虑存储用户ID）
     * @param subject 所有附加的信息内容，本次直接接收了一个Map，但是最终存储的时候存放JSON
     * @return 返回一个有效的Token数据字符串
     */
    public String createToken(String id, Map<String, Object> subject);

    /**
     * 是根据Token的字符串内容解析出其组成的信息（头信息与附加信息）
     * @param token 要解析的Token完整数据
     * @return Jws接口实例
     * @throws JwtException 如果Token失效或者结构错误
     */
    public Jws<Claims> parseToken(String token) throws JwtException;
    /**
     * 校验当前传递的Token数据是否正确
     * @param token 要检查的Token数据
     * @return true表示合法、false表示无效
     */
    public boolean verifyToken(String token);
    /**
     * Token存在有效时间的定义，所以一定要提供有Token刷新机制
     * @param token 原始的Token数据
     * @return 新的Token数据
     */
    public String refreshToken(String token);
}
