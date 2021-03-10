package com.yootk.test;

import com.yootk.vo.Message;

public class TestMessage {
    public static void main(String[] args) {
        Message message = new Message(); // 调用无参构造进行对象实例化
        message.setTitle("沐言科技"); // 自动生成setter方法
        message.setContent("www.yootk.com"); // 自动生成setter方法
        System.out.println(message); // 调用toString()输出
    }
}
