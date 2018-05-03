package com.xxx.design.delegate.leader;

/**
 * boss比较像客户，客户有需求交给Leader，Leader根据需求派发给相应的员工进行干活
 */
public class Boss {

    public static void main(String[] args) {

        new Leader().doing("加密");
        new Leader().doing("登录");
    }
}
