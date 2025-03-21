package cn.citi.listener;

import cn.citi.bus.Event;
import cn.citi.bus.EventBus;
import cn.citi.bus.EventBusListener;
import cn.citi.component.SpringUtil;
import cn.citi.component.WebSocket;
import cn.citi.model.TeacherEvent;
import cn.citi.model.WebSocketEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Carson
 * @created 2025/3/21 星期五 上午 09:29
 */
@Slf4j
public class WebSocketListener implements EventBusListener<WebSocketEvent> {
    @Override
    public void onEvent(Event<WebSocketEvent> event) {
        log.info(event.toString());
        WebSocket webSocket = SpringUtil.getBean(WebSocket.class);
        webSocket.sendMessage(event.toString());
    }

    @Override
    public Class<?> eventType() {
        return WebSocketEvent.class;
    }
}
