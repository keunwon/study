package com.keunwon.algorithm.binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Problem2110 {
    private static int N, C;
    private static int[] houses;

    public static void main(String[] args) throws IOException {
        input();

        var left = 0;
        var right = houses[N - 1] - houses[0];

        while (left <= right) {
            final var mid = (left + right) / 2;

            if (C <= countInstallWifi(mid)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        System.out.println(right);
    }

    private static int countInstallWifi(int distance) {
        var count = 1;
        var preHouse = houses[0];

        for (int i = 1; i < N; i++) {
            final var now = houses[i];

            if (distance <= now - preHouse) {
                count++;
                preHouse = now;
            }
        }
        return count;
    }

    private static void input() throws IOException {
        final var br = new BufferedReader(new InputStreamReader(System.in));

        try (br) {
            final var st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            houses = new int[N];

            for (int i = 0; i < N; i++) {
                houses[i] = Integer.parseInt(br.readLine());
            }
            Arrays.sort(houses);
        }
    }
}
