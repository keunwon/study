package com.keunwon.object.chapter05.step04;

public class SequenceCondition implements DiscountCondition {
    private int sequence;

    public SequenceCondition(int sequence) {
        this.sequence = sequence;
    }

    @Override
    public boolean isStatisfieldBy(Screening screening) {
        return sequence == screening.getSequence();
    }
}
