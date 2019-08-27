package com.keunwon.java8.chap03;

import java.util.function.Function;

public class FunctionTest {
    public static void main(String ... args) {
        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
        Function<Integer, Integer> h1 = f.andThen(g);
        Function<Integer, Integer> h2 = f.compose(g);

        System.out.println(h1.apply(1));
        System.out.println(h2.apply(1));
    }
}
