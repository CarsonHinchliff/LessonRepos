package cn.citi.controller;

import cn.citi.Constant;
import cn.citi.bus.Event;
import cn.citi.bus.EventBus;
import cn.citi.model.WebSocketEvent;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Carson
 * @created 2025/3/21 星期五 上午 09:08
 */
@RestController
@RequestMapping("/ws-trigger")
@AllArgsConstructor
public class WSTriggerController {
    private final EventBus eventBus;

    @GetMapping("/get")
    public String get(){
        return "Hello";
    }

    @GetMapping("/publish")
    public String publish(String name){
        var event = new WebSocketEvent(name);
        eventBus.publish(Constant.Channels.WEBSOCKET_CHANNEL, new Event<WebSocketEvent>(event));
        return name;
    }
}
