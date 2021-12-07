package com.keunwon.algorithm.dp;

import java.io.IOException;
import java.util.Scanner;

public class Problem11057 {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        final int N = sc.nextInt();
        final int[][] dp = new int[N + 1][10];

        for (int i = 0; i <= 9; i++) {
            dp[1][i] = 1;
        }

        for (int i = 2; i <= N; i++) {
            for (int j = 0; j <= 9; j++) {
                for (int k = 0; k <= j; k++) {
                    dp[i][j] += dp[i - 1][k];
                    dp[i][j] %= 10_007;
                }
            }
        }

        int sum = 0;
        for (int i = 0; i <= 9; i++) {
            sum += dp[N][i];
        }
        System.out.println(sum % 10_007);
    }

    private static void other() {
        Scanner sc = new Scanner(System.in);

        final int N = sc.nextInt();
        final int[][] dp = new int[N + 1][10];

        for (int i = 0; i < 10; i++) {
            dp[1][i] = 1;
        }

        for (int i = 2; i <= N; i++) {
            for (int j = 0; j <= 9; j++) {
                if (j == 0) {
                    dp[i][j] = 1;
                    continue;
                }

                dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % 10_007;
            }
        }

        int result = 0;
        for (int i = 0; i <= 9; i++) {
            result += dp[N][i];
        }

        System.out.println(result % 10_007);
    }
}
