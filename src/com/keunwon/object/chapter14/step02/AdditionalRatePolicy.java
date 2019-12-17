package com.keunwon.object.chapter14.step02;

import com.keunwon.object.chapter14.money.Money;

public abstract  class AdditionalRatePolicy implements RatePolicy {
    private RatePolicy next;

    public AdditionalRatePolicy(RatePolicy next) {
        this.next = next;
    }

    @Override
    public Money calculateFee(Phone phone) {
        Money fee = next.calculateFee(phone);
        return afterCalculated(fee) ;
    }

    protected abstract Money afterCalculated(Money fee);
}
