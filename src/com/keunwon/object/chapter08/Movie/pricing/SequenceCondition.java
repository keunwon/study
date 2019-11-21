package com.keunwon.object.chapter08.Movie.pricing;

import com.keunwon.object.chapter08.Movie.DiscountCondition;
import com.keunwon.object.chapter08.Movie.Screening;

public class SequenceCondition implements DiscountCondition {
    private int sequence;

    public SequenceCondition(int sequence) {
        this.sequence = sequence;
    }

    @Override
    public boolean isSatisfiedBy(Screening screening) {
        return screening.isSequence(sequence);
    }
}
