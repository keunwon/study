package com.keunwon.algorithm.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Problem1149 {
    private static final int RED = 0;
    private static final int GREEN = 1;
    private static final int BLUE = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final int n  = Integer.parseInt(br.readLine());
        final int[][] dp = new int[n + 1][3];
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int red = Integer.parseInt(st.nextToken());
            int green = Integer.parseInt(st.nextToken());
            int blue = Integer.parseInt(st.nextToken());

            dp[i][RED] = Math.min(dp[i - 1][GREEN], dp[i - 1][BLUE]) + red;
            dp[i][GREEN] = Math.min(dp[i - 1][RED], dp[i - 1][BLUE]) + green;
            dp[i][BLUE] = Math.min(dp[i - 1][RED], dp[i - 1][GREEN]) + blue;
        }

        System.out.println(Math.min(dp[n][RED], Math.min(dp[n][GREEN], dp[n][BLUE])));
    }
}
