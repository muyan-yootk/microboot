package com.yootk.lombok;

import lombok.SneakyThrows;

public class MessageHandle { // 在此时进行异常的控制
    @SneakyThrows // 会自动的生成try...catch
    public static void print(String message) {  // 信息输出
        if (message == null) {  // 内容为空
            throw new Exception("传递的message参数内容为空，你疯了吗？");
        }
        System.out.println(message.toUpperCase()); // 打印信息
    }
}
