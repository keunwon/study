package com.keunwon.algorithm.programmers;

public class Lesson17679_2 {
    public int solution(int m, int n, String[] board) {
        var map = new char[m][n];
        var brokenCount = 0;

        for (var i = 0; i < board.length; i++) {
            for (var j = 0; j < board[i].length(); j++) {
                map[i][j] = board[i].charAt(j);
            }
        }

        return brokenCount;
    }
}
