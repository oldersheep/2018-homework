package com.xxx.mybatis.pattern.decorator;

public class Ice extends Condiment{

    private Drink drink;

    public Ice(Drink drink) {
        this.drink = drink;
    }

    @Override
    public String getDesc() {
        return drink.getDesc() + "+冰";
    }

    @Override
    public double cost() {
        return 0.5 + drink.cost();
    }

}