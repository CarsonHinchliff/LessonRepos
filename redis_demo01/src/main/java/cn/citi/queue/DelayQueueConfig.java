package cn.citi.queue;

import io.lettuce.core.RedisClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author Carson
 * @created 2025/3/21 星期五 下午 11:44
 */
@Configuration
public class DelayQueueConfig {
//    @Primary
//    @Bean("studentQueue")
//    public DelayQueue studentQueue(Jedis jedis, ThreadPoolTaskExecutor executor) {
//        return new JedisDelayQueue(jedis, executor, "studentQueue");
//    }
//
//    @Bean("teacherQueue")
//    public DelayQueue teacherQueue(Jedis jedis, ThreadPoolTaskExecutor executor) {
//        return new JedisDelayQueue(jedis, executor, "teacherQueue");
//    }

    @Primary
    @Bean(name = "studentQueue")
    public DelayQueue studentQueue(RedisClient redisClient, ThreadPoolTaskExecutor executor) {
        return new LettuceDelayQueue(redisClient, executor, "studentQueue");
    }

    @Bean(name = "teacherQueue")
    public DelayQueue teacherQueue(RedisClient redisClient, ThreadPoolTaskExecutor executor) {
        return new LettuceDelayQueue(redisClient, executor, "teacherQueue");
    }

    @Bean(name = "studentQueue1")
    public DelayQueue studentQueue1(RedisClient redisClient, ThreadPoolTaskExecutor executor) {
        return new LettuceDelayQueue(redisClient, executor, "studentQueue1");
    }

    @Bean(name = "teacherQueue1")
    public DelayQueue teacherQueue1(RedisClient redisClient, ThreadPoolTaskExecutor executor) {
        return new LettuceDelayQueue(redisClient, executor, "teacherQueue1");
    }
}
