class Solution {
    public int solution(int m, int n, int[][] puddles) {
        var sink = new boolean[m * n];
        var dp = new int[m + 1];
        var mod = 1_000_000_007;

        for (var p : puddles) {
            var a = p[0] - 1;
            var b = p[1] - 1;
            sink[b * m + a] = true;
        }

        dp[1] = 1;

        for (var i = 0; i < n; i++) {
            for (var j = 0; j < m; j++) {
                if (sink[i * m + j]) {
                    dp[j + 1] = 0;
                } else {
                    dp[j + 1] = (dp[j + 1] + dp[j]) % mod;
                }
            }
        }
        return dp[m];
    }
}