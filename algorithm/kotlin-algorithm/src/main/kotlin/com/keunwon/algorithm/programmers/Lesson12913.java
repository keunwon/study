package com.keunwon.algorithm.programmers;

public class Lesson12913 {
    public int solution(int[][] land) {
        var dp = new int[land.length][land[0].length];

        for (int i = 0; i < land[0].length; i++) {
            dp[0][i] = land[0][i];
        }

        for (int i = 1; i < land.length; i++) {
            dp[i][0] = Math.max(Math.max(dp[i - 1][1], dp[i - 1][2]), dp[i - 1][3]) + land[i][0];
            dp[i][1] = Math.max(Math.max(dp[i - 1][0], dp[i - 1][2]), dp[i - 1][3]) + land[i][1];
            dp[i][2] = Math.max(Math.max(dp[i - 1][0], dp[i - 1][1]), dp[i - 1][3]) + land[i][2];
            dp[i][3] = Math.max(Math.max(dp[i - 1][0], dp[i - 1][1]), dp[i - 1][2]) + land[i][3];
        }

        var answer = dp[dp.length - 1][0];
        for (int i = 1; i < dp[0].length; i++) {
            answer = Math.max(answer, dp[dp.length - 1][i]);
        }
        return answer;
    }
}
