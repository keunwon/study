package com.keunwon.codingtest.chapter2;

public class Dynamic {

    public int dynamicFunc(int data) {
        int[] cache = new int[data + 1];
        cache[0] = 1;
        cache[1] = 2;

        for (int i = 2; i < data + 1; i++) {
            cache[i] = cache[i - 2] + cache[i - 1];
        }
        return cache[data];
    }
}
