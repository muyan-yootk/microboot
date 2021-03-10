package com.yootk.test;

import com.yootk.vo.Message;

public class TestMessage {
    public static void main(String[] args) {
        Message message = new Message(); // 调用无参构造进行对象实例化
        message.title("沐言科技").content("www.yootk.com"); // 代码链形式访问
        System.out.println(message); // 调用toString()输出
    }
}
