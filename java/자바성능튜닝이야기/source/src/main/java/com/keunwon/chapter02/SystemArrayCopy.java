package com.keunwon.chapter02;

public class SystemArrayCopy {

    public static void main(String[] args) {
        String[] arr = new String[] {"AAA", "BBB", "CCC", "DDD", "EEE"};
        String[] copiedArr = new String[3];

        System.arraycopy(arr, 2, copiedArr, 1, 2);

        for (String s : copiedArr) {
            System.out.println(s);
        }
    }
}
