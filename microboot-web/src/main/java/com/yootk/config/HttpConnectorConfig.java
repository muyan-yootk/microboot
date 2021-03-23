package com.yootk.config;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpConnectorConfig { // 配置HTTP连接器
    public Connector getHTTPConnector() { // 获取新的连接器
        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol"); // 配置连接器的处理协议
        connector.setScheme("http"); // HTTP协议访问
        connector.setSecure(false); // 非安全传输
        connector.setPort(80); // HTTP监听端口
        connector.setRedirectPort(443); // 强制跳转到443端口
        return connector;
    }
    @Bean // 自动配置
    public TomcatServletWebServerFactory tomcatServletWebServerFactory() {
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
            @Override
            protected void postProcessContext(Context context) { // 发送处理
                SecurityConstraint securityConstraint = new SecurityConstraint();
                securityConstraint.setUserConstraint("CONFIDENTIAL"); // 设置约束
                SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*"); // 所有的路径全部进行处理
                securityConstraint.addCollection(collection);
                context.addConstraint(securityConstraint);
            }
        };
        tomcat.addAdditionalTomcatConnectors(this.getHTTPConnector());
        return tomcat;
    }
}
