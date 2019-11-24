package com.keunwon.object.chapter09.step03.pricing;

import com.keunwon.object.chapter09.money.Money;
import com.keunwon.object.chapter09.step03.DiscountCondition;
import com.keunwon.object.chapter09.step03.DiscountPolicy;
import com.keunwon.object.chapter09.step03.Screening;

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
