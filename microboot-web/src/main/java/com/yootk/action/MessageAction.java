package com.yootk.action;

import com.yootk.common.action.abs.AbstractBaseAction;
import com.yootk.vo.Message;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController // 直接基于Rest架构进行处理，省略了@ResponseBody注解
@RequestMapping("/message/*") // 添加父路径
@Validated // 启用当前的JSR303注解
public class MessageAction extends AbstractBaseAction { // 控制层的实现类
    @RequestMapping("echo") // 子路径
    public Object echo(@Valid Message message) { // 进行请求参数的接收以及请求内容的回应
        message.setTitle("【ECHO】" + message.getTitle());
        message.setContent("【ECHO】" + message.getContent());
        return message;
    }
    @RequestMapping("get")
    public Object get(@NotNull @Length(min=5, max=15) String id) {  // 根据id加载数据信息
        return "【YOOTK】" + id;
    }
}
// http://localhost/message/echo?title=沐言科技&content=www.yootk.com&pubdate=1998-09-19
// https://localhost/message/echo?title=沐言科技&content=www.yootk.com&pubdate=1998-09-19