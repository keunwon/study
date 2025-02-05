package com.keunwon.algorithm.programmers;

import java.util.Arrays;

public class Lesson12907 {
    public int solution(int n, int[] money) {
        var dp = new int[n + 1];

        Arrays.sort(money);

        for (var i = 0; i <= n; i++) {
            if (i % money[0] == 0) dp[i] = 1;
        }

        for (var i = 1; i < money.length; i++) {
            for (var j = money[i]; j <= n; j++) {
                dp[j] += (dp[j - money[i]]) % 1_000_000_007;
            }
        }
        return dp[n];
    }
}
