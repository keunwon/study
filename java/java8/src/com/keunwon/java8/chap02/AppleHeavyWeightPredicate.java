package com.keunwon.java8.chap02;

import com.keunwon.java8.common.Apple;

public class AppleHeavyWeightPredicate implements ApplePredicate {

    @Override
    public boolean test(Apple apple) {
        return apple.getWeight() > 150;
    }
}
