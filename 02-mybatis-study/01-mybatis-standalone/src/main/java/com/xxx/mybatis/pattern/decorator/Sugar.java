package com.xxx.mybatis.pattern.decorator;

public class Sugar extends Condiment {
    private Drink drink;

    public Sugar(Drink drink) {
        this.drink = drink;
    }

    public String getDesc() {
        return drink.getDesc() + "+糖";
    }

    public double cost() {
        return 1 + drink.cost();
    }
}
