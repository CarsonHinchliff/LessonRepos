package cn.citi.bus;

/**
 * @author Carson
 * @created 2025/3/21 星期五 上午 09:18
 */
public interface EventBusListener <T>{
    void onEvent(Event<T> event);
    Class<?> eventType();
}
