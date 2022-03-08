package com.keunwon.algorithm.binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem13397 {
    private static final int MAX = 10_000;

    private static int N, M;
    private static int[] numbers;

    public static void main(String[] args) throws IOException {
        input();
        binarySearch();
    }

    private static void binarySearch() {
        var left = 0;
        var right = MAX;

        while (left <= right) {
            final var mid = (left + right) / 2;

            if (check(mid)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(right + 1);
    }

    private static  boolean check(int size) {
        var count = 1;
        var min = MAX + 1;
        var max = 0;

        for (int i = 0; i < N; i++) {
            min = Math.min(min, numbers[i]);
            max = Math.max(max, numbers[i]);

            if (max - min > size) {
                count++;
                min = max = numbers[i];
            }
        }
        return count <= M;
    }

    private static void input() throws IOException {
        final var br = new BufferedReader(new InputStreamReader(System.in));

        try (br) {
            var st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            numbers = new int[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                numbers[i] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
