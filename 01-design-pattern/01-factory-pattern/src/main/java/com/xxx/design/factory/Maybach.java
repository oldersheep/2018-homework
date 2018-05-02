package com.xxx.design.factory;

public class Maybach implements Car {
    @Override
    public void getBrand() {
        System.out.println("迈巴赫---");
    }

    @Override
    public void getColor() {
        System.out.println("稳健黑---");
    }
}
