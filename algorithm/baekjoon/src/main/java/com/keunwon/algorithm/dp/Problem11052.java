package com.keunwon.algorithm.dp;

import java.util.Scanner;

public class Problem11052 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int[] amounts = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            amounts[i] = sc.nextInt();
        }

        int[] dp = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.printf("V1: %d, V2: %d, Amount: %d \n", dp[i], dp[i - j] + amounts[j], amounts[j]);
                dp[i] = Math.max(dp[i], dp[i - j] + amounts[j]);
            }
        }

        System.out.println(dp[N]);
    }
}
