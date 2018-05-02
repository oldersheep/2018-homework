package com.xxx.design.factory;

public class Ferrari implements Car {
    @Override
    public void getBrand() {
        System.out.println("法拉利！");
    }

    @Override
    public void getColor() {
        System.out.println("中国红！");
    }
}
