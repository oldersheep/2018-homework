package com.xxx.design.factory.simple;

import com.xxx.design.factory.Car;
import com.xxx.design.factory.Chevrolet;
import com.xxx.design.factory.Ferrari;
import com.xxx.design.factory.Maybach;

/**
 * 小作坊时代，根据传进来的名字判断造什么车，总而言之，就是你需要什么，我这个工厂就给你造什么。
 */
public class CarFactory {

    public Car getCar(String name) {
        if("Chevrolet".equals(name)) {
            return new Chevrolet();
        } else if ("Ferrari".equals(name)) {
            return new Ferrari();
        } else if ("Maybach".equals(name)) {
            return new Maybach();
        } else {
            System.out.println("这种品牌的车不造！");
            return null;
        }
    }

}
