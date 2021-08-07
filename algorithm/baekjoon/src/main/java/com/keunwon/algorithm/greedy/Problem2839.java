package com.keunwon.algorithm.greedy;

import java.util.Scanner;

public class Problem2839 {
    static int N;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();

        calc();
    }

    static void calc() {
        int value = N;
        int a = 0;
        int b = 0;

        while (true) {
            if (value % 5 == 0) {
                a = value / 5;
                System.out.println(a + b);
                break;
            }

            value -= 3;
            b++;

            if (value < 0) {
                System.out.println(-1);
                break;
            }
        }
    }
}
