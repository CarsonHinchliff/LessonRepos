package cn.citi.listener;

import cn.citi.bus.Event;
import cn.citi.bus.EventBusListener;
import cn.citi.model.StudentEvent;
import cn.citi.model.TeacherEvent;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Carson
 * @created 2025/3/21 星期五 上午 09:29
 */
@Slf4j
public class TeacherListener implements EventBusListener<Event<TeacherEvent>> {
    @Override
    public void onEvent(Event<Event<TeacherEvent>> event) {
        log.info(event.toString());
    }

    @Override
    public Class<?> eventType() {
        return TeacherEvent.class;
    }
}
