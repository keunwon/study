package com.keunwon.algorithm.binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Problem1654 {
    private static int K, N;
    private static long[] lengths;

    public static void main(String[] args) throws IOException {
        input();

        var left = 1L;
        var right = lengths[K - 1];

        while (left <= right) {
            final var mid = (left + right) / 2;

            var count = 0L;
            for (long length : lengths) {
                count += length / mid;
            }

            if (N <= count) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(right);
    }

    private static void input() throws IOException {
        final var br = new BufferedReader(new InputStreamReader(System.in));

        try (br) {
            final var st = new StringTokenizer(br.readLine());

            K = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            lengths = new long[K];

            for (int i = 0; i < K; i++) {
                lengths[i] = Long.parseLong(br.readLine());
            }
            Arrays.sort(lengths);
        }
    }
}
