package cn.citi.lock;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author Carson
 * @created 2025/3/27 星期四 上午 09:28
 */
@Slf4j
@Component
public class RedisDistributedLock {
    private final RedisTemplate<String, Object> redisTemplate;
    private final ScheduledExecutorService scheduledExecutorService;

    public RedisDistributedLock(RedisTemplate<String, Object> redisTemplate, ScheduledExecutorService scheduledExecutorService) {
        this.redisTemplate = redisTemplate;
        this.scheduledExecutorService = scheduledExecutorService;
    }

    public boolean tryLock(String lockKey, String requestId, int expireTime) {
        String luaScript = "if redis.call('setnx', KEYS[1], ARGV[1]) == 1 then return redis.call('expire', KEYS[1], ARGV[2]) else return 0 end";
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptText(luaScript);
        redisScript.setResultType(Long.class);
        Long result = redisTemplate.execute(redisScript, Collections.singletonList(lockKey), requestId, expireTime);
        return result == 1L;
    }

    public boolean releaseLock(String lockKey, String requestId){
        String luaScript = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptText(luaScript);
        redisScript.setResultType(Long.class);
        Long result = redisTemplate.execute(redisScript, Collections.singletonList(lockKey), requestId);
        return 1L == result;
    }

    public boolean renewLock(String lockKey, String requestId, int expireTime){
        String luaScript = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('expire', KEYS[1], ARGV[2]) else return 0 end";
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptText(luaScript);
        redisScript.setResultType(Long.class);
        Long result = redisTemplate.execute(redisScript, Collections.singletonList(lockKey), requestId, expireTime);
        return result == 1L;
    }

    public ScheduledFuture<?> scheduleRenewLockTask(String lockKey, String requestId, int expireTime){
        return this.scheduledExecutorService.scheduleAtFixedRate(() -> {
            if (!this.renewLock(lockKey, requestId, expireTime)){
                log.warn("Failed to renew lock: {}", lockKey);
            }
        }, expireTime / 2, expireTime / 2, TimeUnit.SECONDS);
    }
}
