package com.xxx.factory;

public class Maserati implements Car {
    @Override
    public void getBrand() {
        System.out.println("玛莎拉蒂大叉子！");
    }

    @Override
    public void getColor() {
        System.out.println("五颜六色...");
    }
}
