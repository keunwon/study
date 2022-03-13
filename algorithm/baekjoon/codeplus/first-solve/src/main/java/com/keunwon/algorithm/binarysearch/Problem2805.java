package com.keunwon.algorithm.binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Problem2805 {
    private static int N, M;
    private static int[] trees;

    public static void main(String[] args) throws IOException {
        input();

        var left = 0L;
        var right = (long) trees[N - 1];

        while (left <= right) {
            final var mid = (left + right) / 2;

            var count = 0L;
            for (int tree : trees) {
                count += tree > mid ? tree - mid : 0;
            }

            if (M <= count) {
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
            var st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            trees = new int[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                trees[i] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(trees);
        }
    }
}
