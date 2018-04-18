package com.xxx.strategy.paytype;

import com.xxx.strategy.pay.PayState;

public class AliPay implements Payment {

    @Override
    public PayState pay(String uid, Double amount) {

        System.out.println("支付宝方式支付。。。。");
        System.out.println("此时余额为：" + amount);
        return new PayState(200,"交易成功！",amount);
    }
}
