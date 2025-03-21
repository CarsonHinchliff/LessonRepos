package cn.citi.model;

import lombok.Data;

/**
 * @author Carson
 * @created 2025/3/21 星期五 上午 09:31
 */
@Data
public class TeacherEvent {
    public TeacherEvent(String name) {
        this.name = name;
    }

    private String name;
}
