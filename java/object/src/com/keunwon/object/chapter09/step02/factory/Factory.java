package com.keunwon.object.chapter09.step02.factory;

import com.keunwon.object.chapter09.money.Money;
import com.keunwon.object.chapter09.step02.Movie;
import com.keunwon.object.chapter09.step02.pricing.AmountDiscountPolicy;
import com.keunwon.object.chapter09.step02.pricing.SequenceCondition;

import java.time.Duration;

public class Factory {
    public Movie createAvartarMovie() {
        return new Movie("아바타",
                Duration.ofMinutes(120),
                Money.wons(10000),
                new AmountDiscountPolicy(Money.wons(800),
                    new SequenceCondition(1),
                    new SequenceCondition(10)));
    }
}
