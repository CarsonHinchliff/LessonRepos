package cn.citi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * @author Carson
 * @created 2025/3/27 星期四 上午 09:49
 */
@Configuration
public class SchedulerConfig {
    @Bean
    public ScheduledExecutorService scheduledExecutorService(){
        return Executors.newScheduledThreadPool(20);
    }
}
