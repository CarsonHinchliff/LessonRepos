package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author Carson
 * @created 2025/3/11 星期二 上午 12:12
 */
public class CatThreadLocal003 {
    public static void main(String[] args) {//-Xms25m -Xmx25m
        ExecutorService executorService = Executors.newFixedThreadPool(30);
        IntStream.range(0, 30).forEach(i -> {
            executorService.execute(() -> {
                doSomething();
            });
            try {
                TimeUnit.MICROSECONDS.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public static void doSomething() {
        ThreadLocal<MyClass> threadLocal = new ThreadLocal<>();
        try{
            threadLocal.set(new MyClass());
            System.out.println(Thread.currentThread().getName());
        }finally {
            threadLocal.remove();
        }
    }
}
