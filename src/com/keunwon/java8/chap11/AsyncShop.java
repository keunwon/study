package com.keunwon.java8.chap11;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import static com.keunwon.java8.chap11.Utils.delay;
import static com.keunwon.java8.chap11.Utils.format;

public class AsyncShop {

    private String name;
    private Random random;

    public AsyncShop(String name, Random random) {
        this.name = name;
        this.random = random;
    }

    public Future<Double> getPrice(String product) {
        CompletableFuture<Double> futurePrice = new CompletableFuture<>();
        new Thread(() -> {
            double price = calculatePrice(product);
            futurePrice.complete(price);
        }).start();
        return futurePrice;
    }

    private double calculatePrice(String product) {
        delay();
        if (true) throw new RuntimeException("product not available");
        return format(random.nextDouble() * product.charAt(0) + product.charAt(1));
    }

}
