package com.keunwon.algorithm.sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Problem11652 {
    private static int N;
    private static long[] arr;

    public static void main(String[] args) throws IOException {
        input();

        Arrays.sort(arr);

        var minNum = arr[0];
        var nowCount = 1;
        var maxCount = 1;

        for (int i = 0; i < N - 1; i++) {
            if (arr[i] != arr[i + 1]) {
                nowCount = 1;
                continue;
            }

            nowCount++;
            if (maxCount < nowCount) {
                maxCount = nowCount;
                minNum = arr[i];
            }
        }

        System.out.println(minNum);
    }

    private static void input() throws IOException {
        final var br = new BufferedReader(new InputStreamReader(System.in));

        try (br) {
            N = Integer.parseInt(br.readLine());
            arr = new long[N];

            for (int i = 0; i < N; i++) {
                arr[i] = Long.parseLong(br.readLine());
            }
        }
    }
}
