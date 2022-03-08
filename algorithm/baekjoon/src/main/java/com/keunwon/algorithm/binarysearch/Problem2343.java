package com.keunwon.algorithm.binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem2343 {
    private static int N, M;
    private static int[] times;

    private static int left, right;

    public static void main(String[] args) throws IOException {
        input();
        binarySearch();
    }

    private static void binarySearch() {
        while (left <= right) {
            final var mid = (left + right) / 2;

            if (check(mid)) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        System.out.println(left);
    }

    private static boolean check(int size) {
        var count = 0;
        var sum = 0;

        for (int i = 0; i < N; i++) {
            final var num = times[i];

            if (sum + num <= size) {
                sum += num;
                continue;
            }

            count++;
            sum = num;
        }
        return M <= count;
    }

    private static void input() throws IOException {
        final var br = new BufferedReader(new InputStreamReader(System.in));

        try (br) {
            var st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            times = new int[N];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                final var num = Integer.parseInt(st.nextToken());
                times[i] = num;

                left = Math.max(left, num);
                right += num;
            }
        }
    }
}
