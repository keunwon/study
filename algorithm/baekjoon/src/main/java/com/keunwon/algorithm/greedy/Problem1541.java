package com.keunwon.algorithm.greedy;

import java.util.Scanner;

public class Problem1541 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] items = sc.nextLine().split("-");

        int ans = Integer.MAX_VALUE;
        for (String item : items) {
            String[] a = item.split("\\+");

            int temp = 0;
            for (String value : a) {
                temp += Integer.parseInt(value);
            }

            if (ans == Integer.MAX_VALUE) {
                ans = temp;
            } else {
                ans -= temp;
            }
        }

        System.out.println(ans);
    }
}


