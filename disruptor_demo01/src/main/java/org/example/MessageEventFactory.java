package org.example;

import com.lmax.disruptor.EventFactory;

/**
 * @author Carson
 * @created 2025/3/12 星期三 下午 03:07
 */
public class MessageEventFactory implements EventFactory<MessageEvent> {
    @Override
    public MessageEvent newInstance() {
        return new MessageEvent();
    }
}
