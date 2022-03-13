package com.keunwon.algorithm.greedy;

import java.util.Scanner;

public class Problem2847 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] numbers = new int[n];

        for (int i = 0; i < n; i++) {
            numbers[i] = sc.nextInt();
        }

        int count = 0;
        for (int i = n - 1; i > 0 ; i--) {
            if (numbers[i] > numbers[i - 1]) {
                continue;
            }

            int a = numbers[i - 1] - numbers[i] + 1;
            count += a;
            numbers[i - 1] -= a;
        }

        System.out.println(count);
    }
}
