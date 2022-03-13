package com.keunwon.algorithm.greedy;

import java.util.Scanner;

public class Problem2720 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int[] numbers= new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = sc.nextInt();
        }

        int[] moneys = {25, 10, 5, 1};

        for (int i = 0; i < N; i++) {
            int amount = numbers[i];

            for (int j = 0; j < moneys.length; j++) {
                int a = amount / moneys[j];
                amount = amount % moneys[j];

                System.out.print(a + " ");
            }
            System.out.println();
        }
    }
}
