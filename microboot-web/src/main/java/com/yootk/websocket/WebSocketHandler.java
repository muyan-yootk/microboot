package com.yootk.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.UUID;

@ServerEndpoint("/websocket/{token}") // 需要提供给客户端一个操作终端
@Component
@Slf4j
public class WebSocketHandler { // 定义WebSocket处理类
    @OnOpen // 只有追加此注解之后才表示开启响应的事件监听
    public void openHandler(
            Session session, // 实现客户端的身份保存
            @PathParam("token") String token // 此时存在有一个路径的参数
    ) { // 进行连接的开启事件处理
        // 本次的操作是基于token模拟了一次授权操作，只是一个假的
        if (token == null || "".equals(token)) {    // 象征性的做一个检查
            this.sendMessage(session, "【ERROR】客户端Token错误，连接失败！");
        }
        log.info("客户端创建WebSocket连接，SessionId = {}", session.getId()); // 日志输出
        // 考虑到后续用户的访问情况，可以继续随意发送一个Token数据，本次就直接通过UUID模拟了
        this.sendMessage(session, UUID.randomUUID().toString()); // 假装有了一个随机的Token
    }
    @OnClose // 关闭WebSocket连接通道
    public void closeHandler(Session session) {
        log.info("客户端断开WebSocket连接，SessionID = {}", session.getId());
    }
    @OnError // 错误时处理
    public void errorHandler(Session session, Throwable throwable) {
        log.info("程序出现了错误：{}", throwable);
    }
    @OnMessage // 消息处理
    public void messageHandler(Session session, String message) {
        log.info("【{}】用户发送请求，message内容为：{}", session.getId(), message);
        this.sendMessage(session, "【ECHO】" + message);
    }
    // 不管是打开连接还是关闭连接，以及最终需要进行数据的交互那么都要进行数据的发送处理
    private void sendMessage(Session session, String message) {
        if (session != null) {  // 判断当前的session是否存在
            synchronized (session) {    // 做一个同步
                try {
                    session.getBasicRemote().sendText(message);
                } catch (IOException e) {}
            }
        }
    }
}
