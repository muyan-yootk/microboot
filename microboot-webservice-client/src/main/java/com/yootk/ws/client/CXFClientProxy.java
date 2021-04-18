package com.yootk.ws.client;

import com.yootk.service.IMessageService;
import com.yootk.ws.util.ClientLoginInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

public class CXFClientProxy {
    public static void main(String[] args) throws Exception {
        String address = "http://localhost:8080/services/MessageService?wsdl"; // WebService服务地址
        JaxWsProxyFactoryBean jaxWsProxyFactoryBean = new JaxWsProxyFactoryBean();
        jaxWsProxyFactoryBean.setAddress(address); // 代理地址
        jaxWsProxyFactoryBean.setServiceClass(IMessageService.class); // 映射的接口
        jaxWsProxyFactoryBean.getOutInterceptors().add(
                new ClientLoginInterceptor("muyan", "yootk.com")); // 设置认证数据
        IMessageService messageService = (IMessageService)  jaxWsProxyFactoryBean.create(); // 远程接口映射
        String message = "沐言科技：www.yootk.com";
        String result = messageService.echo(message); // 业务调用
        System.out.println(result);
    }
}
