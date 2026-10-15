import java.util.Arrays;

class Solution {
    public int solution(String[] strs, String t) {
        var dp = new int[t.length() + 1];
        Arrays.fill(dp, (int) 1e9);
        dp[0] = 0;

        for (var i = 0; i <= t.length(); i++) {
            for (var str : strs) {
                var startIdx = i - str.length();
                if (startIdx >= 0 && dp[startIdx] != 1e9 && str.equals(t.substring(startIdx, i))) {
                    dp[i] = Math.min(dp[i], dp[startIdx] + 1);
                }
            }
        }
        return dp[t.length()] == 1e9 ? -1 : dp[t.length()];
    }
}