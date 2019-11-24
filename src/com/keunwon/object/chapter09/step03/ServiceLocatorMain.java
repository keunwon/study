package com.keunwon.object.chapter09.step03;

import com.keunwon.object.chapter09.money.Money;
import com.keunwon.object.chapter09.step03.locator.ServiceLocator;
import com.keunwon.object.chapter09.step03.pricing.AmountDiscountPolicy;
import com.keunwon.object.chapter09.step03.pricing.SequenceCondition;

import java.time.Duration;

public class ServiceLocatorMain {

    public static void main(String ... args) {
        ServiceLocator.provide(new AmountDiscountPolicy(Money.wons(800), new SequenceCondition(1)));
        Movie avartar = new Movie("아바타", Duration.ofMinutes(120), Money.wons(10000));
    }
}
