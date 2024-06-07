package com.keunwon.algorithm.programmers;

public class Lesson42898 {
    public int solution(int m, int n, int[][] puddles) {
        var dp = new int[n + 1][m + 1];
        var map = new int[n + 1][m + 1];

        for (var puddle : puddles) {
            var x = puddle[0];
            var y = puddle[1];
            map[y][x] = -1;
        }

        dp[1][1] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (map[i][j] == -1) continue;

                if (i > 1) {
                    dp[i][j] += dp[i - 1][j] % 1_000_000_007;
                }
                if (j > 1) {
                    dp[i][j] += dp[i][j - 1] % 1_000_000_007;
                }
            }
        }
        return dp[n][m] % 1_000_000_007;
    }
}
