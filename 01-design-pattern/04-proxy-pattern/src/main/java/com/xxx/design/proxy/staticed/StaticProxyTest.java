package com.xxx.design.proxy.staticed;

public class StaticProxyTest {

    public static void main(String[] args) {
        Dao dao = new Dao();
        Service service = new Service(dao);

        service.modify();
        service.query();
    }
}
