package cn.citi.controller;

import cn.citi.Constant;
import cn.citi.bus.Event;
import cn.citi.bus.EventBus;
import cn.citi.model.StudentEvent;
import cn.citi.pubsub.RedisMessagePublisher;
import cn.citi.queue.DelayQueue;
import cn.citi.svc.LockTestSvc;
import cn.citi.svc.StudentCacheSvc;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Carson
 * @created 2025/3/21 星期五 上午 09:08
 */
@AllArgsConstructor
@RestController
@RequestMapping("/lock")
public class LockController {
    private final LockTestSvc lockTestSvc;

    @GetMapping("/get")
    public String get(){
        return "Hello";
    }

    @GetMapping("/lock")
    public String lock(String name) {
        return this.lockTestSvc.test(name);
    }
}
