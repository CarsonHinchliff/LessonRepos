package cn.citi.queue;

import java.util.function.Consumer;

/**
 * @author Carson
 * @created 2025/3/21 星期五 下午 11:43
 */
public interface DelayQueue {
    void push(String message, long delay);
    void pop(Consumer<String> consumer);
}
