package org.springframework.boot;

import java.io.PrintStream;

import org.springframework.core.env.Environment;
@FunctionalInterface // 此为函数式接口
public interface Banner {   // 由SpringBoot所提供的内部接口

    /**
     * 通过指定PrintStream（打印流）来实现启动Banner输出
     * @param environment 项目启动时所指派的profile
     * @param sourceClass 应用程序类
     * @param out 实现Banner信息输出
     */
    void printBanner(Environment environment, Class<?> sourceClass, PrintStream out);
    enum Mode { // Banner的启动的模式
        OFF,    // 不输出Banner信息
        CONSOLE, // 在控制台输出Banner
        LOG // 在日志中输出Banner
    }

}