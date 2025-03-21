package org.example;

import com.lmax.disruptor.EventHandler;

/**
 * @author Carson
 * @created 2025/3/12 星期三 下午 03:07
 */
public class MessageEventHandler implements EventHandler<MessageEvent> {
    @Override
    public void onEvent(MessageEvent messageEvent, long l, boolean b) throws Exception {
        System.out.println(messageEvent);
    }
}
