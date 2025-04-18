package algorithm.programmers;

import java.util.Arrays;

public class Lesson12983 {
    public int solution(String[] strs, String t) {
        var dp = new int[t.length() + 1];
        Arrays.fill(dp, (int) 1e9);
        dp[0] = 0;

        for (var i = 1; i <= t.length(); i++) {
            for (var str : strs) {
                var startIndex = i - str.length();
                var flag = true;

                if (startIndex < 0) continue;

                for (var j = 0; j < str.length(); j++) {
                    if (str.charAt(j) != t.charAt(j + startIndex)) {
                        flag = false;
                        break;
                    }
                }

                if (flag) {
                    dp[i] = Math.min(dp[i], dp[startIndex] + 1);
                }
            }
        }
        return dp[t.length()] == (int) 1e9 ? -1 : dp[t.length()];
    }
}
