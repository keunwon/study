import java.util.Arrays;

class Solution {
    public int solution(int x, int y, int n) {
        var dp = new int[y + 1];

        Arrays.fill(dp, (int) 1e9);
        dp[x] = 0;

        for (var i = x; i <= y; i++) {
            if (i + n <= y) {
                dp[i + n] = Math.min(dp[i + n], dp[i] + 1);
            }

            if (i * 2 <= y) {
                dp[i * 2] = Math.min(dp[i * 2], dp[i] + 1);
            }

            if (i * 3 <= y) {
                dp[i * 3] = Math.min(dp[i * 3], dp[i] + 1);
            }
        }
        return dp[y] == (int) 1e9 ? -1 : dp[y];
    }
}