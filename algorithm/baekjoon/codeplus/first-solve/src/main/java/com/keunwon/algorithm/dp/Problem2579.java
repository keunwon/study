package com.keunwon.algorithm.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Problem2579 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        final int n = Integer.parseInt(br.readLine());
        final int[][] dp = new int[n + 1][2];
        final int[] points = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            points[i] = Integer.parseInt(br.readLine());
        }

        if (n <= 1) {
            System.out.println(points[1]);
            return;
        }

        dp[1][0] = points[1];
        dp[2][0] = points[2];
        dp[2][1] = points[1] + points[2];

        for (int i = 3; i <= n; i++) {
            dp[i][0] = Math.max(dp[i - 2][0], dp[i - 2][1]) + points[i];
            dp[i][1] = dp[i - 1][0] + points[i];
        }

        System.out.println(Math.max(dp[n][0], dp[n][1]));
    }

    private static void other() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        final int n = Integer.parseInt(br.readLine());
        final int[] dp = new int[301];
        final int[] points = new int[301];

        for (int i = 1; i <= n; i++) {
            points[i] = Integer.parseInt(br.readLine());
        }

        dp[1] = points[1];
        dp[2] = points[1] + points[2];
        dp[3] = Math.max(points[1], points[2]) + points[3];

        for (int i = 4; i <= n; i++) {
            dp[i] = Math.max(dp[i - 3] + points[i - 1], dp[i - 2]) + points[i];
        }

        System.out.println(dp[n]);
    }
}
