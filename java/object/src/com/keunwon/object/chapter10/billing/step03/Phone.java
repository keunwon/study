package com.keunwon.object.chapter10.billing.step03;

import com.keunwon.object.chapter10.Money.Money;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Phone {
    private static final int LATE_NIGHT_HOUR = 22;
    enum PhoneType { REGULAR, NIGHTLY }

    private PhoneType phoneType;

    private Money amount;
    private Money regularAmount;
    private Money nightlyAmount;
    private Duration seconds;
    private List<Call> calls = new ArrayList<>();

    public Phone(Money amount, Duration seconds) {
        this(PhoneType.REGULAR, amount, Money.ZERO, Money.ZERO, seconds);
    }

    public Phone(Money nightlyAmount, Money regularAmount, Duration seconds) {
        this(PhoneType.NIGHTLY, Money.ZERO, nightlyAmount, regularAmount, seconds);
    }

    public Phone(PhoneType phoneType, Money amount, Money regularAmount, Money nightlyAmount, Duration seconds) {
        this.phoneType = phoneType;
        this.amount = amount;
        this.regularAmount = regularAmount;
        this.nightlyAmount = nightlyAmount;
        this.seconds = seconds;
    }
}
