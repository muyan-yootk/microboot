package com.yootk.interceptor;

import com.yootk.annotation.JWTCheckToken;
import com.yootk.service.ITokenService;
import jdk.jfr.Frequency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class JWTAuthenticationInterceptor implements HandlerInterceptor { // 认证拦截器
    // Token可以通过参数传递也可以通过头信息传递
    private static final String TOKEN_NAME = "yootkToken"; // Token参数名称
    @Autowired
    private ITokenService tokenService; // Token业务接口

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) { // 不处理拦截操作
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) handler; // 类型转换
        Method method = handlerMethod.getMethod(); // 获取当前要执行的Action方法反射对象
        if (method.isAnnotationPresent(JWTCheckToken.class)) {  // 判断该方法上是否提供有指定的注解
            JWTCheckToken checkToken = method.getAnnotation(JWTCheckToken.class); // 获取指定注解
            if (checkToken.required()) {    // true表示要进行Token检查
                String token = this.getToken(request); // 获取Token数据
                if (!this.tokenService.verifyToken(token)) {    // 验证失败
                    throw new RuntimeException("Token数据无效，无法访问。");
                }
            }
        }
        return true;
    }
    public String getToken(HttpServletRequest request) {
        String token = request.getParameter(TOKEN_NAME); // 通过参数获取头信息
        if (token == null || "".equals(token)) {    // 没有接收到Token
            token = request.getHeader(TOKEN_NAME); // 通过头信息获取
        }
        return token;
    }
}
