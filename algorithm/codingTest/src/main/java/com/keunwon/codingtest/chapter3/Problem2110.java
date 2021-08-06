package com.keunwon.codingtest.chapter3;

import java.util.Arrays;
import java.util.Scanner;

public class Problem2110 {
    static int N, C;
    static int[] numbers;

    public static void main(String[] args) {
        input();
    }

    static boolean determination(int d) {
        int cnt = 1;
        int last = numbers[1];

        for (int i = 2; i < N; i++) {
            if (numbers[i] - last >= d) {
                cnt++;
                last = numbers[i];
            }
        }
        return cnt >= C;
    }

    static void pro() {
        Arrays.sort(numbers);

        int l = 1;
        int r = 1000000000;
        int ans = 0;

        while (l <= r) {
            int m = (l + r) / 2;

            if (determination(m)) {
                ans = m;
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        System.out.println(ans);
    }



    static void input() {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        C = sc.nextInt();

        numbers = new int[N];
        for (int i = 0; i < N; i++) {
            numbers[i] = sc.nextInt();
        }
    }
}
