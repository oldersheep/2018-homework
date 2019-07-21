package com.xxx.jvm;

/**
 * @Description 返回值变化
 * @Date 2019/7/3 20:59
 * @Version 1.0
 */
public class TryFinally {

    public static void main(String[] args) {
        System.out.println(returnString());
        System.out.println(returnInner());
    }

    public static String returnString() {
        String str = "hello";
        try {
            return str;
        } finally {
            str = "finally";
        }
    }

    public static Inner returnInner() {
        Inner inner = new Inner();
        try {
            inner.setStr("hello");
            return inner;
        } finally {
            inner.setStr("finally");
        }
    }

    static class Inner {
        private String str;

        public String getStr() {
            return str;
        }

        public void setStr(String str) {
            this.str = str;
        }

        @Override
        public String toString() {
            return "Inner{" +
                    "str='" + str + '\'' +
                    '}';
        }
    }
}
