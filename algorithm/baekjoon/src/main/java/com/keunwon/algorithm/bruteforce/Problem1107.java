package com.keunwon.algorithm.bruteforce;

import java.util.Scanner;

public class Problem1107 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        final int n = sc.nextInt();
        final int m = sc.nextInt();
        final boolean[] breakdownButtons = new boolean[10];

        for (int i = 0; i < m; i++) {
            breakdownButtons[sc.nextInt()] = true;
        }

        int min = Math.abs(n - 100);
        for (int i = 0; i <= 1000000; i++) {
            final int clickCount = countByClickNumber(breakdownButtons, i);

            if (clickCount > 0) {
                final int temp = Math.abs(n - i);
                min = Math.min(min, clickCount + temp);
            }
        }

        System.out.println(min);
    }

    private static int countByClickNumber(boolean[] breakdownButtons, int num) {
        if (num == 0) {
            return breakdownButtons[0] ? 0 : 1;
        }

        int result = 0;
        while (num > 0) {
            if (breakdownButtons[num % 10]) {
                return 0;
            }

            num /= 10;
            result++;
        }
        return result;
    }
}
