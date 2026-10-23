class Solution {
    public int solution(int[][] triangle) {
        var dp = new int[triangle.length + 1];
        for (var i = triangle.length - 1; i >= 0; i--) {
            for (var j = 0; j < triangle[i].length; j++) {
                dp[j] = Math.max(dp[j], dp[j + 1]) + triangle[i][j];
            }
        }
        return dp[0];
    }
}