package com.xxx.factory.abstr;

import com.xxx.factory.Chevrolet;
import com.xxx.factory.Ferrari;
import com.xxx.factory.Maserati;

public class CarFactory extends AbstactFactory {

    @Override
    Chevrolet getChevrolet() {
        return new Chevrolet();
    }

    @Override
    Ferrari getFerrari() {
        return new Ferrari();
    }

    @Override
    Maserati getMaserati() {
        return new Maserati();
    }
}
