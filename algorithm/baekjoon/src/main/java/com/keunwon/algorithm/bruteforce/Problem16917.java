package com.keunwon.algorithm.bruteforce;

import java.util.Scanner;

public class Problem16917 {

    public static void main(String[] args) {
        final var sc = new Scanner(System.in);
        final var A = sc.nextInt();
        final var B = sc.nextInt();
        final var C = sc.nextInt();
        final var X = sc.nextInt();
        final var Y = sc.nextInt();

        if (A + B <= C * 2) {
            System.out.println(A * X + B * Y);
            return;
        }

        var result = C * 2 * Math.min(X, Y);
        if (X > Y) {
            result += Math.min(C * 2, A) * (X - Y);
        } else {
            result += Math.min(C * 2, B) * (Y - X);
        }

        System.out.println(result);
    }
}
