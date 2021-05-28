package com.keunwon.chapter08;

public class Contribution {

    private int amount = 0;

    public synchronized void donoate() {
        amount++;
    }

    public int getTotal() {
        return amount;
    }
}
