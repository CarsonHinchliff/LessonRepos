package cn.citi.cache;

import cn.citi.Constant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.cache.*;

import java.time.Duration;

/**
 * @author Carson
 * @created 2025/3/26 星期三 下午 04:27
 */
public class CustomRedisCacheManager extends RedisCacheManager {
    public CustomRedisCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration) {
        super(cacheWriter, defaultCacheConfiguration);
    }

    @Override
    protected RedisCache createRedisCache(String name, RedisCacheConfiguration cacheConfiguration) {
        final int lastIndex = name.lastIndexOf("#");
        if (lastIndex > -1) {
            final String ttl = name.substring(lastIndex + 1);
            try {
                final Duration ttlDuration = Duration.ofSeconds(Long.parseLong(ttl));
                RedisCacheConfiguration newConfig = cacheConfiguration.entryTtl(ttlDuration)
                        .computePrefixWith(new CacheKeyPrefix() {
                            @Override
                            public String compute(String cacheName) {
                                return Constant.Redis.Prefix + cacheName + ":";
                            }
                        });
                final String cacheName = StringUtils.substring(name, 0, lastIndex);
                return super.createRedisCache(cacheName, newConfig);
            }catch (Exception e) {
            }
        }
        RedisCacheConfiguration newConfig = cacheConfiguration.computePrefixWith(new CacheKeyPrefix() {
            @Override
            public String compute(String cacheName) {
                return Constant.Redis.Prefix + cacheName + ":";
            }
        });
        return super.createRedisCache(name, newConfig);
    }
}
