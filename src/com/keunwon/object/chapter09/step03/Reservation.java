package com.keunwon.object.chapter09.step03;


import com.keunwon.object.chapter09.money.Money;

public class Reservation {
    private Customer customer;
    private com.keunwon.object.chapter09.step03.Screening Screening;
    private Money fee;
    private int audienceCount;

    public Reservation(Customer customer, com.keunwon.object.chapter09.step03.Screening Screening, Money fee, int audienceCount) {
        this.customer = customer;
        this.Screening = Screening;
        this.fee = fee;
        this.audienceCount = audienceCount;
    }
}
