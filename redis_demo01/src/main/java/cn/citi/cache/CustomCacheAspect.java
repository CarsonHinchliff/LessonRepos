package cn.citi.cache;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author Carson
 * @created 2025/3/23 星期日 上午 11:50
 */
@Aspect
@Component
public class CustomCacheAspect {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Around("@annotation(customCacheable)")
    public Object around(ProceedingJoinPoint joinPoint, CustomCacheable customCacheable) throws Throwable {
        String cacheName = customCacheable.cacheName();
        //MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        var keyExpression = customCacheable.key();
        String key = generateCacheKey(keyExpression, joinPoint.getArgs());
        long expire = customCacheable.expire();

        Object value = redisTemplate.opsForValue().get(cacheName + ":" + key);
        if (value != null) {
            return value;
        }

        Object result = joinPoint.proceed();
        redisTemplate.opsForValue().set(cacheName + ":" + key, result, expire, TimeUnit.SECONDS);
        return result;
    }

    private String generateCacheKey(String keyExpression, Object[] args) {
        SpelExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context = new StandardEvaluationContext();
        for (int i = 0; i < args.length; i++) {
            context.setVariable("p" + i, args[i]);
        }
        Expression expression = parser.parseExpression(keyExpression);
        return expression.getValue(context, String.class);
    }
}
