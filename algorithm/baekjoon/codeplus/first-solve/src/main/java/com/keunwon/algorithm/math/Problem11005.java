package com.keunwon.algorithm.math;

import java.util.Scanner;

public class Problem11005 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int B = sc.nextInt();

        StringBuilder sb = new StringBuilder();
        while (N > 0) {
            int r = N % B;

            char c;
            if (r >= 10) {
                sb.append((char) (r - 10 + 'A'));
            } else {
                sb.append((char) (r + '0'));
            }

            N /= B;
        }

        System.out.println(sb.reverse());
    }
}
