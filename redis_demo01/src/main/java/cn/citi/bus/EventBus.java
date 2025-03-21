package cn.citi.bus;

/**
 * @author Carson
 * @created 2025/3/21 星期五 上午 09:17
 */
public interface EventBus<T> {
    void subscribe(String channel, EventBusListener<T> listener);
    void publish(String channel, Event<T> event);
}
