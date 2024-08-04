package com.keunwon.algorithm.programmers;

import java.util.Arrays;

public class Lesson12982 {
    public int solution(int[] d, int budget) {
        Arrays.sort(d);

        var sum = 0;
        for (var i = 0; i < d.length; i++) {
            sum += d[i];
            if (sum > budget) return i;
        }
        return d.length;
    }
}
