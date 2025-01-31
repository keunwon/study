package com.keunwon.object.chapter14.step02;

import com.keunwon.object.chapter14.DateTimeInterval;
import com.keunwon.object.chapter14.money.Money;

import java.time.Duration;

public class FeePerDuration {
    private Money fee;
    private Duration duration;

    public FeePerDuration(Money fee, Duration duration) {
        this.fee = fee;
        this.duration = duration;
    }

    public Money calculate(DateTimeInterval interval) {
        return fee.times(interval.duration().getSeconds() / duration.getSeconds());
    }
}
