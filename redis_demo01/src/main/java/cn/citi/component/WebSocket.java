package cn.citi.component;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author Carson
 * @created 2025/3/21 星期五 下午 04:58
 */
@Slf4j
@Component
@ServerEndpoint("/webSocket")
public class WebSocket {
    //当前连接（每个websocket连入都会创建一个WebSocket实例）
    private Session session;

    //定义一个websocket容器存储session,即存放所有在线的socket连接
    private static CopyOnWriteArraySet<WebSocket> webSocketSet = new CopyOnWriteArraySet<>();

    //处理连接建立
    @OnOpen
    public void opOpen(Session session){
        this.session = session;
        log.info("【有新的客户端连接了】：{}",session.getId());
        webSocketSet.add(this);  //将新用户加入在线组
        log.info("【websocket消息】有新的连接，总数：{}",webSocketSet.size());
    }

    //处理连接关闭
    @OnClose
    public void Onclose(){
        webSocketSet.remove(this);
        log.info("【websocket消息】连接断开，总数：{}",webSocketSet.size());
    }

    //接受消息
    @OnMessage
    public void onMessage(String message){
        log.info("【websocket消息】收到客户端发来的消息：{}",message);
    }

    // 群发消息
    public void sendMessage(String message) {
        for (WebSocket webSocket : webSocketSet) {
            log.info("【websocket消息】广播群发消息,message={}",message);
            try {
                webSocket.session.getBasicRemote().sendText(message);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
