package com.xxx.mybatis.pattern.decorator;

public class LemonTea extends Drink{
    public LemonTea() {
        desc = "柠檬茶";
    }

    public double cost() {
        return 10;
    }
}
