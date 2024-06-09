package com.keunwon.algorithm.programmers;

public class Lesson17687 {
    public String solution(int n, int t, int m, int p) {
        var sb = new StringBuilder();
        var answer = new StringBuilder();

        for (int i = 0; i <= t * m; i++) {
            sb.append(Integer.toString(i, n).toUpperCase());
        }

        for (int i = p - 1; i < t * m; i += m) {
            answer.append(sb.charAt(i));
        }
        return answer.toString();
    }
}
