package com.keunwon.object.chapter14.step01;

import com.keunwon.object.chapter14.money.Money;

public interface RatePolicy {
    Money calculateFee(Phone phone);
}
