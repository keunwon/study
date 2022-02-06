package com.keunwon.algorithm.bruteforce;

import java.util.Scanner;

public class Problem1476 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        final int E = sc.nextInt();
        final int S = sc.nextInt();
        final int M = sc.nextInt();

        int year = 1;
        int e = 1;
        int s = 1;
        int m = 1;

        while (true) {
            if (E == e && S == s && M == m) {
                System.out.println(year);
                break;
            }

            e++;
            s++;
            m++;
            year++;

            if (e > 15) { e = 1; }
            if (s > 28) { s = 1; }
            if (m > 19) { m = 1; }
        }
    }
}
