package com.xxx.design.singleton;

import com.xxx.design.singleton.hungry.Hungry;

public class SingletonTest {

    public static void main(String[] args) {
        Hungry hungry = Hungry.getInstance();
        System.out.println(hungry);

    }
}
