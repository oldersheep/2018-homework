package com.xxx.mybatis.pattern.decorator;

public abstract class Drink {
    // 饮料名称
    protected String desc;

    public String getDesc() {
        return desc;
    }

    // 饮料价格
    public abstract double cost();
}
