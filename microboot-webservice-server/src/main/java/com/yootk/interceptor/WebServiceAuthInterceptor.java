package com.yootk.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.saaj.SAAJInInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.springframework.stereotype.Component;
import org.w3c.dom.NodeList;

import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;

@Component
@Slf4j
public class WebServiceAuthInterceptor extends AbstractPhaseInterceptor<SoapMessage> {
    private static final String USER_NAME = "muyan"; // 用户名
    private static final String USER_PASSWORD = "yootk.com"; // 密码
    private SAAJInInterceptor saa = new SAAJInInterceptor(); // 创建拦截器
    public WebServiceAuthInterceptor() {
        super(Phase.PRE_PROTOCOL);
        super.getAfter().add(SAAJInInterceptor.class.getName()); // 添加拦截
    }
    @Override
    public void handleMessage(SoapMessage message) throws Fault {
        SOAPMessage soapMessage = message.getContent(SOAPMessage.class); // 获取指定消息
        if (soapMessage == null) {  // 没有消息内容
            this.saa.handleMessage(message); // 直接走默认处理
            soapMessage = message.getContent(SOAPMessage.class); // 尝试获取消息
        }
        SOAPHeader header = null; // SOAP头信息
        try {
            header = soapMessage.getSOAPHeader(); // 通过消息获取头信息
        } catch (SOAPException e) {
            e.printStackTrace();
        }
        if (header == null) {   // 没有头信息
            throw new Fault(new IllegalAccessException("找不到Header信息，无法实现用户认证处理！"));
        }
        // SOAP是基于XML文件结构进行传输的，所以如果要想获取认证信息就必须进行相关的结构约定
        NodeList usernameNodeList = header.getElementsByTagName("username"); // 获取指定标签集合
        NodeList passwordNodeList = header.getElementsByTagName("password"); // 获取DOM数据
        if (usernameNodeList.getLength() < 1) {
            throw new Fault(new IllegalAccessException("找不到Header信息，无法实现用户认证处理！"));
        }
        if (passwordNodeList.getLength() < 1) {
            throw new Fault(new IllegalAccessException("找不到Header信息，无法实现用户认证处理！"));
        }
        String username = usernameNodeList.item(0).getTextContent().trim() ; // 获取用户名
        String password = passwordNodeList.item(0).getTextContent().trim() ; // 获取密码
        System.err.println("【认证数据】用户名 = " + username + "、密码 = " + password);
        if (USER_NAME.equals(username) && USER_PASSWORD.equals(password)) { // 认证信息合法
            log.debug("用户访问认证成功！");
        } else { // 用户认证失败
            SOAPException soapException = new SOAPException("用户认证失败！"); // 抛出异常
            log.debug("用户认证失败！");
            throw new Fault(soapException);
        }
    }
}
