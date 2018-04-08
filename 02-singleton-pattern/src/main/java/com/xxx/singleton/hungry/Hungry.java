package com.xxx.singleton.hungry;

// 饿汉模式，线程安全，会造成一直占用空间
public class Hungry {

    // 构造方法私有，禁止外部私自创建本例的对象
    private Hungry() {
    }

    // 创建一个内部全局私有静态实例，在类加载时就创建，这样可以保证线程安全
    // 只不过会占用JVM的存储空间
    private static final Hungry INSTANCE = new Hungry();

    // 提供外部一个获取该实例的接口
    public static Hungry getInstance() {
        return INSTANCE;
    }
}
