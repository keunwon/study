package com.keunwon.object.chapter09.step01.client;

import com.keunwon.object.chapter09.money.Money;
import com.keunwon.object.chapter09.step01.Movie;
import com.keunwon.object.chapter09.step01.pricing.AmountDiscountPolicy;
import com.keunwon.object.chapter09.step01.pricing.SequenceCondition;

import java.time.Duration;

public class Client {
    public Money getAvartarFee() {
        Movie avartar = new Movie("아바타",
                                    Duration.ofMinutes(120),
                                    Money.wons(10000),
                                    new AmountDiscountPolicy(Money.wons(800),
                                            new SequenceCondition(1),
                                            new SequenceCondition(10)));
        return avartar.getFee();
    }
}
