package com.xxx.strategy.pay;

import com.xxx.strategy.paytype.PayType;
import com.xxx.strategy.paytype.Payment;

public class Order {

    private String orderId;
    private String uid;
    private Double amount;

    public Order() {
    }

    public Order(String orderId, String uid, Double amount) {
        this.orderId = orderId;
        this.uid = uid;
        this.amount = amount;
    }

    public PayState pay(PayType payType) {
        return payType.getPayment().pay(this.uid, this.amount);
    }
}
