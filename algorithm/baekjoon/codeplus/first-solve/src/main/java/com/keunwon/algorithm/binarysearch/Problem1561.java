package com.keunwon.algorithm.binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Problem1561 {
    private static long N;
    private static int M;
    private static int[] drivingTimes;

    public static void main(String[] args) throws IOException {
        input();

        if (M < N) {
            solution();
            return;
        }
        System.out.println(N);
    }

    private static void solution() {
        final var minute = binarySearch();
        var count = getCount(minute - 1);

        for (int i = 0; i < M; i++) {
            if (minute % drivingTimes[i] == 0) {
                count++;
            }

            if (count == N) {
                System.out.println(i + 1);
                break;
            }
        }
    }

    private static long binarySearch() {
        var left = 0L;
        var right = (N / M) * 30L;

        while (left <= right) {
            final var mid = (left + right) / 2;

            if (getCount(mid) < N) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return right + 1;
    }

    private static long getCount(long time) {
        var count = (long) M;

        for (int i = 0; i < M; i++) {
            count += time / drivingTimes[i];
        }
        return count;
    }

    private static void input() throws IOException {
        final var br = new BufferedReader(new InputStreamReader(System.in));

        try (br) {
            var st = new StringTokenizer(br.readLine());
            N = Long.parseLong(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            drivingTimes = new int[M];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                drivingTimes[i] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
