package com.keunwon.algorithm.greedy;

import java.util.Scanner;

public class Problem16953 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int A = sc.nextInt();
        int B = sc.nextInt();
        int count = 0;

        while (A < B) {
            if (B % 2 == 0) {
                B = B / 2;
                count++;
            } else if  (B % 10 == 1) {
                B = B / 10;
                count++;
            } else {
                count = -1;
                System.out.println(count);
                return;
            }

            if (A > B) {
                System.out.println(-1);
                return;
            }
        }

        System.out.println(++count);
    }
}
