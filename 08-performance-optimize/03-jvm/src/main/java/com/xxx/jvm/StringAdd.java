package com.xxx.jvm;

/**
 * @Description
 * @Date 2019/7/3 21:10
 * @Version 1.0
 */
public class StringAdd {

    static void f1() {
        String str = "";
        str += "hello ";
        str += "world";
        str += " end";
        System.out.println(str);
    }

    static void f2() {
        StringBuilder str = new StringBuilder();
        str.append("hello ");
        str.append("world");
        str.append(" end");
        System.out.println(str);
    }
    static void f3() {
        String str = "" + "hello11 " + "world11 " + " end111";
        System.out.println(str);
    }
    public static void main(String[] args) {
        f1();
        f2();
        f3();

        String str1 = "hello";
        str1 += " world";

        String str2 = "hello world";

        String str3 = new String("hello");
        str3 += " world";
        System.out.println(str1 == str2);
        System.out.println(str1 == str3);
        System.out.println(str3 == str2);
    }
}
