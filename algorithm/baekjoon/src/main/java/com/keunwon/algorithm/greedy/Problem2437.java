package com.keunwon.algorithm.greedy;

import java.util.Arrays;
import java.util.Scanner;

public class Problem2437 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] numbers = new int[n];

        for (int i = 0; i < n; i++) {
            numbers[i] = sc.nextInt();
        }

        Arrays.sort(numbers);

        long sum = 0;
        for (int i = 0; i < n; i++) {
            if (sum + 1 < numbers[i]) {
                break;
            }
            sum += numbers[i];
        }

        System.out.println(++sum);
    }
}
