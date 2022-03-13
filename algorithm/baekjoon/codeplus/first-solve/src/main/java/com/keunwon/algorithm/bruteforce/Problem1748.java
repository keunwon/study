package com.keunwon.algorithm.bruteforce;

import java.util.Scanner;

public class Problem1748 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int n = sc.nextInt();

        int result = 0;
        int size = 10;
        int count = 1;

        for (int i = 1; i <= n; i++) {
            if (i % size == 0) {
                count++;
                size *= 10;
            }
            result += count;
        }

        System.out.println(result);
    }
}
