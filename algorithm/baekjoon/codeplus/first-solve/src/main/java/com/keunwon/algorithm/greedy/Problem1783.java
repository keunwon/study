package com.keunwon.algorithm.greedy;

import java.util.Scanner;

public class Problem1783 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();

        int ans = 0;
        if (N == 1) {
            ans = 1;
        }  else if (N == 2) {
            ans = Math.min(4, (M + 1) / 2);
        } else if (N >= 3) {
            if (M < 7) {
                ans = Math.min(4, M);
            } else {
                ans = M - 2;
            }
        }

        System.out.println(ans);
    }
}
