package com.keunwon.object.chapter14.step02;

import com.keunwon.object.chapter14.money.Money;

public class FeeRule {
    private FeeCondition feeCondition;
    private FeePerDuration feePerDuration;

    public FeeRule(FeeCondition feeCondition, FeePerDuration feePerDuration) {
        this.feeCondition = feeCondition;
        this.feePerDuration = feePerDuration;
    }

    public Money calculateFee(Call call) {
        return feeCondition.findTimeIntervals(call)
                            .stream()
                            .map(each -> feePerDuration.calculate(each))
                            .reduce(Money.ZERO, Money::plus);
    }
}
