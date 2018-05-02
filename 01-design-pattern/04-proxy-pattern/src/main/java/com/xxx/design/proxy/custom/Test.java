package com.xxx.design.proxy.custom;

public class Test {

    public static void main(String[] args) {
        Dao dao = (Dao) new Service().getInstance(new DaoImpl());

        dao.modify();

        dao.query();
    }
}
