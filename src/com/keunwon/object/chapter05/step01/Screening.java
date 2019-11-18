package com.keunwon.object.chapter05.step01;

import com.keunwon.object.chapter05.money.Money;

import java.time.LocalDateTime;

public class Screening {
    private Movie movie;
    private int sequencce;
    private LocalDateTime whenScreened;

    public Reservation reserve(Customer customer, int audienceCount) {
        return new Reservation(customer, this, calcaulateFee(audienceCount), audienceCount);
    }

    private Money calcaulateFee(int audienceCount) {
        return movie.calculateMovieFee(this).times(audienceCount);
    }

    public LocalDateTime getWhenScreened() {
        return whenScreened;
    }

    public int getSequence() {
        return sequencce;
    }
}
