package com.xxx.factory.method;

import com.xxx.factory.Car;

public class MethodFactoryTest {

    public static void main(String[] args) {
        CarFactory factory = new ChevroletFactory();
        Car car = factory.getCar();
        System.out.print("车子品牌是：");
        car.getBrand();
        System.out.print("颜色为:");
        car.getColor();

        factory = new FerrariFactory();
        car = factory.getCar();
        System.out.print("车子品牌是：");
        car.getBrand();
        System.out.print("颜色为:");
        car.getColor();
    }

}
