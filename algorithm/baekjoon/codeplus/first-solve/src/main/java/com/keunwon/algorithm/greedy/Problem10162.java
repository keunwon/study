package com.keunwon.algorithm.greedy;

import java.util.Scanner;

public class Problem10162 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int seconds = sc.nextInt();
        int[] numbers = { 300, 60, 10 };

        if (seconds % 10 != 0) {
            System.out.println(-1);
            return;
        }

        for (int number : numbers) {
            int a = seconds / number;
            seconds = seconds % number;

            System.out.print(a + " ");
        }
    }
}
