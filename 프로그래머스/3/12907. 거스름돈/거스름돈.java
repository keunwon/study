import java.util.Arrays;

class Solution {
    public int solution(int n, int[] money) {
        Arrays.sort(money);

        var mod = 1_000_000_007;
        var dp = new int[n + 1];

        Arrays.fill(dp, 0);
        dp[0] = 1;

        for (var m : money) {
            for (var i = m; i <= n; i++) {
                dp[i] += (dp[i - m] % mod);
            }
        }
        return dp[n];
    }
}