package com.yootk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;
@Configuration // 配置Bean
public class DefaultLocaleResolver implements LocaleResolver {  // 配置转换器
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        // 此时接收的参数名称肯定是loc，而传递的loc参数的内容一般是“zh_CN”
        String loc = request.getParameter("loc"); // 接收请求参数
        System.out.println("************* " + loc);
        if (!StringUtils.hasLength(loc)) {   // 现在没有内容
            return Locale.getDefault(); // 使用默认的Locale实例
        } else {    // 现在有请求数据
            String [] split = loc.split("_"); // 数据拆分
            return new Locale(split[0], split[1]); // 手工实例化Locale对象
        }
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {}
    @Bean
    public LocaleResolver localeResolver() { // 定义转换处理的Bean
        return new DefaultLocaleResolver(); // 返回LocaleResolver实例
    }
}
