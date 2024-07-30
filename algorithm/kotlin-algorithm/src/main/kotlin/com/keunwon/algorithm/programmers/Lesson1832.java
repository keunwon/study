package com.keunwon.algorithm.programmers;

public class Lesson1832 {
    private final int MOD = 20_170_805;

    public int solution(int m, int n, int[][] cityMap) {
        var dp = new int[m + 1][n + 1][2];
        dp[0][0][0] = 1;

        for (var i = 0; i < m; i++) {
            for (var j = 0; j < n; j++) {
                if (cityMap[i][j] == 0) {
                    dp[i][j + 1][0] += (dp[i][j][0] + dp[i][j][1]) % MOD;
                    dp[i + 1][j][1] += (dp[i][j][0] + dp[i][j][1]) % MOD;
                } else if (cityMap[i][j] == 2) {
                    dp[i][j + 1][0] += dp[i][j][0];
                    dp[i + 1][j][1] += dp[i][j][1];
                }
            }
        }
        return (dp[m - 1][n - 1][0] + dp[m - 1][n - 1][1]) % MOD;
    }
}
