package cn.citi;

/**
 * @author Carson
 * @created 2025/3/21 星期五 下午 05:07
 */
public interface Constant {
    interface Channels{
       String STUDENT_CHANNEL = "StudentChannel";
       String TEACHER_CHANNEL = "TeacherChannel";
       String WEBSOCKET_CHANNEL = "WebSocketChannel";
    }

    interface Redis{
        String Prefix = "carson-redis-demo:";
    }
}
