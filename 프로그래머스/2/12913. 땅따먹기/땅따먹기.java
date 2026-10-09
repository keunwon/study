class Solution {
    int solution(int[][] land) {
        var dp = new int[4];
        for (int[] arr : land) {
            var a = dp[0];
            var b = dp[1];
            var c = dp[2];
            var d = dp[3];

            dp[0] = Math.max(b, Math.max(c, d)) + arr[0];
            dp[1] = Math.max(a, Math.max(c, d)) + arr[1];
            dp[2] = Math.max(a, Math.max(b, d)) + arr[2];
            dp[3] = Math.max(a, Math.max(b, c)) + arr[3];
        }

        var max = dp[0];
        for (var i = 1; i < 4; i++) {
            max = Math.max(max, dp[i]);
        }
        return max;
    }
}