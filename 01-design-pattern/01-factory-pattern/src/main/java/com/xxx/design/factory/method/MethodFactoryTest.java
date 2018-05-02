package com.xxx.design.factory.method;

import com.xxx.design.factory.Car;

public class MethodFactoryTest {

    public static void main(String[] args) {
        CarFactory factory = new ChevroletFactory();
        Car car = factory.getCar();
        car.getBrand();
        car.getColor();

        factory = new FerrariFactory();
        car = factory.getCar();
        car.getBrand();
        car.getColor();

        factory = new MaybachFactory();
        car = factory.getCar();
        car.getBrand();
        car.getColor();
    }
}
