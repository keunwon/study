package com.keunwon.object.chapter13.step01.pricing;

import com.keunwon.object.chapter13.money.Money;
import com.keunwon.object.chapter13.step01.DiscountCondition;
import com.keunwon.object.chapter13.step01.DiscountPolicy;
import com.keunwon.object.chapter13.step01.Screening;

public class AmountDiscountPolicy extends DiscountPolicy {
    private Money discountAmount;

    public AmountDiscountPolicy(Money discountAmount, DiscountCondition... conditions) {
        super(conditions);
        this.discountAmount = discountAmount;
    }

    @Override
    protected Money getDiscountAmount(Screening screening) {
        return discountAmount;
    }
}
