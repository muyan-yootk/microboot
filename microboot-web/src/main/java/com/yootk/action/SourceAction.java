package com.yootk.action;

import com.yootk.vo.Source;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/source/*")
public class SourceAction { // 文件下载
    @Autowired
    private Source source;
    @GetMapping("show")
    public Object show() {
        return this.source;
    }
}
