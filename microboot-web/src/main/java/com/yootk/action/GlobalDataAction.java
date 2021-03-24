package com.yootk.action;

import com.yootk.common.action.abs.AbstractBaseAction;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController // 直接基于Rest架构进行处理，省略了@ResponseBody注解
@RequestMapping("/data/*") // 添加父路径
public class GlobalDataAction extends AbstractBaseAction { // 控制层的实现类
    @RequestMapping("echo")
    public Object echo(String message, Model model) {
        Map<String, Object> map = (Map<String, Object>) model.asMap().get("bindModel"); // 获取绑定的Map集合
        map.put("data", "【ECHO】" + message); // 不同的处理有可能会返回不同的数据结果
        return map; // 通过Map返回数据

    }

    @RequestMapping("calc")
    public Object calc(int x, int y, Model model){
        Map<String, Object> map = (Map<String, Object>) model.asMap().get("bindModel"); // 获取绑定的Map集合
        map.put("data", x / y); // 不同的处理有可能会返回不同的数据结果
        return map; // 通过Map返回数据
    }
}