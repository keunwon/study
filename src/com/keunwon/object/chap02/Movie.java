package com.keunwon.object.chap02;

public class Movie {
    private Money money;

    public Movie(Money money) {
        this.money = money;
    }


    public Money getFee() {
        return money;
    }
}
