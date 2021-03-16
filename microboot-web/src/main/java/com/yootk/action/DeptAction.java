package com.yootk.action;

import com.yootk.common.action.abs.AbstractBaseAction;
import com.yootk.vo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // 直接基于Rest架构进行处理，省略了@ResponseBody注解
@RequestMapping("/dept/*") // 添加父路径
public class DeptAction extends AbstractBaseAction { // 控制层的实现类
    @Autowired
    private Dept dept;
    @RequestMapping("get") // 子路径
    public Object get() { // 进行请求参数的接收以及请求内容的回应
        return this.dept;
    }
}