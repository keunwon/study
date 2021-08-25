package com.keunwon.algorithm.sorting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Problem2473 {

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int N = Integer.parseInt(br.readLine());
        long[] arr = new long[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(arr);

        long max = Long.MAX_VALUE;
        long[] result = new long[3];

        for (int i = 0; i < N; i++) {
            int left = i + 1;
            int right = N - 1;

            while (left < right) {
                final long sum = arr[i] + arr[left] + arr[right];
                final long abaSum = Math.abs(sum);

                if (abaSum < max) {
                    max = abaSum;
                    result[0] = arr[i];
                    result[1] = arr[left];
                    result[2] = arr[right];
                }

                if (sum > 0) {
                    right--;
                } else {
                    left++;
                }
            }
        }

        for (long l : result) {
            System.out.print(l + " ");
        }
    }
}
