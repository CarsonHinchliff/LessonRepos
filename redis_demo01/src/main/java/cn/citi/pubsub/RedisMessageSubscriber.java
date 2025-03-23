package cn.citi.pubsub;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

/**
 * @author Carson
 * @created 2025/3/23 星期日 上午 11:18
 */
@Component
@Slf4j
public class RedisMessageSubscriber implements MessageListener {

    @Override
    public void onMessage(Message message, byte[] pattern) {
        String channel = new String(message.getChannel());
        String msg = new String(message.getBody());
        log.info("Message received from Redis channel [" + channel + "]: " + msg);
        // 在这里处理接收到的消息逻辑
    }
}