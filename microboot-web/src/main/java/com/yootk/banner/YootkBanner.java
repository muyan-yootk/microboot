package com.yootk.banner;

import org.springframework.boot.Banner;
import org.springframework.core.env.Environment;

import java.io.PrintStream;
import java.util.Random;

// 如果现在只有一个Banner可以直接基于Lambda表达式来进行配置
public class YootkBanner implements Banner { // 实现了自定义的Banner输出
    private static final String[] YOOTK_BANNER = {
        "                                                  __   __",
        "__  _  ____  _  ____  _  __  ___.__. ____   _____/  |_|  | __     ____  ____   _____",
        "\\ \\/ \\/ /\\ \\/ \\/ /\\ \\/ \\/ / <   |  |/  _ \\ /  _ \\   __\\  |/ /   _/ ___\\/  _ \\ /     \\",
        " \\     /  \\     /  \\     /   \\___  (  <_> |  <_> )  | |    <    \\  \\__(  <_> )  Y Y  \\",
        "  \\/\\_/    \\/\\_/    \\/\\_/ /\\ / ____|\\____/ \\____/|__| |__|_ \\ /\\ \\___  >____/|__|_|  /",
        "                          \\/ \\/                            \\/ \\/     \\/            \\/ "
    }; // 所有的Banner信息如果是复合的结构则必须使用数组进行配置
    private static final String[] EDU_BANNER = {
        "           .___                             __   __                             ",
        "  ____   __| _/_ __    ___.__. ____   _____/  |_|  | __     ____  ____   _____  ",
        "_/ __ \\ / __ |  |  \\  <   |  |/  _ \\ /  _ \\   __\\  |/ /   _/ ___\\/  _ \\ /     \\ ",
        "\\  ___// /_/ |  |  /   \\___  (  <_> |  <_> )  | |    <    \\  \\__(  <_> )  Y Y  \\",
        " \\___  >____ |____/ /\\ / ____|\\____/ \\____/|__| |__|_ \\ /\\ \\___  >____/|__|_|  /",
        "     \\/     \\/      \\/ \\/                            \\/ \\/     \\/            \\/ ",
    };
    private static final String MUYAN_BANNER = "沐言科技：www.yootk.com"; // 普通文本
    private static final Random RANDOM = new Random();
    @Override
    public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) { // 方法覆写
        out.println(); // 输出一个换行
        int num = RANDOM.nextInt(10); // 生成一个0 ~ 9的随机数字
        if (num == 0) { // 第一种情况
            for (String line : YOOTK_BANNER) {
                out.println(line);
            }
        } else if (num % 2 == 1) {
            for (String line : EDU_BANNER) {
                out.println(line);
            }
        } else {
            out.println(MUYAN_BANNER);
        }
        out.println(); // 输出一个换行
        out.flush(); // 强制清空缓存
    }
}
