package com.xxx.design.factory.abstr;

import com.xxx.design.factory.Car;

public class AbstrFactoryTest {

    public static void main(String[] args) {
        CarFactory factory = new CarFactory();
        Car car = factory.getChevrolet();
        car.getBrand();
        car.getColor();

        car = factory.getFerrari();
        car.getBrand();
        car.getColor();

        car = factory.getMaybach();
        car.getBrand();
        car.getColor();
    }
}
