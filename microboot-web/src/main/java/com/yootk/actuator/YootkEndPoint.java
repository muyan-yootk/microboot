package com.yootk.actuator;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.Selector;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration // 配置类
@Endpoint(id="muyan-endpoint") // 定义接口名称
public class YootkEndPoint {
    @ReadOperation
    public Map<String, Object> endpoint(@Selector String select) {  // 获取一些参数数据
        Map<String, Object> map = new HashMap<>();
        map.put("author", "李兴华");
        map.put("message", "沐言科技：www.yootk.com");
        map.put("select", select);
        return map;
    }
}
