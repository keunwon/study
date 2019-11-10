package com.keunwon.object.chapter02.movie.step02;

import com.keunwon.object.chapter02.money.Money;

public class Reservation {
    private Customer customer;
    private com.keunwon.object.chapter02.movie.step02.Screening Screening;
    private Money fee;
    private int audienceCount;

    public Reservation(Customer customer, Screening screening, Money fee, int audienceCount) {
        this.customer = customer;
        this.Screening = Screening;
        this.fee = fee;
        this.audienceCount = audienceCount;
    }
}
