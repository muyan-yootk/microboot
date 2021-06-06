package com.yootk.test;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CreatePassword {
    public static void main(String[] args) {
        String password = "hello"; // 密码明文
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder(); // 获取PasswordEncoder接口实例
        System.out.println(encoder.encode(password));
    }
}
