package com.keunwon.object.chapter09.step02.factory;

import com.keunwon.object.chapter09.money.Money;
import com.keunwon.object.chapter09.step02.Movie;

public class Client {
    private Factory factory;

    public Client(Factory factory) {
        this.factory = factory;
    }

    public Money getAvartarFee() {
        Movie avartar = factory.createAvartarMovie();
        return avartar.getFee();
    }
}
