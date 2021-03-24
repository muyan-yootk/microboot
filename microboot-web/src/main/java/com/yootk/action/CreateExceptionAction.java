package com.yootk.action;

import com.yootk.common.action.abs.AbstractBaseAction;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // 直接基于Rest架构进行处理，省略了@ResponseBody注解
@RequestMapping("/create/*") // 添加父路径
public class CreateExceptionAction extends AbstractBaseAction { // 控制层的实现类
    @RequestMapping("exception")
    public Object create() {
        return 10 / 0;
    }
}