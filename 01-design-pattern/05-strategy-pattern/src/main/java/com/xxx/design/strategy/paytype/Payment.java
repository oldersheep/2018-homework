package com.xxx.design.strategy.paytype;

import com.xxx.design.strategy.pay.PayState;

public interface Payment {

    PayState pay(String uid, Double amount);
}
