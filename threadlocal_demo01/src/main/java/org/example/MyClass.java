package org.example;

/**
 * @author Carson
 * @created 2025/3/11 星期二 上午 12:12
 */
public class MyClass {
    private int id;
    private byte[] bytes = new byte[1024 * 1024]; //1mb

    public MyClass() {
    }

    public MyClass(int id) {
        this.id = id;
    }
}
