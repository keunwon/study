package com.keunwon.object.chapter13.step01.pricing;

import com.keunwon.object.chapter13.money.Money;
import com.keunwon.object.chapter13.step01.DiscountPolicy;
import com.keunwon.object.chapter13.step01.Screening;

public class NoneDiscountPolicy extends DiscountPolicy {
    @Override
    protected Money getDiscountAmount(Screening screening) {
        return Money.ZERO;
    }
}
