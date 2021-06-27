package com.yootk.action;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
public class MemberAction { // 创建控制器类
    @RequestMapping("/login_handle") // 定义访问路径
    public Object loginHandler(UsernamePasswordToken token, HttpServletRequest request) {   // 所有的信息通过Token类接收
        Map<String, Object> result = new HashMap<>(); // 保存最终的响应结果
        try {
            SecurityUtils.getSubject().login(token); // 登录处理
            result.put("token", "yootk-jwt-token"); // 返回Token的数据项
            result.put("session-id", request.getSession().getId()); // 返回当前操作的SessionID
        } catch (Exception e) {
            result.put("error", e.getMessage()); // 返回错误信息
        }
        return result;
    }
}
