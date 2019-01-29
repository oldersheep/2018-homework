package com.xxx.dagon.api.spi.core;

import com.xxx.dagon.api.spi.Factory;
import com.xxx.dagon.api.spi.SpiLoader;

import javax.crypto.Cipher;

public interface RsaCipherFactory extends Factory<Cipher> {


    static Cipher create() {
        return SpiLoader.load(RsaCipherFactory.class).get();
    }

}
