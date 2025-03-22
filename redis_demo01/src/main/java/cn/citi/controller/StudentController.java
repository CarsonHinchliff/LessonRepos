package cn.citi.controller;

import cn.citi.Constant;
import cn.citi.bus.Event;
import cn.citi.bus.EventBus;
import cn.citi.model.StudentEvent;
import cn.citi.queue.DelayQueue;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Carson
 * @created 2025/3/21 星期五 上午 09:08
 */
@RestController
@RequestMapping("/student")
public class StudentController {
    private final EventBus eventBus;

    private final DelayQueue delayQueue;
    private final DelayQueue delayQueue1;

    public StudentController(EventBus eventBus,
                             @Qualifier("studentQueue")DelayQueue delayQueue,
                             @Qualifier("studentQueue1")DelayQueue delayQueue1) {
        this.eventBus = eventBus;
        this.delayQueue = delayQueue;
        this.delayQueue1 = delayQueue1;
    }

    @GetMapping("/get")
    public String get(){
        return "Hello";
    }

    @GetMapping("/publish")
    public String publish(String name){
        var studentEvent = new StudentEvent(name);
        eventBus.publish(Constant.Channels.STUDENT_CHANNEL, new Event<StudentEvent>(studentEvent));
        return studentEvent.getName();
    }

    @GetMapping("/delay-publish")
    public String delayPublish(String name){
        this.delayQueue.push(name, 10);
        this.delayQueue1.push(name, 10);
        return name;
    }
}
