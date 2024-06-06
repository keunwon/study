package com.keunwon.algorithm.programmers;

public class Lesson12924 {
    public int solution(int n) {
        var dp = new int[n + 1];
        var sIndex = 1;
        var answer = 0;

        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1] + i;

            while (dp[i] > n) {
                dp[i] -= sIndex;
                sIndex++;
            }
            if (dp[i] == n) ++answer;
        }
        return answer;
    }
}
