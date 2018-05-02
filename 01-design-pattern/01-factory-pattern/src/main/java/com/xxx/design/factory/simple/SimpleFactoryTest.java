package com.xxx.design.factory.simple;

import com.xxx.design.factory.Car;

public class SimpleFactoryTest {

    public static void main(String[] args) {
        CarFactory factory = new CarFactory();
        Car car = factory.getCar("Chevrolet");
        car.getBrand();
        car.getColor();

        car = factory.getCar("Ferrari");
        car.getBrand();
        car.getColor();

        car = factory.getCar("Maybach");
        car.getBrand();
        car.getColor();
    }
}
