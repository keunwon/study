package com.keunwon.object.chapter08.Movie.pricing;

import com.keunwon.object.chapter08.Money.Money;
import com.keunwon.object.chapter08.Movie.DiscountPolicy;
import com.keunwon.object.chapter08.Movie.Screening;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OverlappedDiscountPolicy extends DiscountPolicy {
    private List<DiscountPolicy> discountPolicies = new ArrayList<>();

    public OverlappedDiscountPolicy(DiscountPolicy ... discountPolicies) {
        this.discountPolicies = Arrays.asList(discountPolicies);
    }


    @Override
    protected Money getDiscountAmount(Screening screening) {
        return null;
    }
}
