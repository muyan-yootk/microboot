package com.yootk.action;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

@RestController
@RequestMapping("/data/*")
public class DownloadAction { // 文件下载
    @GetMapping("download")
    public void fileDownload(HttpServletResponse response) throws Exception {
        response.setContentType("application/force-download"); // 强制性下载
        response.setHeader("Content-Disposition", "attachement;filename=muyan.rar");
        Resource fileResource = new ClassPathResource("/files/yootk.com.rar"); // 要下载的文件
        // 要通过IO流读取文件内容，随后一点点进行文件下载操作
        InputStream input = fileResource.getInputStream(); // 获取资源输入流
        byte data[] = new byte[1024]; // 每次读取1024字节
        int len = 0; // 每次读取的个数
        while ((len = input.read(data)) != -1) {
            response.getOutputStream().write(data, 0, len);
        }
    }
}
