package com.xxx.singleton.lazy;

// 懒汉式，针对上一个做出优化，保证了线程安全，但是加锁会造成效率低
public class LazyTwo {

    private static LazyTwo INSTANCE = null;

    private LazyTwo(){}

    public static synchronized LazyTwo getInstance(){
        if (INSTANCE == null) {
            INSTANCE = new LazyTwo();
        }
        return INSTANCE;
    }
}
