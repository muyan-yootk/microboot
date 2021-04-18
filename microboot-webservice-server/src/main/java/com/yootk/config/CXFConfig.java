package com.yootk.config;

import com.yootk.interceptor.WebServiceAuthInterceptor;
import com.yootk.service.IMessageService;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.ws.Endpoint;

@Configuration
public class CXFConfig {
    @Autowired
    private Bus bus; // 注入Bus接口实例
    @Autowired
    private IMessageService messageService; // 注入业务实例
    @Autowired
    private WebServiceAuthInterceptor interceptor; // 认证拦截器
    @Bean
    public ServletRegistrationBean getRegistrationBean() {
        return new ServletRegistrationBean(new CXFServlet(), "/services/*"); // WebService访问的父路径
    }
    @Bean
    public Endpoint messageEndPoint() {
        EndpointImpl endpoint = new EndpointImpl(this.bus, this.messageService);
        endpoint.publish("/MessageService");
        endpoint.getInInterceptors().add(this.interceptor); // 访问拦截器
        return endpoint;
    }
}
