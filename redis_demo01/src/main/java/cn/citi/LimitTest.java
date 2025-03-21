package cn.citi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

import java.util.Arrays;

/**
 * @author Carson
 * @created 2025/3/11 星期二 下午 10:53
 */
@SpringBootApplication
public class LimitTest {
    public static void main(String[] args) {
        ApplicationContext context =  SpringApplication.run(LimitTest.class, args);
        StringRedisTemplate stringRedisTemplate = context.getBean(StringRedisTemplate.class);
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        redisScript.setResultType(Long.class);
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("limit.lua")));

        for (int i = 0; i < 112; i++) {
            String key = "limit:" + System.currentTimeMillis() / 1000; // limit:1705664721
            Long result = stringRedisTemplate.execute(redisScript, Arrays.asList(key), String.valueOf(100));
            if (result == 0) {
                //需要限流
                System.out.println("限流了......");
            }
        }
    }
}
