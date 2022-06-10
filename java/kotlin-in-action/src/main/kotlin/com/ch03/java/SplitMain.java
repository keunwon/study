package com.ch03.java;

public class SplitMain {

    public static void main(String[] args) {
        var text = "12.345-6.A";
        var arr = text.split(".");
        for (String s : arr) {
            System.out.println(s);
        }
    }
}
