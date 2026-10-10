import java.util.Arrays;

class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        var maxAlp = 0;
        var maxCop = 0;

        for (var p : problems) {
            maxAlp = Math.max(maxAlp, p[0]);
            maxCop = Math.max(maxCop, p[1]);
        }

        alp = Math.min(alp, maxAlp);
        cop = Math.min(cop, maxCop);

        var dp = new int[maxAlp + 1][maxCop + 1];
        for (var i = alp; i <= maxAlp; i++) {
            Arrays.fill(dp[i], (int) 1e9);
        }
        dp[alp][cop] = 0;

        for (var i = alp; i <= maxAlp; i++) {
            for (var j = cop; j <= maxCop; j++) {
                if (i + 1 <= maxAlp) {
                    dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + 1);
                }

                if (j + 1 <= maxCop) {
                    dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i][j] + 1);
                }

                for (var p : problems) {
                    if (i >= p[0] && j >= p[1]) {
                        var nextAlp = Math.min(i + p[2], maxAlp);
                        var nextCop = Math.min(j + p[3], maxCop);
                        dp[nextAlp][nextCop] = Math.min(dp[nextAlp][nextCop], dp[i][j] + p[4]);
                    }
                }
            }
        }
        return dp[maxAlp][maxCop];
    }
}