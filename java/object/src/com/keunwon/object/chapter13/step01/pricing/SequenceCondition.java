package com.keunwon.object.chapter13.step01.pricing;

import com.keunwon.object.chapter13.step01.DiscountCondition;
import com.keunwon.object.chapter13.step01.Screening;

public class SequenceCondition implements DiscountCondition {
    private int sequence;

    public SequenceCondition(int sequence) {
        this.sequence = sequence;
    }

    public boolean isSatisfiedBy(Screening screening) {
        return screening.isSequence(sequence);
    }
}
