package com.xxx.design.strategy.paytype;

import com.xxx.design.strategy.pay.PayState;

public class UnionPay implements Payment {

    @Override
    public PayState pay(String uid, Double amount) {
        System.out.println("银联支付方式支付。。。。");
        System.out.println("此时余额为：" + amount);
        return new PayState(200,"交易成功",amount);
    }
}
