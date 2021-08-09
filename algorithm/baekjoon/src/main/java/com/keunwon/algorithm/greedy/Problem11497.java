package com.keunwon.algorithm.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Problem11497 {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            int[] numbers = input();

            Arrays.sort(numbers);
            printMaxNum(sort(numbers));
        }
    }

    private static int[] sort(int[] arr) {
        final int[] tempArr = new int[arr.length];
        final int size = arr.length;

        for (int i = 0; i < size; i++) {
            int idx = i / 2;

            if (i % 2 == 0) {
                tempArr[idx] = arr[i];
            } else {
                tempArr[size - 1 - idx] = arr[i];
            }
        }
        return tempArr;
    }

    private static void printMaxNum(int[] arr) {
        int maxNum = Integer.MIN_VALUE;
        for (int j = 0; j < arr.length - 1; j++) {
            int temp = Math.abs(arr[j + 1] - arr[j]);
            maxNum = Math.max(maxNum, temp);
        }

        System.out.println(maxNum);
    }

    private static int[] input() throws IOException {
        int r = Integer.parseInt(br.readLine());
        int[] result = new int[r];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < r; i++) {
            result[i] = Integer.parseInt(st.nextToken());
        }
        return result;
    }
}
