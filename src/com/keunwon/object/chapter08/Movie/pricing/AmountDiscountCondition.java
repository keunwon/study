package com.keunwon.object.chapter08.Movie.pricing;

import com.keunwon.object.chapter08.Money.Money;
import com.keunwon.object.chapter08.Movie.DiscountCondition;
import com.keunwon.object.chapter08.Movie.DiscountPolicy;
import com.keunwon.object.chapter08.Movie.Screening;

public class AmountDiscountCondition extends DiscountPolicy {
    private Money discountMoney;

    public AmountDiscountCondition(Money discountMoney, DiscountCondition ... conditions) {
        super(conditions);
        this.discountMoney = discountMoney;
    }

    @Override
    protected Money getDiscountAmount(Screening screening) {
        return discountMoney;
    }
}
