package com.keunwon.algorithm.programmers;

import java.util.HashSet;

public class Lesson340198 {
    public int solution(int[] mats, String[][] park) {
        var dp = new int[park.length][park[0].length];
        var set = new HashSet<Integer>();

        for (var i = 0; i < park.length; i++) {
            for (var j = 0; j < park[i].length; j++) {
                if (park[i][j].equals("-1")) dp[i][j] = 1;
            }
        }

        for (var i = 1; i < dp.length; i++) {
            for (var j = 1; j < dp[i].length; j++) {
                if (dp[i][j] > 0) {
                    var min = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
                    dp[i][j] = min + 1;
                    set.add(dp[i][j]);
                }
            }
        }

        var max = -1;
        for (var mat : mats) {
            if (set.contains(mat)) {
                max = Math.max(max, mat);
            }
        }
        return max;
    }
}
