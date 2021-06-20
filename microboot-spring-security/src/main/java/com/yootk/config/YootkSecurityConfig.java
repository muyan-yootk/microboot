package com.yootk.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class YootkSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService; // 注入所需要的实例
    @Bean // 如果要想使用密码，则必须配置有一个密码的编码器
    public PasswordEncoder getPasswordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userDetailsService); // 通过UserDetailsService查询
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception { // 来进行访问配置
        http.authorizeRequests()    // 进行授权访问请求的配置（那些路径与那些角色进行匹配）
                .antMatchers("/admin/**").hasRole("ADMIN") // ADMIN角色可以访问
                .antMatchers("/member/**").access("hasAnyRole('USER')") // USER角色可以访问
                .antMatchers("/message/**").access(
                "hasAnyRole('ADMIN') and hasRole('USER')")
                .anyRequest().authenticated() // 请求认证访问
                .and() // 继续连接后续的其他配置项
                .formLogin().loginProcessingUrl("/yootk-login") // 登录的处理路径
                .usernameParameter("uname").passwordParameter("upass") // 认证的参数名称
                .permitAll()
                .successHandler(new AuthenticationSuccessHandler() { // 实现认证成功之后的配置处理
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        Object principal = authentication.getPrincipal(); // 认证以及授权的内容
                        response.setContentType("application/json;charset=UTF-8"); // 响应的类型为JSON
                        response.setStatus(HttpServletResponse.SC_OK); // 响应200的状态码
                        Map<String, Object> result = new HashMap<>(); // 登录成功之后响应数据
                        result.put("status", HttpServletResponse.SC_OK); // 当前的登录状态
                        result.put("message", "用户登录成功");
                        result.put("principal", principal); // 实际的开发中对于认证数据肯定要筛选
                        result.put("sessionId", request.getSession().getId()); // 所有的认证路径检测都通过SessionID进行
                        // 此时需要将Map集合转为JSON结构，按照Spring默认的转换建议使用Jackson工具
                        ObjectMapper mapper = new ObjectMapper();
                        response.getWriter().println(mapper.writeValueAsString(result)); // Map集合转为JSON数据
                    }
                }).failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
                        response.setContentType("application/json;charset=UTF-8"); // 响应的类型为JSON
                        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 响应401的状态码
                        Map<String, Object> result = new HashMap<>(); // 登录成功之后响应数据
                        result.put("status", HttpServletResponse.SC_UNAUTHORIZED); // 当前的登录状态
                        result.put("principal", null); // 返回一个空的认证数据
                        result.put("sessionId", request.getSession().getId()); // 所有的认证路径检测都通过SessionID进行
                        if (exception instanceof LockedException) {
                            result.put("message", "账户被锁定，登录失败！");
                        } else if (exception instanceof BadCredentialsException) {
                            result.put("message", "账户名或密码输入错误，登录失败！");
                        } else if (exception instanceof DisabledException) {
                            result.put("message", "账户被禁用，登录失败！");
                        } else if (exception instanceof AccountExpiredException) {
                            result.put("message", "账户已过期，登录失败！");
                        } else if (exception instanceof CredentialsExpiredException) {
                            result.put("message", "密码已过期，登录失败！");
                        } else {
                            result.put("message", "未知原因，导致登录失败！");
                        }
                        // 此时需要将Map集合转为JSON结构，按照Spring默认的转换建议使用Jackson工具
                        ObjectMapper mapper = new ObjectMapper();
                        response.getWriter().println(mapper.writeValueAsString(result)); // Map集合转为JSON数据
                    }
                }).and().logout().logoutUrl("/yootk-logout") // 注销处理
                .clearAuthentication(true) // 清除掉所有的认证信息
                .invalidateHttpSession(true) // 注销当前的Session
                .logoutSuccessHandler(new LogoutSuccessHandler() {// 注销成功之后返回的数据内容
                    @Override
                    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        Object principal = authentication.getPrincipal(); // 认证以及授权的内容
                        response.setContentType("application/json;charset=UTF-8"); // 响应的类型为JSON
                        response.setStatus(HttpServletResponse.SC_OK); // 响应200的状态码
                        Map<String, Object> result = new HashMap<>(); // 登录成功之后响应数据
                        result.put("status", HttpServletResponse.SC_OK); // 当前的登录状态
                        result.put("message", "用户注销成功");
                        result.put("principal", null); // 实际的开发中对于认证数据肯定要筛选
                        result.put("sessionId", request.getSession().getId()); // 所有的认证路径检测都通过SessionID进行
                        // 此时需要将Map集合转为JSON结构，按照Spring默认的转换建议使用Jackson工具
                        ObjectMapper mapper = new ObjectMapper();
                        response.getWriter().println(mapper.writeValueAsString(result)); // Map集合转为JSON数据
                    }
                })
                .and().csrf().disable();
    }
}
