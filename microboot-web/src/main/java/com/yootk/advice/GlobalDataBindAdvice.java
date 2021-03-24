package com.yootk.advice;

import com.yootk.vo.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalDataBindAdvice { // 全局数据绑定
    @Autowired
    private Message message; // 注入Message对象实例
    @ModelAttribute(name = "bindModel")
    public Object dataBind() {
        Map<String, Object> map = new HashMap<>(); // 绑定一个Map集合
        map.put("title", "【YOOTK】" + this.message.getTitle());   // 所有的相应都需要提供有此类数据
        map.put("content", "【YOOTK】" + this.message.getContent());   // 所有的相应都需要提供有此类数据
        return map;
    }
}
