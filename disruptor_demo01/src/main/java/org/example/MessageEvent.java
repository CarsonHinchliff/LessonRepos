package org.example;

import lombok.Data;

/**
 * @author Carson
 * @created 2025/3/12 星期三 下午 03:06
 */
@Data
public class MessageEvent {
    private String message;

    @Override
    public String toString() {
        return "MessageEvent{" +
                "message='" + message + '\'' +
                '}';
    }
}
