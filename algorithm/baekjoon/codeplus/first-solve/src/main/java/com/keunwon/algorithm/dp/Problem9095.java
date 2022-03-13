package com.keunwon.algorithm.dp;

import java.util.Scanner;

public class Problem9095 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] dp = new int[12];

        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;

        for (int i = 4; i <= 11; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }

        StringBuilder sb = new StringBuilder();
        int T = sc.nextInt();
        while (T-- > 0) {
            int n = sc.nextInt();

            sb.append(dp[n]).append(System.lineSeparator());
        }

        System.out.print(sb.toString());
    }
}
