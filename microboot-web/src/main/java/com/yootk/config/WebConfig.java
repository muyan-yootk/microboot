package com.yootk.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration // 必须明确的配置此类为配置类
public class WebConfig implements WebMvcConfigurer { // 进行WEB配置
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 1、默认在SpringBoot里面所提供的转换器为Jackson组件，那么首先要移除这个组件
        for (int x = 0; x < converters.size(); x ++) {  // 循环所有的转换器
            if (converters.get(x) instanceof MappingJackson2HttpMessageConverter) {
                converters.remove(x); // 删除当前的转换器
            }
        }
        // 2、项目之中一定是需要提供有一个转换器的，所以此时就可以将FastJSON转换器配置到项目之中
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        // 3、FastJSON在进行最终消息转换处理的时候实际上是需要进行相关配置定义的
        FastJsonConfig config = new FastJsonConfig(); // FastJSON配置
        config.setSerializerFeatures(
                SerializerFeature.WriteMapNullValue, // 允许Map的内容为null
                SerializerFeature.WriteNullListAsEmpty, // List集合为null则使用“[]”代替
                SerializerFeature.WriteNullStringAsEmpty, // String内容为空使用空字符串代替
                SerializerFeature.WriteDateUseDateFormat, // 日期格式化输出
                SerializerFeature.WriteNullNumberAsZero, // 数字为空使用0表示
                SerializerFeature.DisableCircularReferenceDetect // 禁用循环引用
        ); // 配置相关的序列化处理操作
        fastJsonHttpMessageConverter.setFastJsonConfig(config); // 配置FastJSON转换处理
        // 4、既然最终要输出的内容是JSON，所以需要配置好响应的头信息结构
        List<MediaType> fastjsonMediaTypes = new ArrayList<>(); // 定义所有的响应类型
        fastjsonMediaTypes.add(MediaType.APPLICATION_JSON); // 使用JSON的类型进行响应
        fastJsonHttpMessageConverter.setSupportedMediaTypes(fastjsonMediaTypes);
        // 5、在转换器列表之中添加当前配置完成的FastJSON转换组件
        converters.add(fastJsonHttpMessageConverter);
    }
}
