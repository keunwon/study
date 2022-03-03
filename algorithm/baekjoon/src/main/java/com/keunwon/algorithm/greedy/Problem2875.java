package com.keunwon.algorithm.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem2875 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N, M, K;

    public static void main(String[] args) throws IOException {
        input();

        var max = 0;
        for (int i = 0; i <= K; i++) {
            var temp = K - i;
            var left = N - temp;
            var right = M - i;

            if (left <= 0 || right <= 0) { continue; }

            max = Math.max(max, Math.min(left / 2, right));
        }

        System.out.println(max);
    }

    private static void input() throws IOException {
        var st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
    }
}
