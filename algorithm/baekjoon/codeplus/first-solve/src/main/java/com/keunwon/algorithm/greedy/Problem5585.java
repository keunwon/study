package com.keunwon.algorithm.greedy;

import java.util.Scanner;

public class Problem5585 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int amount = sc.nextInt();
        int[] moneys = { 500, 100, 50, 10, 5, 1 };

        int change = 1000 - amount;
        int count = 0;
        for (int money : moneys) {
            int a = change / money;
            change -= money * a;

            count += a;

            if (change == 0) { break; }
        }

        System.out.println(count);
    }
}
