package com.keunwon.algorithm.programmers;

public class Lesson12900 {
    public int solution(int n) {
        var dp = new int[n + 4];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;

        for (int i = 4; i <= n; i++) {
            dp[i] = (dp[i - 2] + dp[i - 1]) % 1_000_000_007;
        }
        return dp[n];
    }
}
