package com.xxx.design.strategy;

import com.xxx.design.strategy.pay.Order;
import com.xxx.design.strategy.paytype.PayType;

public class PayTest {

    public static void main(String[] args) {
        Order order = new Order("201700011","20180416225233001",33330.00);

        // 这里怎么从前台获取到这个值？
        System.out.println(order.pay(PayType.ALI_PAY));
    }
}
