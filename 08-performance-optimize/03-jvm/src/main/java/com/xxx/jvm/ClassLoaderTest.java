package com.xxx.jvm;

/**
 * @Description
 * @Date 2019/6/26 20:58
 * @Version 1.0
 */
public class ClassLoaderTest {

    public static void test01() {}
    public static void test02() {}
    public static void test03() {}
    public static void test04() {}

    public static void main(String[] args) {
        System.out.println(new ClassLoaderTest().getClass().getClassLoader().getParent().getParent());
        System.out.println(new ClassLoaderTest().getClass().getClassLoader().getParent());
        System.out.println(new ClassLoaderTest().getClass().getClassLoader());
        System.out.println("==============");
        System.out.println(new Object().getClass().getClassLoader());
    }
}
