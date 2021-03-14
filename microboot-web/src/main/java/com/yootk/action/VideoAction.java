package com.yootk.action;

import com.yootk.config.handler.VideoResourceHttpRequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController // 直接响应
@RequestMapping("/data/*")
public class VideoAction {
    @Autowired
    private VideoResourceHttpRequestHandler videoResourceHttpRequestHandler;
    @GetMapping("video")
    public void createVideoData(HttpServletRequest request, HttpServletResponse response) throws Exception {
        this.videoResourceHttpRequestHandler.handleRequest(request, response);
    }
}
