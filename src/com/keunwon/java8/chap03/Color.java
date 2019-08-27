package com.keunwon.java8.chap03;

public class Color {
    private Integer a;
    private Integer b;
    private Integer c;

    public Color(Integer a, Integer b, Integer c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    @Override
    public String toString() {
        return "Color{" +
                "a=" + a +
                ", b=" + b +
                ", c=" + c +
                '}';
    }
}
