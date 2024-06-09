package com.keunwon.algorithm.programmers;

import java.util.Arrays;

public class Lesson12987 {
    public int solution(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);

        var bIndex = 0;
        var answer = 0;

        for (int n : A) {
            while (bIndex < B.length && n >= B[bIndex]) {
                ++bIndex;
            }

            if (bIndex >= B.length) break;

            ++answer;
            ++bIndex;
        }
        return answer;
    }
}
