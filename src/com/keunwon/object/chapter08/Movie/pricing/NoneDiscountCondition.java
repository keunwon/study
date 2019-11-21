package com.keunwon.object.chapter08.Movie.pricing;

import com.keunwon.object.chapter08.Money.Money;
import com.keunwon.object.chapter08.Movie.DiscountPolicy;
import com.keunwon.object.chapter08.Movie.Screening;

public class NoneDiscountCondition extends DiscountPolicy  {

    @Override
    protected Money getDiscountAmount(Screening screening) {
        return Money.ZERO;
    }
}
