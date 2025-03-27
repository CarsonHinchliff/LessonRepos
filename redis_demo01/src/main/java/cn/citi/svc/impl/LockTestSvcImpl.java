package cn.citi.svc.impl;

import cn.citi.lock.DistributedLock;
import cn.citi.svc.LockTestSvc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author Carson
 * @created 2025/3/27 星期四 上午 09:44
 */
@Service
@Slf4j
public class LockTestSvcImpl implements LockTestSvc {
    @Override
    @DistributedLock(key = "lock:test-lock01")
    public String test(String name) {
        try {
            Thread.sleep(30 * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error(e.getMessage(), e);
        }
        return "test method called in " + this.getClass().getSimpleName() + " with param: " + name;
    }
}
