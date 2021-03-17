package com.yootk.action;

import com.yootk.common.action.abs.AbstractBaseAction;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController // 直接基于Rest架构进行处理，省略了@ResponseBody注解
@RequestMapping("/object/*") // 添加父路径
public class ObjectAction extends AbstractBaseAction { // 控制层的实现类
    @RequestMapping("first") // 子路径
    public Object firstShow(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> result = new HashMap<>();
        result.put("【request】contextPath", request.getContextPath());
        result.put("【request】messageParam", request.getParameter("message"));
        result.put("【request】method", request.getMethod());
        result.put("【session】sessionId", request.getSession().getId());
        result.put("【application】VirtualServerName", request.getServletContext().getVirtualServerName());
        result.put("【application】InitParameter", request.getServletContext().getInitParameter("teacher"));
        return result;
    }
    @RequestMapping("second") // 子路径
    public Object secondShow() {
        ServletRequestAttributes attributes =  (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();
        Map<String, Object> result = new HashMap<>();
        result.put("【request】contextPath", request.getContextPath());
        result.put("【request】messageParam", request.getParameter("message"));
        result.put("【request】method", request.getMethod());
        result.put("【session】sessionId", request.getSession().getId());
        result.put("【application】VirtualServerName", request.getServletContext().getVirtualServerName());
        result.put("【application】InitParameter", request.getServletContext().getInitParameter("teacher"));
        return result;
    }
}