package com.keunwon.object.chapter09.step03.pricing;

import com.keunwon.object.chapter09.money.Money;
import com.keunwon.object.chapter09.step03.DiscountPolicy;
import com.keunwon.object.chapter09.step03.Screening;

public class NoneDiscountPolicy extends DiscountPolicy {

    @Override
    protected Money getDiscountAmount(Screening screening) {
        return Money.ZERO;
    }
}
