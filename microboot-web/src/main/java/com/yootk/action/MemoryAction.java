package com.yootk.action;

import com.yootk.common.action.abs.AbstractBaseAction;
import com.yootk.vo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController // 直接基于Rest架构进行处理，省略了@ResponseBody注解
@RequestMapping("/jvm/*") // 添加父路径
public class MemoryAction extends AbstractBaseAction { // 控制层的实现类
    @RequestMapping("memory")
    public Object memory() {
        Runtime runtime = Runtime.getRuntime(); // 获取Runtime对象实例
        Map<String, Object> data = new HashMap<>();
        data.put("MaxMemory", runtime.maxMemory());
        data.put("TotalMemory", runtime.totalMemory());
        data.put("FreeMemory", runtime.freeMemory());
        return data;
    }
}