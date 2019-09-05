package com.keunwon.java8.chap11;

import java.util.Random;

public class Shop {

    private String name;
    private Random random;

    public Shop(String name, Random random) {
        this.name = name;
        this.random = random;
    }

    public double getPrice(String product) {
        return calculatePrice(product);
    }

    private double calculatePrice(String product) {
        delay();
        return random.nextDouble() + product.charAt(0) + product.charAt(1);
    }

    public static void delay() {
        try {
            Thread.sleep(1000L);
        } catch(InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
