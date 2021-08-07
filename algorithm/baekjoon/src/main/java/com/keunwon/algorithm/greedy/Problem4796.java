package com.keunwon.algorithm.greedy;

import java.util.Scanner;

public class Problem4796 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int index = 1;
        while (true) {
            int L = sc.nextInt();
            int P = sc.nextInt();
            int V = sc.nextInt();

            if (L == 0 && P == 0 && V == 0) {
                return;
            }

            int q = V / P;
            int r = V % P;

            if (r > L) {
                r = L;
            }
            System.out.println(String.format("Case %d: %d", index++, L * q + r));
        }
    }
}
