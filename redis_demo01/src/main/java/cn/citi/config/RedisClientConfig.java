package cn.citi.config;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;

/**
 * @author Carson
 * @created 2025/3/21 星期五 上午 09:12
 */
@Configuration
public class RedisClientConfig {
//    @Bean
//    public RedisClusterClient redisClusterClient(RedisProperties redisProperties) {
//        RedisURI.Builder builder = RedisURI.builder()
//                .withHost(redisProperties.getHost())
//                .withPort(redisProperties.getPort())
//                .withDatabase(redisProperties.getDatabase());
//        return RedisClusterClient.create(builder.build());
//    }

    @Bean
    public RedisClient redisClient(RedisProperties redisProperties) {
        RedisURI.Builder builder = RedisURI.builder()
                .withHost(redisProperties.getHost())
                .withPort(redisProperties.getPort())
                .withDatabase(redisProperties.getDatabase());
        return RedisClient.create(builder.build());
    }

    @Bean(destroyMethod = "close")
    public Jedis jedis(RedisProperties redisProperties) {
//        JedisPoolConfig poolConfig = new JedisPoolConfig();
//        poolConfig.setMaxTotal(128); // 最大连接数
//        poolConfig.setMaxIdle(128); // 最大空闲连接数
//        poolConfig.setMinIdle(16); // 最小空闲连接数
//        poolConfig.setTestOnBorrow(true); // 借用连接时测试连接有效性
//        poolConfig.setTestOnReturn(true); // 归还连接时测试连接有效性
//        poolConfig.setTestWhileIdle(true); // 空闲时测试连接有效性
//        poolConfig.setMinEvictableIdleTimeMillis(Duration.ofSeconds(60).toMillis()); // 连接空闲时间
//        poolConfig.setTimeBetweenEvictionRunsMillis(Duration.ofSeconds(30).toMillis()); // 检查连接间隔
//        poolConfig.setNumTestsPerEvictionRun(3); // 每次检查的连接数
//        poolConfig.setBlockWhenExhausted(true); // 连接耗尽时是否阻塞
//        poolConfig.setMaxWaitMillis(1000); // 阻塞等待时间
//        Jedis jedis = new JedisPool(poolConfig, redisProperties.getHost(), redisProperties.getPort(), 10000).getResource();
        Jedis jedis = new JedisPool(redisProperties.getHost(), redisProperties.getPort()).getResource();
        return jedis;
    }
}
