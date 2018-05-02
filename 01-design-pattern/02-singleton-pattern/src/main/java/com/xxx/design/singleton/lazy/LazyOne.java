package com.xxx.design.singleton.lazy;

/**
 * 懒汉模式：在使用时再创建，这种方式会有线程安全的隐患。
 */
public class LazyOne {

    private static LazyOne instance;

    private LazyOne() {}

    public LazyOne getInstance() {
        if (instance == null) {
            instance = new LazyOne();
        }
        return instance;
    }
}
