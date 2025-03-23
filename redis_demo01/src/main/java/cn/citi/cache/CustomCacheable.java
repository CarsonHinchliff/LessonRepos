package cn.citi.cache;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Carson
 * @created 2025/3/23 星期日 上午 11:53
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomCacheable {
    String cacheName();
    String key();
    long expire(); // 过期时间，单位为秒
}
