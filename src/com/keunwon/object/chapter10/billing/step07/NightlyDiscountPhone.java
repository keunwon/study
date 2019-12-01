package com.keunwon.object.chapter10.billing.step07;

import com.keunwon.object.chapter10.Money.Money;

import java.time.Duration;

public class NightlyDiscountPhone extends Phone {
    private static final int LATE_NIGHT_HOUR = 22;

    private Money nightlyAmount;
    private Money regularAmount;
    private Duration secons;

    public NightlyDiscountPhone(Money nightlyAmount, Money regularAmount, Duration secons) {
        this.nightlyAmount = nightlyAmount;
        this.regularAmount = regularAmount;
        this.secons = secons;
    }

    @Override
    protected Money calculateCallFee(Call call) {
        if (call.getFrom().getHour() >= LATE_NIGHT_HOUR) {
            return nightlyAmount.times(call.getDuration().getSeconds() / secons.getSeconds());
        } else {
            return regularAmount.times(call.getDuration().getSeconds() / secons.getSeconds());
        }
    }
}
