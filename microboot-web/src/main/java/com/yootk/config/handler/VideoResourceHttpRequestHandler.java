package com.yootk.config.handler;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
@Component
public class VideoResourceHttpRequestHandler extends ResourceHttpRequestHandler { // 请求处理器

    @Override
    protected Resource getResource(HttpServletRequest request) throws IOException {
        return new ClassPathResource("/videos/muyan.mp4");
    }
}
