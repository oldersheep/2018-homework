package com.xxx.dagon.api.router;


import com.xxx.dagon.api.spi.router.ClientClassifierFactory;

public interface ClientClassifier {
    ClientClassifier I = ClientClassifierFactory.create();

    int getClientType(String osName);
}

