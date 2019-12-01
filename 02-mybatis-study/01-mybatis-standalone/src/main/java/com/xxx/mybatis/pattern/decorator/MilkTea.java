package com.xxx.mybatis.pattern.decorator;

public class MilkTea extends Drink {
    public MilkTea() {
        desc = "奶茶";
    }

    @Override
    public double cost() {
        return 22;
    }
}
