package cn.citi.bus;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import io.lettuce.core.RedisClient;
import io.lettuce.core.pubsub.RedisPubSubAdapter;
import io.lettuce.core.pubsub.StatefulRedisPubSubConnection;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * @author Carson
 * @created 2025/3/21 星期五 上午 09:18
 */
@Slf4j
public class RedisEventBus<T> extends RedisPubSubAdapter<String, String> implements EventBus<T> {
    private final RedisClient redisClient;
    private final Map<String, List<EventBusListener<T>>> channelListeners = new HashMap<>();
    private final StatefulRedisPubSubConnection<String, String> connection;

    public RedisEventBus(RedisClient redisClient) {
        this.redisClient = redisClient;
        this.connection = this.redisClient.connectPubSub();
        this.connection.addListener(this);//1.register self
    }

    @Override
    public void message(String channel, String message) {
        log.info("message: " + message + " ,channel: " + channel);
        List<EventBusListener<T>>
                listeners = channelListeners.get(channel);
        if (listeners == null) {return;}
        for (EventBusListener<T> listener : listeners) {
            try{
                Event<T> event = JSON.parseObject(message, new TypeReference<Event<T>>() {});
                listener.onEvent(event);
            }catch (Exception e){
                log.error(e.getMessage());
            }
        }
    }

    @Override
    public void subscribe(String channel, EventBusListener<T> listener) {
        this.channelListeners.computeIfAbsent(channel, k -> Collections.synchronizedList(new ArrayList<>())).add(listener);
        this.connection.sync().subscribe(channel);//2.subscribe channel
    }

    @Override
    public void publish(String channel, Event<T> event) {
        //this.connection.sync().publish(channel, JSON.toJSONString(event));
        this.redisClient.connectPubSub().sync().publish(channel, JSON.toJSONString(event));//3.use new connection for publish
    }
}
