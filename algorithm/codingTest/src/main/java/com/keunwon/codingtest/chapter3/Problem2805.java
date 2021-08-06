package com.keunwon.codingtest.chapter3;

import java.math.BigDecimal;
import java.util.Scanner;

public class Problem2805 {

    static int N, M;
    static int[] numbers;

    public static void main(String[] args) {
        input();
        pro();
    }

    static boolean determination(int h) {
        long sum = 0;
        for (int i = 0; i < N; i++) {
            if (numbers[i] > h) {
                sum += numbers[i] - h;
            }
        }
        return sum >= M;
    }

    static void pro() {
        long l = 0;
        long r = 2000000000;
        long ans = 0;

        while (l <= r) {
            int mid = (int) ((l + r) / 2);

            if (determination(mid)) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        System.out.println(ans);
    }

    static void input() {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();

        numbers = new int[N];

        for (int i = 0; i < N; i++) {
            numbers[i] = sc.nextInt();
        }
    }
}
