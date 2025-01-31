package com.keunwon.object.chapter09.step01.pricing;

import com.keunwon.object.chapter09.money.Money;
import com.keunwon.object.chapter09.step01.DiscountCondition;
import com.keunwon.object.chapter09.step01.DiscountPolicy;
import com.keunwon.object.chapter09.step01.Screening;

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
