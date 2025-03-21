package cn.citi;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Carson
 * @created 2025/3/12 星期三 下午 11:53
 */
@Configuration
public class LettuceClientConfig {
    @Value("${spring.data.redis.host}")
    private String host;

    //Redis服务端口
    @Value("${spring.data.redis.port}")
    private Integer port;

    //Redis默认库，一共0～15
    @Value("${spring.data.redis.database}")
    private Integer database;

//    //Lettuce连接配置（Redis单机版实例）
//    @Bean(name = "redisClient")
//    public RedisClient redisClient() {
//        RedisURI uri = RedisURI.Builder.redis(this.host, this.port)
//                .withDatabase(this.database)
//                .build();
//        return RedisClient.create(uri);
//    }
}
