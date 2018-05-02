package com.xxx.design.singleton.hungry;

/**
 * 单例三步走：
 * 1、提供私有实例对象
 * 2、构造方法隐藏，不允许外部调用
 * 3、对外提供一个得到实例对象的方法
 *
 * 饿汉模式：由名知意，饿了需要吃，一加载就生成实例对象，这种方式保证了线程安全，但是太占内存。
 */
public class Hungry {

    private static Hungry instance = new Hungry();

    private Hungry() {}

    public static Hungry getInstance() {
        return instance;
    }

}
