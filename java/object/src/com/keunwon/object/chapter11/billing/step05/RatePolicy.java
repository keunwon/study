package com.keunwon.object.chapter11.billing.step05;

import com.keunwon.object.chapter11.money.Money;

public interface RatePolicy {
    Money calculateFee(Phone phone);
}
