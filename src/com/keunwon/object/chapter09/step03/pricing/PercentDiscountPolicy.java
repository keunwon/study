package com.keunwon.object.chapter09.step03.pricing;

import com.keunwon.object.chapter09.money.Money;
import com.keunwon.object.chapter09.step03.DiscountCondition;
import com.keunwon.object.chapter09.step03.DiscountPolicy;
import com.keunwon.object.chapter09.step03.Screening;

public class PercentDiscountPolicy extends DiscountPolicy {
    private double percent;

    public PercentDiscountPolicy(double percent, DiscountCondition... conditions) {
        super(conditions);
        this.percent = percent;
    }

    @Override
    protected Money getDiscountAmount(Screening screening) {
        return screening.getMovieFee().times(percent);
    }
}
