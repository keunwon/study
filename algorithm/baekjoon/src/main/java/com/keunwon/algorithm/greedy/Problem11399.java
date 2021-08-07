package com.keunwon.algorithm.greedy;

import java.util.Arrays;
import java.util.Scanner;

public class Problem11399 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[] numbers = new int[n];

        for (int i = 0; i < n; i++) {
            numbers[i] = sc.nextInt();
        }

        Arrays.sort(numbers);

        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j >= 0 ; j--) {
                ans += numbers[j];
            }
        }

        System.out.println(ans);
    }
}
