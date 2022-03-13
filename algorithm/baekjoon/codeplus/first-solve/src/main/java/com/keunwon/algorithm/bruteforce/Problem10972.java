package com.keunwon.algorithm.bruteforce;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

public class Problem10972 {
    public static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        final int N = sc.nextInt();
        final int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }

        if (nextSequence(arr)) {
            for (int i : arr) {
                bw.write(i + " ");
            }
        } else {
            bw.write("-1");
        }

        bw.flush();
        bw.close();
    }

    public static boolean nextSequence(int[] arr) {
        int i = arr.length - 1;
        while (0 < i && arr[i] <= arr[i - 1]) {
            i--;
        }
        if (i == 0) { return false; }

        int j = arr.length - 1;
        while (arr[j] <= arr[i - 1]) {
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
