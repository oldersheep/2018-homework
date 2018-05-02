package com.xxx.design.proxy.jdk;

/**
 * 原理：
 * 1、拿到被代理类的引用，并获取到他所有的接口，反射获取
 * 2、JDK Proxy类重新生成一个类，同时这个类要实现被代理类的所有接口
 * 3、动态生成Java代码，把新加的业务逻辑方法由一定的逻辑代码去调用
 * 4、编译生成新的class文件
 * 5、再重新加载到JVM中运行
 */
public class JdkProxyTest {

    public static void main(String[] args) {
        Dao dao = (Dao) new Service().getInstance(new DaoImpl());

        dao.modify();

        dao.query();
    }
}
