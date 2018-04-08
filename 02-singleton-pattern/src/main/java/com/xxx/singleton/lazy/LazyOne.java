package com.xxx.singleton.lazy;

// 懒汉式，线程不安全，但是节省空间
public class LazyOne {

    // 构造方法私有，禁止外部创建
    private LazyOne() {
    }

    // 提供一个静态实例，初始值为null
    private static LazyOne INSTANCE = null;

    // 提供外部一个可以得到实例的接口
    public static LazyOne getInstance() {
        // 如果该实例还未创建，则创建，这一步会有线程安全问题
        // 当两个个线程同时来到这里，均发现为空，然后同时创建，便会有两个不同的实例
        if (INSTANCE == null) {
            INSTANCE = new LazyOne();
        }
        return INSTANCE;
    }

}
