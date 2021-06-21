package com.yootk.test;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

public class TestSplit {
    public static void main(String[] args) {
        String str = "a;b";
        System.out.println(Arrays.toString(str.split(";")));
    }
}
