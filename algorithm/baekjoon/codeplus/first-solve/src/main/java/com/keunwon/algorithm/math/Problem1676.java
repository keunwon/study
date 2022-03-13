package com.keunwon.algorithm.math;

import java.util.Scanner;

public class Problem1676 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();

        int value1 = 0;
        int value2 = 0;
        for (int i = 1; i <= num; i++) {
            int n = i;

            while (n % 2 == 0) {
                value1++;
                n = n / 2;
            }

            while (n % 5 == 0) {
                value2++;
                n = n / 5;
            }
        }

        System.out.println(Math.min(value1, value2));
    }
}
