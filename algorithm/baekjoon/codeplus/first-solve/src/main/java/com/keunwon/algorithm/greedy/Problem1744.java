package com.keunwon.algorithm.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Problem1744 {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N;
    private static int[] arr;

    public static void main(String[] args) throws IOException {
        input();
        Arrays.sort(arr);

        var left = 0;
        var right = N - 1;
        var result = 0;

        for (; left < right; left += 2) {
            if (arr[left] < 1 && arr[left + 1] < 1) {
                result += arr[left] * arr[left + 1];
                continue;
            }
            break;
        }

        for (; right > 0; right -= 2) {
            if (1 < arr[right] && 1 < arr[right - 1]) {
                result += arr[right] * arr[right - 1];
                continue;
            }
            break;
        }

        for (; right >= left; right--) {
            result += arr[right];
        }

        System.out.println(result);
    }

    private static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
    }
}
