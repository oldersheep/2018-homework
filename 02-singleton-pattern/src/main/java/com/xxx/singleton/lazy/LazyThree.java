package com.xxx.singleton.lazy;

// 懒汉式，静态内部类的方式
// 既可以解决线程安全问题，又可以保证效率，单例模式的最佳方案
public class LazyThree {

    // 构造方法私有，保证外部不能随意创建
    private LazyThree(){}

    // 提供外部一个得到实例的接口
    // final保证此方法不能被重载或者重写
    public static final LazyThree getInstance(){
        return SingletonHolder.INSTANCE;
    }

    // 静态内部类，只有在使用时才会被装载，装载时会实例化静态变量
    public static class SingletonHolder {
        private static final LazyThree INSTANCE = new LazyThree();
    }
}
