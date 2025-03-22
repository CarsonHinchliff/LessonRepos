package cn.citi.svc;

import cn.citi.queue.DelayQueue;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author Carson
 * @created 2025/3/21 星期五 下午 11:53
 */
@Service
@Slf4j
public class StudentQueueConsumer {
    @Qualifier("studentQueue")
    @Autowired
    private DelayQueue delayQueue;
    @Qualifier("studentQueue1")
    @Autowired
    private DelayQueue delayQueue1;

    @PostConstruct
    public void start() {
        delayQueue.pop(msg -> {
            log.info("redis delay student queue message: {}", msg);
        });
        delayQueue1.pop(msg -> {
            log.info("redis delay student queue1 message: {}", msg);
        });
    }
}
