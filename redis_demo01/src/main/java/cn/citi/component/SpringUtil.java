package cn.citi.component;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author Carson
 * @created 2025/3/21 星期五 下午 05:12
 */
@Component
public class SpringUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringUtil.applicationContext = applicationContext;
    }

    /**
     * 根据类型获取 Bean
     *
     * @param clazz Bean 的类型
     * @param <T>   泛型类型
     * @return 返回指定类型的 Bean
     */
    public static <T> T getBean(Class<T> clazz) {
        if (applicationContext == null) {
            throw new IllegalStateException("SpringUtil.applicationContext is null");
        }
        return applicationContext.getBean(clazz);
    }

    /**
     * 根据名称和类型获取 Bean
     *
     * @param name  Bean 的名称
     * @param clazz Bean 的类型
     * @param <T>   泛型类型
     * @return 返回指定名称和类型的 Bean
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        if (applicationContext == null) {
            throw new IllegalStateException("SpringUtil.applicationContext is null");
        }
        return applicationContext.getBean(name, clazz);
    }
}