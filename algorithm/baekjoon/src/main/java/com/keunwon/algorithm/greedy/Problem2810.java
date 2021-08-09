package com.keunwon.algorithm.greedy;

import java.util.Scanner;

public class Problem2810 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        char[] batch = sc.next().toCharArray();

        int couple = 0;
        for (int i = 0; i < N; i++) {
            if (batch[i] == 'L') {
                i++;
                couple++;
            }
        }

        System.out.println(Math.min(N, N - couple + 1));
    }
}
