package com.keunwon.algorithm.dp;

import java.io.*;
import java.util.StringTokenizer;

public class Problem11055 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final int n = Integer.parseInt(br.readLine());
        final int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        final int[] dp = new int[n];
        dp[0] = arr[0];
        int max = arr[0];

        for (int i = 1; i < n; i++) {
            dp[i] = arr[i];

            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + arr[i]);
                    max = Math.max(max, dp[i]);
                }
            }
        }

        System.out.println(max);
    }
}
