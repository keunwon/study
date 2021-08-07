package com.keunwon.algorithm.greedy;

import java.util.Scanner;

public class Problem11047 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int amount = sc.nextInt();
        int[] money = new int[n];

        for (int i = 0; i < n; i++) {
            money[i] = sc.nextInt();
        }

        int ans = 0;
        for (int i = n - 1; i >= 0; i--) {
            int a = amount / money[i];

            ans += a;
            amount -= money[i] * a;
        }

        System.out.println(ans);
    }
}
