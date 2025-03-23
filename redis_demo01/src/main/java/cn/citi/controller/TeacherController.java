package cn.citi.controller;

import cn.citi.Constant;
import cn.citi.bus.Event;
import cn.citi.bus.EventBus;
import cn.citi.model.StudentEvent;
import cn.citi.model.TeacherEvent;
import cn.citi.pubsub.RedisMessagePublisher;
import cn.citi.queue.DelayQueue;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Carson
 * @created 2025/3/21 星期五 上午 09:08
 */
@RestController
@RequestMapping("/teacher")
public class TeacherController {
    private final EventBus eventBus;
    private final RedisMessagePublisher redisMessagePublisher;

    private final DelayQueue delayQueue;
    private final DelayQueue delayQueue1;

    public TeacherController(EventBus eventBus,
                             RedisMessagePublisher redisMessagePublisher,
                             @Qualifier("teacherQueue")DelayQueue delayQueue,
                             @Qualifier("teacherQueue1")DelayQueue delayQueue1) {
        this.eventBus = eventBus;
        this.redisMessagePublisher = redisMessagePublisher;
        this.delayQueue = delayQueue;
        this.delayQueue1 = delayQueue1;
    }

    @GetMapping("/get")
    public String get(){
        return "Hello";
    }

    @GetMapping("/publish")
    public String publish(String name){
        var teacherEvent = new TeacherEvent(name);
        eventBus.publish(Constant.Channels.TEACHER_CHANNEL, new Event<TeacherEvent>(teacherEvent));
        redisMessagePublisher.publish(Constant.Channels.TEACHER_CHANNEL, "message for:" + name);
        return teacherEvent.getName();
    }

    @GetMapping("/delay-publish")
    public String delayPublish(String name){
        this.delayQueue.push(name, 10);
        this.delayQueue1.push(name, 10);
        return name;
    }
}
