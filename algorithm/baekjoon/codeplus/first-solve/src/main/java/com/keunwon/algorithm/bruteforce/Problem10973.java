package com.keunwon.algorithm.bruteforce;

import java.util.Scanner;

public class Problem10973 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int N = sc.nextInt();
        final int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        if (preSequence(arr)) {
            for (int i : arr) {
                System.out.print(i + " ");
            }
        } else {
            System.out.println(-1);
        }
    }

    public static boolean preSequence(int[] arr) {
        int i = arr.length - 1;
        while (0 < i && arr[i - 1] <= arr[i]) {
            i--;
        }
        if (i == 0) { return false; }

        int j = arr.length - 1;
        while (arr[i - 1] <= arr[j]) {
            j--;
        }

        swap(arr, i - 1, j);

        int start = i;
        int end = arr.length - 1;
        while (start < end) {
            swap(arr, start, end);
            start++;
            end--;
        }

        return true;
    }

    public static void swap(int[] arr, int idx1, int idx2) {
        final int temp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = temp;
    }
}
