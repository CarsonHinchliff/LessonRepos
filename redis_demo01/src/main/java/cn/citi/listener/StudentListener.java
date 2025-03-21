package cn.citi.listener;

import cn.citi.bus.Event;
import cn.citi.bus.EventBusListener;
import cn.citi.model.StudentEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Carson
 * @created 2025/3/21 星期五 上午 09:29
 */
@Slf4j
public class StudentListener implements EventBusListener<Event<StudentEvent>> {
    @Override
    public void onEvent(Event<Event<StudentEvent>> event) {
        log.info(event.toString());
    }

    @Override
    public Class<?> eventType() {
        return StudentEvent.class;
    }
}
