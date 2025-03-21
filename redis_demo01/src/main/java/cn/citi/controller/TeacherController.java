package cn.citi.controller;

import cn.citi.Constant;
import cn.citi.bus.Event;
import cn.citi.bus.EventBus;
import cn.citi.model.StudentEvent;
import cn.citi.model.TeacherEvent;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Carson
 * @created 2025/3/21 星期五 上午 09:08
 */
@RestController
@RequestMapping("/teacher")
@AllArgsConstructor
public class TeacherController {
    private final EventBus eventBus;

    @GetMapping("/get")
    public String get(){
        return "Hello";
    }

    @GetMapping("/publish")
    public String publish(String name){
        var teacherEvent = new TeacherEvent(name);
        eventBus.publish(Constant.Channels.TEACHER_CHANNEL, new Event<TeacherEvent>(teacherEvent));
        return teacherEvent.getName();
    }
}
