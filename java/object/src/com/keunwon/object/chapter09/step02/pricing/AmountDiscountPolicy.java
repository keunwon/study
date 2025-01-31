package com.keunwon.object.chapter09.step02.pricing;

import com.keunwon.object.chapter09.money.Money;
import com.keunwon.object.chapter09.step02.DiscountCondition;
import com.keunwon.object.chapter09.step02.DiscountPolicy;
import com.keunwon.object.chapter09.step02.Screening;

public class AmountDiscountPolicy extends DiscountPolicy {
    private Money discountMoney;

    public AmountDiscountPolicy(Money discountMoney, DiscountCondition... conditions) {
        super(conditions);
        this.discountMoney = discountMoney;
    }

    @Override
    protected Money getDiscountAmount(Screening screening) {
        return discountMoney;
    }
}
