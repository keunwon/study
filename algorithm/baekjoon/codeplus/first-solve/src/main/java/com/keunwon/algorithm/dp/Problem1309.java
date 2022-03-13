package com.keunwon.algorithm.dp;

import java.io.IOException;
import java.util.Scanner;

public class Problem1309 {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        final int n = sc.nextInt();

        final int divide = 9901;
        final int[][] dp = new int[n + 1][3];

        dp[1][0] = dp[1][1] = dp[1][2] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i][0] = (dp[i - 1][0] + dp[i - 1][1] + dp[i - 1][2]) % divide;
            dp[i][1] = (dp[i - 1][0] + dp[i - 1][2]) % divide;
            dp[i][2] = (dp[i - 1][0] + dp[i - 1][1]) % divide;
        }

        System.out.println((dp[n][0] + dp[n][1] + dp[n][2]) % divide);
    }
}
