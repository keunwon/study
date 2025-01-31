package com.keunwon.object.chapter09.step02.pricing;

import com.keunwon.object.chapter09.money.Money;
import com.keunwon.object.chapter09.step02.DiscountPolicy;
import com.keunwon.object.chapter09.step02.Screening;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OverlappedDiscountPolicy extends DiscountPolicy {
    private List<DiscountPolicy> discountPolicies = new ArrayList<>();

    public OverlappedDiscountPolicy(DiscountPolicy... discountPolicies) {
        this.discountPolicies = Arrays.asList(discountPolicies);
    }


    @Override
    protected Money getDiscountAmount(Screening screening) {
        return null;
    }
}
