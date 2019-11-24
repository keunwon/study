package com.keunwon.object.chapter09.step03.locator;

import com.keunwon.object.chapter09.step03.DiscountPolicy;

public class ServiceLocator {
    private static ServiceLocator soloinstance = new ServiceLocator();
    private DiscountPolicy discountPolicy;

    public static DiscountPolicy discountPolicy() {
        return soloinstance.discountPolicy;
    }

    public static void provide(DiscountPolicy discountPolicy) {
        soloinstance.discountPolicy = discountPolicy;
    }

    private ServiceLocator() {
    }
}
