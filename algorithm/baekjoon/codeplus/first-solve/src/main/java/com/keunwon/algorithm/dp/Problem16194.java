package com.keunwon.algorithm.dp;

import java.util.Scanner;

public class Problem16194 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int[] amounts = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            amounts[i] = sc.nextInt();
        }

        int[] dp = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            dp[i] = amounts[i];

            for (int j = 1; j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j] + amounts[j]);
            }
        }

        System.out.println(dp[N]);
    }
}
