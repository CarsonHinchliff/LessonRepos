package cn.citi.config;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.cluster.RedisClusterClient;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
}
