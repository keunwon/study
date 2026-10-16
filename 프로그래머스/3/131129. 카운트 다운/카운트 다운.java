import java.util.Arrays;

class Solution {
    private static final int INF = 100_001;
    private int[][] dp;

    public int[] solution(int target) {
        this.dp = new int[target + 1][2];

        for (var i = 0; i <= target; i++) {
            dp[i][0] = INF;
        }
        dp[0][0] = 0;

        return dfs(target);
    }

    private int[] dfs(int target) {
        if (dp[target][0] != INF) {
            return Arrays.copyOf(dp[target], 2);
        }

        if (target >= 50) {
            var arr = dfs(target - 50);
            ++arr[0];
            ++arr[1];

            if (isMin(arr, target)) {
                dp[target][0] = arr[0];
                dp[target][1] = arr[1];
            }
        }

        var end = Math.min(target, 20);
        for (var i = end; i >= 1; i--) {
            for (var j = 1; j <= 3; j++) {
                var nextTarget = target - (i * j);
                if (nextTarget < 0) break;

                var arr = dfs(nextTarget);
                ++arr[0];
                if (j == 1) ++arr[1];

                if (isMin(arr, target)) {
                    dp[target][0] = arr[0];
                    dp[target][1] = arr[1];
                }
            }
        }
        return Arrays.copyOf(dp[target], 2);
    }

    private boolean isMin(int[] arr, int target) {
        var a = arr[0];
        var b = arr[1];
        return (dp[target][0] > a) || (dp[target][0] == a && dp[target][1] < b);
    }
}