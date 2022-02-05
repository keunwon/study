package com.keunwon.algorithm.dp;

import java.util.Scanner;

public class Problem2133 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        final int n = sc.nextInt();
        final int[] dp = new int[31];

        dp[0] = 1;
        dp[2] = 3;

        for (int i = 4; i <= n; i += 2) {
            dp[i] = dp[i - 2] * dp[2];

            for (int j = i - 4; j >= 0; j -= 2) {
                dp[i] = dp[i] + (dp[j] * 2);
            }
        }

        System.out.println(dp[n]);
    }
}
