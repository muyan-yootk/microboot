package com.yootk.action;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/source/*")
public class SourceAction { // 文件下载
    @Value("${source.mysql}")
    private String mysql;
    @Value("${source.redis}")
    private String redis;
    @Value("${source.messages}")
    private List<String> messages;
    @Value("#{${source.infos}}") // 通过SpEL表达式来进行数据的处理
    private Map<String, String> infos;
    @GetMapping("show")
    public Object show() {
        Map<String, Object> info = new HashMap<>();
        info.put("mysql", this.mysql);
        info.put("redis", this.redis);
        info.put("messages", this.messages);
        info.put("infos", this.infos);
        return info;
    }
}
