package com.xxx.strategy.paytype;

import com.xxx.strategy.pay.PayState;

public interface Payment {

    PayState pay(String uid, Double amount);
}
