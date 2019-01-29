package com.xxx.dagon.api.spi.router;

import com.xxx.dagon.api.router.ClientClassifier;
import com.xxx.dagon.api.spi.Factory;
import com.xxx.dagon.api.spi.SpiLoader;

public interface ClientClassifierFactory extends Factory<ClientClassifier> {

    static ClientClassifier create() {
        return SpiLoader.load(ClientClassifierFactory.class).get();
    }
}

