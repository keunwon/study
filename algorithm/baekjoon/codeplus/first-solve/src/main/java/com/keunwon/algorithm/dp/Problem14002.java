package com.keunwon.algorithm.dp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Problem14002 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final int N = Integer.parseInt(br.readLine());
        final int[] arr = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        final int[] dp = new int[N + 1];
        int index = 1;

        for (int i = 1; i <= N; i++) {
            dp[i] = 1;

            for (int j = 1; j <= i; j++) {
                if (arr[j] < arr[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    index = Math.max(index, dp[i]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(index).append(System.lineSeparator());

        final int[] result = new int[index];
        for (int i = N; i > 0; i--) {
            if (dp[i] == index) {
                result[index - 1] = arr[i];
                index--;
            }
        }

        for (int i : result) {
            sb.append(i).append(" ");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
    }
}
