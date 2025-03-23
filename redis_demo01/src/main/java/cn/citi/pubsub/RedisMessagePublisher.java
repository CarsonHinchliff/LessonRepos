package cn.citi.pubsub;

import cn.citi.bus.Event;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Carson
 * @created 2025/3/23 星期日 上午 11:17
 */
@Component
@Slf4j
public class RedisMessagePublisher {

    @Autowired
    private StringRedisTemplate redisTemplate;

    public void publish(String channel, String message) {
        publish(channel, new Event(message));
    }

    public void publish(String channel, Event event) {
        var message = JSON.toJSONString(event);
        redisTemplate.convertAndSend(channel, message);
        log.info("Message published to Redis channel [" + channel + "]: " + message);
    }
}