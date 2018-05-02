package com.xxx.design.factory.method;

import com.xxx.design.factory.Car;
import com.xxx.design.factory.Chevrolet;

public class ChevroletFactory implements CarFactory {

    @Override
    public Car getCar() {
        return new Chevrolet();
    }
}
