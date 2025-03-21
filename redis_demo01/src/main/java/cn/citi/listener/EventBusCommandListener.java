package cn.citi.listener;

import cn.citi.Constant;
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
        eventBus.subscribe(Constant.Channels.STUDENT_CHANNEL, new StudentListener());
        eventBus.subscribe(Constant.Channels.TEACHER_CHANNEL, new TeacherListener());
        eventBus.subscribe(Constant.Channels.WEBSOCKET_CHANNEL, new WebSocketListener());
    }
}
