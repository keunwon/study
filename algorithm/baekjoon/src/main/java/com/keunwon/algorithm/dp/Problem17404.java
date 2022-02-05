package com.keunwon.algorithm.dp;

import java.io.*;
import java.util.StringTokenizer;

public class Problem17404 {

    private static final int MAX_NUM = 1000 * 1000 + 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final int n = Integer.parseInt(br.readLine());
        final int[][] arr = new int[n + 1][3];

        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            for (int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int min = Integer.MAX_VALUE;
        final int[][] dp = new int[n + 1][3];

        for (int i = 0; i < 3; i++) {
            dp[1] = new int[]{MAX_NUM, MAX_NUM, MAX_NUM};
            dp[1][i] = arr[1][i];

            for (int j = 2; j <= n; j++) {
                dp[j][0] = Math.min(dp[j - 1][1], dp[j - 1][2]) + arr[j][0];
                dp[j][1] = Math.min(dp[j - 1][0], dp[j - 1][2]) + arr[j][1];
                dp[j][2] = Math.min(dp[j - 1][0], dp[j - 1][1]) + arr[j][2];
            }

            for (int j = 0; j < 3; j++) {
                if (i != j) {
                    min = Math.min(min, dp[n][j]);
                }
            }
        }

        System.out.println(min);
    }
}
