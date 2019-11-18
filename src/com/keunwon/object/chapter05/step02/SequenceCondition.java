package com.keunwon.object.chapter05.step02;

public class SequenceCondition {
    private int sequence;

    public SequenceCondition(int sequence) {
        this.sequence = sequence;
    }

    public boolean isSatisfiedBySequence(Screening screening) {
        return sequence == screening.getSequence();
    }
}
