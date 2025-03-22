package cn.citi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * @author Carson
 * @created 2025/3/22 星期六 上午 12:24
 */
@Configuration
public class ThreadPoolConfig {
    @Bean
    public ThreadPoolTaskExecutor threadPoolExecutor() {
        ThreadPoolTaskExecutor threadPoolExecutor = new ThreadPoolTaskExecutor();
        threadPoolExecutor.setCorePoolSize(5);
        threadPoolExecutor.setMaxPoolSize(8);
        return threadPoolExecutor;
    }

    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(5);
        return threadPoolTaskScheduler;
    }
}
