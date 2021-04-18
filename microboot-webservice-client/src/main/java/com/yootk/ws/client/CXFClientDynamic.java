package com.yootk.ws.client;

import com.yootk.ws.util.ClientLoginInterceptor;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

public class CXFClientDynamic {
    public static void main(String[] args) throws Exception {
        String address = "http://localhost:8080/services/MessageService?wsdl"; // WebService服务地址
        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
        Client client = dcf.createClient(address); // 创建一个客户端类
        client.getOutInterceptors().add(new ClientLoginInterceptor("muyan", "yootk.com"));
        String message = "沐言科技：www.yootk.com";
        Object[] result = client.invoke("echo", message);
        System.out.println(result[0]);
    }
}
