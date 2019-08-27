package com.keunwon.java8.chap03;

public class TriFunctionMain {

    public static void main(String ... args) {
        TriFunction<Integer, Integer, Integer, Color> c = Color::new;
        Color color = c.apply(1,2, 3);
        System.out.println(color.toString());
    }
}
