class Solution {
    public int solution(int[] mats, String[][] park) {
        var max = 0;
        var dp = new int[park.length][park[0].length];

        for (var i = 0; i < park.length; i++) {
            for (var j = 0; j < park[0].length; j++) {
                if (!park[i][j].equals("-1")) continue;

                dp[i][j] = 1;
                if (i > 0 && j > 0) {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
                }
                max = Math.max(max, dp[i][j]);
            }
        }

        var res = -1;
        for (var mat : mats) {
            if (max >= mat) {
                res = Math.max(res, mat);
            }
        }
        return res;
    }
}