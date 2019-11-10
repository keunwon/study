package com.keunwon.object.chapter02.movie.step02.pricing;

import com.keunwon.object.chapter02.money.Money;
import com.keunwon.object.chapter02.movie.step02.DiscountCondition;
import com.keunwon.object.chapter02.movie.step02.DiscountPolicy;
import com.keunwon.object.chapter02.movie.step02.Screening;

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
