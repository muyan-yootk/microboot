package com.yootk; // 父包，这个包中的所有子包的类会被自动扫描

import com.yootk.banner.YootkBanner;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // 一个注解解决所有的问题
public class StartSpringBootApplication {
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(
                StartSpringBootApplication.class); // 获取实例化对象
        springApplication.setBannerMode(Banner.Mode.OFF); // 关闭Banner输出
        springApplication.setBanner(new YootkBanner()); // 配置自定义的Banner生成器
        springApplication.run(args); // 运行SpringBoot程序
    }
}
