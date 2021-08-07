package com.keunwon.algorithm.greedy;

import java.util.Arrays;
import java.util.Scanner;

public class Problem2217 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] numbers = new int[n];

        for (int i = 0; i < n; i++) {
            numbers[i] = sc.nextInt();
        }

        Arrays.sort(numbers);

        int max = numbers[n - 1];
        for (int i = 0; i < n; i++) {
            int s = numbers[i] * (n - i);
            max = Math.max(max, s);
        }

        System.out.println(max);
    }
}
