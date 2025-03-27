package cn.citi.lock;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.ScheduledFuture;

/**
 * @author Carson
 * @created 2025/3/27 星期四 上午 09:35
 */
@Slf4j
@Aspect
@Component
public class DistributedLockAspect {
    private final RedisDistributedLock redisDistributedLock;

    public DistributedLockAspect(RedisDistributedLock redisDistributedLock) {
        this.redisDistributedLock = redisDistributedLock;
    }

    @Around("@annotation(lock)")
    public Object around(ProceedingJoinPoint joinPoint, DistributedLock lock) throws Throwable {
        String lockKey = lock.key();
        int expireTime = lock.expireTime();
        String requestId = UUID.randomUUID().toString();
        if (!this.redisDistributedLock.tryLock(lockKey, requestId, expireTime)){
            throw new RuntimeException("Failed to acquire lock :" + lockKey);
        }
        ScheduledFuture<?> renewTask = null;
        try{
            renewTask = redisDistributedLock.scheduleRenewLockTask(lockKey, requestId, expireTime);
            return joinPoint.proceed();
        }finally {
            if (renewTask != null){
                renewTask.cancel(true);
            }
            redisDistributedLock.releaseLock(lockKey, requestId);
        }
    }
}
