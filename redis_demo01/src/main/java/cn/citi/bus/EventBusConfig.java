package cn.citi.bus;

import io.lettuce.core.RedisClient;
import io.lettuce.core.cluster.RedisClusterClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Carson
 * @created 2025/3/21 星期五 上午 09:27
 */
@Configuration
public class EventBusConfig {
//    @Bean
//    public EventBus eventBus(RedisClusterClient clusterClient){
//        return new RedisEventBus(clusterClient);
//    }

    @Bean
    public EventBus eventBus(RedisClient redisClient){
        return new RedisEventBus(redisClient);
    }
}
