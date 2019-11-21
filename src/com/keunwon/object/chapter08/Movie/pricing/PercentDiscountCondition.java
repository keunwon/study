package com.keunwon.object.chapter08.Movie.pricing;

import com.keunwon.object.chapter08.Money.Money;
import com.keunwon.object.chapter08.Movie.DiscountCondition;
import com.keunwon.object.chapter08.Movie.DiscountPolicy;
import com.keunwon.object.chapter08.Movie.Screening;

public class PercentDiscountCondition extends DiscountPolicy {
    private double percent;

    public PercentDiscountCondition(double percent, DiscountCondition ... conditions) {
        super(conditions);
        this.percent = percent;
    }

    @Override
    protected Money getDiscountAmount(Screening screening) {
        return screening.getMovieFee().times(percent);
    }
}
