package com.keunwon.algorithm.bluteforce;

import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Problem2309 {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        final int[] arr = new int[9];
        int sum = 0;

        for (int i = 0; i < 9; i++) {
            arr[i] = sc.nextInt();
            sum += arr[i];
        }
        Arrays.sort(arr);

        final int[] other = new int[2];
        for (int i = 0; i < 8; i++) {
            for (int j = i + 1; j < 9; j++) {
                if (sum - arr[i] - arr[j] == 100) {
                    other[0] = i;
                    other[1] = j;
                }
            }
        }

        for (int i = 0; i < 9; i++) {
            if (i == other[0] || i == other[1]) {
                continue;
            }
            System.out.println(arr[i]);
        }
    }
}
