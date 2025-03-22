package cn.citi.queue;

import io.lettuce.core.RedisClient;
import io.lettuce.core.ScoredValue;
import io.lettuce.core.api.sync.RedisCommands;
import jakarta.annotation.PreDestroy;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

/**
 * @author Carson
 * @created 2025/3/21 星期五 下午 11:38
 */
public class LettuceDelayQueue implements DelayQueue {
    private final String queueKey;
    private final RedisClient redisClusterClient;
    private final RedisCommands<String, String> syncCommands;

    private final ThreadPoolTaskExecutor executor;

    public LettuceDelayQueue(RedisClient redisClusterClient, ThreadPoolTaskExecutor executor, String queueKey) {
        syncCommands = redisClusterClient.connect().sync();
        this.redisClusterClient = redisClusterClient;
        this.queueKey = queueKey;
        this.executor = executor;
    }

    /**
     * 将消息推送到延时队列
     *
     * @param message 消息内容
     * @param delay   延迟时间（毫秒）
     */
    public void push(String message, long delay) {
        long score = System.currentTimeMillis()/1000 + delay;
        syncCommands.zadd(queueKey, score, message);
    }

    /**
     * 从延时队列中弹出消息
     *
     * @return 已到期的消息，如果没有到期的消息则返回 null
     */
    public void pop(Consumer<String> consumer) {
        this.executor.submit(() -> {
            while (true) {
                long currentTime = System.currentTimeMillis();
                //System.out.println("current time: " + currentTime);
                List<String> messages = syncCommands.zrangebyscore(queueKey, 0, currentTime/1000, 0, 1);
                if (messages == null || messages.isEmpty()) {
                    continue;
                }

                String message = String.valueOf(messages.iterator().next());
                consumer.accept("lettuce message: " + message + ", queueKey: " + queueKey);
                System.out.println(syncCommands.hashCode());
                syncCommands.zrem(queueKey, message);
            }
        });
    }

    @PreDestroy
    public void close() {
        redisClusterClient.shutdown(Duration.ofSeconds(10), Duration.ofSeconds(5));
    }
}
