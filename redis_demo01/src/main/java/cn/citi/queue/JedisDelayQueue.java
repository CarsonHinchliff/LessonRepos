//package cn.citi.queue;
//
//import jakarta.annotation.PreDestroy;
//import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.resps.Tuple;
//
//import java.time.Duration;
//import java.util.List;
//import java.util.Set;
//import java.util.function.Consumer;
//
///**
// * @author Carson
// * @created 2025/3/21 星期五 下午 11:31
// */
//public class JedisDelayQueue implements DelayQueue {
//    private final String queueKey;
//    private Jedis jedis;
//    private final ThreadPoolTaskExecutor executor;
//
//    public JedisDelayQueue(Jedis jedis, ThreadPoolTaskExecutor executor, String queueKey) {
//        this.jedis = jedis;
//        this.queueKey = queueKey;
//        this.executor = executor;
//    }
//
//    /**
//     * 将消息推送到延时队列
//     *
//     * @param message 消息内容
//     * @param delay   延迟时间（毫秒）
//     */
//    public void push(String message, long delay) {
//        long score = System.currentTimeMillis()/1000 + delay;
//        jedis.zadd(queueKey, score, message);
//    }
//
//    /**
//     * 从延时队列中弹出消息
//     *
//     * @return 已到期的消息，如果没有到期的消息则返回 null
//     */
//    public void pop(Consumer<String> consumer) {
//        this.executor.execute(() -> {
//            while(true) {
//                List<String> messages = jedis.zrangeByScore(queueKey, 0, System.currentTimeMillis()/1000, 0, 1);
//                if (messages.isEmpty()) {
//                    continue;
//                }
//                String message = messages.iterator().next();
//                consumer.accept("jedis message: " + message + ", queueKey: " + queueKey);
//                jedis.zrem(queueKey, message);
//            }
//        });
//    }
//
//    @PreDestroy
//    public void close() {
//        jedis.shutdown();
//    }
//}
