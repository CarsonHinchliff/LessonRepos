package cn.citi.bus;

import lombok.Data;

/**
 * @author Carson
 * @created 2025/3/21 星期五 上午 09:15
 */
@Data
public class Event <T>{
    private T payload;
    private final long timestamp;
    public Event(T payload) {
        this.payload = payload;
        this.timestamp = System.currentTimeMillis();
    }
}
