package com.keunwon.algorithm.programmers;

import java.util.Arrays;

public class Lesson340199 {
    public int solution(int[] wallet, int[] bill) {
        var result = 0;

        Arrays.sort(wallet);
        Arrays.sort(bill);

        while (wallet[0] < bill[0] || wallet[1] < bill[1]) {
            bill[1] /= 2;
            ++result;
            Arrays.sort(bill);
        }
        return result;
    }
}
