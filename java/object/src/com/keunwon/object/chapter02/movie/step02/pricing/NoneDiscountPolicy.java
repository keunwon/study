package com.keunwon.object.chapter02.movie.step02.pricing;

import com.keunwon.object.chapter02.money.Money;
import com.keunwon.object.chapter02.movie.step02.DiscountPolicy;
import com.keunwon.object.chapter02.movie.step02.Screening;

public class NoneDiscountPolicy implements DiscountPolicy {

    @Override
    public Money calculateDiscountAmount(Screening screening) {
        return Money.ZERO;
    }
}
