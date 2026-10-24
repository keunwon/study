import java.util.Arrays;

class Solution {
    public int solution(int[][] info, int n, int m) {
        var dp = new int[info.length + 1][m];
        for (var i = 0; i <= info.length; i++) {
            Arrays.fill(dp[i], (int) 1e9);
        }
        dp[0][0] = 0;
            
        for (var i = 0; i < info.length; i++) {
            var a = info[i][0];
            var b = info[i][1];

            for (var j = 0; j < m; j++) {
                dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + a);
                if (j + b < m) {
                    dp[i + 1][j + b] = Math.min(dp[i + 1][j + b], dp[i][j]);
                }
            }
        }

        var min = (int) 1e9;
        for (var i = 0; i < m; i++) {
            min = Math.min(min, dp[info.length][i]);
        }
        return min >= n ? -1 : min;
    }
}