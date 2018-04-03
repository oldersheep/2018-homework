package com.xxx.factory;

public class Ferrari implements Car {

    @Override
    public void getBrand() {
        System.out.println("法拉利430!");
    }

    @Override
    public void getColor() {
        System.out.println("红黄相间..");
    }
}
