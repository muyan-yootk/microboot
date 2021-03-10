package com.yootk.test;

import com.yootk.vo.Message;

public class TestMessage {
    public static void main(String[] args) {
        // 此时将基于Lombok生成的构建者模式来进行Message对象的生成以及属性配置
        Message message = Message.builder().title("沐言科技").content("www.yootk.com").build();
        System.out.println(message);
    }
}
