package com.keunwon.algorithm.greedy;

import java.util.Scanner;

public class Problem1789 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        long value = sc.nextLong();

        long sum = 0L;
        int count = 0;
        while (sum <= value) {
            count++;
            sum += count;
        }

        System.out.println(count - 1);
    }
}
