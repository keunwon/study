package com.keunwon.algorithm.programmers;

public class Lesson12905 {
    public int solution(int[][] board) {
        var dp = new int[board.length][board[0].length];

        for (int i = 0; i < board.length; i++) {
            dp[i][0] = board[i][0];
        }

        for (int i = 0; i < board[0].length; i++) {
            dp[0][i] = board[0][i];
        }

        for (int i = 1; i < board.length; i++) {
            for (int j = 1; j < board[0].length; j++) {
                if (board[i][j] == 0) continue;

                var min = Math.min(Math.min(dp[i][j - 1], dp[i - 1][j]), dp[i - 1][j - 1]);
                dp[i][j] = min + 1;
            }
        }

        var answer = 0;
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                answer = Math.max(answer, dp[i][j]);
            }
        }
        return answer * answer;
    }
}
