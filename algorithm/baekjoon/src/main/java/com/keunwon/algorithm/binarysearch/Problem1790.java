package com.keunwon.algorithm.binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem1790 {
    private static int N, K;

    public static void main(String[] args) throws IOException {
        input();

        var result = 0L;
        var temp = (long) K;
        var count = 9L;
        var length = 1L;

        while (count * length < temp) {
            temp -= (count * length);
            result += count;

            count *= 10;
            length++;
        }
        result = (result + 1) + (temp - 1) / length;

        if (N < result) {
            System.out.println(-1);
            return;
        }

        final var n = String.valueOf(result).charAt((int) ((temp - 1) % length));
        System.out.println(n);
    }

    private static void input() throws IOException {
        final var br = new BufferedReader(new InputStreamReader(System.in));

        try (br) {
            final var st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
        }
    }
}
