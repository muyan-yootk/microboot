package com.yootk.action;

import com.yootk.common.action.abs.AbstractBaseAction;
import com.yootk.vo.Message;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/form/*")
public class UploadAction extends AbstractBaseAction {
    @PostMapping("upload")
    public Object uploadHandler(Message message, MultipartFile photo) {
        Map<String, Object> result = new HashMap<>(); // 保存最终的上传结果
        result.put("message", message); // 普通对象
        result.put("photoName", photo.getName());
        result.put("photoOriginalFilename",photo.getOriginalFilename());
        result.put("photoContentType", photo.getContentType());
        result.put("photoSize", photo.getSize());
        return result;
    }
}
