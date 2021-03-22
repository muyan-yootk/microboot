package com.yootk.action;

import com.yootk.common.action.abs.AbstractBaseAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@RestController // 直接基于Rest架构进行处理，省略了@ResponseBody注解
@RequestMapping("/i18n/*") // 添加父路径
public class I18NAction extends AbstractBaseAction { // 控制层的实现类
    @Autowired // 容器启动的时候会自动的提供此Bean实例
    private MessageSource messageSource; // 消息资源
    @RequestMapping("base") // 子路径
    public Object showBase() { // 进行请求参数的接收以及请求内容的回应
        Map<String, String> map = new HashMap<>(); // 定义Map集合
        map.put("message", this.messageSource.getMessage("yootk.message", null, Locale.getDefault()));
        map.put("url", this.messageSource.getMessage("yootk.url", null, Locale.getDefault()));
        return map;
    }
    @RequestMapping("locale") // 子路径
    public Object showLocale(Locale loc) { // 进行请求参数的接收以及请求内容的回应
        Map<String, String> map = new HashMap<>(); // 定义Map集合
        map.put("message", this.messageSource.getMessage("yootk.message", null, loc));
        map.put("url", this.messageSource.getMessage("yootk.url", null, loc));
        return map;
    }
}