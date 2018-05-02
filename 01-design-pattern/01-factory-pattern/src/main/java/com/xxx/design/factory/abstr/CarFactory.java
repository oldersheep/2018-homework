package com.xxx.design.factory.abstr;

import com.xxx.design.factory.Chevrolet;
import com.xxx.design.factory.Ferrari;
import com.xxx.design.factory.Maybach;

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
    Maybach getMaybach() {
        return new Maybach();
    }
}
