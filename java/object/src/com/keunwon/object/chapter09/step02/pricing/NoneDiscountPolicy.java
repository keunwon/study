package com.keunwon.object.chapter09.step02.pricing;

import com.keunwon.object.chapter09.money.Money;
import com.keunwon.object.chapter09.step02.DiscountPolicy;
import com.keunwon.object.chapter09.step02.Screening;

public class NoneDiscountPolicy extends DiscountPolicy {

    @Override
    protected Money getDiscountAmount(Screening screening) {
        return Money.ZERO;
    }
}
