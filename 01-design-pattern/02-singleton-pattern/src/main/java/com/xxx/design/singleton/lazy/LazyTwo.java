package com.xxx.design.singleton.lazy;

/**
 * 懒汉模式：通过加锁的方式保证线程安全，但是效率底下，在多线程的环境中，得到实例对象需要阻塞，虽然保证了线程安全，但是牺牲了效率
 */
public class LazyTwo {

    private static LazyTwo instance = null;

    private LazyTwo() {}

    public static synchronized LazyTwo getInstance() {
        if (instance == null) {
            instance = new LazyTwo();
        }
        return instance;
    }
}
