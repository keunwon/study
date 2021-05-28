package com.keunwon.chapter08;

public class ContributionStatic {
    private static int amount = 0;

    public synchronized static void donate() {
        amount++;
    }

    public int getTotal() {
        return amount;
    }
}
