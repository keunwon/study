import java.util.Arrays;

class Solution {
    private int[][] q;
    private int[] ans;
    private int res = 0;

    public int solution(int n, int[][] q, int[] ans) {
        this.q = q;
        this.ans = ans;

        dfs(new boolean[n + 1], 1, n, 0);
        return res;
    }

    private void dfs(boolean[] pick, int startIndex, int n, int depth) {
        if (depth == 5) {
            var point = new int[q.length];

            for (var i = 0; i < q.length; i++) {
                var arr = q[i];
                for (var x : arr) {
                    if (pick[x]) ++point[i];
                }
            }

            if (Arrays.equals(ans, point)) ++res;
            return;
        }

        for (var i = startIndex; i <= n; i++) {
            pick[i] = true;
            dfs(pick, i + 1, n, depth + 1);
            pick[i] = false;
        }
    }
}