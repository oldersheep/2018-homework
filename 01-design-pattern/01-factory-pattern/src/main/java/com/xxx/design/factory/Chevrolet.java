package com.xxx.design.factory;

public class Chevrolet implements Car {
    @Override
    public void getBrand() {
        System.out.println("雪佛兰...");
    }

    @Override
    public void getColor() {
        System.out.println("大黄蜂...");
    }
}
