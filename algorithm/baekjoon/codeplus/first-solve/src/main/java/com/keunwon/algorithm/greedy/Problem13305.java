package com.keunwon.algorithm.greedy;

import java.util.Scanner;

public class Problem13305 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] distances = new int[n - 1];
        int[] fees = new int[n];

        for (int i = 0; i < n - 1; i++) {
            distances[i] = sc.nextInt();
        }

        for (int i = 0; i < n; i++) {
            fees[i] = sc.nextInt();
        }

        long amount = 0L;
        long min = Long.MAX_VALUE;
        for (int i = 0; i < n  -1; i++) {
            if (fees[i] < min) {
                min = fees[i];
            }
            amount += min * distances[i];
        }

        System.out.println(amount);
    }
}
