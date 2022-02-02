package com.keunwon.algorithm.dp;

import java.io.*;

public class Problem15988 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final int divide = 1_000_000_009;
        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            final int num = Integer.parseInt(br.readLine());
            final long[] dp = new long[num + 4];

            dp[0] = 0;
            dp[1] = 1;
            dp[2] = 2;
            dp[3] = 4;

            for (int i = 4; i <= num; i++) {
                dp[i] = (dp[i - 3] + dp[i - 2] + dp[i - 1]) % divide;
            }

            sb.append(dp[num]).append(System.lineSeparator());
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
