package com.xxx.factory.simple;

import com.xxx.factory.Car;

public class SimpleFactoryTest {

    public static void main(String[] args) {
        CarFactory factory = new CarFactory();
        Car car = factory.getCar("Ferrari");
        printCar(car);
        car = factory.getCar("Maserati");
        printCar(car);
        car = factory.getCar("Chevrolet");
        printCar(car);
        car = factory.getCar("Benz");
        printCar(car);
    }

    public static void printCar(Car car) {
        if (car != null) {
            System.out.print("车子品牌是：");
            car.getBrand();
            System.out.print("颜色为:");
            car.getColor();
        }
    }
}
