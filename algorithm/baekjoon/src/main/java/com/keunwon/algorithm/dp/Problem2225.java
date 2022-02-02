package com.keunwon.algorithm.dp;

import java.util.Scanner;

public class Problem2225 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int divide = 1_000_000_000;

        final int n = sc.nextInt();
        final int k = sc.nextInt();
        final int[][] dp = new int[n + 1][k + 1];

        for (int i = 1; i <= n; i++) {
            dp[i][1] = 1;
        }

        for (int i = 1; i <= k; i++) {
            dp[1][i] = i;
        }

        for (int i = 2; i <= n; i++) {
            for (int j =2; j <= k; j++) {
                dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % divide;
            }
        }

        System.out.println(dp[n][k]);
    }
}
