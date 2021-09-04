package com.keunwon.algorithm.math;

import java.util.Scanner;

public class Problem2745 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String N = sc.next();
        int B = sc.nextInt();

        int result = 0;
        for (int i = 0; i < N.length(); i++) {
            int n = N.charAt(i);

            if ('0' <= n && n <= '9') {
                result = result * B + (n - '0');
            } else {
                result = result * B + (n  + 10 - 'A');
            }
        }
        System.out.println(result);
    }
}
