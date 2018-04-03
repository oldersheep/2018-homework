package com.xxx.factory.simple;

import com.xxx.factory.Car;
import com.xxx.factory.Chevrolet;
import com.xxx.factory.Ferrari;
import com.xxx.factory.Maserati;

public class CarFactory {

    public Car getCar(String name) {

        if ("Chevrolet".equals(name)){
            return new Chevrolet();
        } else if ("Ferrari".equals(name)) {
            return new Ferrari();
        } else if ("Maserati".equals(name)) {
            return new Maserati();
        } else {
            System.out.println("暂不生产此类车，请等我们牛逼了再说-_-");
            return null;
        }
    }

}
