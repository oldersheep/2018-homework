package com.xxx.design.singleton.lazy;

/**
 * 懒汉模式双重加锁校验，只在第一次时会有阻塞，既可以保证线程安全，又保证了效率。这个已经接近完美。
 */
public class LazyThree {

    private static LazyThree instance = null;

    private LazyThree() {}

    public LazyThree getInstance() {

        if (instance == null) {
            synchronized (LazyThree.class) {
                if (instance == null) {
                    instance = new LazyThree();
                }
            }
        }
        return instance;
    }
}
