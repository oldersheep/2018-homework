package com.xxx.factory.abstr;


import com.xxx.factory.Car;

public class AbstractFactoryTest {

    public static void main(String[] args) {
        CarFactory factory = new CarFactory();
        Car car = factory.getChevrolet();
        System.out.print("车子品牌是：");
        car.getBrand();
        System.out.print("颜色为:");
        car.getColor();
    }
}
