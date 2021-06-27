package com.yootk.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.session.mgt.WebSessionKey;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class ShiroAuthFilter extends FormAuthenticationFilter { // 认证过滤器
    private static final String COOKIE_SESSION_ID = "session-id"; // 要获取保存在客户端的SessionID

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        HttpServletRequest req = (HttpServletRequest) request; // 强制转型，变为HTTP内置对象
        String sessionId = req.getHeader(COOKIE_SESSION_ID); // 通过头信息获取内容
        if (sessionId == null || "".equals(sessionId)) { // 头信息没有具体内容
            sessionId = req.getParameter(COOKIE_SESSION_ID); // 通过参数再获取一次
        }
        if (sessionId != null) {    // 要进行进一步的处理
            SessionKey key = new WebSessionKey(sessionId, request, response); // 将接收到的SessionID进行包装
            org.apache.shiro.mgt.SecurityManager securityManager = SecurityUtils.getSecurityManager(); // 获取安全管理器
            try {
                Subject.Builder builder = new Subject.Builder(securityManager);
                builder.sessionId(sessionId); // 绑定SessionID
                Subject subject = builder.buildSubject(); // 构建用户内容
                ThreadContext.bind(subject); // 绑定在容器之中
                Session session = securityManager.getSession(key); // 获取Session内容
                return session != null; // true允许访问
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
        return false;       // 访问拒绝
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        // 如果以上的过滤访问处理返回的是一个False，则触发此方法，这个方法用于进行错误显示
        HttpServletResponse resp = (HttpServletResponse) response;
        resp.setContentType("application/json; charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // HTTP响应状态码
        PrintWriter out = resp.getWriter(); // 获取响应输出流
        Map<String, Object> map = new HashMap<>() ; // 保存响应数据信息
        map.put("status", HttpServletResponse.SC_UNAUTHORIZED);
        map.put("message", "用户未登录，无法进行资源访问！");
        out.write(new ObjectMapper().writeValueAsString(map)); // 使用Jacks工具将对象转为JSON数据
        out.close(); // 关闭输出流
        return false; // 请求拦截
    }
}
