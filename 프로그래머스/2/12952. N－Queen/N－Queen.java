class Solution {
    private int[] pick;
    private boolean[] visited;
    private int res = 0;

    public int solution(int n) {
        this.pick = new int[n];
        this.visited = new boolean[n];
        this.res = 0;

        dfs(n, 0);
        return res;
    }

    private void dfs(int n, int depth) {
        if (depth == n) {
            ++res;
            return;
        }

        for (var i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                pick[depth] = i;

                if (check(pick, depth)) {
                    dfs(n, depth + 1);
                }
                visited[i] = false;
            }
        }
    }

    private boolean check(int[] pick, int depth) {
        for (var i = 0; i < depth; i++) {
            if (depth - i == Math.abs(pick[i] - pick[depth])) {
                return false;
            }
        }
        return true;
    }
}