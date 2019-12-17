package com.keunwon.object.chapter14.step02;

import com.keunwon.object.chapter14.money.Money;

public class RateDiscountablePolicy extends AdditionalRatePolicy {
    private Money discountAmount;

    public RateDiscountablePolicy(Money discountAmount, RatePolicy next) {
        super(next);
        this.discountAmount = discountAmount;
    }
}
