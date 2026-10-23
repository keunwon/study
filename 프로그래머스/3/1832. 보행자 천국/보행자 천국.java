class Solution {
    public int solution(int m, int n, int[][] cityMap) {
        int mod = 20170805;
        var dp = new int[m + 1][n + 1][2];
        
        dp[0][0][0] = 1;

        for (var i = 0; i < m; i++) {
            for (var j = 0; j < n; j++) {
                var x = cityMap[i][j];
                if (x == 0) {
                    dp[i][j + 1][0] = (dp[i][j][0] + dp[i][j][1]) % mod;
                    dp[i + 1][j][1] = (dp[i][j][0] + dp[i][j][1]) % mod;
                } else if (x == 2) {
                    dp[i][j + 1][0] += dp[i][j][0];
                    dp[i + 1][j][1] += dp[i][j][1];
                }
            }
        }
        return (dp[m - 1][n - 1][0] + dp[m - 1][n - 1][1]) % mod;
    }
}