package cn.citi.listener;

import cn.citi.bus.EventBus;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author Carson
 * @created 2025/3/21 星期五 上午 10:01
 */
@Component
@AllArgsConstructor
public class EventBusCommandListener implements CommandLineRunner {
    private final EventBus eventBus;
    @Override
    public void run(String... args) throws Exception {
        eventBus.subscribe("StudentChannel", new StudentListener());
        eventBus.subscribe("TeacherChannel", new TeacherListener());
    }
}
