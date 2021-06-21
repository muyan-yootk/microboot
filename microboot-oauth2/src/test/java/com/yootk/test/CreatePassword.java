package com.yootk.test;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CreatePassword {
    public static void main(String[] args) {
        String password = "hello";      // 定义明文密码
        PasswordEncoder passwordEncoder = PasswordEncoderFactories
                .createDelegatingPasswordEncoder();  // 获取加密器实例
        String encode = passwordEncoder.encode(password); // 密码加密
        System.out.println(encode.length()); // 输出密码
    }
}
