package com.keunwon.codingtest.chapter3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Problem2470 {
    static int N;
    static int[] numbers;

    public static void main(String[] args) throws IOException {
        intput();
        pro();
    }

    static void pro() {
        Arrays.sort(numbers);

        int sum = Integer.MAX_VALUE;
        int v1 = 0, v2 = 0;

        for (int i = 0; i < N - 1; i++) {
            int candi = findTargetIndex(numbers, -numbers[i], i + 1, N - 1);

            int leftValue = Math.abs(numbers[i] + numbers[candi - 1]);
            if (i < candi - 1 && leftValue < sum) {
                sum = leftValue;
                v1 = numbers[i];
                v2 = numbers[candi - 1];
            }

            int rightValue = Math.abs(numbers[i] + numbers[candi]);
            if (candi <= N && rightValue < sum) {
                sum = rightValue;
                v1 = numbers[i];
                v2 = numbers[candi];
            }
        }

        System.out.println(v1 + " " + v2);
    }

    static int findTargetIndex(int[] arr, int target, int low, int high) {
        int res = high;

        while (low <= high) {
            int m = (low + high) / 2;

            if (arr[m] < target) {
                low = m + 1;
            } else {
                res = m;
                high = m - 1;
            }
        }
        return res;
    }

    static void intput() throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            N = Integer.parseInt(br.readLine());

            numbers = new int[N];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                numbers[i] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
